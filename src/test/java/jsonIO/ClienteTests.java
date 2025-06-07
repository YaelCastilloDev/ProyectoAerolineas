package jsonIO;

import controladores.ClienteControlador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import modelos.Cliente;
import modelos.dao.implementaciones.ClienteDAOimpl;
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
public class ClienteTests {
    @Mock
    private ClienteDAOimpl clienteDAOimpl;
    
    @InjectMocks
    private ClienteControlador controlador;
    
    private Cliente cliente;
    
    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setId("2");
        cliente.setNombre("Mauricio");
        cliente.setNacionalidad("Mexicana");
        cliente.setTelefono("2299010203");
        cliente.setCorreoElectronico("test@correo.com");
        cliente.setFechaNacimiento(LocalDate.of(2000, 11, 16));
        cliente.setPasaportes(List.of("Mexicano", "Italiano"));
    }
    
    @Test
    public void testCrearClienteValido() {
        when(clienteDAOimpl.buscarPorId(cliente.getCorreoElectronico())).thenReturn(null);
        
        assertDoesNotThrow(() -> controlador.crear(cliente));
        
        verify(clienteDAOimpl).crear(cliente);
    }
    
    @Test
    public void testCrearClienteExistente() {
        when(clienteDAOimpl.buscarPorId(cliente.getCorreoElectronico())).thenReturn(cliente);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.crear(cliente));
        
        assertEquals("Ya existe un cliente con el correo: " + cliente.getCorreoElectronico(), exception.getMessage());
    }
    
    @Test
    public void testBuscarClienteExistente() {
        when(clienteDAOimpl.buscarPorId("test@correo.com")).thenReturn(cliente);
        
        Cliente encontrado = controlador.buscarPorId("test@correo.com");
        
        assertEquals(cliente, encontrado);
    }
    
    @Test
    public void testBuscarClienteInexistente() {
        when(clienteDAOimpl.buscarPorId("noexiste@correo.com")).thenReturn(null);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.buscarPorId("noexiste@correo.com"));
        
        assertEquals("No se encontró el cliente con el correo: noexiste@correo.com", exception.getMessage());
    }
    
    @Test
    public void testListarTodosClientesConRegistros() {
        List<Cliente> lista = new ArrayList<>();
        lista.add(cliente);
        when(clienteDAOimpl.listarTodas()).thenReturn(lista);
        
        List<Cliente> resultado = controlador.listarTodas();
        
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }
    
    @Test
    public void testListarTodosClientesSinRegistros() {
        when(clienteDAOimpl.listarTodas()).thenReturn(new ArrayList<>());
        
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
                controlador.listarTodas());
        
        assertEquals("No hay clientes registrados.", exception.getMessage());
    }
    
    @Test
    public void testActualizarClienteExistente() {
        when(clienteDAOimpl.buscarPorId(cliente.getCorreoElectronico())).thenReturn(cliente);
        
        assertDoesNotThrow(() -> controlador.actualizar(cliente));
        
        verify(clienteDAOimpl).actualizar(cliente);
    }
    
    @Test
    public void testActualizarClienteInexistente() {
        when(clienteDAOimpl.buscarPorId(cliente.getCorreoElectronico())).thenReturn(null);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.actualizar(cliente));
        
        assertEquals("No existe cliente con el correo: " + cliente.getCorreoElectronico(), exception.getMessage());
    }
    
    @Test
    public void testEliminarClienteExistente() {
        when(clienteDAOimpl.buscarPorId("test@correo.com")).thenReturn(cliente);
        
        assertDoesNotThrow(() -> controlador.eliminar("test@correo.com"));
        
        verify(clienteDAOimpl).eliminar("test@correo.com");
    }
    
    @Test
    public void testEliminarClienteInexistente() {
        when(clienteDAOimpl.buscarPorId("noexiste@correo.com")).thenReturn(null);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.eliminar("noexiste@correo.com"));
        
        assertEquals("No existe cliente con el correo: noexiste@correo.com", exception.getMessage());
    }
    
    @Test
    public void testValidacionCorreo() {
        cliente.setCorreoElectronico("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(cliente));
        assertTrue(exception.getMessage().contains("correo"));
    }
    
    @Test
    public void testTelefonoInvalido() {
        cliente.setTelefono("293931");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(cliente));
        assertTrue(exception.getMessage().toLowerCase().contains("inválido"));
    }
    
    @Test
    public void testPasaportesInvalidos() {
        cliente.setPasaportes(List.of("Mexicano", "Italiano", "Argentino", "Estadounidense"));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(cliente));
        assertTrue(exception.getMessage().toLowerCase().contains("pasaportes"));
    }
}
