package company.proyectoaerolineas;

import db.dbConeccion;
import io.jsondb.JsonDBTemplate;
import modelos.Aerolinea;

public class Proyectoaerolineas {
    
public static void main(String[] args) {
    try {
        System.out.println("Iniciando prueba de conexión...");
        JsonDBTemplate db = dbConeccion.getConnection();
        
        // Verifica colecciones
        System.out.println("Colecciones detectadas: " + db.getCollectionNames());
        
        // Prueba básica de operación
        Aerolinea a = new Aerolinea();
        a.setNombre("test-1");
        db.insert(a);
        System.out.println("Inserción de prueba exitosa!");
        
    } catch (Exception e) {
        System.err.println("Prueba fallida:");
        e.printStackTrace();
    }
}
    
/*    public static void main(String[] args) {
    try {
        System.out.println("Iniciando prueba de conexión...");
        JsonDBTemplate db = dbConeccion.getConnection();
            if (!db.collectionExists("aerolineas")) {
                db.createCollection(Aerolinea.class);
            }
        // Verifica colecciones
        System.out.println("Colecciones detectadas: " + db.getCollectionNames());
        
        // Prueba básica de operación
        Aerolinea a = new Aerolinea();
        a.setNombre("test-1");
        db.insert(a);
        System.out.println("Inserción de prueba exitosa!");
        
    } catch (Exception e) {
        System.err.println("Prueba fallida:");
        e.printStackTrace();
    }
} */
}