package modelos;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalTime;

@Document(collection = "administrativos", schemaVersion= "1.0")
public class Administrativo implements Serializable {
    
    @NotBlank(message = "El departamento no puede estar vacío")
    @Size(max = 50, message = "El departamento no puede exceder 50 caracteres")
    private String deptoTrabajo;
    
    @NotNull(message = "El horario no puede ser nulo")
    private LocalTime horarioEntrada;
    
    @NotNull(message = "El horario no puede ser nulo")
    private LocalTime horarioSalida;
    
    @NotBlank(message = "El puesto no puede estar vacío")
    @Size(max = 30, message = "El puesto no puede exceder 30 caracteres")
    private String puesto;
    
  //  @Pattern(regexp = "TIEMPO_COMPLETO|MEDIO_TIEMPO|CONTRATO",
  //           message = "Tipo de contrato inválido")
    private String tipoContrato;
    
    @PositiveOrZero(message = "Los años de experiencia no pueden ser negativos")
    @Max(value = 50, message = "Máximo 50 años de experiencia")
    private int anosExperiencia;
    
    
    @Id
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Debe ser un correo electrónico válido")
    @Size(max = 60, message = "El correo no puede exceder 60 caracteres")
    private String correoElectronico;
    
 //   @Secret 
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    private String contrasena;

    // Getters y Setters
    public String getDeptoTrabajo() {
        return deptoTrabajo;
    }

    public void setDeptoTrabajo(String deptoTrabajo) {
        this.deptoTrabajo = deptoTrabajo;
    }
    
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
        public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public LocalTime getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(LocalTime horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }
}