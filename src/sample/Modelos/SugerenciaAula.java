package sample.Modelos;

import sample.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SugerenciaAula {
    private static ArrayList<Aula> aulasRecomendadas = new ArrayList<Aula>();

    private static ArrayList<OfertaAcademica> clases = (ArrayList<OfertaAcademica>) ExportacionExcel.getClases().clone();

    public static void buscarAulas(String codigoFacultad) {

        for (int i = 0; i < clases.size(); i++) {
            Pattern pattern = Pattern.compile("^" + codigoFacultad);
            Matcher matcher = pattern.matcher(clases.get(i).getCodigoClase());

            if (matcher.find()) {
                System.out.println("Entre aqui");

                try {
                    Connection con = Main.getConexion();
                    Statement statement = con.createStatement();
                    String sql = "select aula.IdAula from aula, clase, tipo_aula " +
                            "where clase.IdTipo = tipo_aula.IdTipo and tipo_aula.IdTipo = aula.IdTipo and " +
                            " aula.Capacidad between " + "" + clases.get(i).getCupo() + " and " + (clases.get(i).getCupo() + 10) + " " +
                            "and clase.IdTipo = " + "(select IdTipo from clase where clase.nomClase = '" + clases.get(i).getNombreClase() + "')" +
                            " group by aula.IdAula";
                    ResultSet resultSet = statement.executeQuery(sql);
                    System.out.println("ljlljlj");
                    while (resultSet.next()) {
                        Aula listadoAulas = new Aula();
                        listadoAulas.setIdAula(resultSet.getString("IdAula"));
                        listadoAulas.setCapacidad(resultSet.getInt("capacidad"));
                        aulasRecomendadas.add(listadoAulas);
                    }

                    for (int j = 0; j < aulasRecomendadas.size(); j++) {
                        System.out.println("HOLA MUNDO");
                    }
                    System.out.println("-----------------------------------");

                    //*String query = "select * from seccion where idAula = '" + aulasRecomendadas.get(i).getIdAula() + "'" + condicionalDias(clases.get(i).getLunes(), clases.get(i).getMartes(), clases.get(i).getMiercoles(), clases.get(i).getJueves(), clases.get(i).getViernes(), clases.get(i).getSabado());
                    //resultSet = statement.executeQuery(query);
                    //  if (resultSet.wasNull()) {

                    //}*//*
                    aulasRecomendadas.clear();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
