package sample.Modelos;

import sample.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.Modelos.ExportacionExcel.getClases;

public class ImportacionDatos {
    private Connection con = null;


    public void insertarClases() {
        for (int i = 0; i < getClases().size(); i++) {

        }
    }


    public int asignarAula(String nombreClase) {
        int idAula = 0;
        con = Main.getConexion();

        try {
            Statement statement = con.createStatement();
            String sql = "SELECT id FROM tipo_aula WHERE tipo = " + nombreClase;
            ResultSet resultSet = statement.executeQuery(sql);
            if (!(resultSet.next())) {
                idAula = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idAula;

    }

    public boolean comprobarClasesExistente(String codigoClase) {
        boolean verificacion = true;
        con = Main.getConexion();

        try {
            Statement statement = con.createStatement();
            String sql = "SELECT IdClase FROM clase IdClase = " + codigoClase;
            ResultSet resultSet = statement.executeQuery(sql);
            if (!(resultSet.next())) {
                verificacion = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return verificacion;

    }

}
