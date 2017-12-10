package sample.Modelos;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExportacionExcel {

    private static int contadorFila = 0;
    private final static int inicioFila = 6;
    private final static int maximoColumna = 16;

    public static void leerDatos() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        List<File> archivos = SeleccionArchivo.seleccionarArchivos();
        for (int i = 0; i < archivos.size(); i++) {
            try {
                contadorFila = 0;
                FileInputStream archivoExel = new FileInputStream(new File(String.valueOf(archivos.get(i).getAbsoluteFile())));
                XSSFWorkbook worbook = new XSSFWorkbook(archivoExel);
                XSSFSheet sheet = worbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();
                Row row;

                while (rowIterator.hasNext()) {

                    row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    Cell cell;

                    int contadorColumnas = 0;

                    OfertaAcademica cargaAcademica = new OfertaAcademica();

                    while (cellIterator.hasNext()) {

                        cell = cellIterator.next();

                        if (contadorFila >= inicioFila && contadorColumnas <= maximoColumna) {

                            if (!(contadorColumnas == 15 || contadorColumnas == 14 || contadorColumnas == 4 || contadorColumnas == 5)) {

                                switch (cell.getCellType()) {
                                    case Cell.CELL_TYPE_NUMERIC:
                                        System.out.print(cell.getNumericCellValue() + "  ");
                                        break;
                                    case Cell.CELL_TYPE_STRING:
                                        System.out.print(cell.getStringCellValue() + "  ");
                                        break;
                                }
                            }
                        }
                        contadorColumnas++;
                    }
                    if (contadorFila >= 6) {
                        System.out.println();
                    }
                    contadorFila++;
                }

                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------------------------------");

            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}
