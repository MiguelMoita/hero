import java.io.IOException;

public class Application {
    public static void main(String[] args)  {
        try {
            Game game = new Game(60, 30);
            game.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

