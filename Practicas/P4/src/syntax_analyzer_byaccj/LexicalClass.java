package src.syntax_analyzer_byaccj;

/**
 * Enum `LexicalClass` defines the various token types
 * that the lexical analyzer can identify in the C_1 language.
 * Each constant represents a category of lexemes from the source code.
 * 
 * This structure helps to classify tokens into meaningful groups
 * for parsing and semantic processing.
 * 
 * @author Yonathan Berith Jaramillo Ramírez
 */
public enum LexicalClass {
    // Keywords
    INT("INT", Colors.RED),
    FLOAT("FLOAT", Colors.GREEN),
    IF("IF", Colors.YELLOW),
    ELSE("ELSE", Colors.BLUE),
    WHILE("WHILE", Colors.MAGENTA),

    // Identifiers
    ID("ID", Colors.CYAN),

    // Numeric Literals
    INTEGER_NUMBER("INTEGER_NUMBER", Colors.ORANGE),
    REAL_NUMBER("REAL_NUMBER", Colors.PURPLE),

    // Symbols and Operators
    SEMICOLON("SEMICOLON", Colors.PINK),
    COMMA("COMMA", Colors.LIGHT_PINK),
    LPAREN("LPAREN", Colors.BRIGHT_EMERALD),
    RPAREN("RPAREN", Colors.BROWN),
    LBRACE("LBRACE", Colors.LIME),
    RBRACE("RBRACE", Colors.GOLD),
    ASSIGNMENT("ASSIGNMENT", Colors.FOREST_GREEN),
    EQUALS("EQUALS", Colors.TURQUOISE),
    GREATER_THAN("GREATER_THAN", Colors.CORAL),
    LESS_THAN("LESS_THAN", Colors.OLIVE),
    PLUS("PLUS", Colors.SAND),
    MINUS("MINUS", Colors.INDIGO),
    MULTIPLY("MULTIPLY", Colors.SALMON),
    DIVIDE("DIVIDE", Colors.MINT),

    // Relational and Logical Operators
    GREATER_EQUALS("GREATER_EQUALS", Colors.EMERALD),
    LESS_EQUALS("LESS_EQUALS", Colors.CHOCOLATE),
    NOT_EQUALS("NOT_EQUALS", Colors.PLUM),

    // End of File
    EOF("EOF", Colors.AQUAMARINE),

    // Unrecognized Tokens
    UNKNOWN("UNKNOWN", Colors.RESTORE);

    private final String name;
    private final String color;

    /**
     * Constructor for `LexicalClass` enum.
     * 
     * @param name  The name of the lexical class.
     * @param color The color associated with the lexical class.
     */
    LexicalClass(String name, String color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Retrieves the name of the lexical class.
     * 
     * @return The name of the lexical class.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the color assigned to the lexical class.
     * 
     * @return The associated color of the lexical class.
     */
    public String getColor() {
        return color;
    }

    /**
     * Fetches the name of the lexical class given its code.
     * 
     * @param lexicalClass The code representing the lexical class.
     * @return The name of the corresponding lexical class.
     */
    public static String getClassName(LexicalClass lexicalClass) {
        for (LexicalClass cls : values()) {
            if (cls == lexicalClass) {
                return cls.getName();
            }
        }
        return UNKNOWN.getName();
    }

    /**
     * Fetches the color associated with the lexical class given its code.
     * 
     * @param lexicalClass The code representing the lexical class.
     * @return The color assigned to the corresponding lexical class.
     */
    public static String getClassColor(LexicalClass lexicalClass) {
        for (LexicalClass cls : values()) {
            if (cls == lexicalClass) {
                return cls.getColor();
            }
        }
        return UNKNOWN.getColor();
    }
}
