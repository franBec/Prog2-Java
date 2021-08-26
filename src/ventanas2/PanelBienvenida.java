package ventanas2;

import Manejadores.ManejadorPanelComponente;
import integrador.roles.Persona;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelBienvenida extends javax.swing.JPanel {
    
    //variables del panel
    private MainVentana padre;
    
    enum Operaciones {
        VER_MI_INFO,VER_INFO_PERSONAL,ADMINISTRAR_ACTAS
    }
    private String[] textoActividades;
    
    private ComponentListener manejadorPack;
    
    private final String VER_MI_INFO = "Ver mi informacion",
        VER_INFO_PERSONAL  = "Ver informacion del personal registrado",
        ADMINISTRAR_ACTAS = "Administrar actas",
        AGREGAR_PERSONAL = "Ingresar nuevo personal al sistema";
    
    private final String[] textoActividades1 = {VER_MI_INFO};//pasante y profesor
    private final String[] textoActividades2 = {VER_MI_INFO, VER_INFO_PERSONAL, AGREGAR_PERSONAL};//directivo
    private final String[] textoActividades3 = {VER_MI_INFO, VER_INFO_PERSONAL};//preceptor
    private final String[] textoActividades4 = {VER_MI_INFO, VER_INFO_PERSONAL, ADMINISTRAR_ACTAS};//secretario
    
    /*
    private final Operaciones[] actividades1 = {Operaciones.VER_MI_INFO};   //pasante y profesor
    private final Operaciones[] actividades2 = {Operaciones.VER_MI_INFO,Operaciones.VER_INFO_PERSONAL}; //directivo
    private final Operaciones[] actividades3 = {Operaciones.VER_MI_INFO,Operaciones.VER_INFO_PERSONAL}; //preceptor
    private final Operaciones[] actividades4 = {Operaciones.VER_MI_INFO,Operaciones.VER_INFO_PERSONAL,Operaciones.ADMINISTRAR_ACTAS}; //secretario
    */
    
    //constructores
    public PanelBienvenida() {
        initComponents();
        
    }
    public PanelBienvenida(MainVentana padre){
        initComponents();
        this.padre = padre;
        this.padre.setResizable(false);
        this.padre.setLocationRelativeTo(null);
        this.padre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JPanel prueba = this;
        //manejador que utiliza el metodo pack de jframe
        this.manejadorPack = new ComponentListener() {
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
                padre.setContentPane(prueba);
                padre.setLocationRelativeTo(null);
                prueba.setVisible(true);
                padre.pack();
            }
        };
        
        
        Persona p = this.padre.getUsuarioLogueado();
        this.labelTitulo.setText("Bienvenido "+this.padre.getUsuarioLogueado().getNombreyApellido());
        //segun la persona que se loguea llena el combobox
        switch(p.getRol()){
            case PASANTE:
                this.padre.setTitle("Bienvenido Pasante");
                //actividades = actividades1;
                textoActividades = textoActividades1;
                break;
            case PROFESOR:
                this.padre.setTitle("Bienvenido Profesor");
                //actividades = actividades1;
                textoActividades = textoActividades1;
                break;
            case DIRECTIVO:
                this.padre.setTitle("Bienvenido Directivo");
                //actividades = actividades2;
                textoActividades = textoActividades2;
                break;
            case SECRETARIO:
                this.padre.setTitle("Bienvenido Secretario");
                //actividades = actividades4;
                textoActividades = textoActividades4;
                break;
            case PRECEPTOR:
                this.padre.setTitle("Bienvenido Preceptor");
                //actividades = actividades3;
                textoActividades = textoActividades3;
                break;      
        }
        for (String s:textoActividades)
            this.comboBoxTareas.addItem(s);
    }
    
    //funciones del panel
    public void accionBotonAceptar() {
        String opcion = this.comboBoxTareas.getItemAt(this.comboBoxTareas.getSelectedIndex());
        switch (opcion) {
            case VER_MI_INFO:
                this.operacionVerInfo();
                break;
            case VER_INFO_PERSONAL:
                this.operacionConsultarPersonal();
                break;
            case ADMINISTRAR_ACTAS:
                this.operacionAdministarActas();
                break;
            case AGREGAR_PERSONAL:
                this.operacionAgregarPersonal();
                break;
        }
    }
    
    public void operacionVerInfo(){
        PanelVerMiInfo info = new PanelVerMiInfo(padre, padre.getUsuarioLogueado());
        
        
        info.addComponentListener(this.manejadorPack);
        this.setVisible(false);
        this.padre.setContentPane(info);  
    }
    
    public void operacionConsultarPersonal() {
        PanelConsultandoPersonal consultar = new PanelConsultandoPersonal(padre);
        
        
        consultar.addComponentListener(this.manejadorPack);
        this.setVisible(false);
        this.padre.setContentPane(consultar);
        
    }
    
    public void operacionAdministarActas(){
        PanelAdministrarActas administrar = new PanelAdministrarActas(padre);
        
        
        administrar.addComponentListener(this.manejadorPack);
        this.setVisible(false);
        this.padre.setContentPane(administrar);
    }
    
    public void operacionAgregarPersonal(){
        PanelAgregarPersonal agregar = new PanelAgregarPersonal(padre);
        
        
        //agregar.addComponentListener(new ManejadorPanelComponente(padre, this, this.tamaño));
        agregar.addComponentListener(this.manejadorPack);
        this.setVisible(false);
        this.padre.setContentPane(agregar);
    }

    //resto del codigo
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        labelInformacion = new javax.swing.JLabel();
        comboBoxTareas = new javax.swing.JComboBox<>();
        botonCerrarSesion = new javax.swing.JButton();
        botonAceptar = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(800, 600));

        labelTitulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Bienvenido ...");

        labelInformacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelInformacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelInformacion.setText("Seleccione la actividad que desea realizar en el sistema");

        comboBoxTareas.setToolTipText("Seleccione la operación que desea realizar");
        comboBoxTareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTareasActionPerformed(evt);
            }
        });

        botonCerrarSesion.setText("Cerrar sesión");
        botonCerrarSesion.setToolTipText("Permite cerrar su sesión de usuario");
        botonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarSesionActionPerformed(evt);
            }
        });

        botonAceptar.setText("Aceptar");
        botonAceptar.setToolTipText("Ejecuta la acción seleccionada en el Combo Box");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelInformacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboBoxTareas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelInformacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxTareas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxTareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTareasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTareasActionPerformed

    private void botonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarSesionActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION) == 0) {
            this.setVisible(false);
        }

    }//GEN-LAST:event_botonCerrarSesionActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        this.accionBotonAceptar();
    }//GEN-LAST:event_botonAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCerrarSesion;
    private javax.swing.JComboBox<String> comboBoxTareas;
    private javax.swing.JLabel labelInformacion;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
