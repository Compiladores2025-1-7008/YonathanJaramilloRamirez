package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import main.jflex.Lexer2; // Importar Lexer2, no Lexer

public class MainWithToken {
    public static void main(String[] args) {
        // Verificar si se proporcionó el argumento del archivo
        if (args.length != 1) {
            System.err.println("Uso: java -cp build main.java.Main <archivo_de_prueba.txt>");
            System.exit(1);
        }

        String filePath = args[0];
        File file = new File(filePath);

        // Verificar si el archivo existe
        if (!file.exists() || !file.isFile()) {
            System.err.println("Error: No se encontró el archivo especificado: " + filePath);
            System.exit(1);
        }
        System.out.println("---------------------------------------------------------"); // Línea separadora para claridad
        System.out.println("Procesando archivo: " + file.getName());
        try {
            ParserInterface parser = new ParserConToken(new Lexer2(new FileReader(file))); // Usa Lexer2
            parser.parse();  // Aquí se ejecutará el análisis y se imprimirá el resultado directamente desde ParserConToken.
        } catch (FileNotFoundException fnfe) {
            System.err.println("Error: No fue posible leer del archivo de entrada: " + file.getName());
            fnfe.printStackTrace();
        } catch (RuntimeException re) {
            System.err.println("Error de Sintaxis en el archivo: " + file.getName());
            System.err.println(re.getMessage()); // Imprime el mensaje de error de sintaxis
        } catch (Exception e) {
            System.err.println("Error inesperado en el archivo: " + file.getName());
            e.printStackTrace(); // Imprimir la traza completa del error para entender qué falló
        }

        System.out.println("Fin del procesamiento del archivo: " + file.getName());
        System.out.println("---------------------------------------------------------"); // Línea separadora para claridad
    }
}
