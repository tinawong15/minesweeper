import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Square extends StackPane {
    Button button = new Button();
    Rectangle r = new Rectangle();
    int xcor;
    int ycor;

    public Square(int xcor, int ycor) {
        this.xcor = xcor;
        this.ycor = ycor;
        r.setWidth(40);
        r.setHeight(40);
        r.setStroke(Color.GRAY);
        r.setFill(Color.WHITE);
        this.getChildren().add(r);
        button.setMinHeight(35);
		button.setMinWidth(35);
        this.getChildren().add(button);
    }
}