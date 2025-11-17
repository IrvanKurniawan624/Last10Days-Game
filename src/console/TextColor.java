package console;

public class TextColor {
    public static final String RESET  = "\033[0m";
    public static final String RED    = "\033[31m";
    public static final String GREEN  = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE   = "\033[34m";
    public static final String CYAN   = "\033[36m";
    public static final String BOLD   = "\033[1m";

    public static String color(String color, String text) {
        return color + text + RESET;
    }
}
