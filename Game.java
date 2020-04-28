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
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                Rectangle r = new Rectangle(j, i, 1, 1);
                r.setStroke(Color.BLACK);
                r.setFill(Color.WHITE);
                rootPane.getChildren().add(r);
            }
        }
    }

    public Pane getRootPane() {
        return rootPane;
    }
}