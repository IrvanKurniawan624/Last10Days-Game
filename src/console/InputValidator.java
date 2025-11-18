package console;

public class InputValidator {

    public int getChoiceInRange(String input, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {
                    return value;
                } else {
                    TextColor.color(TextColor.RED, "Invalid choice! Enter " + min + "-" + max + ": ");
                    return -1;
                }

            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Numbers only: ");
                return -1;
            }
        }
    }
}
