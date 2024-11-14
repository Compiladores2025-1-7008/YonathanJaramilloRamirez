package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.jflex.Lexer2;
import main.java.Token;
import main.java.ClaseLexica;

public class ParserConToken implements ParserInterface {
    private Lexer2 lexer;
    private Token actual;
    private List<String> errores = new ArrayList<>();  // Lista para almacenar los errores

    public ParserConToken(Lexer2 lexer) {
        this.lexer = lexer;
    }

    public void eat(int claseLexica) {
        if (actual.getClaseLexica() == claseLexica) {
            // Imprimir el token reconocido antes de avanzar al siguiente
            imprimirToken(actual);
            try {
                actual = lexer.yylex();
            } catch (IOException ioe) {
                errores.add("Error al leer el siguiente token en la línea " + actual.getLinea());
            }
        } else {
            error("Se esperaba el token: " + claseLexica);
        }
    }

    public void error(String msg) {
        // Registrar el error sin detener el programa
        errores.add("ERROR DE SINTAXIS: " + msg + " en la línea " + actual.getLinea() + ". Token actual: " + actual.getLexema());
        // Intentar recuperar el análisis saltando al siguiente token
        try {
            actual = lexer.yylex();
        } catch (IOException ioe) {
            errores.add("Error al intentar recuperar el análisis en la línea " + actual.getLinea());
        }
    }

    public void parse() {
        try {
            actual = lexer.yylex();
        } catch (IOException ioe) {
            errores.add("Error: No fue posible obtener el primer token de la entrada.");
        }

        // Iniciar el análisis desde el símbolo inicial
        programa();

        // Mostrar el resultado del análisis
        if (!errores.isEmpty()) {
            // Mostrar todos los errores acumulados
            System.out.println("Se encontraron errores durante el análisis:");
            for (String error : errores) {
                System.out.println(error);
            }
            System.out.println("La cadena no pertenece al lenguaje generado por la gramática debido a los errores mencionados.");
        } else if (actual.getClaseLexica() == ClaseLexica.EOF) {
            // Si no hay errores y se llegó al final del archivo, aceptar la cadena
            System.out.println("La cadena es aceptada");
        } else {
            // En caso de tokens sobrantes sin errores previos
            System.out.println("La cadena no pertenece al lenguaje generado por la gramática.");
        }
    }

    // Implementación de la función para el símbolo inicial
    public void programa() { // programa -> declaraciones sentencias
        declaraciones();
        sentencias();
    }

    public void declaraciones() { // declaraciones -> declaraciones declaracion | declaracion
        declaracion();
        while (actual.getClaseLexica() == ClaseLexica.INT || actual.getClaseLexica() == ClaseLexica.FLOAT) {
            declaracion();
        }
    }

    public void declaracion() { // declaracion -> tipo lista_var ;
        tipo();
        listaVar();
        eat(ClaseLexica.PUNTO_Y_COMA);
    }

    public void tipo() { // tipo -> int | float
        if (actual.getClaseLexica() == ClaseLexica.INT) {
            eat(ClaseLexica.INT);
        } else if (actual.getClaseLexica() == ClaseLexica.FLOAT) {
            eat(ClaseLexica.FLOAT);
        } else {
            error("Se esperaba 'int' o 'float'");
        }
    }

    public void listaVar() { // lista_var -> lista_var , identificador | identificador
        eat(ClaseLexica.IDENTIFICADOR);
        while (actual.getClaseLexica() == ClaseLexica.COMA) {
            eat(ClaseLexica.COMA);
            eat(ClaseLexica.IDENTIFICADOR);
        }
    }

    public void sentencias() { // sentencias -> { sentencia+ } | epsilon
        while (actual.getClaseLexica() == ClaseLexica.IDENTIFICADOR || actual.getClaseLexica() == ClaseLexica.IF || 
               actual.getClaseLexica() == ClaseLexica.WHILE || actual.getClaseLexica() == ClaseLexica.LLAVE_IZQ) {
            sentencia();
        }
    }

    public void sentencia() {
        if (actual.getClaseLexica() == ClaseLexica.IDENTIFICADOR) {
            eat(ClaseLexica.IDENTIFICADOR);
            eat(ClaseLexica.IGUAL);
            expresion();
            eat(ClaseLexica.PUNTO_Y_COMA);
        } else if (actual.getClaseLexica() == ClaseLexica.IF) {
            eat(ClaseLexica.IF);
            eat(ClaseLexica.PAREN_IZQ);
            expresion();
            eat(ClaseLexica.PAREN_DER);
            sentencia();
            if (actual.getClaseLexica() == ClaseLexica.ELSE) {
                eat(ClaseLexica.ELSE);
                sentencia();
            }
        } else if (actual.getClaseLexica() == ClaseLexica.WHILE) {
            eat(ClaseLexica.WHILE);
            eat(ClaseLexica.PAREN_IZQ);
            expresion();
            eat(ClaseLexica.PAREN_DER);
            sentencia();
        } else if (actual.getClaseLexica() == ClaseLexica.LLAVE_IZQ) {
            eat(ClaseLexica.LLAVE_IZQ);
            while (actual.getClaseLexica() != ClaseLexica.LLAVE_DER) {
                sentencia();
            }
            eat(ClaseLexica.LLAVE_DER);
        } else {
            error("Se esperaba una sentencia");
        }
    }

