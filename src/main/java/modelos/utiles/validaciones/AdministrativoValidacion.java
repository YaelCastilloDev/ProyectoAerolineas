package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalTime;
import java.util.Set;
import modelos.Administrativo;

public class AdministrativoValidacion {
    private final Administrativo administrativo;

    public AdministrativoValidacion() {
        this.administrativo = new Administrativo();
    }

    public void validarCompleto(
            String deptoTrabajo,
            LocalTime horarioEntrada,
            LocalTime horarioSalida,
            String puesto,
            String tipoContrato,
            int anosExperiencia,
            String correoElectronico,
            String contrasena) {
        
        administrativo.setDeptoTrabajo(deptoTrabajo);
        administrativo.setHorarioEntrada(horarioEntrada);
        administrativo.setHorarioSalida(horarioSalida);
        administrativo.setPuesto(puesto);
        administrativo.setTipoContrato(tipoContrato);
        administrativo.setAnosExperiencia(anosExperiencia);
        administrativo.setCorreoElectronico(correoElectronico);
        administrativo.setContrasena(contrasena);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Administrativo>> violations = validator.validate(administrativo);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public Administrativo getAdministrativoValidado() {
        return this.administrativo;
    }
}