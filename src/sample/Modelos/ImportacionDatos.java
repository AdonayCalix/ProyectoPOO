package sample.Modelos;

import org.apache.poi.ss.formula.functions.T;
import sample.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportacionDatos {
    private static ArrayList<OfertaAcademica> clases = (ArrayList<OfertaAcademica>) ExportacionExcel.getClases().clone();

    private static ArrayList<TipoAula> informacionAulas = new ArrayList<TipoAula>();

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
                    sentencia.setInt(4, asignarAulas(nombreClase, codigoClase));
                    sentencia.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
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


    // Aqui se guardan en un array list los valores de la tabla tipo_aula para evitar hacer muchas conexiones a la DB
    public static void asignarValores() {

        try {
            Connection con = Main.getConexion();
            Statement statement = con.createStatement();
            String sql = "SELECT idTipo, tipo FROM tipo_aula";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                TipoAula aula = new TipoAula();
                aula.setIdTipo(resultSet.getInt("idTipo"));
                aula.setTipo(resultSet.getString("tipo"));
                informacionAulas.add(aula);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int asignarAulas(String nombreClase, String codigoClase) {
        int codigoAula = 1;

        for (int i = 0; i < informacionAulas.size(); i++) {
            Pattern pattern = Pattern.compile(informacionAulas.get(i).getTipo(), Pattern.DOTALL);
            Matcher matcher = pattern.matcher(nombreClase);
            if (matcher.find()) {
                return informacionAulas.get(i).getIdTipo();
            }
        }

        if (aulaICC(codigoClase)) {
            codigoAula = 8;
        }

        return codigoAula;
    }


    public boolean aulaICC(String codigoClase) {
        Pattern pattern = Pattern.compile("^IF");
        Matcher matcher = pattern.matcher(codigoClase);
        return matcher.find();
    }

}
