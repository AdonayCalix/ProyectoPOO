package sample.Controladores;

import javafx.event.ActionEvent;
import sample.Modelos.reporteExcel;

/**
 * Created by Josue on 12/12/2017.
 */
public class reporteController {


    public void WriteExcel(ActionEvent actionEvent) {
        new reporteExcel();
    }
}