    public void expresion() {
        termino();
        while (actual.getClaseLexica() == ClaseLexica.MAS || actual.getClaseLexica() == ClaseLexica.MENOS) {
            if (actual.getClaseLexica() == ClaseLexica.MAS) {
                eat(ClaseLexica.MAS);
            } else {
                eat(ClaseLexica.MENOS);
            }
            termino();
        }

        if (actual.getClaseLexica() == ClaseLexica.MAYOR || actual.getClaseLexica() == ClaseLexica.MENOR ||
            actual.getClaseLexica() == ClaseLexica.MAYOR_IGUAL || actual.getClaseLexica() == ClaseLexica.MENOR_IGUAL ||
            actual.getClaseLexica() == ClaseLexica.IGUAL_IGUAL || actual.getClaseLexica() == ClaseLexica.DIFERENTE) {
            int operador = actual.getClaseLexica();
            eat(operador);
            termino();
        }
    }

    public void termino() {
        factor();
        while (actual.getClaseLexica() == ClaseLexica.POR || actual.getClaseLexica() == ClaseLexica.DIV) {
            if (actual.getClaseLexica() == ClaseLexica.POR) {
                eat(ClaseLexica.POR);
            } else {
                eat(ClaseLexica.DIV);
            }
            factor();
        }
    }

    public void factor() {
        if (actual.getClaseLexica() == ClaseLexica.PAREN_IZQ) {
            eat(ClaseLexica.PAREN_IZQ);
            expresion();
            eat(ClaseLexica.PAREN_DER);
        } else if (actual.getClaseLexica() == ClaseLexica.IDENTIFICADOR) {
            eat(ClaseLexica.IDENTIFICADOR);
        } else if (actual.getClaseLexica() == ClaseLexica.NUMERO) {
            eat(ClaseLexica.NUMERO);
        } else {
            error("Se esperaba un factor (número, identificador o una expresión entre paréntesis)");
        }
    }

    // Método para imprimir el token reconocido
    private void imprimirToken(Token token) {
        int claseLexica = token.getClaseLexica();
        String lexema = token.getLexema();

        switch (claseLexica) {
            case ClaseLexica.INT:
                System.out.println("Encontramos una palabra reservada: int");
                break;
            case ClaseLexica.FLOAT:
                System.out.println("Encontramos una palabra reservada: float");
                break;
            case ClaseLexica.IF:
                System.out.println("Encontramos una palabra reservada: if");
                break;
            case ClaseLexica.ELSE:
                System.out.println("Encontramos una palabra reservada: else");
                break;
            case ClaseLexica.WHILE:
                System.out.println("Encontramos una palabra reservada: while");
                break;
            case ClaseLexica.IDENTIFICADOR:
                System.out.println("Encontramos un identificador: " + lexema);
                break;
            case ClaseLexica.NUMERO:
                System.out.println("Encontramos un número: " + lexema);
                break;
            case ClaseLexica.MAS:
                System.out.println("Encontramos un símbolo: +");
                break;
            case ClaseLexica.MENOS:
                System.out.println("Encontramos un símbolo: -");
                break;
            case ClaseLexica.POR:
                System.out.println("Encontramos un símbolo: *");
                break;
            case ClaseLexica.DIV:
                System.out.println("Encontramos un símbolo: /");
                break;
            case ClaseLexica.IGUAL:
                System.out.println("Encontramos un símbolo: =");
                break;
            case ClaseLexica.PUNTO_Y_COMA:
                System.out.println("Encontramos un símbolo: ;");
                break;
            case ClaseLexica.COMA:
                System.out.println("Encontramos un símbolo: ,");
                break;
            case ClaseLexica.PAREN_IZQ:
                System.out.println("Encontramos un símbolo: (");
                break;
            case ClaseLexica.PAREN_DER:
                System.out.println("Encontramos un símbolo: )");
                break;
            case ClaseLexica.LLAVE_IZQ:
                System.out.println("Encontramos un símbolo: {");
                break;
            case ClaseLexica.LLAVE_DER:
                System.out.println("Encontramos un símbolo: }");
                break;
            case ClaseLexica.MAYOR:
                System.out.println("Encontramos un símbolo: >");
                break;
            case ClaseLexica.MENOR:
                System.out.println("Encontramos un símbolo: <");
                break;
            case ClaseLexica.MAYOR_IGUAL:
                System.out.println("Encontramos un símbolo: >=");
                break;
            case ClaseLexica.MENOR_IGUAL:
                System.out.println("Encontramos un símbolo: <=");
                break;
            case ClaseLexica.IGUAL_IGUAL:
                System.out.println("Encontramos un símbolo: ==");
                break;
            case ClaseLexica.DIFERENTE:
                System.out.println("Encontramos un símbolo: !=");
                break;
            default:
                System.out.println("Token desconocido: " + lexema);
                break;
        }
    }

    @Override
    public void S() {
        throw new UnsupportedOperationException("Unimplemented method 'S'");
    }
}
