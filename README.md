# JavaFX Calculator Application

## Introduction
This is a simple calculator application built using JavaFX. It supports basic arithmetic operations such as addition, subtraction, multiplication, and division, as well as additional operations like exponentiation, square root, and modulus.
Simpler two entries calculation is available in Main2.java file.  


## Instructions

### Requirements
- JDK 8 or higher
- JavaFX SDK (included in JDK 8 up to JDK 10, separate installation required for JDK 11 and later)

### Compilation
To compile the application, navigate to the root directory of the project containing the `Main.java` file and execute the following command:

`javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls Main.java`


Replace `/path/to/javafx-sdk/lib` with the path to the `lib` directory of your JavaFX SDK installation.

### Running the Application
After compiling the application, execute the following command to run it:

`java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls Main`


Replace `/path/to/javafx-sdk/lib` with the path to the `lib` directory of your JavaFX SDK installation.



## Design Choices
- **JavaFX**: JavaFX was chosen for its ease of use in creating rich graphical user interfaces (GUIs) in Java applications.
- **BorderPane Layout**: The BorderPane layout was chosen for the main UI layout, with the display area and buttons arranged in a vertical order.
- **Separate Display for Input and Result**: Two separate labels are used for displaying the input expression and the result, providing clarity to the user.
- **Encapsulation of Arithmetic Operations**: Each arithmetic operation is encapsulated within its own method for modularity and ease of maintenance.
- **Error Handling**: Error handling is implemented for division by zero and other invalid inputs, providing a better user experience.


## Algorithms Used
- **Arithmetic Operations**: Basic arithmetic operations (addition, subtraction, multiplication, division) are performed using the built-in operators of Java.
- **Exponentiation**: Exponentiation is performed using the `Math.pow()` method.
- **Square Root**: Square root is calculated using the `Math.sqrt()` method.
- **Modulus**: Modulus is calculated using the `%` operator.

### Expression Evaluation
- The `evaluateExpression()` method parses and evaluates arithmetic expressions.
- It utilizes a recursive descent parser to handle different arithmetic operations.
- The algorithm evaluates the expression from left to right, following operator precedence rules.


## Challenges Faced
- **JavaFX Setup**: Setting up the JavaFX environment and ensuring proper integration with the IDE (Integrated Development Environment) was a challenge, especially with newer versions of JDK.
- **UI Design**: Designing a user-friendly UI with proper layout and styling required some experimentation and fine-tuning.
- **Error Handling**: Implementing robust error handling for various edge cases, such as division by zero and invalid inputs, required careful consideration and testing.
