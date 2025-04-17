package jsonIO;

import controladores.AerolineaControlador;
import jakarta.validation.ConstraintViolationException;
import modelos.Aerolinea;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AerolineaTests {
    
    private AerolineaControlador controlador;
    private Aerolinea testAerolinea;
    
    @BeforeEach
    public void setup() {
        controlador = new AerolineaControlador();
        // Create a valid test aerolinea
        testAerolinea = new Aerolinea();
        testAerolinea.setNombre("TestAir");
        testAerolinea.setPais("Mexico");
        testAerolinea.setCentroOperacionPrincipal("CDMX");
        testAerolinea.setBases(List.of("Base1"));
        testAerolinea.setSitioOficial("https://test.com");
        testAerolinea.setNombreContacto("John Doe");
        testAerolinea.setTelefono("+525512345678");
    }
    
    @AfterEach
    public void cleanup() {
        // Clean up after each test
        try {
            controlador.eliminar("TestAir");
            controlador.eliminar("Volaris_Test");
            controlador.eliminar("AeroUnique_Test");
            controlador.eliminar("AeroUpdate_Test");
            controlador.eliminar("AeroDelete_Test");
        } catch (IllegalArgumentException e) {
            // Ignore if aerolinea doesn't exist
        }
    }
    
    @Test
    public void testCrearAerolineaValida() {
        assertDoesNotThrow(() -> controlador.crear(testAerolinea));
        
        Aerolinea recuperada = controlador.buscarPorId("TestAir");
        assertNotNull(recuperada);
        assertEquals("Mexico", recuperada.getPais());
    }
    
    @Test
    public void testNoPermitirNombresDuplicados() {
        controlador.crear(testAerolinea);
        
        Aerolinea duplicada = new Aerolinea();
        duplicada.setNombre("TestAir"); // Same name
        duplicada.setPais("USA");
        duplicada.setCentroOperacionPrincipal("NYC");
        duplicada.setBases(List.of("Base2"));
        duplicada.setSitioOficial("https://duplicate.com");
        duplicada.setNombreContacto("Jane Doe");
        duplicada.setTelefono("+12125551234");
        
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> controlador.crear(duplicada));
        
        assertEquals("Ya existe una aerolínea con el nombre: TestAir", exception.getMessage());
    }
    
    @Test
    public void testActualizarAerolineaExistente() {
        controlador.crear(testAerolinea);
        
        testAerolinea.setPais("Canada");
        assertDoesNotThrow(() -> controlador.actualizar(testAerolinea));
        
        Aerolinea actualizada = controlador.buscarPorId("TestAir");
        assertEquals("Canada", actualizada.getPais());
    }
    
    @Test
    public void testEliminarAerolineaExistente() {
        controlador.crear(testAerolinea);
        
        assertDoesNotThrow(() -> controlador.eliminar("TestAir"));
        assertThrows(IllegalArgumentException.class,
            () -> controlador.buscarPorId("TestAir"));
    }
    
    @Test
    public void testValidacionesDeCampos() {
        // Test empty name
        Aerolinea invalida = new Aerolinea();
        invalida.setNombre("");
        invalida.setPais("Mexico");
        invalida.setCentroOperacionPrincipal("CDMX");
        invalida.setBases(List.of("Base1"));
        invalida.setSitioOficial("https://test.com");
        invalida.setNombreContacto("John Doe");
        invalida.setTelefono("+525512345678");
        
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
            () -> controlador.crear(invalida));
        assertTrue(exception.getMessage().contains("El nombre no puede estar vacío"));
        
        // Test invalid URL
        invalida.setNombre("InvalidAir");
        invalida.setSitioOficial("not-a-url");
        exception = assertThrows(ConstraintViolationException.class,
            () -> controlador.crear(invalida));
        assertTrue(exception.getMessage().contains("Debe ser una URL válida"));
        
        // Test empty bases
        invalida.setSitioOficial("https://valid.com");
        invalida.setBases(List.of());
        exception = assertThrows(ConstraintViolationException.class,
            () -> controlador.crear(invalida));
        assertTrue(exception.getMessage().contains("Debe especificar al menos una base"));
    }
    
    @Test
    public void testBuscarAerolineaInexistente() {
        String nombreInexistente = "NonExistentAirline";
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> controlador.buscarPorId(nombreInexistente));
        assertEquals("No se encontró aerolínea con ID: " + nombreInexistente, exception.getMessage());
    }
    
    @Test
    public void testActualizarAerolineaInexistente() {
        Aerolinea inexistente = new Aerolinea();
        inexistente.setNombre("GhostAir");
        inexistente.setPais("Nowhere");
        inexistente.setCentroOperacionPrincipal("Void");
        inexistente.setBases(List.of("BaseX"));
        inexistente.setSitioOficial("https://ghost.com");
        inexistente.setNombreContacto("Nobody");
        inexistente.setTelefono("+0000000000");
        
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> controlador.actualizar(inexistente));
        assertEquals("No existe aerolínea con ID: GhostAir", exception.getMessage());
    }
    
    @Test
    public void testEliminarAerolineaInexistente() {
        String nombreInexistente = "MissingAirline";
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> controlador.eliminar(nombreInexistente));
        assertEquals("No existe aerolínea con ID: " + nombreInexistente, exception.getMessage());
    }
}