package company.proyectoaerolineas;

import dao.implementacion.AerolineaDAOimpl;
import db.dbConeccion;
import io.jsondb.JsonDBTemplate;
import java.util.List;
import modelos.Aerolinea;

public class Proyectoaerolineas {
    
    public static void main(String[] args) {
        try {
            // 1. Obtener conexión
            JsonDBTemplate db = dbConeccion.getConnection();
            
            // 2. Crear colección usando la clase, no el nombre
            if (!db.collectionExists(Aerolinea.class)) {
                System.out.println("Creando colección aerolineas...");
                db.createCollection(Aerolinea.class); // Cambio clave aquí
            }
            
            // 3. Inicializar DAO
            AerolineaDAOimpl dao = new AerolineaDAOimpl();
            
            // 4. Crear objeto Aerolinea
            Aerolinea nueva = new Aerolinea();
            nueva.setNombre("AeroMéxico");
            nueva.setPais("México");
            nueva.setCentroOperacionPrincipal("Ciudad de México");
            nueva.setBases(List.of(
                "Aeropuert",
                "Aeropuert"
            ));
            nueva.setSitioOficial("https://www.aeromexico.com");
            nueva.setNombreContacto("Juan Pérez");
            nueva.setTelefono("+525512345678");
            
            // 5. Insertar
            dao.actualizar(nueva);
            System.out.println(" Aerolínea creada exitosamente!");
            
        } catch (Exception e) {
            System.err.println(" Error crítico:");
            e.printStackTrace();
        }
    }
}
