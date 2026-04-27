package Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Evento implements Serializable {
    private String artista;
    private LocalDate fecha;
    private String lugar;
    private int capacidad;
    private int entradasVendidas;
    private double precioEntrada;

    public Evento(String artista, LocalDate fecha, String lugar, int capacidad, int entradasVendidas, double precioEntrada) {
        this.artista = artista;
        this.fecha = fecha;
        this.lugar = lugar;
        this.capacidad = capacidad;
        this.entradasVendidas = entradasVendidas;
        this.precioEntrada = precioEntrada;
    }

    public double getIngresos() {
        return entradasVendidas * precioEntrada;
    }

    public String getArtista() {
        return artista;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getEntradasVendidas() {
        return entradasVendidas;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }
}