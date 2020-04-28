import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Game {
    private int height;
    private int width;
    private int bombs;

    private final Pane rootPane;

    public Game(int height, int width, int bombs) {
        this.height = height;
        this.width = width;
        this.bombs = bombs;

        rootPane = new Pane();
        for(int i = 0; i < height * 50; i+=50) {
            for(int j = 0; j < width * 50; j+=50) {
                Rectangle r = new Rectangle(i, j, 50, 50);
                r.setStroke(Color.BLACK);
                r.setFill(Color.WHITE);
                rootPane.getChildren().add(r);
            }
        }
    }

    public Pane getRootPane() {
        return rootPane;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}