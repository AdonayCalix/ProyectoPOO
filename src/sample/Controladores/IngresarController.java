package sample.Controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Modelos.ExportacionExcel;
import sample.Modelos.ImportacionDatos;
import sample.Modelos.SeleccionArchivo;
import sample.Modelos.SugerenciaAula;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IngresarController {
    public ImageView imgVolver;
    public JFXButton btbUpload;
    public JFXListView listInformativo;
    public StackPane rootPane;

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

    public void escogerArchivos(ActionEvent actionEvent) {

        List<File> archivos = SeleccionArchivo.seleccionarArchivos();
        if (archivos != null) {
            for (int i = 0; i < archivos.size(); i++) {
                listInformativo.getItems().add(archivos.get(i).getPath());
            }
        }
        ExportacionExcel.leerDatos(archivos);
    }

    public void confirmacionIngreso(ActionEvent actionEvent) {
        ImportacionDatos.asignarValores();
        ImportacionDatos importar = new ImportacionDatos();
        importar.insertarClases();



//        JFXDialogLayout contenido = new JFXDialogLayout();
//        contenido.setHeading(new Text("Aviso"));
//        contenido.setBody(new Text("Información ingresada con exito a la base de datos"));
//        JFXDialog dialog = new JFXDialog(rootPane,contenido,JFXDialog.DialogTransition.CENTER);
//        JFXButton boton = new JFXButton("Seguir");
//        boton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                dialog.close();
//            }
//        });
//        contenido.setActions(boton);
//        dialog.show();
    }
}
