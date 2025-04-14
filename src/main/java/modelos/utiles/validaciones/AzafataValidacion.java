package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.Set;
import modelos.Azafata;

public class AzafataValidacion {
    private final Azafata azafata;

    public AzafataValidacion() {
        this.azafata = new Azafata();
    }

    public void validarCompleto(
            String nombre,
            String direccion,
            LocalDate fechaNacimiento,
            String genero,
            double salario,
            String correoElectronico,
            String contrasena,
            int numIdiomas,
            LocalDate anoInicio) {
        
        azafata.setNombre(nombre);
        azafata.setDireccion(direccion);
        azafata.setFechaNacimiento(fechaNacimiento);
        azafata.setGenero(genero);
        azafata.setSalario(salario);
        azafata.setCorreoElectronico(correoElectronico);
        azafata.setContrasena(contrasena);
        azafata.setNumIdiomas(numIdiomas);
        azafata.setAnoInicio(anoInicio);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Azafata>> violations = validator.validate(azafata);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public Azafata getAzafataValidada() {
        return this.azafata;
    }
}