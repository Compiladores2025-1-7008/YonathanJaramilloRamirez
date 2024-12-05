package src.syntax_analyzer_byaccj;

/**
 * The `Token` class represents a lexical token identified during analysis.
 * 
 * Each token is characterized by its lexical class, lexeme, and the line 
 * number where it appears in the source code.
 * 
 * @version Yonathan Berith Jaramillo Ramírez
 */
public class Token {
    private LexicalClass lexicalClass;
    private String lexeme;
    private int line;

    /**
     * Constructs a `Token` instance with the given properties.
     * 
     * @param lexicalClass The lexical class of the token.
     * @param lexeme       The lexeme associated with the token.
     * @param line         The line number where the token is located.
     * @throws IllegalArgumentException If the lexeme is null or empty, or if 
     *                                  the line number is negative.
     */
    public Token(LexicalClass lexicalClass, String lexeme, int line) {
        if (lexeme == null || lexeme.isEmpty()) {
            throw new IllegalArgumentException("Lexeme cannot be null or empty.");
        }
        if (line < 0) {
            throw new IllegalArgumentException("Line number cannot be negative.");
        }
        this.lexicalClass = lexicalClass;
        this.lexeme = lexeme;
        this.line = line;
    }

    /**
     * Retrieves the lexical class of the token.
     * 
     * @return The lexical class of this token.
     */
    public LexicalClass getLexicalClass() {
        return lexicalClass;
    }

    /**
     * Retrieves the lexeme associated with the token.
     * 
     * @return The lexeme as a string.
     */
    public String getLexeme() {
        return lexeme;
    }

    /**
     * Retrieves the line number where the token is located.
     * 
     * @return The line number of this token.
     */
    public int getLine() {
        return line;
    }

    /**
     * Converts the token to a string representation.
     * 
     * @return A string in the format "<lexicalClass, lexeme, line>".
     */
    @Override
    public String toString() {
        return "<" + this.lexicalClass.getName() + "," + this.lexeme + "," + this.line + ">";
    }

    /**
     * Checks if the token belongs to a specific lexical class.
     * 
     * @param lexicalClass The lexical class to check against.
     * @return `true` if the token matches the specified class, `false` otherwise.
     */
    public boolean isOfClass(LexicalClass lexicalClass) {
        return this.lexicalClass == lexicalClass;
    }

    /**
     * Compares this token with another token for equality.
     * 
     * @param other The other token to compare with.
     * @return `true` if both tokens are identical, `false` otherwise.
     */
    public boolean equals(Token other) {
        return this.lexicalClass == other.lexicalClass &&
               this.lexeme.equals(other.lexeme) &&
               this.line == other.line;
    }

    /**
     * Verifies the token's validity and prints its details.
     * 
     * Invalid tokens (e.g., those with unknown classes or empty lexemes) are 
     * flagged, and valid tokens are displayed with their associated properties.
     */
    public void verifyAndPrintToken() {
        String color = this.lexicalClass.getColor();
        String message;

        if (this.lexicalClass == LexicalClass.UNKNOWN || this.lexeme == null || this.lexeme.isEmpty()) {
            message = "\nInvalid token: " + this.toString();
        } else {
            message = "\nValid token: " + this.toString();
        }

        System.out.println(color + message + Colors.RESTORE);
    }
}
