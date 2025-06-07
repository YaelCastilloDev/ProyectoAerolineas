package excepciones;

public class AvionNoDisponibleException extends RuntimeException {
    public AvionNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}