package sample.Controladores;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Modelos.SugerenciaAula;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class asignacionController {
    public JFXListView listClases;
    public JFXTreeTableView tableAulas;


    public void asignarAula(ActionEvent actionEvent) {
        String palabra = "IF";
        SugerenciaAula.buscarAulas(palabra);
    }

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
}
