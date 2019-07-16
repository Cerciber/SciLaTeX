package scilatex.View.Main;

import scilatex.View.Windows.PrincipalPanel;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import static javax.swing.SwingUtilities.updateComponentTreeUI;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

// Clase principal
public class Main {

    // Metodo principal
    public static void main(String[] args) {
        
        // Crear ventana
        JFrame frame = new JFrame();
        
        // Asginar finalizar aplicación al cerrar el frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  

        // Asginar vista de Windows
        try {

            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
            updateComponentTreeUI(frame);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("No se pudo asignar vista de windows");
        }

        // Redimensionar
        frame.setSize(1200, 600);                // Asignar ancho y alto cunado no está en modo pantalla completa
        frame.setExtendedState(MAXIMIZED_BOTH);  // Asignar modo pantalla completa
        
        // Asignar panel al frame
        frame.setContentPane(new PrincipalPanel());        
        
        // Mostrar frame
        frame.setVisible(true);
        
    }

}
