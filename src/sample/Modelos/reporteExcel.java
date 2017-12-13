package sample.Modelos;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.*;
import jxl.write.Number;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Locale;

/**
 * Created by Josue on 12/12/2017.
 */
public class reporteExcel {


    private String db = "proyecto";
    private String user = "root";
    private String password = "";
    private String hoja = "Ciudades";
    private String carpeta = "Ficheros-Excel";
    private String documnto = "output.xls";

    private String url = "jdbc:mysql://localhost/" + db;

    private Connection conn = null;

    File file = new File("C:\\" + carpeta + "\\" + documnto + "");


    public reporteExcel() {
        this.url = "jdbc:mysql://localhost/" + this.db;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(this.url, this.user, this.password);
            if (conn != null) {
                System.out.println("Conexión a la base de datos " + this.db + "...... Listo ");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        WriteExcel();

    }


    public void WriteExcel() {
        int row = 0;
        // tipo de letra
        WritableFont wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
        WritableCellFormat cf = new WritableCellFormat(wf);


        WritableSheet excelSheet = null;
        WritableWorkbook workbook = null;

        //lenguaje
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));


        try {
            workbook = Workbook.createWorkbook(file, wbSettings);
            //nombre de la hoja de excel que se va a crear
            workbook.createSheet(hoja, 0);
            excelSheet = workbook.getSheet(0);

            JOptionPane.showMessageDialog(null, "hoja de Excel " + hoja + " creada con Exito ");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        //Consulta SQL
        String sql = "SELECT IdAula , edificio , capacidad ,estado, tipo FROM aula, tipo_aula WHERE  aula.IdTipo = tipo_aula.IdTipo";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet res = pstm.executeQuery();

            while (res.next()) {

                Label id = new Label(0, row, res.getString("IdAula"), cf);
                jxl.write.Number edificio = new Number(1, row, res.getLong("edificio"), cf);
                jxl.write.Number capacidad = new Number(2, row, res.getLong("capacidad"), cf);
                jxl.write.Number estado = new Number(3, row, res.getLong("estado"), cf);
                Label tipo = new Label(4, row, res.getString("tipo"), cf);

                row++;
                try {
                    excelSheet.addCell(id);
                    excelSheet.addCell(edificio);
                    excelSheet.addCell(capacidad);
                    excelSheet.addCell(estado);
                    excelSheet.addCell(tipo);

                } catch (WriteException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //Escribe el archivo excel
        try {
            workbook.write();
            workbook.close();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (WriteException ex) {
            System.err.println(ex.getMessage());
        }

        JOptionPane.showMessageDialog(null, "El reporte ha sido creado en la carpeta " + carpeta + " en el documento " + documnto + "");

    }


}