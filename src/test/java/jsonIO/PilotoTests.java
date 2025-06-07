package jsonIO;

import controladores.PilotoControlador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import modelos.Piloto;
import modelos.dao.implementaciones.PilotoDAOimpl;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PilotoTests {
    @Mock
    private PilotoDAOimpl pilotoDAOimpl;
    
    @InjectMocks
    private PilotoControlador controlador;
    
    private Piloto piloto;
    
    @BeforeEach
    public void setUp() {
        piloto = new Piloto();
        piloto.setNombre("Juan Pérez");
        piloto.setDireccion("Avenida Viaje 123");
        piloto.setFechaNacimiento(LocalDate.of(1990, 8, 20));
        piloto.setGenero("Masculino");
        piloto.setSalario(30000.0);
        piloto.setCorreoElectronico("test@correo.com");
        piloto.setContrasena("Piloto123!");
        piloto.setTipoLicencia("ATP");
        piloto.setAnoInicio(LocalDate.of(2018, 5, 10));
    }
    
    @Test
    public void testCrearPilotoValido() {
        when(pilotoDAOimpl.buscarPorId(piloto.getCorreoElectronico())).thenReturn(null);
        
        assertDoesNotThrow(() -> controlador.crear(piloto));
        
        verify(pilotoDAOimpl).crear(piloto);
    }
    
    @Test
    public void testCrearPilotoExistente() {
        when(pilotoDAOimpl.buscarPorId(piloto.getCorreoElectronico())).thenReturn(piloto);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.crear(piloto));
        
        assertEquals("Ya existe un piloto con el correo: " + piloto.getCorreoElectronico(), exception.getMessage());
    }
    
    @Test
    public void testBuscarPilotoExistente() {
        when(pilotoDAOimpl.buscarPorId("test@correo.com")).thenReturn(piloto);
        
        Piloto encontrado = controlador.buscarPorId("test@correo.com");
        
        assertEquals(piloto, encontrado);
    }
    
    @Test
    public void testBuscarPilotoInexistente() {
        when(pilotoDAOimpl.buscarPorId("noexiste@correo.com")).thenReturn(null);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.buscarPorId("noexiste@correo.com"));
        
        assertEquals("No se encontró el piloto con el correo: noexiste@correo.com", exception.getMessage());
    }
    
    @Test
    public void testListarTodosPilotosConRegistros() {
        List<Piloto> lista = new ArrayList<>();
        lista.add(piloto);
        when(pilotoDAOimpl.listarTodos()).thenReturn(lista);
        
        List<Piloto> resultado = controlador.listarTodos();
        
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }
    
    @Test
    public void testListarTodosPilotosSinRegistros() {
        when(pilotoDAOimpl.listarTodos()).thenReturn(new ArrayList<>());
        
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
                controlador.listarTodos());
        
        assertEquals("No hay pilotos registrados.", exception.getMessage());
    }
    
    @Test
    public void testActualizarPilotoExistente() {
        when(pilotoDAOimpl.buscarPorId(piloto.getCorreoElectronico())).thenReturn(piloto);
        
        assertDoesNotThrow(() -> controlador.actualizar(piloto));
        
        verify(pilotoDAOimpl).actualizar(piloto);
    }
    
    @Test
    public void testActualizarPilotoInexistente() {
        when(pilotoDAOimpl.buscarPorId(piloto.getCorreoElectronico())).thenReturn(null);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.actualizar(piloto));
        
        assertEquals("No existe piloto con el correo: " + piloto.getCorreoElectronico(), exception.getMessage());
    }
    
    @Test
    public void testEliminarPilotoExistente() {
        when(pilotoDAOimpl.buscarPorId("test@correo.com")).thenReturn(piloto);
        
        assertDoesNotThrow(() -> controlador.eliminar("test@correo.com"));
        
        verify(pilotoDAOimpl).eliminar("test@correo.com");
    }
    
    @Test
    public void testEliminarPilotoInexistente() {
        when(pilotoDAOimpl.buscarPorId("noexiste@correo.com")).thenReturn(null);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.eliminar("noexiste@correo.com"));
        
        assertEquals("No existe el piloto con el correo: noexiste@correo.com", exception.getMessage());
    }
    
    @Test
    public void testValidacionCorreo() {
        piloto.setCorreoElectronico("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(piloto));
        assertTrue(exception.getMessage().contains("correo"));
    }
    
    @Test
    public void testContrasenaInvalida() {
        piloto.setContrasena("123");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(piloto));
        assertTrue(exception.getMessage().toLowerCase().contains("contraseña") || exception.getMessage().toLowerCase().contains("válida"));
    }
    
    @Test
    public void testTipoLicenciaInvalida() {
        piloto.setTipoLicencia("AAA");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(piloto));
        assertTrue(exception.getMessage().toLowerCase().contains("licencia"));
    }
}
