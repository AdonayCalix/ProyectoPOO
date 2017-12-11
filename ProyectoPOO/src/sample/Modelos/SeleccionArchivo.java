package sample.Modelos;

import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class SeleccionArchivo {

    public static List<File> seleccionarArchivos() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
        List<File> archivos = fileChooser.showOpenMultipleDialog(null);
        return archivos;
    }
}

