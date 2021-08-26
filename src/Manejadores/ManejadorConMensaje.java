//este majeador se uso en una version vieja del programa, no se si se usa

package Manejadores;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ManejadorConMensaje implements WindowListener {
    
    //variables privadas
    private JFrame ventana;
    private String titulo, mensaje;

    //constructor
    public ManejadorConMensaje(JFrame ventana,String titulo,String mensaje) {
        this.ventana = ventana;
        this.titulo = titulo;
        this.mensaje = mensaje;
    }
    
    //implementaciones
    @Override
    public void windowOpened(WindowEvent e) {
        ventana.setVisible(false);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        ventana.setVisible(true);
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
}