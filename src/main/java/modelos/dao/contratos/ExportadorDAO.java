package modelos.dao.contratos;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface ExportadorDAO {
    public <T> void exportarColeccion(Collection<T> coleccion, String rutaArchivo) throws IOException;
    public <T> void exportarCamposSeleccionados(Collection<T> coleccion, String rutaArchivo, String... nombresCampos) throws IOException;
    public void exportarDatos(List<String[]> datos, String rutaArchivo) throws IOException;
}
