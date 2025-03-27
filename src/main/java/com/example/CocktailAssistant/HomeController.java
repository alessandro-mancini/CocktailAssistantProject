package com.example.CocktailAssistant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

public class HomeController {

    @FXML
    private ImageView startImage;
    @FXML
    private ImageView leftImage;
    @FXML
    private ImageView rightImage;

    @FXML
    private void initialize(){
        startImage.setMouseTransparent(true);
        startImage.setImage(new Image(Objects.requireNonNull(getClass().getResource("/img/Start.png")).toExternalForm(),500,500,true,true));
        leftImage.setImage(new Image(Objects.requireNonNull(getClass().getResource("/img/Ichnusa.png")).toExternalForm(),163,500,true,true));
        rightImage.setImage(new Image(Objects.requireNonNull(getClass().getResource("/img/Gin.png")).toExternalForm(),163,500,true,true));
    }

    @FXML
    private void start(){
        try{
            Stage stage = (Stage) startImage.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(CocktailAssistantApp.class.getResource("list.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setResizable(false);

            stage.show();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}
