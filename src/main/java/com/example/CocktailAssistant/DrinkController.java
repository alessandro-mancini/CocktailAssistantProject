package com.example.CocktailAssistant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DrinkController {


    @FXML
    private AnchorPane pane;
    @FXML
    private Label titleLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label alcoholicLabel;
    @FXML
    private Label descLabel;

    @FXML
    public void showInfo(Drink drink) {

        ((Stage) pane.getScene().getWindow()).setTitle("Ricetta " + drink.getName());

        titleLabel.setText(drink.getName());
        categoryLabel.setText("Categoria: " + drink.getCategory());

        if(drink.isAlcoholic()){
            alcoholicLabel.setText("Alcolico: Sì");
        }else{
            alcoholicLabel.setText("Alcolico: No");
        }

        descLabel.setText(drink.getInstructions());

    }



}
