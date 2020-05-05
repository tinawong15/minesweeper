/*
 * Tina Wong & Ray Onishi
 * CSE160
 * Section 1
 * Final Project - Minesweeper
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.geometry.*;

// RUN THIS CLASS TO START THE GAME 
public class Main extends Application {

    Game game;
    Square[][] grid;

    @Override
    public void start(Stage primaryStage) {
        // Create a pane and set its properties
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        // ADD MENU DETAILS:

        // Place nodes in the pane at positions column,row
        pane.add(new Label("Height"), 1, 0);
        pane.add(new Label("Width"), 2, 0);
        pane.add(new Label("Bombs"), 3, 0);
        
        // Place buttons for user to select which level
        Button beginner = new Button("Beginner");
        Button intermediate = new Button("Intermediate");
        Button expert = new Button("Expert");
        Button custom = new Button("Custom");
        pane.add(beginner, 0, 1);
        pane.add(intermediate, 0, 2);
        pane.add(expert, 0, 3);
        pane.add(custom, 0, 4);
        pane.add(new Label("9"), 1, 1);
        pane.add(new Label("9"), 2, 1);
        pane.add(new Label("10"), 3, 1);
        pane.add(new Label("16"), 1, 2);
        pane.add(new Label("16"), 2, 2);
        pane.add(new Label("40"), 3, 2);
        pane.add(new Label("16"), 1, 3);
        pane.add(new Label("30"), 2, 3);
        pane.add(new Label("99"), 3, 3);

        // Allow user to choose grid size by providing text fields
        TextField tfHeight = new TextField();
        tfHeight.setMaxWidth(75);
        TextField tfWidth = new TextField();
        tfWidth.setMaxWidth(75);
        TextField tfBombs = new TextField();
        tfBombs.setMaxWidth(75);
        pane.add(tfHeight, 1, 4);
        pane.add(tfWidth, 2, 4);
        pane.add(tfBombs, 3, 4);

        Label directions = new Label("For custom: fill in fields then press \"Custom\" button.");
        pane.add(directions, 0,5,4,1);
        GridPane.setHalignment(directions, HPos.LEFT);

        // Add new stage so new window with the game can pop up after user selects which level
        Stage secondStage = new Stage();
        beginner.setOnAction(e -> {
            Game.resetGame();
            game = new Game(9, 9, 10);
            Scene secondScene = new Scene(Game.getRootPane(), Game.getWidth() * 40 +10, Game.getHeight() * 40 +10); // added a few pixels to adjust for margins
            secondStage.setScene(secondScene);
            secondStage.setX(primaryStage.getX() + 250);
            secondStage.setY(primaryStage.getY() + 100);
            secondStage.setTitle("Minesweeper");
            secondStage.show();
        });

        intermediate.setOnAction(e -> {
            Game.resetGame();
            game = new Game(16, 16, 40);
            Scene secondScene = new Scene(Game.getRootPane(), Game.getWidth() * 40 +20, Game.getHeight() * 40 +20);
            secondStage.setScene(secondScene);
            secondStage.setX(primaryStage.getX() + 200);
            secondStage.setY(primaryStage.getY() + 100);
            secondStage.setTitle("Minesweeper");
            secondStage.show();
        });

        expert.setOnAction(e -> {
            Game.resetGame();
            game = new Game(16, 30, 99);
            Scene secondScene = new Scene(Game.getRootPane(), Game.getWidth() * 40 +20, Game.getHeight() * 40 +20);
            secondStage.setScene(secondScene);
            secondStage.setX(primaryStage.getX() + 250);
            secondStage.setY(primaryStage.getY() + 100);
            secondStage.setTitle("Minesweeper");
            secondStage.show();
        });

        custom.setOnAction(e -> {
            try {
                int height = Integer.parseInt(tfHeight.getText());
                int width = Integer.parseInt(tfWidth.getText());
                int bombs = Integer.parseInt(tfBombs.getText());
                if(bombs >= 0 && bombs < height * width) {
                    Game.resetGame();
                    game = new Game(height, width, bombs);
                    Scene secondScene = new Scene(Game.getRootPane(), Game.getWidth() * 40 +10, Game.getHeight() * 40 +10);
                    secondStage.setScene(secondScene);
                    secondStage.setX(primaryStage.getX() + 250);
                    secondStage.setY(primaryStage.getY() + 100);
                    secondStage.setTitle("Minesweeper");
                    secondStage.show();
                }
                else {
                    System.out.println("Number of bombs must be less than height * width and greater than or equal to 0. Please try again.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Only integers are accepted. Please enter an integer for each of the fields.");
            } catch (NegativeArraySizeException ex) {
                System.out.println("Only positive integers are accepted. Please try again.");
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 350, 200);
        primaryStage.setTitle("Game Settings");
        primaryStage.setScene(scene); primaryStage.show(); 
    }
    public static void main(String[] args) {
        launch(args);
    }
} 