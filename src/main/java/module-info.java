module com.example.CocktailAssistant {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;


    opens com.example.CocktailAssistant to javafx.fxml, com.google.gson;

    exports com.example.CocktailAssistant;
}