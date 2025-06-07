package excepciones;

public class LicenciaNoValidaException extends RuntimeException {
    public LicenciaNoValidaException(String mensaje) {
        super(mensaje);
    }
}