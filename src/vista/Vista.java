package vista;

import javax.swing.*;


public class Vista extends JFrame {
    private JPanel mainpanel;
    private JTextField txtArtista;
    private JTextField txtLugar;
    private JComboBox<Integer> cbanio;
    private JComboBox<Integer> cbMes;
    private JComboBox<Integer> cbDia;
    private JPanel panelFechas;
    private JLabel FechaEvento;
    private JTextField txtCap;
    private JTextField txtVendidas;
    private JTextField txtPrecioUnit;
    private JButton btnAlta;
    private JButton btnBaja;
    private JButton btnBuscar;
    private JButton btnMod;
    private JTable tbEventos;

    public JPanel getMainpanel() {
        return mainpanel;
    }

    public JTextField getTxtArtista() {
        return txtArtista;
    }

    public JTextField getTxtLugar() {
        return txtLugar;
    }

    public JComboBox<Integer> getCbanio() {
        return cbanio;
    }

    public JComboBox<Integer> getCbMes() {
        return cbMes;
    }

    public JComboBox<Integer> getCbDia() {
        return cbDia;
    }

    public JTextField getTxtCap() {
        return txtCap;
    }

    public JTextField getTxtVendidas() {
        return txtVendidas;
    }

    public JTextField getTxtPrecioUnit() {
        return txtPrecioUnit;
    }

    public JButton getBtnAlta() {
        return btnAlta;
    }

    public JButton getBtnBaja() {
        return btnBaja;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnMod() {
        return btnMod;
    }

    public JTable getTbEventos() {
        return tbEventos;
    }
}