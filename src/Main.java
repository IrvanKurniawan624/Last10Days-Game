import java.io.IOException;
import console.TextColor;

public class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.start();
        System.out.print(TextColor.RESET);
    }

}
