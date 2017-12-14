package sample.Controladores;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
<<<<<<< Updated upstream
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.Modelos.Aula;
import sample.Modelos.ExportacionExcel;
import sample.Modelos.OfertaAcademica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
=======
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Modelos.SugerenciaAula;
>>>>>>> Stashed changes

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class asignacionController {
    public JFXListView listClases;
    public JFXTreeTableView tableAulas;
    private static ArrayList<OfertaAcademica> clases = (ArrayList<OfertaAcademica>) ExportacionExcel.getClases().clone();


    public void asignarAula(ActionEvent actionEvent) throws SQLException {
        for (int i = 0; i < clases.size(); i++) {
            Connection con = Main.getConexion();
            System.out.println("haber");
            System.gc();
            Statement statement = con.createStatement();
            System.out.println("ujum");
            String sql = "select aula.IdAula from aula, clase, tipo_aula " +
                    "where clase.IdTipo = tipo_aula.IdTipo and tipo_aula.IdTipo = aula.IdTipo and " +
                    " aula.Capacidad between " + "" + clases.get(i).getCupo() + " and " + (clases.get(i).getCupo() + 10) + " " +
                    "and clase.IdTipo = " + "(select IdTipo from clase where clase.nomClase = '" + clases.get(i).getNombreClase() + "')" +
                    " group by aula.IdAula";
            System.out.println("llklkl");
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("lklklkl");
            System.out.println("ljlljlj");
            while (resultSet.next()) {
                Aula listadoAulas = new Aula();
                listadoAulas.setIdAula(resultSet.getString("IdAula"));
                listadoAulas.setCapacidad(resultSet.getInt("capacidad"));
                List<Aula> aulasRecomendadas = null;
                aulasRecomendadas.add(listadoAulas);
            }
        }

    }

    public void Volver(MouseEvent mouseEvent) {
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
