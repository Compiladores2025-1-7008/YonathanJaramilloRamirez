/**
 * Syntax Analyzer Calculator
 *
 * This file implements the grammar and semantic actions for a basic calculator
 * that supports arithmetic operations, power calculations, and expressions
 * with parentheses.
 *
 * Grammar:
 * input -> ε | input line
 * line  -> EOL | exp EOL
 * exp   -> NUM | exp op exp | -exp | (exp)
 * op    -> + | - | * | / | ^
 *
 */

%{
/* Required imports for calculator functionality */
  import java.io.*;
  import java.lang.Math;
%}

/* Token declarations and their types */
%token <dval> NUM                     /* Token for numeric values (decimals) */
%token PLUS MINUS TIMES DIVIDE POWER  /* Arithmetic operators */
%token LPAREN RPAREN                  /* Parentheses */
%token EOL                            /* End-of-line or input */

%type <dval> exp                      /* Expressions return decimal values */

/* Operator precedence and associativity (from lowest to highest) */
%left PLUS MINUS     /* + and - are left-associative */
%left TIMES DIVIDE   /* * and / are left-associative */
%right POWER         /* ^ is right-associative */
%nonassoc NEG        /* Unary - has the highest precedence */

%%
/* Grammar rules and semantic actions */

input:
| input line
;

line: EOL
    | exp EOL { System.out.print(expression + "\nResult: " + $1); }
;

exp: NUM { $$ = $1; }
| exp PLUS exp { $$ = $1 + $3; }
| exp MINUS exp { $$ = $1 - $3; }
| exp TIMES exp { $$ = $1 * $3; }
| exp DIVIDE exp { $$ = $1 / $3; }
| MINUS exp %prec NEG { $$ = -$2; }
| exp POWER exp { $$ = Math.pow($1, $3); }
| LPAREN exp RPAREN { $$ = $2; }
;

%%

/* Supporting code */

private Lexer lexer;           // Lexer instance for tokenization
private String expression;     // Current expression being evaluated

/**
 * Constructor for the syntax analyzer.
 * 
 * @param r Reader for input source.
 */
public Parser(Reader r) {
    lexer = new Lexer(r, this);
}

/**
 * Sets the current expression being evaluated.
 * 
 * @param expression The expression to evaluate.
 */
public void setExpression(String expression) {
    this.expression = expression;
}

/**
 * Retrieves the next token from the lexer.
 * 
 * @return Token code or -1 in case of an error.
 */
public int yylex() {
    try {
        return lexer.yylex();
    } catch (IOException e) {
        System.err.println("Input/output error ❌: " + e);
        return -1;
    }
}

/**
 * Handles syntax errors.
 * 
 * @param s Error message.
 */
void yyerror(String s) {
    System.err.println("Syntax error ❌: " + s);
}
