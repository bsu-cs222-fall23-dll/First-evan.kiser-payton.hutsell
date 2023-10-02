package edu.bsu.cs222;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox();
        vbox.getChildren().add(new Label("Wikipedia API Reader"));

        HBox articleName = new HBox(new Label("Enter the Name of the article you want to search up?"));
        TextField textField = new TextField();
        articleName.getChildren().add(textField);
        vbox.getChildren().add(articleName);

        Button button = new Button("Search!");
        button.setOnAction(event -> {
            WikipediaConnection connector = new WikipediaConnection(textField.getText());
            WikipediaRevisionParser parser = new WikipediaRevisionParser();
            RevisionPrinter printer = new RevisionPrinter();
            try {
                InputStream wikipediaData = connector.callingConnectToWikipedia();
                JSONArray listAllRevisions = parser.parse(wikipediaData);
                printer.printListAllRevisions(listAllRevisions);
            } catch (IOException e) {
                System.err.println("Runtime Error: " + e.getMessage());
            }        });
        vbox.getChildren().add(button);

        primaryStage.setScene(new Scene(vbox));
        primaryStage.show();

    }
}
