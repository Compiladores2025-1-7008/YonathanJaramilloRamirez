/* Código para Lexer2.flex */
package main.jflex;

import java.io.IOException;
import main.java.ClaseLexica;
import main.java.Token;

%%

%public
%class Lexer2
%unicode
%type Token

%{
    private int linea = 1;

    public int getLine() {
        return linea;
    }
%}

%%

\r | \n { linea++; }

"int"                   { return new Token(ClaseLexica.INT, yytext(), getLine()); }
"float"                 { return new Token(ClaseLexica.FLOAT, yytext(), getLine()); }
"if"                    { return new Token(ClaseLexica.IF, yytext(), getLine()); }
"else"                  { return new Token(ClaseLexica.ELSE, yytext(), getLine()); }
"while"                 { return new Token(ClaseLexica.WHILE, yytext(), getLine()); }
[0-9]+(\.[0-9]+)?       { return new Token(ClaseLexica.NUMERO, yytext(), getLine()); }
[a-zA-Z_][a-zA-Z_0-9]*  { return new Token(ClaseLexica.IDENTIFICADOR, yytext(), getLine()); }
"="                     { return new Token(ClaseLexica.IGUAL, yytext(), getLine()); }
"=="                    { return new Token(ClaseLexica.IGUAL_IGUAL, yytext(), getLine()); }
"<"                     { return new Token(ClaseLexica.MENOR, yytext(), getLine()); }
">"                     { return new Token(ClaseLexica.MAYOR, yytext(), getLine()); }
"<="                    { return new Token(ClaseLexica.MENOR_IGUAL, yytext(), getLine()); }
">="                    { return new Token(ClaseLexica.MAYOR_IGUAL, yytext(), getLine()); }
"!="                    { return new Token(ClaseLexica.DIFERENTE, yytext(), getLine()); }
"+"                     { return new Token(ClaseLexica.MAS, yytext(), getLine()); }
"-"                     { return new Token(ClaseLexica.MENOS, yytext(), getLine()); }
"*"                     { return new Token(ClaseLexica.POR, yytext(), getLine()); }
"/"                     { return new Token(ClaseLexica.DIV, yytext(), getLine()); }
","                     { return new Token(ClaseLexica.COMA, yytext(), getLine()); }
";"                     { return new Token(ClaseLexica.PUNTO_Y_COMA, yytext(), getLine()); }
"{"                     { return new Token(ClaseLexica.LLAVE_IZQ, yytext(), getLine()); }
"}"                     { return new Token(ClaseLexica.LLAVE_DER, yytext(), getLine()); }
"("                     { return new Token(ClaseLexica.PAREN_IZQ, yytext(), getLine()); }
")"                     { return new Token(ClaseLexica.PAREN_DER, yytext(), getLine()); }

[ \t]+                  { /* ignorar espacios en blanco */ }

.                       { System.out.println("Error: caracter no reconocido: " + yytext()); 
                          return new Token(-1, yytext(), getLine()); }

<<EOF>>                 { return new Token(ClaseLexica.EOF, "EOF", getLine()); }
