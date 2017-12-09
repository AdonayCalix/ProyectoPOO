package sample.Controladores;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import sample.Modelos.SeleccionArchivo;

import java.io.File;
import java.util.List;

public class Controller {

    public Button btnAceptar;
    public Button btbUpload;
    public ListView listInformativo;

    public void escogerArchivos(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
        List<File> archivos = fileChooser.showOpenMultipleDialog(null);
        if (archivos != null) {
            for (int i = 0; i < archivos.size(); i++) {
                listInformativo.getItems().addAll(archivos.get(i).getName());
            }
        }

        btnAceptar.setVisible(true);
    }

    public void bye(ActionEvent actionEvent) {
        System.exit(0);
    }
}
