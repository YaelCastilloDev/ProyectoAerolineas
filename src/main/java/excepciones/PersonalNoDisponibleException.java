package excepciones;

public class PersonalNoDisponibleException extends RuntimeException {
    public PersonalNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}