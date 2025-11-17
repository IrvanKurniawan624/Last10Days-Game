package console;

public class DayBanner {

    private static final String[][] DIGITS = {
        { " ██████╗ ", "██╔═████╗", "██║██╔██║", "████╔╝██║", "╚██████╔╝", " ╚═════╝ " },
        { " ██╗", "███║", "╚██║", " ██║", " ██║", " ╚═╝" },
        { "██████╗ ", "╚════██╗", " █████╔╝", "██╔═══╝ ", "███████╗", "╚══════╝" },
        { "██████╗ ", "╚════██╗", " █████╔╝", " ╚═══██╗", "██████╔╝", "╚═════╝ " },
        { "██╗  ██╗", "██║  ██║", "███████║", "╚════██║", "     ██║", "     ╚═╝" },
        { "███████╗", "██╔════╝", "███████╗", "╚════██║", "███████║", "╚══════╝" },
        { " ██████╗ ", "██╔════╝ ", "███████╗ ", "██╔═══██╗", "╚██████╔╝", " ╚═════╝ " },
        { "███████╗", "╚════██║", "    ██╔╝", "   ██╔╝ ", "   ██║  ", "   ╚═╝  " },
        { " █████╗ ", "██╔══██╗", "╚█████╔╝", "██╔══██╗", "╚█████╔╝", " ╚════╝ " },
        { " █████╗ ", "██╔══██╗", "╚██████║", " ╚═══██║", " █████╔╝", " ╚════╝ " }
    };

    private static final String[] DAY = {
        "██████╗  █████╗ ██╗   ██╗",
        "██╔══██╗██╔══██╗╚██╗ ██╔╝",
        "██║  ██║███████║ ╚████╔╝ ",
        "██║  ██║██╔══██║  ╚██╔╝  ",
        "██████╔╝██║  ██║   ██║   ",
        "╚═════╝ ╚═╝  ╚═╝   ╚═╝   ",
                                
    };

    public static void printDay(int dayNumber) {
        Utils.clear();

        String numStr = String.valueOf(dayNumber);
        StringBuilder[] digitLines = new StringBuilder[6];
        for (int i = 0; i < 6; i++) digitLines[i] = new StringBuilder();

        for (char c : numStr.toCharArray()) {
            int digit = c - '0';
            String[] art = DIGITS[digit];

            for (int i = 0; i < 6; i++) {
                digitLines[i].append(art[i]).append("  ");
            }
        }
        for (int i = 0; i < 6; i++) {
            System.out.println(DAY[i] + "    " + digitLines[i]);
        }

        System.out.println();
    }

}
