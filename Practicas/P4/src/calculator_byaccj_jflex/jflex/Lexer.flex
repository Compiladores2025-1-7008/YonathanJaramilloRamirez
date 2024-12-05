package src.calculator_byaccj_jflex;

/* 
 * Lexical Analyzer for the Calculator
 * 
 * This file defines tokens and patterns for a calculator that supports:
 * - Integers and decimal numbers
 * - Operators: +, -, *, /, ^
 * - Parentheses: (, )
 * - Whitespace
 *
 * The lexical analyzer identifies and categorizes the tokens to be used by the parser.
 * 
 * @author Yonathan Berith Jaramillo Ramírez
 */

%%

%class Lexer
%byaccj
%unicode

%{
  /* Constructor and instance variables */
  private Parser yyparser;

  /**
   * Constructor for the lexical analyzer.
   * 
   * @param r Reader providing the input
   * @param yyparser Reference to the parser
   */
  public Lexer(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

/* Pattern Definitions */
NUM    = [0-9]+ ("." [0-9]*)?    /* Integer or decimal numbers */
WHITE  = [ \t\r]                /* Whitespace: spaces, tabs, and carriage returns */

%%
/* Lexical Rules */

/**
 * Matches numbers (integers and decimals) and assigns their value to the parser.
 */
{NUM}    { 
  yyparser.yylval = new ParserVal(Double.parseDouble(yytext())); 
  return Parser.NUM; 
}

/**
 * Matches arithmetic operators and returns their corresponding token.
 */
"+"      { return Parser.PLUS; }
"-"      { return Parser.MINUS; }
"*"      { return Parser.TIMES; }
"/"      { return Parser.DIVIDE; }
"^"      { return Parser.POWER; }

/**
 * Matches parentheses for grouping expressions.
 */
"("      { return Parser.LPAREN; }
")"      { return Parser.RPAREN; }

/**
 * Matches end-of-line characters.
 */
\n       { return Parser.EOL; }

/**
 * Ignores whitespace characters.
 */
{WHITE}  { /* Skip whitespace */ }

/**
 * Catches illegal characters and reports them as errors.
 */
.        { 
  System.err.println("Illegal character: " + yytext()); 
}
