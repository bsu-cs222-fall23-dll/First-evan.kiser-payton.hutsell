package edu.bsu.cs222;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class GUI extends Application {
    Text printedRevisions = new Text();
    @Override
    public void start(Stage primaryStage) throws IOException {
        VBox vbox = new VBox();
        vbox.getChildren().add(new Label("Wikipedia API Reader"));

        HBox askingForArticleName = new HBox(new Label("Enter the Name of the article you want to search up?"));
        TextField textField = new TextField();
        askingForArticleName.getChildren().add(textField);
        vbox.getChildren().add(askingForArticleName);

        Button button = new Button("Search!");
        button.setOnAction(event -> {
            WikipediaConnection connector = new WikipediaConnection(textField.getText());
            WikipediaRevisionParser parser = new WikipediaRevisionParser();
            try {
                InputStream wikipediaData = connector.callingConnectToWikipedia();
                String listOfRevisions = parser.parse(wikipediaData);
                printedRevisions.setText(listOfRevisions);
                primaryStage.setScene(new Scene(new Group(printedRevisions), 600, 300));

            } catch (IOException e) {
                System.err.println("Runtime Error: " + e.getMessage());
            }
        });
        vbox.getChildren().add(button);

        primaryStage.setScene(new Scene(vbox));
        primaryStage.show();

    }

}
