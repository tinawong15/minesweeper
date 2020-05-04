import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Text; 
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class Game {
    private static int height;
    private static int width;
    
    private static int bombs;
    private static Set<Square> visitedSquares = new HashSet<Square>(); // includes all Squares that have been visited, except mines

    private Square[][] grid;

    private static GridPane rootPane;

    public Game(int height, int width, int bombs) {
        Game.height = height;
        Game.width = width;
        
        Game.bombs = bombs;

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

    public static Pane getRootPane() {
        return rootPane;
    }

    public static Set<Square> getVisitedSquares() {
        return visitedSquares;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static int getBombs() {
        return bombs;
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
        if(i-1 >= 0 && j-1 >= 0) {
            grid[i][j].getNeighbors().add(grid[i-1][j-1]);
        }
        if(i+1 < height && j-1 >= 0) {
            grid[i][j].getNeighbors().add(grid[i+1][j-1]);
        }
        if(i-1 >= 0 && j+1 < width) {
            grid[i][j].getNeighbors().add(grid[i-1][j+1]);
        }
        if(i+1 < height && j+1 < width) {
            grid[i][j].getNeighbors().add(grid[i+1][j+1]);
        }
    }

    public static void win() {
        System.out.println("Congratulations! You win!");
        rootPane.getChildren().clear();
        rootPane.add(new Text("Congratulations! You win!"), height/2, width/2);
        rootPane.setAlignment(Pos.CENTER);
        resetGame();
    }

    public static void lose() {
        System.out.println("Game over! You lose!");
        rootPane.getChildren().clear();
        rootPane.add(new Text("Game over! You lose!"), height/2, width/2);
        rootPane.setAlignment(Pos.CENTER);
        resetGame();
    }

    private static void resetGame(){
        bombs = 0;
        visitedSquares = new HashSet<Square>();
        rootPane = new GridPane();
    }
}