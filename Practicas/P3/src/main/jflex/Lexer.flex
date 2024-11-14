package main.jflex;

import main.java.ClaseLexica;

%%

%{

public int getLine() { return yyline + 1; }  // JFlex usa 0-based indexing, así que sumamos 1

%}

%public
%class Lexer
%standalone
%unicode
%line

// Definiciones de patrones
espacio = [ \t\n]
id = ([a-zA-Z_][a-zA-Z_0-9]*){1,31}
numero = ([0-9]+(\.[0-9]+)?)

%%

// Ignorar espacios
{espacio}+ { }

// Palabras reservadas y tokens importantes
"int" { System.out.println("Encontramos una palabra reservada: int"); return ClaseLexica.INT; }
"float" { System.out.println("Encontramos una palabra reservada: float"); return ClaseLexica.FLOAT; }
"if" { System.out.println("Encontramos una palabra reservada: if"); return ClaseLexica.IF; }
"else" { System.out.println("Encontramos una palabra reservada: else"); return ClaseLexica.ELSE; }
"while" { System.out.println("Encontramos una palabra reservada: while"); return ClaseLexica.WHILE; }
";" { System.out.println("Encontramos un símbolo: ;"); return ClaseLexica.PUNTO_Y_COMA; }
"," { System.out.println("Encontramos un símbolo: ,"); return ClaseLexica.COMA; }
"=" { System.out.println("Encontramos un símbolo: ="); return ClaseLexica.IGUAL; }
"+" { System.out.println("Encontramos un símbolo: +"); return ClaseLexica.MAS; }
"-" { System.out.println("Encontramos un símbolo: -"); return ClaseLexica.MENOS; }
"*" { System.out.println("Encontramos un símbolo: *"); return ClaseLexica.POR; }
"/" { System.out.println("Encontramos un símbolo: /"); return ClaseLexica.DIV; }
"(" { System.out.println("Encontramos un símbolo: ("); return ClaseLexica.PAREN_IZQ; }
")" { System.out.println("Encontramos un símbolo: )"); return ClaseLexica.PAREN_DER; }
"{" { System.out.println("Encontramos un símbolo: {"); return ClaseLexica.LLAVE_IZQ; }
"}" { System.out.println("Encontramos un símbolo: }"); return ClaseLexica.LLAVE_DER; }
"<" { System.out.println("Encontramos un símbolo: <"); return ClaseLexica.MENOR; }
">" { System.out.println("Encontramos un símbolo: >"); return ClaseLexica.MAYOR; }
"<=" { System.out.println("Encontramos un símbolo: <="); return ClaseLexica.MENOR_IGUAL; }
">=" { System.out.println("Encontramos un símbolo: >="); return ClaseLexica.MAYOR_IGUAL; }
"==" { System.out.println("Encontramos un símbolo: =="); return ClaseLexica.IGUAL_IGUAL; }
"!=" { System.out.println("Encontramos un símbolo: !="); return ClaseLexica.DIFERENTE; }

// Identificadores
{id} { System.out.println("Encontramos un identificador: " + yytext()); return ClaseLexica.IDENTIFICADOR; }

// Números (enteros y flotantes)
{numero} { System.out.println("Encontramos un número: " + yytext()); return ClaseLexica.NUMERO; }

// EOF
<<EOF>> { return ClaseLexica.EOF; }

// Caracteres no reconocidos
. { System.out.println("Error: caracter no reconocido: " + yytext()); return -1; }
