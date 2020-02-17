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

    private final VBox root;
    private final HBox lengthBox;
    private final HBox heightBox;
    private final HBox controlBox;

    private final Label errorLabel;
    private final Label lengthLabel;
    private final Label heightLabel;
    private final Label areaLabel;
    private final Label perimeterLabel;

    private final TextField lengthField;
    private final TextField heightField;

    private final Button areaButton;
    private final Button perimeterButton;

    private final Scene scene;

    public GUI() {
        this.root = new VBox();
        this.lengthBox = new HBox();
        this.heightBox = new HBox();
        this.controlBox = new HBox();

        this.errorLabel = new Label("");

        this.lengthLabel = new Label("Length");
        this.heightLabel = new Label("Height");

        this.lengthField = new TextField();
        this.heightField = new TextField();

        this.areaButton = new Button("Area");
        this.areaLabel = new Label("0.0");

        this.perimeterButton = new Button("Perimeter");
        this.perimeterLabel = new Label("0.0");

        this.scene = new Scene(root);

        initStyles();
        initHierarchy();
        initCallbacks();
    }

    private void initHierarchy() {
        this.lengthBox.getChildren().addAll(this.lengthLabel,
                this.lengthField);
        this.heightBox.getChildren().addAll(this.heightLabel,
                this.heightField);
        this.controlBox.getChildren().addAll(this.areaButton,
                this.areaLabel,
                this.perimeterButton,
                this.perimeterLabel);
        this.root.getChildren().addAll(this.errorLabel,
                this.lengthBox,
                this.heightBox,
                this.controlBox);
    }

    private void initStyles() {
        this.errorLabel.setTextFill(Paint.valueOf("#f00"));
        this.root.setPadding(new Insets(0, 16, 16, 16));
    }

    private void initCallbacks() {
        this.areaButton.setOnMouseClicked(event ->
                handleAction((l, h) -> l * h, this.areaLabel));

        perimeterButton.setOnMouseClicked(event ->
                handleAction((l, h) -> l * 2 + h * 2, this.perimeterLabel));
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(this.scene);
        primaryStage.setMinWidth(280);
        primaryStage.setTitle("<epam> Lab-1");
        primaryStage.show();
    }

    private interface Action {
        float action(float length, float height);
    }

    private void handleAction(Action action, Label resultLabel) {
        try {
            float length = Float.parseFloat(this.lengthField.textProperty().getValue());
            float height = Float.parseFloat(this.heightField.textProperty().getValue());

            if (length < 0 || height < 0) {
                this.errorLabel.setText("Numbers must be positive");
                return;
            }

            resultLabel.setText(String.valueOf(action.action(length, height)));

            this.errorLabel.setText("");
        } catch (Exception e) {
            this.errorLabel.setText("Invalid input");
        }
    }
}
