package src.syntax_analyzer_byaccj;

import java.io.FileReader;

/**
 * Entry point for the syntax analysis process.
 * 
 * This class contains the `main` method, which initializes the syntax analysis
 * using the lexical and syntactic analyzers. It ensures the input file is 
 * properly processed and handles potential errors gracefully.
 * 
 * @author Yonathan Berith
 */
public class Main {
    /**
     * Main method to initiate the syntax analysis.
     * 
     * @param args Command-line arguments. A single argument is expected, representing 
     *             the path to the input file.
     */
    public static void main(String[] args) {
        // Validate the number of arguments
        if (args.length != 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(1);
        }

        // Display introductory messages
        Colors.println("BYACCJ + JFlex Syntax Analyzer", Colors.HIGH_INTENSITY);
        Colors.println("Input File: " + args[0], Colors.HIGH_INTENSITY);

        try {
            // Initialize the parser with the provided input file
            Parser parser = new Parser(new FileReader(args[0]));
            parser.yyparse();
        } catch (Exception e) {
            // Handle any errors that occur during analysis
            System.err.println("An error occurred: " + e.getMessage());
            System.exit(1);
        }
    }
}
