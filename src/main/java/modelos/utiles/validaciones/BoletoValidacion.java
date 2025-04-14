package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalTime;
import java.util.Set;
import modelos.Boleto;

public class BoletoValidacion {
    private final Boleto boleto;

    public BoletoValidacion() {
        this.boleto = new Boleto();
    }

    public void validarCompleto(
            double costoBoleto,
            int sala,
            int control,
            String clase,
            int zona,
            LocalTime horaExpedicion,
            LocalTime horaSalida) {
        
        boleto.setCostoBoleto(costoBoleto);
        boleto.setSala(sala);
        boleto.setControl(control);
        boleto.setClase(clase);
        boleto.setZona(zona);
        boleto.setHoraExpedicion(horaExpedicion);
        boleto.setHoraSalida(horaSalida);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Boleto>> violations = validator.validate(boleto);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public Boleto getBoletoValidado() {
        return this.boleto;
    }
}