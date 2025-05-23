package controladores;

import io.jsondb.JsonDBTemplate;
import java.io.IOException;
import java.util.Collection;
import modelos.dao.implementaciones.ExportadorExcel;

public class ExportadorExcelControlador extends ExportadorBaseControlador {
    
    public ExportadorExcelControlador(JsonDBTemplate jsonDB) {
        super(jsonDB);
    }
    
    public <T> void exportarAXLS(Class<T> clase, String rutaArchivo) throws IOException {
        exportarTodo(clase, rutaArchivo, new ExportadorExcel(false));
    }
    
    public <T> void exportarAXLSX(Class<T> clase, String rutaArchivo) throws IOException {
        exportarTodo(clase, rutaArchivo, new ExportadorExcel(true));
    }
    
    public <T> void exportarColeccionAExcel(Collection<T> coleccion, String rutaArchivo, boolean esXLSX) throws IOException {
        new ExportadorExcel(esXLSX).exportarColeccion(coleccion, rutaArchivo);
    }
}
