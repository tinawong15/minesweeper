import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class Game {
    private int height;
    private int width;
    private int bombs;
    private Square[][] grid;

    private final GridPane rootPane;

    public Game(int height, int width, int bombs) {
        this.height = height;
        this.width = width;
        this.bombs = bombs;

        grid = new Square[height][width];
        rootPane = new GridPane();
        rootPane.setMaxSize(height * 40,width * 40);
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                Square s = new Square(i, j);
                grid[i][j] = s;
                rootPane.add(s, i, j);
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