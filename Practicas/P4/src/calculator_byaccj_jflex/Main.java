package src.calculator_byaccj_jflex;

import java.io.*;

/**
 * Main class for the calculator.
 * Implements a calculator for mathematical expressions with support for
 * basic operations, powers, and parentheses.
 * 
 * This program runs interactively, allowing users to input mathematical expressions
 * and view their results. Users can quit the application by typing 'q'.
 * 
 * @author Yonathan Berith Jaramillo Ramírez
 */
public class Main {

    // Controls the main program loop
    private static boolean isRunning = true;

    /**
     * Main entry point of the program.
     * Displays the calculator menu and starts processing user input.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        displayMenu();
        processInputs();
    }

    /**
     * Processes user inputs interactively.
     * Reads mathematical expressions, evaluates them, and displays the results.
     * The program terminates when the user enters 'q'.
     */
    private static void processInputs() {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        
        while (isRunning) {
            System.out.print("\nExpression > ");
            try {
                // Read the user's input
                String expression = inputReader.readLine();

                // Check if the input is null or empty
                if (expression == null || expression.trim().isEmpty()) {
                    System.out.println("Please enter a valid expression.");
                    continue;
                }

                // Exit condition
                if (expression.trim().equalsIgnoreCase("q")) {
                    System.out.println("Goodbye!");
                    isRunning = false;
                    continue;
                }

                // Parse and evaluate the expression
                try {
                    Parser parser = new Parser(new StringReader(expression + "\n"));
                    parser.setExpression(expression);
                    parser.yyparse();
                } catch (Exception parsingException) {
                    System.err.println("Syntax Error: Unable to evaluate the expression.");
                }

            } catch (IOException ioException) {
                System.err.println("Input Error: " + ioException.getMessage());
            }
        }
    }

    /**
     * Displays the main menu with instructions and supported operations.
     * Lists all available operations in the calculator.
     */
    private static void displayMenu() {
        System.out.println("===== Calculator =====");
        System.out.println("\nSupported Operations:");
        System.out.println("   +  : Addition");
        System.out.println("   -  : Subtraction");
        System.out.println("   *  : Multiplication");
        System.out.println("   /  : Division");
        System.out.println("   ^  : Power");
        System.out.println("  ( ) : Parentheses");
        System.out.println("\nType 'q' to quit.");
        System.out.println("======================");
    }
}
