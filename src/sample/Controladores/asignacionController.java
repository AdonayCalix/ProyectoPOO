package sample.Controladores;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import sample.Modelos.SugerenciaAula;

public class asignacionController {
    public JFXListView listClases;
    public JFXTreeTableView tableAulas;


    public void asignarAula(ActionEvent actionEvent) {
        String palabra = "IF";
        SugerenciaAula.buscarAulas(palabra);
    }
}
