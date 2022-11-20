module todo.test.demo {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;


    opens todo.test.demo to javafx.fxml;
    exports todo.test.demo;
}