package jsonIO;

import controladores.AzafataControlador;
import modelos.Azafata;
import modelos.dao.implementaciones.AzafataDAOimpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AzafataTest {

    @Mock
    private AzafataDAOimpl azafataDAOimpl;

    @InjectMocks
    private AzafataControlador controlador;

    private Azafata azafata;

    @BeforeEach
    public void setUp() {
        azafata = new Azafata();
        azafata.setNombre("María González");
        azafata.setDireccion("Avenida Viaje 456");
        azafata.setFechaNacimiento(LocalDate.of(1990, 8, 20));
        azafata.setGenero("Femenino");
        azafata.setSalario(30000.0);
        azafata.setCorreoElectronico("maria.azafata@aerolinea.com");
        azafata.setContrasena("Azafata123!");
        azafata.setNumIdiomas(3);
        azafata.setAnoInicio(LocalDate.of(2018, 5, 10));
    }

    @Test
    public void testCrearAzafataExitosamente() {
        when(azafataDAOimpl.buscarPorId(azafata.getCorreoElectronico())).thenReturn(null);
        
        assertDoesNotThrow(() -> controlador.crear(azafata));
        
        verify(azafataDAOimpl).crear(azafata);
    }
    
    @Test
    public void testCrearAzafataExistente() {
        when(azafataDAOimpl.buscarPorId(azafata.getCorreoElectronico())).thenReturn(azafata);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.crear(azafata));
        
        assertEquals("Ya existe una azafata con el correo: " + azafata.getCorreoElectronico(), exception.getMessage());
    }
    
    @Test
    public void testBuscarAzafataExistente() {
        when(azafataDAOimpl.buscarPorId("maria.azafata@aerolinea.com")).thenReturn(azafata);
        
        Azafata encontrada = controlador.buscarPorId("maria.azafata@aerolinea.com");
        
        assertEquals(azafata, encontrada);
    }
    
    @Test
    public void testBuscarAzafataInexistente() {
        when(azafataDAOimpl.buscarPorId("noexiste@correo.com")).thenReturn(null);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.buscarPorId("noexiste@correo.com"));
        
        assertEquals("No se encontró la azafata con el correo: noexiste@correo.com", exception.getMessage());
    }
    
    @Test
    public void testListarTodasAzafatasConRegistros() {
        List<Azafata> lista = new ArrayList<>();
        lista.add(azafata);
        when(azafataDAOimpl.listarTodas()).thenReturn(lista);
        
        List<Azafata> resultado = controlador.listarTodas();
        
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }
    
    @Test
    public void testListarTodasAzafatasSinRegistros() {
        when(azafataDAOimpl.listarTodas()).thenReturn(new ArrayList<>());
        
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
                controlador.listarTodas());
        
        assertEquals("No hay azafatas registradas.", exception.getMessage());
    }
    
    @Test
    public void testActualizarAzafataExistente() {
        when(azafataDAOimpl.buscarPorId(azafata.getCorreoElectronico())).thenReturn(azafata);
        
        assertDoesNotThrow(() -> controlador.actualizar(azafata));
        
        verify(azafataDAOimpl).actualizar(azafata);
    }
    
    @Test
    public void testActualizarAzafataInexistente() {
        when(azafataDAOimpl.buscarPorId(azafata.getCorreoElectronico())).thenReturn(null);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.actualizar(azafata));
        
        assertEquals("No existe azafata con el correo: " + azafata.getCorreoElectronico(), exception.getMessage());
    }
    
    @Test
    public void testEliminarAzafataExistente() {
        when(azafataDAOimpl.buscarPorId("maria.azafata@aerolinea.com")).thenReturn(azafata);
        
        assertDoesNotThrow(() -> controlador.eliminar("maria.azafata@aerolinea.com"));
        
        verify(azafataDAOimpl).eliminar("maria.azafata@aerolinea.com");
    }
    
    @Test
    public void testEliminarAzafataInexistente() {
        when(azafataDAOimpl.buscarPorId("noexiste@correo.com")).thenReturn(null);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.eliminar("noexiste@correo.com"));
        
        assertEquals("No existe azafata con el correo: noexiste@correo.com", exception.getMessage());
    }
    
    @Test
    public void testValidacionCorreoVacio() {
        azafata.setCorreoElectronico("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(azafata));
        assertTrue(exception.getMessage().contains("correo"));
    }
    
    @Test
    public void testValidacionContrasenaInvalida() {
        azafata.setContrasena("123");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(azafata));
        assertTrue(exception.getMessage().toLowerCase().contains("contraseña") || exception.getMessage().toLowerCase().contains("válida"));
    }
    
    @Test
    public void testValidacionNumeroIdiomasInvalido() {
        azafata.setNumIdiomas(-3);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(azafata));
        assertTrue(exception.getMessage().toLowerCase().contains("idioma"));
    }
}
