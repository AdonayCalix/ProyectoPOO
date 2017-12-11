package sample.Modelos;

import sample.Main;

import java.sql.*;
import java.util.ArrayList;

public class ImportacionDatos {
    private ArrayList<OfertaAcademica> clases = (ArrayList<OfertaAcademica>) ExportacionExcel.getClases().clone();

    public void insertarClases() {

        String nombreClase;
        String codigoClase;

        for (int i = 0; i < clases.size(); i++) {

            nombreClase = clases.get(i).getNombreClase();
            codigoClase = clases.get(i).getCodigoClase();

            if (comprobarClasesExistente(codigoClase)) {

                try {
                    Connection con = Main.getConexion();
                    String sql = "INSERT INTO clase" + " (idClase, nomClase, creditos, IdTipo)" + "VALUES (?,?,?,?)";
                    PreparedStatement sentencia = con.prepareStatement(sql);
                    sentencia.setString(1, clases.get(i).getCodigoClase());
                    sentencia.setString(2, clases.get(i).getNombreClase());
                    sentencia.setInt(3, clases.get(i).getCreditos());
                    sentencia.setInt(4, asignarAula(nombreClase));
                    sentencia.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public int asignarAula(String nombreClase) {
        int idAula = 1;
        System.gc();
        try {
            Connection con = Main.getConexion();
            Statement statement = con.createStatement();
            String sql = "SELECT idTipo FROM tipo_aula WHERE tipo = '" + nombreClase + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.wasNull()) {
                idAula = resultSet.getInt("idTipo");
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idAula;

    }

    public boolean comprobarClasesExistente(String codigoClase) {
        boolean verificacion = false;
        try {
            Connection con = Main.getConexion();
            Statement statement = con.createStatement();
            String sql = "SELECT IdClase FROM clase WHERE IdClase = '" + codigoClase + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!(resultSet.next())) {
                verificacion = true;
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return verificacion;

    }

}
