/*
 * Tina Wong & Ray Onishi
 * CSE160
 * Section 1
 * Final Project - Minesweeper
 */

import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.text.Text; 
import javafx.scene.paint.Color;
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

    private static int turn;
    private static Square[][] grid;

    private static GridPane rootPane;

    public Game(int height, int width, int bombs) {
        Game.height = height;
        Game.width = width;
        
        Game.bombs = bombs;

        grid = new Square[height][width];

        // Adjust size of pane to fit all Squares
        rootPane = new GridPane();
        rootPane.setMaxSize(height * 40,width * 40); 

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                Square s = new Square(i, j);
                grid[i][j] = s;
                rootPane.add(s, j, i);
            }
        }

        selectMines();

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                addNeighbors(i, j);
            }
        }
    }

    // Getter method for the rootPane
    public static Pane getRootPane() {
        return rootPane;
    }

    // Getter method for visitedSquares
    public static Set<Square> getVisitedSquares() {
        return visitedSquares;
    }

    // Getter method for width
    public static int getWidth() {
        return width;
    }

    // Getter method for height
    public static int getHeight() {
        return height;
    }

    // Getter method for # bombs
    public static int getBombs() {
        return bombs;
    }

    // Getter method for the current turn
    public static int getTurn() {
        return turn;
    }

    // Setter method for the turn
    public static void setTurn(int newTurn) {
        turn = newTurn;
    }

    // Selects random mines on the grid
    public static void selectMines() {
        Random random = new Random();
        ArrayList<Integer> chosenHeight = new ArrayList<Integer>();
        ArrayList<Integer> chosenWidth = new ArrayList<Integer>();
        int bombCount = 0;
        int randHeight;
        int randWidth;
        boolean exists = false;
        while(bombCount != bombs){
            exists = false;
            randHeight = random.nextInt(height);
            randWidth = random.nextInt(width);
            for(int k = 0; k < chosenHeight.size(); k++){
                // Check if randomly selected index is already in list of mines. if so, pick again
                if(randHeight == chosenHeight.get(k) && randWidth == chosenWidth.get(k)){ 
                    exists = true; 
                    break;
                }
            }
            if(!exists){
                chosenHeight.add(randHeight);
                chosenWidth.add(randWidth);
                bombCount++;
            }
        }

        for(int l = 0; l < chosenHeight.size(); l++){
            grid[chosenHeight.get(l)][chosenWidth.get(l)].setIsMine(true);
        }
    }

    // Selects random mines on the grid excluding the square at  i,j (avoiding first click loss)
    public static void selectMines(int i, int j) {
        // Reset the grid to reselect mines excluding square at i,j
        for(int k = 0; k < height; k++) {
            for(int m = 0; m < width; m++) {
                grid[k][m].setIsMine(false);
            }
        }
        Random random = new Random();
        ArrayList<Integer> chosenHeight = new ArrayList<Integer>();
        ArrayList<Integer> chosenWidth = new ArrayList<Integer>();
        int bombCount = 0;
        int randHeight;
        int randWidth;
        boolean exists = false;
        while(bombCount != bombs){
            exists = false;
            randHeight = random.nextInt(height);
            randWidth = random.nextInt(width);

            // Check if randomly selected integers match the coordinates of the Square that the user first clicked
            if(i != randHeight || j != randWidth ){ 
                for(int k = 0; k < chosenHeight.size(); k++){
                    // Check if randomly selected index is already in list of mines
                    if(randHeight == chosenHeight.get(k) && randWidth == chosenWidth.get(k)){ 
                        exists = true;
                        break;
                    }
                }
                if(!exists){
                    chosenHeight.add(randHeight);
                    chosenWidth.add(randWidth);
                    // Increment whenever an index for a mine has been successfully chosen
                    bombCount++; 
                }
            }
        }

        for(int l = 0; l < chosenHeight.size(); l++){
            // Set the chosen square indices as mines
            grid[chosenHeight.get(l)][chosenWidth.get(l)].setIsMine(true); 
        }
    }

    // Adds neighbors to a square
    public void addNeighbors(int i, int j) { // add neighboring Squares to array so each Square can keep track of its neighbors
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

    // Ran when the game is won by the user
    public static void win() {
        for(int k = 0; k < height; k++) {
            for(int m = 0; m < width; m++) {
                if(grid[k][m].getIsMine()){
                    grid[k][m].numberMinesAdjacent.setText("X");
                    grid[k][m].numberMinesAdjacent.setFill(Color.CRIMSON);
                    Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20);
                    grid[k][m].numberMinesAdjacent.setFont(font);
                    grid[k][m].getChildren().add(grid[k][m].numberMinesAdjacent);
                    grid[k][m].getChildren().remove(grid[k][m].button);
                }
            }
        }

        java.util.Timer timer = new java.util.Timer();
        timer.schedule( new java.util.TimerTask() {
            public void run() {
                Platform.runLater(new Runnable(){
                    public void run(){
                        System.out.println("Congratulations! You win!");
                        rootPane.getChildren().clear();
                        rootPane.add(new Text("Congratulations! You win!"), height/2, width/2);
                        rootPane.setAlignment(Pos.CENTER);
                        resetGame(); // reset all used variables so user can replay game, as long as they do not close the menu settings while game is playing
                        timer.cancel();                 
                    }});
            }
        }, 2000 );
    }

    // Ran when the game is lost by the user
    public static void lose() {
        for(int k = 0; k < height; k++) {
            for(int m = 0; m < width; m++) {
                if(grid[k][m].getIsMine()){
                    grid[k][m].numberMinesAdjacent.setText("X");
                    grid[k][m].numberMinesAdjacent.setFill(Color.CRIMSON);
                    Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20);
                    grid[k][m].numberMinesAdjacent.setFont(font);
                    grid[k][m].getChildren().add(grid[k][m].numberMinesAdjacent);
                    grid[k][m].getChildren().remove(grid[k][m].button);
                }
            }
        }

        java.util.Timer timer = new java.util.Timer();
        timer.schedule( new java.util.TimerTask() {
            public void run() {
                Platform.runLater(new Runnable(){
                    public void run(){
                        System.out.println("Game over! You lose!");
                        rootPane.getChildren().clear();
                        rootPane.add(new Text("Game over! You lose!"), height/2, width/2);
                        rootPane.setAlignment(Pos.CENTER);
                        resetGame(); // reset all used variables so user can replay game, as long as they do not close the menu settings while game is playing
                        timer.cancel();
                }});
            }
        }, 2000 );
    }

    // Resets all necessary parameters of the game once a game has been won or lost
    public static void resetGame(){
        turn = 0;
        bombs = 0;
        visitedSquares = new HashSet<Square>();
        grid = null;
        rootPane = new GridPane();
    }
}