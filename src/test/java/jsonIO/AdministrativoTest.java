package jsonIO;

import controladores.AdministrativoControlador;
import java.time.LocalTime;
import modelos.Administrativo;
import modelos.dao.implementaciones.AdministrativoDAOimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdministrativoTest {

    @Mock
    private AdministrativoDAOimpl administrativoDAOimpl;

    @InjectMocks
    private AdministrativoControlador controlador;

    private Administrativo administrativo;

    @BeforeEach
    public void setUp() {
        administrativo = new Administrativo();
        administrativo.setCorreoElectronico("test@correo.com");
        administrativo.setContrasena("Password123");
        administrativo.setDeptoTrabajo("Sistemas");
        administrativo.setHorarioEntrada(LocalTime.parse("08:00"));
        administrativo.setHorarioSalida(LocalTime.parse("16:00"));
        administrativo.setPuesto("Encargado");
        administrativo.setTipoContrato("TIEMPO_COMPLETO");
        administrativo.setAnosExperiencia(5);
    }

    @Test
    public void testCrearAdministrativoExitosamente() {
        when(administrativoDAOimpl.buscarPorId(administrativo.getCorreoElectronico())).thenReturn(null);

        assertDoesNotThrow(() -> controlador.crear(administrativo));

        verify(administrativoDAOimpl).crear(administrativo);
    }

    @Test
    public void testCrearAdministrativoExistente() {
        when(administrativoDAOimpl.buscarPorId(administrativo.getCorreoElectronico())).thenReturn(administrativo);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.crear(administrativo));

        assertEquals("Ya existe un administrativo con el correo: " + administrativo.getCorreoElectronico(), exception.getMessage());
    }

    @Test
    public void testBuscarAdministrativoExistente() {
        when(administrativoDAOimpl.buscarPorId("test@correo.com")).thenReturn(administrativo);

        Administrativo encontrado = controlador.buscarPorId("test@correo.com");

        assertEquals(administrativo, encontrado);
    }

    @Test
    public void testBuscarAdministrativoInexistente() {
        when(administrativoDAOimpl.buscarPorId("noexiste@correo.com")).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.buscarPorId("noexiste@correo.com"));

        assertEquals("No se encontró el administrativo con el correo: noexiste@correo.com", exception.getMessage());
    }

    @Test
    public void testListarTodosAdministrativosConRegistros() {
        List<Administrativo> lista = new ArrayList<>();
        lista.add(administrativo);
        when(administrativoDAOimpl.listarTodas()).thenReturn(lista);

        List<Administrativo> resultado = controlador.listarTodas();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }

    @Test
    public void testListarTodosAdministrativosSinRegistros() {
        when(administrativoDAOimpl.listarTodas()).thenReturn(new ArrayList<>());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
                controlador.listarTodas());

        assertEquals("No hay administrativos registrados.", exception.getMessage());
    }

    @Test
    public void testActualizarAdministrativoExistente() {
        when(administrativoDAOimpl.buscarPorId(administrativo.getCorreoElectronico())).thenReturn(administrativo);

        assertDoesNotThrow(() -> controlador.actualizar(administrativo));

        verify(administrativoDAOimpl).actualizar(administrativo);
    }

    @Test
    public void testActualizarAdministrativoInexistente() {
        when(administrativoDAOimpl.buscarPorId(administrativo.getCorreoElectronico())).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.actualizar(administrativo));

        assertEquals("No existe administrativo con el correo: " + administrativo.getCorreoElectronico(), exception.getMessage());
    }

    @Test
    public void testEliminarAdministrativoExistente() {
        when(administrativoDAOimpl.buscarPorId("test@correo.com")).thenReturn(administrativo);

        assertDoesNotThrow(() -> controlador.eliminar("test@correo.com"));

        verify(administrativoDAOimpl).eliminar("test@correo.com");
    }

    @Test
    public void testEliminarAdministrativoInexistente() {
        when(administrativoDAOimpl.buscarPorId("noexiste@correo.com")).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.eliminar("noexiste@correo.com"));

        assertEquals("No existe administrativo con el correo: noexiste@correo.com", exception.getMessage());
    }

    // Validaciones individuales simuladas para ilustrar estructura
    @Test
    public void testValidacionCorreoVacio() {
        administrativo.setCorreoElectronico("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(administrativo));
        assertTrue(exception.getMessage().contains("correo"));
    }

    @Test
    public void testValidacionContrasenaInvalida() {
        administrativo.setContrasena("123");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(administrativo));
        assertTrue(exception.getMessage().toLowerCase().contains("contraseña") || exception.getMessage().toLowerCase().contains("válida"));
    }

    @Test
    public void testValidacionAnosExperienciaNegativo() {
        administrativo.setAnosExperiencia(-3);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> controlador.crear(administrativo));
        assertTrue(exception.getMessage().toLowerCase().contains("años") || exception.getMessage().toLowerCase().contains("experiencia"));
    }

}
