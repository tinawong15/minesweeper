import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

import java.util.ArrayList;
import java.util.Random;

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
        rootPane.setMaxSize(height * 40,width * 40); // adjust size to fit all Squares

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                Square s = new Square(i, j);
                grid[i][j] = s;
                rootPane.add(s, i, j);
            }
        }

        selectMines();

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                addNeighbors(i, j);
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

    public void selectMines() {
        Random random = new Random();
        ArrayList<Integer> chosenIndices = new ArrayList<Integer>();
        int bombCount = 0;
        while(bombCount != bombs) {
            int index = random.nextInt(height * width); // converted 2D array length to 1D array to select random index from array
            if(!chosenIndices.contains(index)) { // check if index was already selected to have a mine
                chosenIndices.add(index);
                bombCount++;
            }
        }
        for(Integer chosenIndex : chosenIndices) {
            int remainingIndex = chosenIndex;
            int height = 0;
            // convert index to its position in a 2D array to match the grid
            while(remainingIndex >= width) {
                remainingIndex -= width;
                height++;
            }
            // System.out.println("i: "+height+" j: "+remainingIndex);
            grid[height][remainingIndex].setIsMine(true);
        }
    }

    public void addNeighbors(int i, int j) {
        if(i-1 >= 0) {
            grid[i][j].getNeighbors().add(grid[i-1][j]);
        }
        if(j-1 >= 0) {
            grid[i][j].getNeighbors().add(grid[i][j-1]);
        }
        if(i+1 < height) {
            grid[i][j].getNeighbors().add(grid[i+1][j]);
        }
        if(j+1 < width) {
            grid[i][j].getNeighbors().add(grid[i][j+1]);
        }
    }
}