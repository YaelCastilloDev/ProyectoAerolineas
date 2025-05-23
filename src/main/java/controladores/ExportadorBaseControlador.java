package controladores;

import io.jsondb.JsonDBTemplate;
import java.io.IOException;
import java.util.Collection;
import modelos.dao.contratos.ExportadorDAO;

public abstract class ExportadorBaseControlador {
    protected JsonDBTemplate jsonDB;
    
    public ExportadorBaseControlador(JsonDBTemplate jsonDB) {
        this.jsonDB = jsonDB;
    }
    
    public <T> void exportarTodo(Class<T> clase, String rutaArchivo, ExportadorDAO exportador) throws IOException {
        Collection<T> datos = jsonDB.findAll(clase);
        exportador.exportarColeccion(datos, rutaArchivo);
    }
    
    public <T> void exportarFiltrados(Class<T> clase, String consulta, String rutaArchivo, ExportadorDAO exportador) throws IOException {
        Collection<T> datos = jsonDB.find(consulta, clase);
        exportador.exportarColeccion(datos, rutaArchivo);
    }
    
    public <T> void exportarCamposEspecificos(Class<T> clase, String rutaArchivo, ExportadorDAO exportador, String... nombresCampos) throws IOException {
        Collection<T> datos = jsonDB.findAll(clase);
        exportador.exportarCamposSeleccionados(datos, rutaArchivo, nombresCampos);
    }
}
