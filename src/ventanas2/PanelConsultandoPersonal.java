/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas2;

import Manejadores.ManejadorPanelComponente;
import integrador.roles.Persona;
import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author franb
 */
public class PanelConsultandoPersonal extends javax.swing.JPanel {
    
    private DefaultTableModel modeloTablaPersonal;
    private MainVentana padre;
    /**
     * Creates new form PanelConsultandoPersonal
     */
    public PanelConsultandoPersonal() {
        initComponents();
        
        this.inicializarTablaPersonal();
            
            
    }
    
    public PanelConsultandoPersonal(MainVentana padre) {
        initComponents();
        
        this.padre = padre;
        this.configurarJFrame();
        
        this.inicializarTablaPersonal();
        
        llenarTablaPersonal();  
    }

    //funciones extras
    public void configurarJFrame() {
        this.padre.setResizable(false);
        this.padre.setTitle("Consuta de información");
        this.padre.setLocationRelativeTo(null);
        this.padre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.padre.setSize(this.getPreferredSize());
    }
    
    public void llenarTablaPersonal() {
        Iterator<Persona> iterador = this.padre.lista.iterator();
        
        while (iterador.hasNext()) {
            Persona temporal = iterador.next();
            
            this.modeloTablaPersonal.addRow(new String[] {temporal.getNombreyApellido(),new Integer(temporal.getDni()).toString(),temporal.getRol().toString()});
        }
        
        
        this.tablaPersonal.setModel(modeloTablaPersonal);
    }
    
    
    public void inicializarTablaPersonal() {
        this.modeloTablaPersonal = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre y Apellido", "DNI", "Rol"
            }
        ) {
        @Override
            public boolean isCellEditable(int row, int column) {
            return false;
        }};
        
        this.tablaPersonal.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (e.getClickCount()==2 && e.getSource().equals(tablaPersonal)) {
                    Persona personaClick = padre.lista.get(tablaPersonal.getSelectedRow());
                    
                    abrirInfoPersona(personaClick);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        /*
        JTableHeader cabecera = this.tablaPersonal.getTableHeader();
        
        
        cabecera.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                System.out.println("mouse clicked");
                System.out.println(e.getComponent().toString());
                System.out.println(e.getSource().toString());
                System.out.println(e.paramString());
                Component temp = cabecera.findComponentAt(e.getX(), e.getY());
                System.out.println(temp.toString());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        
            }
        });*/
        
        this.tablaPersonal.setRowSelectionAllowed(true);
        this.tablaPersonal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tablaPersonal.setColumnSelectionAllowed(false);
    }
    
    public void abrirInfoPersona(Persona persona) {
        PanelVerMiInfo panel = new PanelVerMiInfo(this.padre, persona);
        
        //panel.addComponentListener(new ManejadorPanelComponente(this.padre, this, this.getPreferredSize()));
        JPanel estePanel = this;    //variable para capturar el jpanel total
        panel.addComponentListener(new ComponentListener() {
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
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                padre.setContentPane(estePanel);
                padre.setSize(getPreferredSize());
                padre.setLocationRelativeTo(null);
                //actualiza la tabla, ya que al eliminar una persona desaparece de la lista y debe ser escaneada de vuelta
                inicializarTablaPersonal();
                llenarTablaPersonal();
                estePanel.setVisible(true);
            }
        });
        
        
        this.setVisible(false);
        this.padre.setContentPane(panel);
    }
    
    public void accionBotonBuscar() {
        try {
            int dniBuscado = new Integer(this.textFieldDNI.getText());
            
            Persona buscado = this.padre.lista.buscarPersonaPorDni(dniBuscado);
            
            if (buscado != null) {
                abrirInfoPersona(buscado);
            }
            else {
                JOptionPane.showMessageDialog(null, "El numero de DNI ingresado ("+dniBuscado+") no se encuentra en el sistema","Persona No encontrada",JOptionPane.ERROR_MESSAGE);
            }
            
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La contraseña solo puede estar compuesta por numeros","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPersonal = new javax.swing.JTable();
        labelBuscarDni = new javax.swing.JLabel();
        textFieldDNI = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();
        botonRegresar = new javax.swing.JButton();

        labelTitulo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Consulta de personal");

        tablaPersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre y Apellido", "DNI", "Rol"
            }
        ));
        tablaPersonal.setToolTipText("Lista de personas registradas en el sistema. Puede realizar doble clic en una persona particular y mostrar su información completa.\n--El personal directivo podrá eliminar las personas desde su ventana de información completa");
        jScrollPane1.setViewportView(tablaPersonal);

        labelBuscarDni.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelBuscarDni.setText("Ingrese DNI para buscar");

        textFieldDNI.setToolTipText("Ingrese aquí un DNI para buscar una persona particular en el sistema y ver su información completa");

        botonBuscar.setText("Buscar");
        botonBuscar.setToolTipText("Realiza la busqueda de una persona particular en el sistema en base al DNI indicado y muestra su información completa");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        botonRegresar.setText("Regresar");
        botonRegresar.setToolTipText("Regresa a la ventana anterior");
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelBuscarDni, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(botonRegresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBuscarDni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botonBuscar, labelBuscarDni, textFieldDNI});

    }// </editor-fold>//GEN-END:initComponents

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        this.accionBotonBuscar();
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonRegresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBuscarDni;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTable tablaPersonal;
    private javax.swing.JTextField textFieldDNI;
    // End of variables declaration//GEN-END:variables
}
