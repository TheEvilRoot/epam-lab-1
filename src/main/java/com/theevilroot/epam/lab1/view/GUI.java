package com.theevilroot.epam.lab1.view;

import com.theevilroot.epam.lab1.model.Model;
import com.theevilroot.epam.lab1.presenter.Presenter;
import com.theevilroot.epam.lab1.presenter.PresenterImpl;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class GUI extends Application implements MainView {

    private final VBox root;
    private final VBox lengthBox;
    private final VBox heightBox;
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

    private final Presenter presenter;

    public GUI() {
        this.presenter = new PresenterImpl(this, Model.defaultModel);

        this.root = new VBox();
        this.lengthBox = new VBox();
        this.heightBox = new VBox();
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
                presenter.requestArea(lengthField.getText(), heightField.getText()));

        perimeterButton.setOnMouseClicked(event ->
                presenter.requestPerimeter(lengthField.getText(), heightField.getText()));
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(this.scene);
        primaryStage.setMinWidth(280);
        primaryStage.setTitle("<epam> Lab-1");
        primaryStage.show();
    }


    @Override
    public void showError(String error) {
        errorLabel.setText(error);
    }

    @Override
    public void showAreaResult(String result) {
        areaLabel.setText(result);
    }

    @Override
    public void showPerimeterResult(String result) {
        perimeterLabel.setText(result);
    }

    @Override
    public void clearError() {
        errorLabel.setText("");
    }
}
