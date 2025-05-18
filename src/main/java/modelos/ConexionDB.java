package modelos;

import io.jsondb.JsonDBTemplate;
import java.io.File;

public class ConexionDB {
    private static final String DB_FILES_LOCATION = "basededatosJSON";
    private static final String BASE_SCAN_PACKAGE = "modelos";
    private static JsonDBTemplate jsonDBTemplate;
    
    public static JsonDBTemplate getConnection() {
        if (jsonDBTemplate == null) {
            try {
                File dbDir = new File(DB_FILES_LOCATION);
                if (!dbDir.exists()) {
                    if (!dbDir.mkdirs()) {
                        throw new RuntimeException("No se pudo crear el directorio de la base de datos");
                    }
                }
                jsonDBTemplate = new JsonDBTemplate(DB_FILES_LOCATION, BASE_SCAN_PACKAGE);
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