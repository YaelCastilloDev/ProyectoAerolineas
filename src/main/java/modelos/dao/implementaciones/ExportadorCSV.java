package modelos.dao.implementaciones;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import modelos.dao.contratos.ExportadorDAO;

public class ExportadorCSV implements ExportadorDAO {

    public ExportadorCSV() {}

    @Override
    public <T> void exportarColeccion(Collection<T> coleccion, String rutaArchivo) throws IOException {
        validarColeccion(coleccion);
        exportarDatos(convertirACadenas(coleccion), rutaArchivo);
    }

    @Override
    public <T> void exportarCamposSeleccionados(Collection<T> coleccion, String rutaArchivo, String... nombresCampos) throws IOException {
        validarColeccion(coleccion);
        exportarDatos(convertirCamposSeleccionados(coleccion, nombresCampos), rutaArchivo);
    }

    @Override
    public void exportarDatos(List<String[]> datos, String rutaArchivo) throws IOException {
        try (CSVWriter escritor = new CSVWriter(new FileWriter(rutaArchivo))) {
            escritor.writeAll(datos);
        }
    }
    
    public <T> List<String[]> convertirACadenas(Collection<T> coleccion) {
        if (coleccion.isEmpty()) {
            return new ArrayList<>();
        }
        T primerObjeto = coleccion.iterator().next();
        Field[] campos = primerObjeto.getClass().getDeclaredFields();
        List<String[]> resultado = new ArrayList<>();
        
        resultado.add(obtenerNombresCampos(campos));
        coleccion.forEach(objeto -> resultado.add(obtenerValoresCampos(objeto, campos)));
        return resultado;
    }
    
    public <T> List<String[]> convertirCamposSeleccionados(Collection<T> coleccion, String... nombresCampos) {
        List<String[]> resultado = new ArrayList<>();
        if (coleccion.isEmpty()) return resultado;
        
        resultado.add(nombresCampos);
        Field[] campos = obtenerCampos(coleccion.iterator().next().getClass(), nombresCampos);
        
        coleccion.forEach(objeto -> resultado.add(obtenerValoresCampos(objeto, campos)));
        
        return resultado;
    }
    
    private <T> String[] obtenerNombresCampos(Field[] campos) {
        String[] nombres = new String[campos.length];
        for (int i = 0; i < campos.length; i++) {
            campos[i].setAccessible(true);
            nombres[i] = campos[i].getName();
        }
        return nombres;
    }
    
    private <T> String[] obtenerValoresCampos(T objeto, Field[] campos) {
        String[] valores = new String[campos.length];
        for (int i = 0; i < campos.length; i++) {
            try {
                valores[i] = String.valueOf(campos[i].get(objeto));
            } catch (IllegalAccessException e) {
                valores[i] = "";
            }
        }
        return valores;
    }
    
    private Field[] obtenerCampos(Class<?> clase, String[] nombresCampos) {
        Field[] campos = new Field[nombresCampos.length];
        for (int i = 0; i < nombresCampos.length; i++) {
            try {
                campos[i] = clase.getDeclaredField(nombresCampos[i]);
                campos[i].setAccessible(true);
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("Campo no encontrado: " + nombresCampos[i]);
            }
        }
        return campos;
    }
    
    private <T> void validarColeccion(Collection<T> coleccion) {
        if (coleccion == null || coleccion.isEmpty()) {
            throw new IllegalArgumentException("La colección no puede estar vacía.");
        }
    }
}
