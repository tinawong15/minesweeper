import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.text.Text; 

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

        rootPane = new GridPane();
        rootPane.setMaxSize(height * 40,width * 40); // adjust size to fit all Squares

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

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int newTurn) {
        turn = newTurn;
    }

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

    public static void selectMines(int i, int j) {
        // reset all the mines
        for(int k = 0; k < height; k++) {
            for(int m = 0; m < width; m++) {
                grid[k][m].setIsMine(false);
            }
        }
        // System.out.println("i: "+i+" j: "+j);
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
            // System.out.println("Random Height: "+randHeight);
            // System.out.println("Random Width: "+randWidth);
            // System.out.println(i == randHeight);
            // System.out.println(j == randWidth);
            if(i != randHeight || j != randWidth ){
                for(int k = 0; k < chosenHeight.size(); k++){
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
        }

        for(int l = 0; l < chosenHeight.size(); l++){
            grid[chosenHeight.get(l)][chosenWidth.get(l)].setIsMine(true);
            System.out.println("i: "+chosenHeight.get(l)+" j: "+chosenWidth.get(l));
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
        resetGame(); // reset all used variables so user can replay game, as long as they do not close the menu settings while game is playing
    }

    public static void lose() {
        System.out.println("Game over! You lose!");
        rootPane.getChildren().clear();
        rootPane.add(new Text("Game over! You lose!"), height/2, width/2);
        rootPane.setAlignment(Pos.CENTER);
        resetGame(); // reset all used variables so user can replay game, as long as they do not close the menu settings while game is playing
    }

    public static void resetGame(){
        turn = 0;
        bombs = 0;
        visitedSquares = new HashSet<Square>();
        grid = null;
        rootPane = new GridPane();
    }
}