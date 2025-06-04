package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import modelos.Avion;
import modelos.dao.implementaciones.AvionDAOimpl;

public class AvionValidacion {
    private final Avion avion;

    public AvionValidacion() {
        this.avion = new Avion();
    }

    public void validarCompleto(
            String nombre,
            int capacidad,
            String modelo,
            int peso,
            String matricula,
            String aerolineaPropietaria) {
        
        avion.setNombre(nombre);
        avion.setCapacidad(capacidad);
        avion.setModelo(modelo);
        avion.setPeso(peso);
        avion.setMatricula(matricula);
        avion.setAerolineaPropietaria(aerolineaPropietaria);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Avion>> violations = validator.validate(avion);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public void validarMatriculaUnica(String matricula, AvionDAOimpl dao) {
        if (dao.buscarPorId(matricula) != null) {
            throw new IllegalArgumentException("Ya existe un avión con la matrícula: " + matricula);
        }
    }

    public Avion getAvionValidado() {
        return this.avion;
    }
}