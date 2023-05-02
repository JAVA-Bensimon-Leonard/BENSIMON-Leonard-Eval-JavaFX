package com.example.bensimonleonardevaljavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class App extends Application {
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Menu");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(0);
        grid.setHgap(10);
        StackPane layout = new StackPane();
        Scene scene2 = new Scene(layout, 500, 500);

        Button consult = new Button("Consulter les données");
        consult.setOnAction(e -> window.setScene(scene2));
        GridPane.setConstraints(consult,0,0);
        Label data = new Label(api());
        layout.getChildren().add(data);

        Button telec = new Button("Télécharger le fichier");
        telec.setOnAction(e -> print());
        GridPane.setConstraints(telec,1,0);

        Button meteo = new Button("Mettre la météo à jour");
        GridPane.setConstraints(meteo,0,1);

        Button bonus = new Button("Bonus");
        GridPane.setConstraints(bonus,1,1);

        grid.getChildren().addAll(consult, telec, meteo, bonus);

        Scene scene = new Scene(grid, 350, 200);
        window.setScene(scene);

        window.show();

    }

    public static void print() {
        String content = api();

        File myObj = new File("src/meteo-" + "lyon" + ".json"); // Specify the filename

        try {
            FileWriter myWriter = new FileWriter(myObj);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String api() {
        try {
            URL url = new URL("https://api.weatherbit.io/v2.0/current?key=e5545a3a54ea431c8d5ca9eab312d06f&city=lyon");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            conn.disconnect();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static void main(String[] args) {
        launch(args);
    }
}