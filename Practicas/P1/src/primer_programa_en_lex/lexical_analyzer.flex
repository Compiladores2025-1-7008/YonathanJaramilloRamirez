/**
 * Escáner que detecta números y palabras
*/

%%

// Sección de declaraciones
%public
%class Lexer
%standalone

// Macros para simplificar las expresiones regulares
digito=[0-9]
letra=[a-zA-Z]
palabra={letra}+
espacio=[ \t\n]

%%

{espacio} {/* La acción léxica puede ir vacía si queremos que el escáner ignore la regla*/}
{digito}+ { System.out.print("Encontré un número: "+yytext()+"\n"); }
{palabra} { System.out.print("Encontré una palabra: "+yytext()+"\n"); }