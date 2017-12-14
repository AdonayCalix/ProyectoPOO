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

    private static ArrayList<OfertaAcademica> clases = new ArrayList<OfertaAcademica>();
    private final static int inicioFila = 6;
    private final static int maximoColumna = 16;

    public static void leerDatos(List<File> archivos) {

        for (int i = 0; i < archivos.size(); i++) {

            try {
                int contadorFila = 0;
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

                            switch (contadorColumnas) {

                                case 0:
                                    cargaAcademica.setCodigoClase(cell.getStringCellValue());
                                    break;
                                case 1:
                                    cargaAcademica.setNombreClase(cell.getStringCellValue());
                                    break;
                                case 2:
                                    cargaAcademica.setCreditos((int) cell.getNumericCellValue());
                                    break;
                                case 3:
                                    cargaAcademica.setSeccion(cell.getStringCellValue());
                                    break;
                                case 6:
                                    cargaAcademica.setHoraInicio(cell.getStringCellValue());
                                    break;
                                case 7:
                                    cargaAcademica.setHoraFinal(cell.getStringCellValue());
                                    break;
                                case 8:
                                    cargaAcademica.setLunes(cell.getStringCellValue());
                                    break;
                                case 9:
                                    cargaAcademica.setMartes(cell.getStringCellValue());
                                    break;
                                case 10:
                                    cargaAcademica.setMiercoles(cell.getStringCellValue());
                                    break;
                                case 11:
                                    cargaAcademica.setJueves(cell.getStringCellValue());
                                    break;
                                case 12:
                                    cargaAcademica.setViernes(cell.getStringCellValue());
                                    break;
                                case 13:
                                    cargaAcademica.setSabado(cell.getStringCellValue());
                                    break;

                                case 16:
                                    cargaAcademica.setCupo((int) cell.getNumericCellValue());
                                    break;

                            }

                        }
                        contadorColumnas++;
                    }
                    if (contadorFila >= 6) {
                        clases.add(cargaAcademica);
                    }

                    contadorFila++;
                }

            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static ArrayList<OfertaAcademica> getClases() {
        return clases;
    }

}
