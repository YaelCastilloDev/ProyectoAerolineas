package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import modelos.Piloto;


public class PilotoValidacion {
    private final Piloto piloto;

    public PilotoValidacion() {
        this.piloto = new Piloto();
    }

    public void validarCompleto(
            String nombre, 
            String direccion,
            LocalDate fechaNacimiento,
            String genero,
            double salario,
            String correoElectronico,
            String contrasena,
            LocalDate anoInicio,
            String tipoLicencia) {
        
        piloto.setNombre(nombre);
        piloto.setDireccion(direccion);
        piloto.setFechaNacimiento(fechaNacimiento);
        piloto.setGenero(genero);
        piloto.setSalario(salario);
        piloto.setCorreoElectronico(correoElectronico);
        piloto.setContrasena(contrasena);
        piloto.setAnoInicio(anoInicio);
        piloto.setTipoLicencia(tipoLicencia);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Piloto>> violations = validator.validate(piloto);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public Piloto getPilotoValidado() {
        return this.piloto;
    }
}