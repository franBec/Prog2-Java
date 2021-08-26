package Manejadores;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import ventanas2.MainVentana;

public class ManejadorPanelComponente implements ComponentListener{
    
    //variables privadas
    private MainVentana padre;
    private Container nuevo;
    private boolean configurar;
    private Dimension tamaño;
    
    //constructores
    public ManejadorPanelComponente(MainVentana padre, Container nuevo){
        this.padre = padre;
        this.nuevo = nuevo;
        configurar = true;
        this.tamaño = null;
    }
    
    public ManejadorPanelComponente(MainVentana padre, Container nuevo, Dimension dimension){
        this.padre = padre;
        this.nuevo = nuevo;
        this.configurar = false;
        this.tamaño = dimension;
    }
    
    //implementaciones
    @Override
    public void componentResized(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        padre.setContentPane(nuevo);
        if (configurar)
            padre.configurarJFrame();
        else
            padre.setSize(tamaño);
        padre.setLocationRelativeTo(null);
        nuevo.setVisible(true);
    }
}
