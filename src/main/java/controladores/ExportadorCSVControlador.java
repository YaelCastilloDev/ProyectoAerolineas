package controladores;

import io.jsondb.JsonDBTemplate;
import java.io.IOException;
import java.util.Collection;
import modelos.dao.implementaciones.ExportadorCSV;

public class ExportadorCSVControlador extends ExportadorBaseControlador {
    private final ExportadorCSV exportadorCSV;
    
    public ExportadorCSVControlador(JsonDBTemplate jsonDB) {
        super(jsonDB);
        this.exportadorCSV = new ExportadorCSV();
    }

    public ExportadorCSV getExportadorCSV() {
        return exportadorCSV;
    }
    
    public <T> void exportarACSV(Class<T> clase, String rutaArchivo, boolean incluirEncabezados) throws IOException {
        Collection<T> datos = jsonDB.findAll(clase);
        
        if (incluirEncabezados) {
            exportadorCSV.exportarColeccion(datos, rutaArchivo);
        } else {
            exportadorCSV.exportarColeccion(datos, rutaArchivo);
        }
    }
    
    public <T> void exportarColeccionACSV(Collection<T> coleccion, String rutaArchivo) throws IOException {
        exportadorCSV.exportarColeccion(coleccion, rutaArchivo);
    }
}
