/**
 * Escáner extendido que detecta hexadecimales, palabras reservadas, identificadores de Java y espacios en blanco
 */

%%

// Sección de declaraciones
%public
%class Lexer
%standalone

// Definiciones de expresiones regulares
digito   = [0-9]
letra    = [a-zA-Z]
hex      = 0[xX][0-9a-fA-F]+
palabra  = {letra}+
espacio  = [ \t\n\r]+
keyword  = \b(int|double|float|boolean|char)\b
identifier = [a-zA-Z_][a-zA-Z0-9_]{0,31}

%%

{espacio}      { System.out.print("Espacio en blanco detectado\n"); }
{hex}          { System.out.print("Hexadecimal encontrado: " + yytext() + "\n"); }
{keyword}      { System.out.print("Palabra reservada encontrada: " + yytext() + "\n"); }
{identifier}   { System.out.print("Identificador válido encontrado: " + yytext() + "\n"); }
{digito}+      { System.out.print("Número encontrado: " + yytext() + "\n"); }
{palabra}      { System.out.print("Palabra encontrada: " + yytext() + "\n"); }
