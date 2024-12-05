package src.syntax_analyzer_byaccj;

/**
 * Utility class that provides <strong>formatting strings</strong>
 * for controlling terminal output styles.
 *
 * This class offers pre-defined escape sequences to adjust
 * the font color, background color, and text styles in terminal outputs.
 *
  * @author Yonathan Berith Jaramillo Ramírez
 */
public class Colors {

    // Foreground colors
    /** Sets the text color to black. */
    public static final String BLACK = "\033[0;30m";

    /** Sets the text color to red. */
    public static final String RED = "\033[0;31m";

    /** Sets the text color to green. */
    public static final String GREEN = "\033[0;32m";

    /** Sets the text color to yellow. */
    public static final String YELLOW = "\033[0;33m";

    /** Sets the text color to blue. */
    public static final String BLUE = "\033[0;34m";

    /** Sets the text color to light blue. */
    public static final String BLUE_LIGHT = "\033[38;5;69m";

    /** Sets the text color to magenta. */
    public static final String MAGENTA = "\033[0;35m";

    /** Sets the text color to light magenta. */
    public static final String MAGENTA_LIGHT = "\033[38;5;177m";

    /** Sets the text color to cyan. */
    public static final String CYAN = "\033[0;36m";

    /** Sets the text color to purple. */
    public static final String PURPLE = "\033[0;35m";

    /** Sets the text color to white. */
    public static final String WHITE = "\033[0;37m";

    /** Sets the text color to pink. */
    public static final String PINK = "\033[0;35m";

    /** Sets the text color to dark gray. */
    public static final String GRAY_DARK = "\033[38;5;240m";

    /** Sets the text color to light gray. */
    public static final String GRAY_LIGHT = "\033[1;30m";

    /** Sets the text color to orange. */
    public static final String ORANGE = "\033[38;5;208m";

    /** Sets the text color to teal. */
    public static final String TEAL = "\033[38;5;30m";

    /** Sets the text color to brown. */
    public static final String BROWN = "\033[38;5;94m";

    /** Sets the text color to lime green. */
    public static final String LIME = "\033[38;5;154m";

    /** Sets the text color to gold. */
    public static final String GOLD = "\033[38;5;220m";

    /** Sets the text color to lavender. */
    public static final String LAVENDER = "\033[38;5;183m";

    /** Sets the text color to turquoise. */
    public static final String TURQUOISE = "\033[38;5;45m";

    /** Sets the text color to coral. */
    public static final String CORAL = "\033[38;5;209m";

    /** Sets the text color to olive green. */
    public static final String OLIVE = "\033[38;5;100m";

    /** Sets the text color to beige. */
    public static final String BEIGE = "\033[38;5;230m";

    /** Sets the text color to indigo. */
    public static final String INDIGO = "\033[38;5;54m";

    /** Sets the text color to salmon. */
    public static final String SALMON = "\033[38;5;216m";

    /** Sets the text color to mint green. */
    public static final String MINT = "\033[38;5;121m";

    /** Sets the text color to emerald green. */
    public static final String EMERALD = "\033[38;5;48m";

    /** Sets the text color to chocolate brown. */
    public static final String CHOCOLATE = "\033[38;5;166m";

    /** Sets the text color to plum. */
    public static final String PLUM = "\033[38;5;96m";

    /** Sets the text color to aquamarine. */
    public static final String AQUAMARINE = "\033[38;5;86m";

    /** Sets the text color to navy blue. */
    public static final String NAVY = "\033[38;5;17m";

    /** Sets the text color to bright emerald green. */
    public static final String BRIGHT_EMERALD = "\033[38;5;10m";

    /** Sets the text color to steel gray. */
    public static final String STEEL_GRAY = "\033[38;5;102m";

    /** Sets the text color to sand brown. */
    public static final String SAND = "\033[38;5;137m";

    /** Sets the text color to light lavender. */
    public static final String LIGHT_LAVENDER = "\033[38;5;189m";

    /** Sets the text color to forest green. */
    public static final String FOREST_GREEN = "\033[38;5;28m";

    /** Sets the text color to sky blue. */
    public static final String SKY_BLUE = "\033[38;5;117m";

    /** Sets the text color to dark purple. */
    public static final String DARK_PURPLE = "\033[38;5;91m";

    /** Sets the text color to light pink. */
    public static final String LIGHT_PINK = "\033[38;5;217m";

    /** Sets the text color to ice blue. */
    public static final String ICE_BLUE = "\033[38;5;123m";

    /** Sets the text color to slate gray. */
    public static final String SLATE_GRAY = "\033[38;5;66m";

    /** Sets the text color to moss green. */
    public static final String MOSS_GREEN = "\033[38;5;58m";

    /** Sets the text color to old gold. */
    public static final String OLD_GOLD = "\033[38;5;136m";

    // Additional text formatting
    /** Removes all text formatting. */
    public static final String RESTORE = "\033[0m";

    /** Makes text appear more intense. */
    public static final String HIGH_INTENSITY = "\033[1m";

    /** Makes text appear less intense. */
    public static final String LOW_INTENSITY = "\033[2m";

    /** Applies italics to the text. */
    public static final String ITALICS = "\033[3m";

    /** Underlines the text. */
    public static final String UNDERLINE = "\033[4m";

    /** Makes the text blink. */
    public static final String BLINK = "\033[5m";

    /** Makes the text blink rapidly. */
    public static final String FAST_BLINK = "\033[6m";

    /** Swaps text color with the background color. */
    public static final String REVERSE = "\033[7m";

    /** Makes the text invisible. */
    public static final String INVISIBLE_TEXT = "\033[8m";

    // Background colors
    /** Sets the background color to black. */
    public static final String BGD_BLACK = "\033[0;40m";

    /** Sets the background color to red. */
    public static final String BGD_RED = "\033[0;41m";

    /** Sets the background color to green. */
    public static final String BGD_GREEN = "\033[0;42m";

    /** Sets the background color to yellow. */
    public static final String BGD_YELLOW = "\033[0;43m";

    /** Sets the background color to blue. */
    public static final String BGD_BLUE = "\033[0;44m";

    /** Sets the background color to magenta. */
    public static final String BGD_MAGENTA = "\033[0;45m";

    /** Sets the background color to cyan. */
    public static final String BGD_CYAN = "\033[0;46m";

    /** Sets the background color to white. */
    public static final String BGD_WHITE = "\033[0;47m";

    /** Sets the background color to gray. */
    public static final String BGD_GRAY = "\033[0;100m";

    /**
     * Prints the given string <code>s</code> with the specified format. Adds a
     * newline at the end.
     *
     * @param s      The string to print.
     * @param format The format to apply to the text.
     */
    public static final void println(Object s, String format) {
        System.out.println(format + s + RESTORE);
    }

    /**
     * Prints the given string <code>s</code> with the specified format without
     * adding a newline.
     *
     * @param s      The string to print.
     * @param format The format to apply to the text.
     */
    public static final void print(Object s, String format) {
        System.out.print(format + s + RESTORE);
    }
}
