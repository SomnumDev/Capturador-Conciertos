package Controlador;

import Modelo.Evento;
import Modelo.ModeloEventos;
import vista.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class ControladorEventos {
    private ModeloEventos modelo;
    private Vista vista;
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ControladorEventos(ModeloEventos modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;

        configurarTabla();
        incCombos();

        this.vista.getBtnAlta().addActionListener(e -> registrar());
        this.vista.getBtnBaja().addActionListener(e -> eliminar());
        this.vista.getBtnMod().addActionListener(e -> modificar());
        this.vista.getBtnBuscar().addActionListener(e -> buscar());

        this.vista.getCbMes().addActionListener(e -> actualizarDias());
        this.vista.getCbanio().addActionListener(e -> actualizarDias());

        actualizarTabla();
    }

    private void configurarTabla() {
        DefaultTableModel m = new DefaultTableModel(
                new Object[]{"ARTISTA", "FECHA", "LUGAR", "VENDIDOS", "PRECIO", "INGRESOS"}, 0
        );
        vista.getTbEventos().setModel(m);
    }

    private void incCombos() {
        for (int i = 1; i <= 12; i++) vista.getCbMes().addItem(i);
        for (int i = 2026; i <= 2036; i++) vista.getCbanio().addItem(i);
        actualizarDias();
    }

    private void actualizarDias() {
        if (vista.getCbMes().getSelectedItem() == null || vista.getCbanio().getSelectedItem() == null) return;
        int mes = (int) vista.getCbMes().getSelectedItem();
        int anio = (int) vista.getCbanio().getSelectedItem();
        int diasEnMes = YearMonth.of(anio, mes).lengthOfMonth();
        Object seleccionado = vista.getCbDia().getSelectedItem();
        int diaActual = (seleccionado != null) ? (int) seleccionado : 1;
        vista.getCbDia().removeAllItems();
        for (int i = 1; i <= diasEnMes; i++) vista.getCbDia().addItem(i);
        if (diaActual <= diasEnMes) vista.getCbDia().setSelectedItem(diaActual);
    }

    private void registrar() {
        try {
            modelo.agregar(capturarDatos());
            actualizarTabla();
            limpiar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en los datos");
        }
    }

    private Evento capturarDatos() {
        String art = vista.getTxtArtista().getText();
        String lug = vista.getTxtLugar().getText();
        LocalDate fec = LocalDate.of((int) vista.getCbanio().getSelectedItem(), (int) vista.getCbMes().getSelectedItem(), (int) vista.getCbDia().getSelectedItem());
        int cap = Integer.parseInt(vista.getTxtCap().getText());
        int ven = Integer.parseInt(vista.getTxtVendidas().getText());
        double pre = Double.parseDouble(vista.getTxtPrecioUnit().getText());
        return new Evento(art, fec, lug, cap, ven, pre);
    }

    private void eliminar() {
        int fila = vista.getTbEventos().getSelectedRow();
        if (fila != -1) {
            modelo.eliminar(fila);
            actualizarTabla();
        }
    }

    private void modificar() {
        int fila = vista.getTbEventos().getSelectedRow();
        if (fila != -1) {
            try {
                modelo.getLista().set(fila, capturarDatos());
                modelo.guardarDatos();
                actualizarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }
    }

    private void buscar() {
        String busqueda = vista.getTxtArtista().getText();
        for (int i = 0; i < modelo.getLista().size(); i++) {
            Evento e = modelo.getLista().get(i);
            if (e.getArtista().equalsIgnoreCase(busqueda)) {
                vista.getTbEventos().setRowSelectionInterval(i, i);
                vista.getTxtLugar().setText(e.getLugar());
                vista.getTxtCap().setText(String.valueOf(e.getCapacidad()));
                vista.getTxtVendidas().setText(String.valueOf(e.getEntradasVendidas()));
                vista.getTxtPrecioUnit().setText(String.valueOf(e.getPrecioEntrada()));
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No encontrado");
    }

    private void actualizarTabla() {
        DefaultTableModel m = (DefaultTableModel) vista.getTbEventos().getModel();
        m.setRowCount(0);
        double total = 0;
        for (Evento e : modelo.getLista()) {
            total += e.getIngresos();
            m.addRow(new Object[]{
                    e.getArtista(),
                    e.getFecha().format(fmt),
                    e.getLugar(),
                    e.getEntradasVendidas(),
                    e.getPrecioEntrada(),
                    e.getIngresos()
            });
        }
    }

    private void limpiar() {
        vista.getTxtArtista().setText("");
        vista.getTxtLugar().setText("");
        vista.getTxtCap().setText("");
        vista.getTxtVendidas().setText("");
        vista.getTxtPrecioUnit().setText("");
    }
}