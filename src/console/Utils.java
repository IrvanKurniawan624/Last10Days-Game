package console;

public class Utils{
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void type(String text, int time) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(time); 
        }
        System.out.println();
    }

    public static void progressBar() {
        clear();
        System.out.println("Loading...");
        for (int i = 0; i <= 30; i++) {
            System.out.print("\r[");

            for (int j = 0; j < i; j++) System.out.print("#");
            for (int j = i; j < 30; j++) System.out.print(" ");

            System.out.print("] " + (i * 100 / 30) + "%");
            sleep(70);
        }
        clear();
    }

    public static void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}