package db;

//import io.jsondb.JsonDBTemplate;
//import java.io.File;

import io.jsondb.JsonDBTemplate;
import java.io.File;

/**
 *
 * @author yael
 */


/*
public class dbConeccion {
    // Ruta absoluta corregida (o mejor usa ruta relativa)
    private static final String DB_FILES_LOCATION = "/home/yael/Escritorio/javaprojecto/Proyectoaerolineas/basededatosJSON/";
    
    // Paquete debe coincidir con donde están realmente tus clases modelo
    private static final String BASE_SCAN_PACKAGE = "company.proyectoaerolineas.modelos"; 
    
    private static JsonDBTemplate jsonDBTemplate;
    
    public static JsonDBTemplate getConnection() {
        if (jsonDBTemplate == null) {
            try {
                jsonDBTemplate = new JsonDBTemplate(DB_FILES_LOCATION, BASE_SCAN_PACKAGE);
                System.out.println("Base de datos conectada. Ubicación: " + DB_FILES_LOCATION);
            } catch (Exception e) {
                System.err.println("Error al conectar con la base de datos:");
                e.printStackTrace();
                throw new RuntimeException("No se pudo inicializar la conexión", e);
            }
        }
        return jsonDBTemplate;
    }
} 

*/
public class dbConeccion {
    // Usa esta ruta exacta (ajusta según tu sistema)
    private static final String DB_FILES_LOCATION = "/home/yael/Escritorio/basededatosJSON/";
    private static final String BASE_SCAN_PACKAGE = "modelos"; // Asegúrate que coincida con tu paquete
    private static JsonDBTemplate jsonDBTemplate;
    
public static JsonDBTemplate getConnection() {
    if (jsonDBTemplate == null) {
        try {
            // 1. Verifica que el directorio existe
            File dbDir = new File(DB_FILES_LOCATION);
            if (!dbDir.exists()) {
                if (!dbDir.mkdirs()) {
                    throw new RuntimeException("No se pudo crear el directorio de la base de datos");
                }
            }

            // 2. Inicialización alternativa
            jsonDBTemplate = new JsonDBTemplate(DB_FILES_LOCATION, BASE_SCAN_PACKAGE); // Usa la clase directamente
            
            System.out.println("Conexión exitosa. Colecciones: " + jsonDBTemplate.getCollectionNames());
            
        } catch (Exception e) {
            System.err.println("Error crítico en conexión:");
            e.printStackTrace();
            throw new RuntimeException("Falló la inicialización de JsonDB", e);
        }
    }
    return jsonDBTemplate;
}
}