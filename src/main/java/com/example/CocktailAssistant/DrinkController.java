package com.example.CocktailAssistant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DrinkController {


    @FXML
    private ImageView image;
    @FXML
    private Label titleLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label alcoholicLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label ingredientsLabel;

    @FXML
    public void showInfo(Drink drink) {

        // crea il cerchio che conterrà l'immagine
        double size = 200;
        Circle clip = new Circle(size / 2);
        clip.setCenterX(size / 2);
        clip.setCenterY(size / 2);
        //

        image.setClip(clip); // assegna all' imageView il formato a cerchio

        image.setImage(new Image(drink.getImageURL(), 200, 200, true, true)); // crea l'immagine


        // inserisce i dati testuali del drink nelle label
        titleLabel.setText(drink.getName());

        categoryLabel.setText(drink.getCategory());

        if(drink.isAlcoholic()){
            alcoholicLabel.setText("Alcolico: Sì");
        }else{
            alcoholicLabel.setText("Alcolico: No");
        }

        ingredientsLabel.setText("Ingredienti:\n" + drink.getIngredients());

        descLabel.setText(drink.getInstructions());
        //

    }



}
