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

               try {
                   Connection con = Main.getConexion();
                   Statement statement = con.createStatement();
                   System.out.println("ljlljlj");
                   String sql = "select aula.IdAula from aula, clase, tipo_aula " +
                           "where clase.IdTipo = tipo_aula.IdTipo and tipo_aula.IdTipo = aula.IdTipo and " +
                           " aula.Capacidad between " + "" + clases.get(i).getCupo() + " and " + (clases.get(i).getCupo() + 10) + " " +
                           "and clase.IdTipo = " + "(select IdTipo from clase where clase.nomClase = '" + clases.get(i).getNombreClase() + "')" +
                           " group by aula.IdAula";
                   ResultSet resultSet = statement.executeQuery(sql);
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

//                    String query = "select * from seccion where idAula = '" + aulasRecomendadas.get(i).getIdAula() + "'" + condicionalDias(clases.get(i).getLunes(), clases.get(i).getMartes(), clases.get(i).getMiercoles(), clases.get(i).getJueves(), clases.get(i).getViernes(), clases.get(i).getSabado());
//                   resultSet = statement.executeQuery(query);
//                   if (resultSet.wasNull()) {
//
//                   }
//                   aulasRecomendadas.clear();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       }
   }

   public int duraccionClase(String horaInicio, String horaFinal) {
        String[] inicio = horaInicio.split(":");
        String[] salida = horaFinal.split(":");

        int horaInicial = Integer.parseInt(inicio[0]);
        int horaFinalizada = Integer.parseInt(salida[0]);

        return (horaFinalizada - horaInicial) + 1;
    }

    public String condicionalDias(String lunes, String martes, String miercoles, String jueves, String viernes, String sabado) {

        String concatenador = "";

        if (lunes.equals("X")) {
            concatenador = concatenador + "and Lunes = 0 ";
        }
        if (martes.equals("X")) {
            concatenador = concatenador + "and Martes = 0 ";
        }
        if (miercoles.equals("X")) {
            concatenador = concatenador + "and Miercoles = 0 ";
        }
        if (jueves.equals("X")) {
            concatenador = concatenador + "and Jueves = 0 ";
        }
        if (viernes.equals("X")) {
            concatenador = concatenador + "and Viernes = 0 ";
        }
        if (sabado.equals("X")) {
            concatenador = concatenador + "and Sabado = 0";
        }
        return concatenador;
    }

      public boolean insertarSeccion(int iterador, String idAula, int hora) {

          try {
              Connection con = Main.getConexion();
              String sql = "INSERT INTO seccion" + "(seccion, numeroSeccion, nalumnos, idAula, idPeriodo, idClase, hora, Lunes, Martes, Miercoles, Jueve, Viernes, Sabado)"
                      + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

              PreparedStatement sentencia = con.prepareStatement(sql);
              sentencia.setString(1, clases.get(iterador).getSeccion());
              sentencia.setString(2, "1");
              sentencia.setInt(3, clases.get(iterador).getCupo());
              sentencia.setString(4, idAula);
              sentencia.setString(5, "2");
              sentencia.setString(6, clases.get(iterador).getCodigoClase());
              sentencia.setInt(7, hora);
              sentencia.setString(8, clases.get(iterador).getLunes());
              sentencia.setString(9, clases.get(iterador).getMartes());
              sentencia.setString(10, clases.get(iterador).getMiercoles());
              sentencia.setString(11, clases.get(iterador).getJueves());
              sentencia.setString(12, clases.get(iterador).getViernes());
              sentencia.setString(13, clases.get(iterador).getSabado());


              sentencia.executeUpdate();
          } catch (SQLException e) {
              e.printStackTrace();
          }

          return false;
      }
}
