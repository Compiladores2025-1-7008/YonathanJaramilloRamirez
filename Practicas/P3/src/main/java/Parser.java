package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.jflex.Lexer;
import main.java.ClaseLexica;

public class Parser implements ParserInterface {
    private Lexer lexer;
    private int actual;
    private List<String> errores = new ArrayList<>();  // Lista para almacenar los errores

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void eat(int claseLexica) {
        if (actual == claseLexica) {
            try {
                actual = lexer.yylex();
            } catch (IOException ioe) {
                errores.add("Error al leer el siguiente token en la línea " + lexer.getLine());
            }
        } else {
            error("Se esperaba el token: " + claseLexica);
        }
    }

    public void error(String msg) {
        // Registrar el error sin detener el programa
        errores.add("ERROR DE SINTAXIS: " + msg + " en la línea " + lexer.getLine() + ". Token actual: " + actual);
        // Intentar recuperar el análisis saltando al siguiente token
        try {
            actual = lexer.yylex();
        } catch (IOException ioe) {
            errores.add("Error al intentar recuperar el análisis en la línea " + lexer.getLine());
        }
    }

    public void parse() {
        try {
            this.actual = lexer.yylex();
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
        } else if (actual == ClaseLexica.EOF) {
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
        while (actual == ClaseLexica.INT || actual == ClaseLexica.FLOAT) {
            declaracion();
        }
    }

    public void declaracion() { // declaracion -> tipo lista_var ;
        tipo();
        listaVar();
        eat(ClaseLexica.PUNTO_Y_COMA);
    }

    public void tipo() { // tipo -> int | float
        if (actual == ClaseLexica.INT) {
            eat(ClaseLexica.INT);
        } else if (actual == ClaseLexica.FLOAT) {
            eat(ClaseLexica.FLOAT);
        } else {
            error("Se esperaba 'int' o 'float'");
        }
    }

    public void listaVar() { // lista_var -> lista_var , identificador | identificador
        eat(ClaseLexica.IDENTIFICADOR);
        while (actual == ClaseLexica.COMA) {
            eat(ClaseLexica.COMA);
            eat(ClaseLexica.IDENTIFICADOR);
        }
    }

    public void sentencias() { // sentencias -> { sentencia+ } | epsilon
        while (actual == ClaseLexica.IDENTIFICADOR || actual == ClaseLexica.IF || actual == ClaseLexica.WHILE || actual == ClaseLexica.LLAVE_IZQ) {
            sentencia();
        }
    }

    public void sentencia() {
        if (actual == ClaseLexica.IDENTIFICADOR) {
            // Asignación: identificador = expresion ;
            eat(ClaseLexica.IDENTIFICADOR);
            eat(ClaseLexica.IGUAL);
            expresion();
            eat(ClaseLexica.PUNTO_Y_COMA);
        } else if (actual == ClaseLexica.IF) {
            // Estructura if (...) sentencia else sentencia
            eat(ClaseLexica.IF);
            eat(ClaseLexica.PAREN_IZQ);
            expresion();
            eat(ClaseLexica.PAREN_DER);
            sentencia();
            if (actual == ClaseLexica.ELSE) {
                eat(ClaseLexica.ELSE);
                sentencia();
            }
        } else if (actual == ClaseLexica.WHILE) {
            // Estructura while (...) sentencia
            eat(ClaseLexica.WHILE);
            eat(ClaseLexica.PAREN_IZQ);
            expresion();
            eat(ClaseLexica.PAREN_DER);
            sentencia();
        } else if (actual == ClaseLexica.LLAVE_IZQ) {
            // Bloque de sentencias entre llaves
            eat(ClaseLexica.LLAVE_IZQ);
            while (actual != ClaseLexica.LLAVE_DER) {
                sentencia();
            }
            eat(ClaseLexica.LLAVE_DER);
        } else {
            error("Se esperaba una sentencia");
        }
    }

    public void expresion() { // expresion -> termino ((+|-) termino)*
        termino();
        while (actual == ClaseLexica.MAS || actual == ClaseLexica.MENOS) {
            if (actual == ClaseLexica.MAS) {
                eat(ClaseLexica.MAS);
            } else {
                eat(ClaseLexica.MENOS);
            }
            termino();
        }

        // Comparaciones adicionales
        if (actual == ClaseLexica.MAYOR || actual == ClaseLexica.MENOR ||
            actual == ClaseLexica.MAYOR_IGUAL || actual == ClaseLexica.MENOR_IGUAL ||
            actual == ClaseLexica.IGUAL_IGUAL || actual == ClaseLexica.DIFERENTE) {
            int operador = actual;
            eat(operador);
            termino();
        }
    }

    public void termino() { // termino -> factor ((*|/) factor)*
        factor();
        while (actual == ClaseLexica.POR || actual == ClaseLexica.DIV) {
            if (actual == ClaseLexica.POR) {
                eat(ClaseLexica.POR);
            } else {
                eat(ClaseLexica.DIV);
            }
            factor();
        }
    }

    public void factor() { // factor -> ( expresion ) | identificador | numero
        if (actual == ClaseLexica.PAREN_IZQ) {
            eat(ClaseLexica.PAREN_IZQ);
            expresion();
            eat(ClaseLexica.PAREN_DER);
        } else if (actual == ClaseLexica.IDENTIFICADOR) {
            eat(ClaseLexica.IDENTIFICADOR);
        } else if (actual == ClaseLexica.NUMERO) {
            eat(ClaseLexica.NUMERO);
        } else {
            error("Se esperaba un factor (número, identificador o una expresión entre paréntesis)");
        }
    }

    @Override
    public void S() {
        throw new UnsupportedOperationException("Unimplemented method 'S'");
    }
}
