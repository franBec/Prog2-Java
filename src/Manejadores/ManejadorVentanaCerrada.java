package Manejadores;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ManejadorVentanaCerrada implements WindowListener{
    
    //variables privadas
    private JFrame ventana;
    
    //constructor
    public ManejadorVentanaCerrada(JFrame ventana) {
        this.ventana = ventana;
    }

    //implementaciones
    @Override
    public void windowOpened(WindowEvent e) {
        //ventana.setVisible(false);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //setVisible(true);
        if (JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cerrar el programa?\nPerderá todo el progreso realizado,\npara conservarlo cierre sesión y salga del programa apropiadamente", "Confirmar acción", JOptionPane.YES_NO_OPTION) ==0)
            System.exit(1);
    }

    @Override
    public void windowClosed(WindowEvent e) {
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
        //ventana.setVisible(true);
    }
}
