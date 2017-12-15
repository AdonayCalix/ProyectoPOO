package sample.Controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;
import sample.Modelos.ExportacionExcel;
import sample.Modelos.OfertaAcademica;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModificarController {
    public JFXTextField txtNombre;
    public JFXListView<String> lvClases;
    public JFXButton btnGuardar;
    public JFXTextField txtAula;
    public JFXListView lvaulas;

    private ObservableList listaClase = FXCollections.observableArrayList();
    private ObservableList listaAulas = FXCollections.observableArrayList();
    private static ArrayList<OfertaAcademica> clases = (ArrayList<OfertaAcademica>) ExportacionExcel.getClases().clone();

    private String claseSeleccionada;
    private String aulasSeleccionada;
    private String cambioAula;

    public void Volver(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Vistas/menu.fxml"));
            Scene scene = new Scene(root);
            Stage anterior = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            anterior.setScene(scene);
            anterior.show();
        } catch (IOException ex) {
            Logger.getLogger(IngresarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarCambio(ActionEvent actionEvent) {
        cambioAula = txtAula.getText();
        int opcion = actualizarRegistro(cambioAula, claseSeleccionada);
        if (opcion != 0) {
            JOptionPane.showMessageDialog(null, "Registro Actualizado");
        }

    }

    public static int actualizarRegistro(String aula, String claseSeleccionada) {
        int resultado = 0;
        String bandera = "";

        try {
            Connection con = Main.getConexion();
            Statement stmt = con.createStatement();
            String sql = "UPDATE seccion SET IdAula=" + aula + " WHERE IdClase = (SELECT IdClase FROM clase WHERE nomClase='" + claseSeleccionada + "')";
            resultado = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @FXML
    public void initialize() {
        lvClases.setItems(listaClase);
        lvaulas.setItems(listaAulas);
        lvClases.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                claseSeleccionada = newValue;
                try {
                    Connection con = Main.getConexion();
                    Statement stmt = con.createStatement();
                    String sql = "SELECT IdAula FROM seccion WHERE IdClase = (SELECT IdClase FROM clase WHERE nomClase='" + claseSeleccionada + "')";
                    ResultSet resultado = stmt.executeQuery(sql);
                    while (resultado.next()) {
                        txtAula.setText(resultado.getString("IdAula"));
                    }


                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        });



    }


    public void buscarClase(KeyEvent keyEvent) {
        listaClase.clear();
        String busqueda = txtNombre.getText().trim();
        if (busqueda.length() >= 1) {
            try {
                Connection con = Main.getConexion();
                Statement stmt = con.createStatement();
                String sql = "SELECT nomClase FROM clase WHERE nomClase LIKE'" + busqueda + "%'";
                ResultSet resultSet = stmt.executeQuery(sql);
                while (resultSet.next()) {
                    listaClase.add(resultSet.getString("nomClase"));
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


}
