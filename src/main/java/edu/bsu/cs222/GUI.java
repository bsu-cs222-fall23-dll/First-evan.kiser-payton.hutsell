package edu.bsu.cs222;

import edu.bsu.cs222.Exceptions.*;
import edu.bsu.cs222.Model.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;

public class GUI extends Application  {
    Text printedRevisions = new Text();
    @Override
    public void start(Stage primaryStage)  {
        VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);
        vbox.getChildren().add(new Label("Wikipedia API Reader"));

        HBox askingForArticleName = new HBox(new Label("Enter the Name of the article you want to search up?"));
            askingForArticleName.setAlignment(Pos.CENTER);
            askingForArticleName.setSpacing(10);
        TextField textField = new TextField();
        askingForArticleName.getChildren().add(textField);
        vbox.getChildren().add(askingForArticleName);

        Button button = new Button("Search!");
        button.setOnAction(event -> {
            if (textField.getText().equals("")) {
                showErrorDialog("Error, You did not input anything!");
                System.exit(0);
            }
            WikipediaConnection connector = new WikipediaConnection(textField.getText());
            WikipediaRevisionParser parser = new WikipediaRevisionParser();
            try {
                InputStream wikipediaData = connector.callingConnectToWikipedia();
                String listOfRevisions = parser.parse(wikipediaData);
                printedRevisions.setText(listOfRevisions);
                primaryStage.setScene(new Scene(new Group(printedRevisions), 600, 300));

            } catch (NoPageExistException noPageExistException) {
                Label noPageExistLabel = new Label(noPageExistException.getMessage());
                    noPageExistLabel.setAlignment(Pos.CENTER);
                    noPageExistLabel.setFont(Font.font(25));
                primaryStage.setScene(new Scene(noPageExistLabel) );
            } catch (Exception exception) {
                showErrorDialog(exception.getMessage());
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
