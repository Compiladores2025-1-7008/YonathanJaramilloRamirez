/**
 * Escáner que detecta el lenguaje C_1
 *
 * Este archivo define las expresiones regulares y acciones léxicas para
 * reconocer los tokens del lenguaje C_1.
 */

package main.jflex;

import main.java.ClaseLexica;
import main.java.Token; // Importa la clase Token

%%

%public
%class Lexer
%standalone
%unicode


%{
  /**
   * Método auxiliar para imprimir tokens de manera uniforme.
   * @param type El tipo de token que será impreso.
   * @param value El valor o lexema asociado al token.
   * Se utiliza trim() para evitar espacios en blanco adicionales en los lexemas.
   */
  private void printToken(String type, String value) {
    System.out.print("<" + type + ", " + value.trim() + "> ");  // Usamos print en lugar de println para evitar saltos de línea
  }
%}

%%

// Ignorar espacios en blanco y manejar adecuadamente las nuevas líneas
[ \t\r]+        ;  // Ignora espacios, tabulaciones y retornos de carro
"\n"            ;  // Ignora saltos de línea

// Palabras clave del lenguaje que no necesitan guardar su lexema
"int"   { printToken("INT", "int"); return ClaseLexica.INT.ordinal(); }
"float" { printToken("FLOAT", "float"); return ClaseLexica.FLOAT.ordinal(); }
"if"    { printToken("IF", "if"); return ClaseLexica.IF.ordinal(); }
"else"  { printToken("ELSE", "else"); return ClaseLexica.ELSE.ordinal(); }
"while" { printToken("WHILE", "while"); return ClaseLexica.WHILE.ordinal(); }

// Los tokens que pueden tener un lexema variable (como números e identificadores) siguen guardando el lexema
[0-9]+(\.[0-9]+)?([eE][-+]?[0-9]+)? { printToken("NUMERO", yytext()); return ClaseLexica.NUMERO.ordinal(); }
[a-zA-Z_][a-zA-Z0-9_]*               { printToken("ID", yytext()); return ClaseLexica.ID.ordinal(); }

// Operadores y signos de puntuación que no requieren guardar lexemas
";"     { printToken("PYC", ";"); return ClaseLexica.PYC.ordinal(); }
","     { printToken("COMA", ","); return ClaseLexica.COMA.ordinal(); }
"("     { printToken("LPAR", "("); return ClaseLexica.LPAR.ordinal(); }
")"     { printToken("RPAR", ")"); return ClaseLexica.RPAR.ordinal(); }
