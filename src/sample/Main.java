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
//489, 328

public class Main extends Application {

<<<<<<< HEAD
    public String CONEXION_STRING = "jdbc:mysql://127.0.0.1:3306/proyecto";
    public String USUARIO = "root";
    public String PASSWORD = "";
=======
>>>>>>> 36edd0ec354e8cf668e306208692f2d45bbc932b
    private static Connection conexion;



    @Override
    public void start(Stage primaryStage) throws Exception {
        abrirConexion();
        Parent root = FXMLLoader.load(getClass().getResource("Vistas/pantallaDos.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 459, 358));
        primaryStage.show();
    }

    public static void abrirConexion() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proyectopoo","root","");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "La conexion no se realizo exitosamente: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Conexion exitosa");

    }

    public static Connection getConexion() {
        return conexion;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
