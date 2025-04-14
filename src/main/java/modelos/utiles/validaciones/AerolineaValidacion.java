package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import modelos.Aerolinea;

public class AerolineaValidacion {
    private final Aerolinea aerolinea;

    public AerolineaValidacion() {
        this.aerolinea = new Aerolinea();
    }

    public void validarCompleto(
            String nombre,
            String pais,
            String centroOperacionPrincipal,
            List<String> bases,
            String sitioOficial,
            String nombreContacto,
            String telefono) {
        
        aerolinea.setNombre(nombre);
        aerolinea.setPais(pais);
        aerolinea.setCentroOperacionPrincipal(centroOperacionPrincipal);
        aerolinea.setBases(bases);
        aerolinea.setSitioOficial(sitioOficial);
        aerolinea.setNombreContacto(nombreContacto);
        aerolinea.setTelefono(telefono);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Aerolinea>> violations = validator.validate(aerolinea);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public Aerolinea getAerolineaValidada() {
        return this.aerolinea;
    }
}