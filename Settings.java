import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.geometry.*;
public class Settings extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a pane and set its properties
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        // Place nodes in the pane at positions column,row
        pane.add(new Label("Height"), 1, 0);
        pane.add(new Label("Width"), 2, 0);
        pane.add(new Label("Bombs"), 3, 0);

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
        TextField tfHeight = new TextField();
        tfHeight.setMaxWidth(75);
        TextField tfWidth = new TextField();
        tfWidth.setMaxWidth(75);
        TextField tfBombs = new TextField();
        tfBombs.setMaxWidth(75);
        pane.add(tfHeight, 1, 4);
        pane.add(tfWidth, 2, 4);
        pane.add(tfBombs, 3, 4);
        // pane.add(new Label("MI:"), 0, 1);
        // pane.add(new TextField(), 1, 1);
        // pane.add(new Label("Last Name:"), 0, 2);
        // pane.add(new TextField(), 1, 2);
        // Button btAdd = new Button("Add Name");
        // pane.add(btAdd, 1, 3);
        Label directions = new Label("For custom: fill in fields then press \"Custom\" button.");
        pane.add(directions, 0,5,4,1);
        GridPane.setHalignment(directions, HPos.LEFT);

        beginner.setOnAction(e -> {
            Game game = new Game(9, 9, 10);
            primaryStage.getScene().setRoot(game.getRootPane());
        });

        intermediate.setOnAction(e -> {
            Game game = new Game(16, 16, 40);
            primaryStage.getScene().setRoot(game.getRootPane());
        });

        expert.setOnAction(e -> {
            Game game = new Game(16, 30, 99);
            primaryStage.getScene().setRoot(game.getRootPane());
        });

        custom.setOnAction(e -> {
            int height = Integer.parseInt(tfHeight.getText());
            int width = Integer.parseInt(tfWidth.getText());
            int bombs = Integer.parseInt(tfBombs.getText());
            Game game = new Game(height, width, bombs);
            primaryStage.getScene().setRoot(game.getRootPane());
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