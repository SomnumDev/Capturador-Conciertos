import vista.Vista;
import javax.swing.JFrame;
import Modelo.ModeloEventos;
import Controlador.ControladorEventos;

public class Main {
    public static void main(String[] args) {
        Vista v = new Vista();
        JFrame frame = new JFrame("Conciertos");
        frame.setContentPane(v.getMainpanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ModeloEventos m = new ModeloEventos();
        new ControladorEventos(m, v);
    }
}