/**
 * Parser for the C_1 Programming Language
 *
 * This file contains the grammar rules and semantic actions required to parse
 * a simplified version of the C language. The grammar includes constructs for:
 * - Variable declarations
 * - Control structures such as `if` and `while`
 * - Arithmetic and relational expressions
 *
 * Grammar Structure:
 * 1. program → declaration_list program_body EOF
 * 2. program_body → main_block | statement_list
 * 3. main_block → { statement_list }
 * 4. declaration_list → declaration_list declaration | declaration
 * 5. declaration → type variable_list ;
 * 6. type → int | float
 * 7. variable_list → variable_list , ID | ID
 * 8. statement_list → statement_list statement | statement
 * 9. statements → { statement_list } | statement
 * 10. statement → assignment | if_statement | while_statement
 * 11. assignment → ID = expression ;
 * 12. if_statement → if ( expression ) statements 
 *                    | if ( expression ) statements else statements
 * 13. while_statement → while ( expression ) statements
 * 14. expression → expression + term | expression - term | term
 *                  | expression < term | expression > term
 *                  | expression <= term | expression >= term
 *                  | expression == term | expression != term
 * 15. term → term * factor | term / factor | factor
 * 16. factor → ( expression ) | ID | INTEGER_NUMBER | REAL_NUMBER
 * @author Yonathan Berith Jaramillo Ramírez
 */

%{
/* Essential imports for parsing functionality */
import java.io.*;
import src.syntax_analyzer_byaccj.*;
%}

/* Defining the starting point of the grammar */
%start program

/* Tokens and their classifications */
%token INT FLOAT IF ELSE WHILE                   /* Reserved Keywords */
%token ID INTEGER_NUMBER REAL_NUMBER             /* Identifiers and numeric literals */
%token SEMICOLON COMMA LPAREN RPAREN LBRACE RBRACE /* Punctuation symbols */
%token ASSIGNMENT EQUALS GREATER_THAN LESS_THAN  /* Basic operators */
%token PLUS MINUS MULTIPLY DIVIDE                /* Arithmetic operators */
%token GREATER_EQUALS LESS_EQUALS NOT_EQUALS     /* Relational operators */
%token EOF                                       /* End of file */

/* Operator precedence and associativity */
%left PLUS MINUS
%left MULTIPLY DIVIDE
%nonassoc LESS_THAN GREATER_THAN LESS_EQUALS GREATER_EQUALS EQUALS NOT_EQUALS
%nonassoc THEN
%nonassoc ELSE

%%
/* Grammar rules and associated semantic actions */

/**
 * Entry point for parsing the program.
 * Confirms the overall structure is valid.
 */
program : declaration_list program_body EOF
        { System.out.println("The program was successfully parsed!! 🚀"); }
        ;

/**
 * Describes the main content of the program, which could be
 * either a block of statements or a simple statement sequence.
 */
program_body : main_block 
             | statement_list
             ;

/**
 * A block of statements encapsulated within curly braces.
 */
main_block : LBRACE statement_list RBRACE
           ;

/**
 * Handles consecutive variable declarations.
 */
declaration_list : declaration_list declaration
                 | declaration
                 ;

/**
 * Represents a single variable declaration with a type and variable list.
 */
declaration : type variable_list SEMICOLON
            { System.out.println("Declaration completed ✅"); }
            ;

/**
 * Permitted types for variable declarations.
 */
type : INT | FLOAT ;

/**
 * Manages variable lists separated by commas in declarations.
 */
variable_list : variable_list COMMA ID
              | ID
              ;

/**
 * Processes a sequence of statements.
 */
statement_list : statement_list statement
               | statement
               ;

/**
 * Either a block of statements or an individual statement.
 */
statements : LBRACE statement_list RBRACE
           | statement
           ;

/**
 * Represents the different types of statements allowed.
 */
statement : assignment
          | if_statement
          | while_statement
          ;

/**
 * Parses an assignment statement, assigning an expression to a variable.
 */
assignment : ID ASSIGNMENT expression SEMICOLON
           { System.out.println("Assignment successfully processed ✅"); }
           ;

/**
 * Handles conditional structures, with or without the `else` clause.
 */
if_statement : IF LPAREN expression RPAREN statements %prec THEN
             | IF LPAREN expression RPAREN statements ELSE statements
             ;

/**
 * Parses a `while` loop structure.
 */
while_statement : WHILE LPAREN expression RPAREN statements
                ;

/**
 * Expressions with arithmetic and relational operators.
 */
expression : expression PLUS term
           | expression MINUS term
           | expression LESS_THAN term
           | expression GREATER_THAN term
           | expression LESS_EQUALS term
           | expression GREATER_EQUALS term
           | expression EQUALS term
           | expression NOT_EQUALS term
           | term
           ;

/**
 * Terms represent intermediate steps in mathematical operations.
 */
term : term MULTIPLY factor
     | term DIVIDE factor
     | factor
     ;

/**
 * Basic building blocks of expressions, such as numbers or variables.
 */
factor : LPAREN expression RPAREN
       | ID
       | INTEGER_NUMBER
       | REAL_NUMBER
       ;

%%

/* Supporting code */

/**
 * Lexer for lexical analysis and token generation.
 */
private Lexer lexer;
private Token currentToken;

/**
 * Constructor initializes the parser with input data.
 * @param input Reader object for input source
 */
public Parser(Reader input) {
    lexer = new Lexer(input);
    System.out.println("Starting parser ⏳");
}

/**
 * Error handling method to report syntax errors.
 * @param msg Error message to display
 */
private void yyerror(String msg) {
    System.out.println("Syntax Error ❌ Sat line " + 
        (currentToken != null ? currentToken.getLine() : "unknown") + 
        ": " + msg + 
        "\nCurrent Token: " + (currentToken != null ? currentToken.getLexeme() : "null"));
}

/**
 * Retrieves the next token from the lexer.
 * @return Token code or -1 for errors
 */
private int yylex() {
    try {
        currentToken = lexer.yylex();
        if (currentToken == null) {
            System.out.println("End of file reached 📜");
            return 0;
        }
        
        yylval = new ParserVal(currentToken);
        return currentToken.getLexicalClass().ordinal() + 257;
    } catch (IOException e) {
        System.out.println("I/O Error ❌ : " + e.getMessage());
        return -1;
    }
}

/**
 * Entry point for running the parser.
 * @param args Command-line arguments, expects input file
 */
public static void main(String args[]) {
    if (args.length < 1) {
        System.err.println("Usage: java Parser <input_file>");
        System.exit(1);
    }
    
    try {
        Parser parser = new Parser(new FileReader(args[0]));
        parser.yyparse();
    } catch (FileNotFoundException e) {
        System.err.println("Error ❌ : Unable to open file " + args[0]);
    }
}
