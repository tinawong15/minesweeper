/*
 * Tina Wong & Ray Onishi
 * CSE160
 * Section 1
 * Final Project - Minesweeper
 */

import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.text.Text; 
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class Square extends StackPane {
    Button button = new Button();
    Rectangle r = new Rectangle();
    
    private int xcor;
    private int ycor;
    private boolean isMine;
    private boolean visited = false;

    Text numberMinesAdjacent = new Text();
    private ArrayList<Square> neighbors = new ArrayList<Square>();

    public Square(int xcor, int ycor) {
        this.xcor = xcor;
        this.ycor = ycor;

        // Add a grid formatting using Rectangle objects
        r.setWidth(40);
        r.setHeight(40);
        r.setStroke(Color.GRAY);
        r.setFill(Color.WHITE);
        this.getChildren().add(r);

        // Add buttons for user to click within each of the Squares
        button.setMinHeight(35);
        button.setMinWidth(35);
        this.getChildren().add(button);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    // Use helper function to remove button if user clicks on it
                    removeButton();
                }
                else { // User double clicked on button
                    // Mark the square as containing a mine
                    button.setText("F");
                    button.setTextFill(Color.CRIMSON);
                }
            }
        });

    }

    // Setter method for isMine
    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    // Getter method for isMine
    public boolean getIsMine() {
        return isMine;
    }
    
    // Getter method for visited
    public boolean getVisited() {
        return visited;
    }

    // Getter method for x coordinate
    public int getXcor() {
        return xcor;
    }

    // Getter method for y coordinate
    public int getYcor() {
        return ycor;
    }

    // Removes the button to reveal the mine and handles win/loss conditions
    public void removeButton() {
        Game.setTurn(Game.getTurn() + 1);
        // User is clicking a Square for the first time
        if(Game.getTurn() == 1) {
            // Check so that first button a user clicks cannot be a mine
            if(isMine) { 
                Game.selectMines(xcor, ycor);
            }
        }
        if(!visited){
            // Removes the button to reveal the text underneath
            this.getChildren().remove(button);
            // If the square is a mine, set the Square to display as mine and end game as loss
            if(isMine) { 
                visited = true;
                Game.lose();
                numberMinesAdjacent.setText("X");
                numberMinesAdjacent.setFill(Color.CRIMSON);
            }
            // Sets the number of Square neighbors that are mines
            else {
                visited = true;
                Game.getVisitedSquares().add(this);
                int neighborsMineCount = 0;
                for(Square neighbor : neighbors) {
                    if(neighbor.getIsMine()) {
                        neighborsMineCount++;
                    }
                }
                if(neighborsMineCount == 0){
                    for(Square neighbor:neighbors){
                        neighbor.removeButton();
                    }
                }
                else{
                    numberMinesAdjacent.setText(Integer.toString(neighborsMineCount));
                    if(neighborsMineCount == 1) {
                        numberMinesAdjacent.setFill(Color.BLUE);
                    }
                    else if(neighborsMineCount == 2) {
                        numberMinesAdjacent.setFill(Color.GREEN);
                    }
                    else if(neighborsMineCount == 3) {
                        numberMinesAdjacent.setFill(Color.RED);
                    }
                    else if(neighborsMineCount == 4) {
                        numberMinesAdjacent.setFill(Color.DARKSLATEBLUE);
                    }
                    else if(neighborsMineCount == 5) {
                        numberMinesAdjacent.setFill(Color.DARKRED);
                    }
                    else if(neighborsMineCount == 6) {
                        numberMinesAdjacent.setFill(Color.PURPLE);
                    }
                    else if(neighborsMineCount == 7) {
                        numberMinesAdjacent.setFill(Color.SPRINGGREEN);
                    }
                    else { // neighborsMineCount == 8
                        numberMinesAdjacent.setFill(Color.DARKRED);
                    }
                }
            }
            Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20);
            numberMinesAdjacent.setFont(font);
            this.getChildren().add(numberMinesAdjacent);

            // Win condition (number of squares unopened == number of squares that are bombs/mines)
            if(Game.getHeight() * Game.getWidth() - Game.getVisitedSquares().size() == Game.getBombs()) {
                Game.win();
            }
        }
    }

    // Getter method for the button
    public Button getButton() {
        return button;
    }

    // Getter method for the neighbors
    public ArrayList<Square> getNeighbors() {
        return neighbors;
    }
}