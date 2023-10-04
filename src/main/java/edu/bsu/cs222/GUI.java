package edu.bsu.cs222;

import edu.bsu.cs222.Exceptions.*;
import edu.bsu.cs222.Model.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.InputStream;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) throws NoInputException {
        VBox vbox = new VBox();
        vbox.getChildren().add(new Label("Wikipedia API Reader"));

        HBox askingForArticleName = new HBox(new Label("Enter the Name of the article you want to search up?"));
            askingForArticleName.setSpacing(10);
            askingForArticleName.setAlignment(Pos.CENTER);
        TextField textField = new TextField();
        askingForArticleName.getChildren().add(textField);
        if(askingForArticleName.equals("")) {
//            throw new NoInputException();
            showErrorDialog(NoInputException.toString());
        }

        vbox.getChildren().add(askingForArticleName);

        Button button = new Button("Search!");
        button.setOnAction(event -> {
            try {
                WikipediaConnection connector = new WikipediaConnection(textField.getText());
                WikipediaRevisionParser parser = new WikipediaRevisionParser();

                InputStream wikipediaData = connector.callingConnectToWikipedia();
                String listOfRevisions = parser.parse(wikipediaData);
                Text printedRevisions = new Text(listOfRevisions);
                primaryStage.setScene(new Scene(new Group(printedRevisions), 600, 300));

            }
            catch (NoPageExistException exception) {
                Label errorMessage = new Label(exception.getMessage());
                    errorMessage.setFont(Font.font(20));
                    errorMessage.setLineSpacing(10);
                    errorMessage.setAlignment(Pos.CENTER);
                primaryStage.setScene(new Scene(errorMessage, 350, 100));
            }
            catch (Exception e  ) {
                showErrorDialog(e.getMessage());
            }
        });
        vbox.getChildren().add(button);

        primaryStage.setScene(new Scene(vbox));
        primaryStage.show();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
