package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import modelos.Cliente;

public class ClienteValidacion {
    private final Cliente cliente;

    public ClienteValidacion() {
        this.cliente = new Cliente();
    }

    public void validarCompleto(
            String nombre,
            String nacionalidad,
            LocalDate fechaNacimiento,
            String correoElectronico,
            String telefono,
            List<String> pasaportes) {
        
        cliente.setNombre(nombre);
        cliente.setNacionalidad(nacionalidad);
        cliente.setFechaNacimiento(fechaNacimiento);
        cliente.setCorreoElectronico(correoElectronico);
        cliente.setTelefono(telefono);
        cliente.setPasaportes(pasaportes);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public Cliente getClienteValidado() {
        return this.cliente;
    }
}