/**
 * Tokenization Rules for the C_1 Language
 *
 * This file outlines the lexical analysis process for the C_1 language,
 * providing patterns to classify segments of input into meaningful tokens.
 * The lexical analyzer is responsible for identifying:
 * - Language-specific reserved words
 * - User-defined names (identifiers)
 * - Numeric constants
 * - Arithmetic and relational operators
 * - Common symbols used for structuring code
 *
 * The tokens produced by this analyzer serve as the building blocks
 * for higher-level syntactic parsing. Each token is categorized into
 * a well-defined lexical class to facilitate further analysis.
 *
 * Categories covered:
 * - Keywords: int, float, if, else, while
 * - Identifiers: Combinations of letters and numbers beginning with a letter
 * - Numbers: Both integer and decimal values
 * - Operators: Arithmetic (+, -, *, /), assignment (=), relational (==, <, >, <=, >=, !=)
 * - Delimiters and separators: (, ), {, }, ;, ,
 *
 * @author Yonathan Berith Jaramillo Ramírez
 */


package src.syntax_analyzer_byaccj;

%%

%{
/* Current token being processed by the lexical analyzer */
public Token currentToken;

/**
 * Retrieves the current line number of the input being analyzed.
 * 
 * @return The current line in the input stream.
 */
public int getLine() { return yyline + 1; }

/**
 * Logs details about a recognized token, including its value,
 * lexical class, and position in the input.
 * 
 * @param lexeme The text of the token.
 * @param lexicalClass The category of the token.
 */
public void logToken(String lexeme, LexicalClass lexicalClass) {
    String color = lexicalClass.getColor();
    int value = lexicalClass.ordinal() + 257;
    Colors.println("\nToken: " + lexeme + 
                  " | Class: " + lexicalClass.getName() + 
                  " | Line: " + getLine() +
                  " | Value: " + value, color);
}

/* Internal state for handling end-of-file recognition */
private boolean eofReached = false;
%}

%public           
%class Lexer
%standalone
%unicode
%line
%type Token

/* Fundamental patterns used to define tokens */
whitespace = [ \t\n]                        /* Handles spaces, tabs, and line breaks */
letter = [a-zA-Z_]                          /* Matches alphabetic characters and underscores */
digit = [0-9]                               /* Digits from 0 to 9 */
identifier = {letter}({letter}|{digit})*    /* Valid variable and function names */
integer_number = {digit}+                   /* Positive integers */
real_number = {digit}+"."{digit}+           /* Real numbers with mandatory decimal points */

%%
/* Token definitions and handling rules */

/* Ignore whitespace characters */
{whitespace}+ { /* Skip spaces and similar */ }

/* Reserved words and their associated lexical classes */
"int"    { logToken(yytext(), LexicalClass.INT); return new Token(LexicalClass.INT, yytext(), getLine()); }
"float"  { logToken(yytext(), LexicalClass.FLOAT); return new Token(LexicalClass.FLOAT, yytext(), getLine()); }
"if"     { logToken(yytext(), LexicalClass.IF); return new Token(LexicalClass.IF, yytext(), getLine()); }
"else"   { logToken(yytext(), LexicalClass.ELSE); return new Token(LexicalClass.ELSE, yytext(), getLine()); }
"while"  { logToken(yytext(), LexicalClass.WHILE); return new Token(LexicalClass.WHILE, yytext(), getLine()); }

/* Patterns for identifiers and numeric literals */
{identifier} { logToken(yytext(), LexicalClass.ID); return new Token(LexicalClass.ID, yytext(), getLine()); }
{integer_number} { logToken(yytext(), LexicalClass.INTEGER_NUMBER); return new Token(LexicalClass.INTEGER_NUMBER, yytext(), getLine()); }
{real_number}   { logToken(yytext(), LexicalClass.REAL_NUMBER); return new Token(LexicalClass.REAL_NUMBER, yytext(), getLine()); }

/* Operators and special symbols */
";"   { logToken(yytext(), LexicalClass.SEMICOLON); return new Token(LexicalClass.SEMICOLON, yytext(), getLine()); }
","   { logToken(yytext(), LexicalClass.COMMA); return new Token(LexicalClass.COMMA, yytext(), getLine()); }
"("   { logToken(yytext(), LexicalClass.LPAREN); return new Token(LexicalClass.LPAREN, yytext(), getLine()); }
")"   { logToken(yytext(), LexicalClass.RPAREN); return new Token(LexicalClass.RPAREN, yytext(), getLine()); }
"{"   { logToken(yytext(), LexicalClass.LBRACE); return new Token(LexicalClass.LBRACE, yytext(), getLine()); }
"}"   { logToken(yytext(), LexicalClass.RBRACE); return new Token(LexicalClass.RBRACE, yytext(), getLine()); }
"="   { logToken(yytext(), LexicalClass.ASSIGNMENT); return new Token(LexicalClass.ASSIGNMENT, yytext(), getLine()); }
"=="  { logToken(yytext(), LexicalClass.EQUALS); return new Token(LexicalClass.EQUALS, yytext(), getLine()); }
">"   { logToken(yytext(), LexicalClass.GREATER_THAN); return new Token(LexicalClass.GREATER_THAN, yytext(), getLine()); }
"<"   { logToken(yytext(), LexicalClass.LESS_THAN); return new Token(LexicalClass.LESS_THAN, yytext(), getLine()); }
"+"   { logToken(yytext(), LexicalClass.PLUS); return new Token(LexicalClass.PLUS, yytext(), getLine()); }
"-"   { logToken(yytext(), LexicalClass.MINUS); return new Token(LexicalClass.MINUS, yytext(), getLine()); }
"*"   { logToken(yytext(), LexicalClass.MULTIPLY); return new Token(LexicalClass.MULTIPLY, yytext(), getLine()); }
"/"   { logToken(yytext(), LexicalClass.DIVIDE); return new Token(LexicalClass.DIVIDE, yytext(), getLine()); }

/* Relational operators */
">="  { logToken(yytext(), LexicalClass.GREATER_EQUALS); return new Token(LexicalClass.GREATER_EQUALS, yytext(), getLine()); }
"<="  { logToken(yytext(), LexicalClass.LESS_EQUALS); return new Token(LexicalClass.LESS_EQUALS, yytext(), getLine()); }
"!="  { logToken(yytext(), LexicalClass.NOT_EQUALS); return new Token(LexicalClass.NOT_EQUALS, yytext(), getLine()); }

/* End-of-file handling */
<<EOF>> { 
    if (!eofReached) {
        eofReached = true;
        logToken("EOF", LexicalClass.EOF); 
        return new Token(LexicalClass.EOF, "EOF", getLine()); 
    }
    return null;
}

/* Handling unrecognized characters */
. { 
    Colors.println("\nError: Unknown symbol '" + yytext() + 
                   "' encountered on line " + getLine() + ".", Colors.RED); 
    return new Token(LexicalClass.UNKNOWN, yytext(), getLine()); 
}
