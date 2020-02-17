import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        HBox lengthBox = new HBox();
        HBox heightBox = new HBox();
        HBox controlBox = new HBox();

        Label errorLabel = new Label("");

        Label lengthLabel = new Label("Length");
        Label heightLabel = new Label("Height");

        TextField lengthField = new TextField();
        TextField heightField = new TextField();

        Button areaButton = new Button("Area");
        Label areaLabel = new Label("0.0");

        Button perimeterButton = new Button("Perimeter");
        Label perimeterLabel = new Label("0.0");

        lengthBox.getChildren().addAll(lengthLabel, lengthField);
        heightBox.getChildren().addAll(heightLabel, heightField);

        controlBox.getChildren().addAll(areaButton, areaLabel, perimeterButton, perimeterLabel);
        root.getChildren().addAll(errorLabel, lengthBox, heightBox, controlBox);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        areaButton.setOnMouseClicked(event -> {
            handleAction((l, h) -> l * h, lengthField, heightField, errorLabel, areaLabel);
        });

        perimeterButton.setOnMouseClicked(event -> {
            handleAction((l, h) -> l * 2 + h * 2, lengthField, heightField, errorLabel, perimeterLabel);
        });

        errorLabel.setTextFill(Paint.valueOf("#f00"));

        root.setPadding(new Insets(0, 16, 16, 16));
        root.setSpacing(10);
        lengthBox.setSpacing(10);
        heightBox.setSpacing(10);
        controlBox.setSpacing(4);


        primaryStage.setMinWidth(280);
        primaryStage.setTitle("EPAM Lab-1");
        primaryStage.show();
    }

    interface Action {
        float action(float length, float height);
    }

    private void handleAction(Action action, TextField lengthField, TextField heightField, Label errorLabel, Label resultLabel) {
        try {
            float length = Float.parseFloat(lengthField.textProperty().getValue());
            float height = Float.parseFloat(heightField.textProperty().getValue());

            if (length < 0 || height < 0) {
                errorLabel.setText("Numbers must be positive");
                return;
            }

            resultLabel.setText(String.valueOf(action.action(length, height)));
            errorLabel.setText("");
        } catch (Exception e) {
            errorLabel.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        Application.launch(GUI.class);
    }
}
