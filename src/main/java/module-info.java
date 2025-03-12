module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;


    opens com.example.demo1 to javafx.fxml, com.google.gson;

    exports com.example.demo1;
}