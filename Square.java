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
    ArrayList<Square> neighbors = new ArrayList<Square>(); // TODO

    public Square(int xcor, int ycor) {
        this.xcor = xcor;
        this.ycor = ycor;

        r.setWidth(40);
        r.setHeight(40);
        r.setStroke(Color.GRAY);
        r.setFill(Color.WHITE);
        this.getChildren().add(r);

        numberMinesAdjacent.setText("4");
        numberMinesAdjacent.setFill(Color.BLUE);
        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20);
        numberMinesAdjacent.setFont(font);
        this.getChildren().add(numberMinesAdjacent);

        button.setMinHeight(35);
        button.setMinWidth(35);

        this.getChildren().add(button);

        button.setOnAction(e -> removeButton());

    }

    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    public void removeButton() {
        // System.out.println("testing1");
        this.getChildren().remove(button);
    }

    public Button getButton() {
        return button;
    }
}