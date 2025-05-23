package modelos.dao.implementaciones;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import modelos.dao.contratos.ExportadorDAO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportadorExcel implements ExportadorDAO {
    private final boolean esXLSX;
    
    public ExportadorExcel(boolean esXLSX) {
        this.esXLSX = esXLSX;
    }
    
    @Override
    public <T> void exportarColeccion(Collection<T> coleccion, String rutaArchivo) throws IOException {
        ExportadorCSV exportadorCSV = new ExportadorCSV();
        exportarDatos(exportadorCSV.convertirACadenas(coleccion), rutaArchivo);
    }
    
    @Override
    public <T> void exportarCamposSeleccionados(Collection<T> coleccion, String rutaArchivo, String... nombresCampos) throws IOException {
        ExportadorCSV exportadorCSV = new ExportadorCSV();
        exportarDatos(exportadorCSV.convertirCamposSeleccionados(coleccion, nombresCampos), rutaArchivo);
    }
    
    @Override
    public void exportarDatos(List<String[]> datos, String rutaArchivo) throws IOException {
        Workbook libro = esXLSX ? new XSSFWorkbook() : new HSSFWorkbook();
        Sheet hoja = libro.createSheet("Datos");
        
        for (int i = 0; i < datos.size(); i++) {
            Row fila = hoja.createRow(i);
            String[] valores = datos.get(i);
            
            for (int j = 0; j < valores.length; j++) {
                Cell celda = fila.createCell(j);
                celda.setCellValue(valores[j] != null ? valores[j] : "");
            }
        }
        
        for (int i = 0; !datos.isEmpty() && i < datos.get(0).length; i++) {
            hoja.autoSizeColumn(i);
        }
        
        try (FileOutputStream salida = new FileOutputStream(rutaArchivo)) {
            libro.write(salida);
        } finally {
            libro.close();
        }
    }
}
