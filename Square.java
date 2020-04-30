import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.text.Text; 
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class Square extends StackPane {
    Button button = new Button();
    Rectangle r = new Rectangle();
    int xcor;
    int ycor;
    boolean isMine;
    Text numberMinesAdjacent = new Text();
    ArrayList<Square> neighbors = new ArrayList<Square>();

    public Square(int xcor, int ycor) {
        this.xcor = xcor;
        this.ycor = ycor;

        // add a grid formatting using Rectangle objects
        r.setWidth(40);
        r.setHeight(40);
        r.setStroke(Color.GRAY);
        r.setFill(Color.WHITE);
        this.getChildren().add(r);

        // add buttons for user to click within each of the Squares
        button.setMinHeight(35);
        button.setMinWidth(35);
        this.getChildren().add(button);

        // use helper function to remove button if user clicks on it
        button.setOnAction(e -> removeButton());

    }

    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean getIsMine() {
        return isMine;
    }

    public void removeButton() {
        // System.out.println("testing1");
        this.getChildren().remove(button);
        // once button is revealed, show text
        if(isMine) { // show that Square is a mine
            numberMinesAdjacent.setText("X");
            numberMinesAdjacent.setFill(Color.CRIMSON);
        }
        else { // show how many Square neighbors of the Square are mines
            int neighborsMineCount = 0;
            for(Square neighbor : neighbors) {
                if(neighbor.getIsMine()) {
                    neighborsMineCount++;
                }
            }
            if(neighborsMineCount != 0) {
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
    }

    public Button getButton() {
        return button;
    }

    public ArrayList<Square> getNeighbors() {
        return neighbors;
    }
}