package company.proyectoaerolineas;

import controladores.dao.implementaciones.AdministrativoDAOimpl;
import controladores.dao.implementaciones.AerolineaDAOimpl;
import controladores.dao.implementaciones.ClienteDAOimpl;
import modelos.ConexionDB;
import io.jsondb.JsonDBTemplate;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import modelos.Administrativo;
import modelos.Aerolinea;
import modelos.Cliente;

public class ProyectoAerolineas {
    public static void main(String[] args) {
        try {
            JsonDBTemplate db = ConexionDB.getConnection();
            if (!db.collectionExists(Administrativo.class)) {
                System.out.println("Creando colección clientes...");
                db.createCollection(Administrativo.class); // Cambio clave aquí
            }
            
            AdministrativoDAOimpl adao = new AdministrativoDAOimpl();
            Administrativo administrativo = new Administrativo();
            administrativo.setAnosExperiencia(4);
            administrativo.setCorreoElectronico("mauricio@gmail.com");
            administrativo.setContrasena("Hola123456");
            administrativo.setDeptoTrabajo("Contabilidad");
            administrativo.setPuesto("Contador");
            administrativo.setTipoContrato("TIEMPO_COMPLETO");
            administrativo.setHorarioEntrada(LocalTime.parse("12:00:00"));
            administrativo.setHorarioSalida(LocalTime.parse("12:50:00"));
            
            adao.actualizar(administrativo);
            System.out.println("Administrativo agregado exitosamente");
            
            /*ClienteDAOimpl clienteDAOimpl = new ClienteDAOimpl();
            Cliente cliente = new Cliente();
            cliente.setNombre("Mauricio");
            cliente.setFechaNacimiento(LocalDate.parse("2005-11-16"));
            cliente.setNacionalidad("Mexicano");
            cliente.setCorreoElectronico("mauricio@gmail.com");
            cliente.setTelefono("1234567890");
            cliente.setPasaportes(List.of("Mexicana", "Estadounidense"));
            
            clienteDAOimpl.actualizar(cliente);
            System.out.println("Cliente agregado exitosamente");*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*public static void main(String[] args) {
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
    }*/
}
