package sample.Controladores;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import sample.Modelos.ExportacionExcel;

public class Controller {

    public Button btnAceptar;
    public Button btbUpload;
    public ListView listInformativo;

    public void escogerArchivos(ActionEvent actionEvent) {
        ExportacionExcel.leerDatos();
    }

    public void bye(ActionEvent actionEvent) {
        System.exit(0);
    }
}
