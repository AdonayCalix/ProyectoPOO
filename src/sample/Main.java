package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main extends Application {

    public String CONEXION_STRING = "jdbc:mysql://127.0.0.1:3306/proyecto";
    public String USUARIO = "root";
    public String PASSWORD = "mooseladra";
    private static Connection conexion;

    public static Connection getConexion() {
        return conexion;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Vistas/menu.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Asignacion de Aulas");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void abrirConexion() {
        try {
            conexion = DriverManager.getConnection(CONEXION_STRING, USUARIO, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "La conexion no se realizo exitosamente: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
