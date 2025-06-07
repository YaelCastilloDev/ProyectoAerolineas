package controladores;

import io.jsondb.JsonDBTemplate;
import java.io.IOException;
import java.util.Collection;
import modelos.dao.implementaciones.ExportadorPDF;

public class ExportadorPDFControlador extends ExportadorBaseControlador {
    
    private final ExportadorPDF exportadorPDF;
    
    public ExportadorPDFControlador(JsonDBTemplate jsonDB) {
        super(jsonDB);
        this.exportadorPDF = new ExportadorPDF();
    }
    
    public <T> void exportarAPDF(Class<T> clase, String rutaArchivo) throws IOException {
        exportarTodo(clase, rutaArchivo, exportadorPDF);
    }
    
    public <T> void exportarFiltradosAPDF(Class<T> clase, String consulta, String rutaArchivo) throws IOException {
        exportarFiltrados(clase, consulta, rutaArchivo, exportadorPDF);
    }
    
    public <T> void exportarCamposEspecificosAPDF(Class<T> clase, String rutaArchivo, String... nombresCampos) throws IOException {
        exportarCamposEspecificos(clase, rutaArchivo, exportadorPDF, nombresCampos);
    }
    
    public <T> void exportarColeccionAPDF(Collection<T> coleccion, String rutaArchivo) throws IOException {
        exportadorPDF.exportarColeccion(coleccion, rutaArchivo);
    }
    
    public ExportadorPDF getExportadorPDF() {
        return exportadorPDF;
    }
}