package db;

import io.jsondb.JsonDBTemplate;
import java.io.File;

import io.jsondb.JsonDBTemplate;

/**
 *
 * @author yael
 */

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
/*public class dbConeccion {
    // Usa esta ruta exacta (ajusta según tu sistema)
    private static final String DB_FILES_LOCATION = "/home/yael/Escritorio/basededatosJSON/";
    private static final String BASE_SCAN_PACKAGE = "modelos"; // Asegúrate que coincida con tu paquete
    private static JsonDBTemplate jsonDBTemplate;
    
public static JsonDBTemplate getConnection() {
    if (jsonDBTemplate == null) {
        try {
            File dbDir = new File(DB_FILES_LOCATION);
            System.out.println("Absolute path: " + dbDir.getAbsolutePath());
            
            if (!dbDir.exists()) {
                throw new RuntimeException("DB directory doesn't exist");
            }
            
            // Verify specific file exists
            File aerolineasFile = new File(dbDir, "aerolineas.json");
            System.out.println("Aerolineas file exists: " + aerolineasFile.exists());
            System.out.println("Aerolineas file readable: " + aerolineasFile.canRead());
            System.out.println("Aerolineas file writable: " + aerolineasFile.canWrite());
            
            jsonDBTemplate = new JsonDBTemplate(DB_FILES_LOCATION, BASE_SCAN_PACKAGE);
            
            // Force collection check
            System.out.println("Collections found: " + jsonDBTemplate.getCollectionNames());
            
        } catch (Exception e) {
            System.err.println("Detailed error:");
            e.printStackTrace();
            throw new RuntimeException("DB initialization failed", e);
        }
    }
    return jsonDBTemplate;
}
}*/