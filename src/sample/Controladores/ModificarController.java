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

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModificarController {
    public JFXTextField txtNombre;
    public JFXListView<String> lvClases;
    public JFXButton btnGuardar;
    public JFXTextField txtAula;

    private ObservableList listaClase = FXCollections.observableArrayList();

    private String claseSeleccionada;

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
    }

    @FXML
    public void initialize() {
        lvClases.setItems(listaClase);
        lvClases.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                claseSeleccionada = newValue;
                listaClase.clear();
                try {
                    Connection con = Main.getConexion();
                    Statement stmt = con.createStatement();
                    String sql = "SELECT IdAula FROM seccion WHERE IdClase = (SELECT IdClase FROM clase WHERE nomClase='" + claseSeleccionada + "')";
                    ResultSet resultado = stmt.executeQuery(sql);
                    while (resultado.next()) {
                        txtAula.setText(resultado.getString("IdAula"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
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
                String sql = "SELECT nomClase FROM clase WHERE nomClase LIKE'" + busqueda + "'";
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
