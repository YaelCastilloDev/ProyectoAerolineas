/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "bb", schemaVersion = "1.0")
public class Prueba {
    @Id
    private String id;
    private String nombre;
    private String paisOrigen;
    private int fundacionYear;
    private int numeroAviones;
    private boolean activa;
    private String codigoIATA;

    // Required default constructor for JSONDB
    public Prueba() {}

    public Prueba(String id, String nombre, String paisOrigen, int fundacionYear, 
                    int numeroAviones, boolean activa, String codigoIATA) {
        this.id = id;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.fundacionYear = fundacionYear;
        this.numeroAviones = numeroAviones;
        this.activa = activa;
        this.codigoIATA = codigoIATA;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public int getFundacionYear() {
        return fundacionYear;
    }

    public void setFundacionYear(int fundacionYear) {
        this.fundacionYear = fundacionYear;
    }

    public int getNumeroAviones() {
        return numeroAviones;
    }

    public void setNumeroAviones(int numeroAviones) {
        this.numeroAviones = numeroAviones;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getCodigoIATA() {
        return codigoIATA;
    }

    public void setCodigoIATA(String codigoIATA) {
        this.codigoIATA = codigoIATA;
    }

    @Override
    public String toString() {
        return "Aerolinea{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", fundacionYear=" + fundacionYear +
                ", numeroAviones=" + numeroAviones +
                ", activa=" + activa +
                ", codigoIATA='" + codigoIATA + '\'' +
                '}';
    }
}