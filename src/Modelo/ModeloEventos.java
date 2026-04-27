package Modelo;

import java.io.*;
import java.util.ArrayList;

public class ModeloEventos {
    private ArrayList<Evento> listaEventos;
    private final String archivoDatos = "conciertos.dat";

    public ModeloEventos() {
        listaEventos = cargarDatos();
    }

    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDatos))) {
            oos.writeObject(listaEventos);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Evento> cargarDatos() {
        File file = new File(archivoDatos);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoDatos))) {
                return (ArrayList<Evento>) ois.readObject();
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

    public void agregar(Evento e) {
        listaEventos.add(e);
        guardarDatos();
    }

    public void eliminar(int index) {
        if (index >= 0 && index < listaEventos.size()) {
            listaEventos.remove(index);
            guardarDatos();
        }
    }

    public ArrayList<Evento> getLista() {
        return listaEventos;
    }
}