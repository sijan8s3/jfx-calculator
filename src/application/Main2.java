package application;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main2 extends Application {

    private final SimpleStringProperty currentInput = new SimpleStringProperty("");
    private final SimpleStringProperty displayText = new SimpleStringProperty("");
    private Label displayLabel;
    private Label resultLabel;
    private double result = 0;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = createRoot();
        Scene scene = new Scene(root, 400, 500);
        setupStage(primaryStage, scene);
        primaryStage.show();
    }

    private BorderPane createRoot() {
        BorderPane root = new BorderPane();
        VBox vbox = new VBox(createDisplay(), createButtonGrid());
        root.setCenter(vbox);
        return root;
    }

    private VBox createDisplay() {
        VBox displayBox = new VBox();

        displayLabel = new Label();
        displayLabel.setStyle("-fx-background-color: white; -fx-font-size: 24; -fx-padding: 10;");
        displayLabel.setMinHeight(50);
        displayLabel.setAlignment(Pos.CENTER_RIGHT);
        displayLabel.textProperty().bind(displayText);

        resultLabel = new Label();
        resultLabel.setStyle("-fx-background-color: white; -fx-font-size: 24; -fx-padding: 10;");
        resultLabel.setMinHeight(50);
        resultLabel.setAlignment(Pos.CENTER_LEFT);

        displayBox.getChildren().addAll(resultLabel, displayLabel);
        return displayBox;
    }

    private GridPane createButtonGrid() {
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setPadding(new Insets(10));
        buttonGrid.setVgap(5);
        buttonGrid.setHgap(5);

        String[][] buttonLabels = {
                {"7", "8", "9", "/", "C"},
                {"4", "5", "6", "*", "^"},
                {"1", "2", "3", "-", "√"},
                {"0", ".", "=", "+", "%"}
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Button button = createButton(buttonLabels[i][j]);
                buttonGrid.add(button, j, i);
            }
        }

        return buttonGrid;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMinSize(60, 60);
        button.setStyle("-fx-font-size: 18;");
        button.getStyleClass().add("button"); // Add style class for clicked animation
        if (text.equals("=")) {
            button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 18;");
        } else if (text.equals("C")) {
            button.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white; -fx-font-size: 18;");
        } else {
            button.setStyle("-fx-background-color: #f0f0f0; -fx-font-size: 18;");
        }

        // Add mouse pressed and released event handlers for clicked effect
        button.setOnMousePressed(e -> {
            button.setStyle("-fx-background-color: #d9d9d9; -fx-font-size: 18;");
        });
        button.setOnMouseReleased(e -> {
            if (text.equals("=")) {
                button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 18;");
            } else if (text.equals("C")) {
                button.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white; -fx-font-size: 18;");
            } else {
                button.setStyle("-fx-background-color: #f0f0f0; -fx-font-size: 18;");
            }
        });

        button.setOnAction(e -> {
            handleButtonAction(text);
            button.requestFocus(); // Ensure button retains focus after click
        });
        return button;
    }

    private void setupStage(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Calculator");
    }

    private void handleButtonAction(String input) {
        if (input.equals("=")) {
            calculateResult();
        } else if (input.equals("C")) {
            clearInput();
        } else {
            appendInput(input);
            displayText.set(currentInput.get()); // Update display text with current input
        }
    }

    private void clearInput() {
        currentInput.set("");
        displayText.set(currentInput.get());
        resultLabel.setText("");
    }

    private void appendInput(String input) {
        currentInput.set(currentInput.get() + input);
    }

    private void calculateResult() {
        String input = currentInput.get();
        try {
            result = evaluateExpression(input);
            displayText.set(currentInput.get()); // Set display text to current input
            currentInput.set(""); // Clear current input
            resultLabel.setText(Double.toString(result));
        } catch (ArithmeticException | IllegalArgumentException e) {
            displayText.set("Error");
            currentInput.set("");
            resultLabel.setText("");
        }
    }

    private double evaluateExpression(String expression) {
        double x;
        double y;

        // Split the expression into operands and operator
        String[] parts = expression.split("[^0-9\\.]+");
        x = Double.parseDouble(parts[0]);
        y = Double.parseDouble(parts[1]);

        // Get the operator
        char operator = expression.replaceAll("[0-9\\.]+", "").charAt(0);

        switch (operator) {
            case '+':
                return add(x, y);
            case '-':
                return subtract(x, y);
            case '*':
                return multiply(x, y);
            case '/':
                return divide(x, y);
            case '^':
                return exponentiate(x, y);
            case '√':
                return squareRoot(x);
            case '%':
                return modulus(x, y);
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    private double add(double x, double y) {
        return x + y;
    }

    private double subtract(double x, double y) {
        return x - y;
    }

    private double multiply(double x, double y) {
        return x * y;
    }

    private double divide(double x, double y) {
        if (y == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return x / y;
    }

    private double exponentiate(double x, double y) {
        return Math.pow(x, y);
    }

    private double squareRoot(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Square root of negative number");
        }
        return Math.sqrt(x);
    }

    private double modulus(double x, double y) {
        if (y == 0) {
            throw new ArithmeticException("Modulus by zero");
        }
        return x % y;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
