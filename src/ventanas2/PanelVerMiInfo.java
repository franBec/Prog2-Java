package ventanas2;

import Manejadores.ManejadorPanelComponente;
import integrador.horarios.Horario;
import integrador.roles.Directivo;
import integrador.roles.Pasante;
import integrador.roles.Persona;
import integrador.roles.Preceptor;
import integrador.roles.Profesor;
import integrador.roles.Secretario;
import java.awt.CardLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import ventanas2.mostrarEspecificos.PanelesMostrar.PanelMostrarDirectivo;
import ventanas2.mostrarEspecificos.PanelesMostrar.PanelMostrarPasante;
import ventanas2.mostrarEspecificos.PanelesMostrar.PanelMostrarPreceptor;
import ventanas2.mostrarEspecificos.PanelesMostrar.PanelMostrarProfesor;
import ventanas2.mostrarEspecificos.PanelesMostrar.PanelMostrarSecretario;

public class PanelVerMiInfo extends javax.swing.JPanel {

    //variables privadas    
    private MainVentana padre;
    private Persona usuario;
    
    //constructores
    public PanelVerMiInfo() {
        initComponents();
        padre = null;
        usuario = null;
        //this.configurarJFrame();
    }
    
    public PanelVerMiInfo(MainVentana padre,Persona usuario) {
        initComponents();
        this.padre = padre;
        this.usuario = usuario;

        this.configurarJFrame();        //configurar ventana padre
        this.configurarInformacion();   //configurar la informacion
        this.configurarBotonEliminar();
        
        switch(usuario.getRol()) {
            case PASANTE:
            {   
                Pasante p = (Pasante) usuario;  //downcast
                this.crearPanelInfoPasante(p);
            }
            break;
            case PROFESOR:
            {
                Profesor p = (Profesor) usuario;
                this.crearPanelInfoProfesor(p);
            }
            break;
            case SECRETARIO:
            {
                Secretario s = (Secretario) usuario;
                this.crearPanelInfoSecretario(s);
            }
            break;
            case PRECEPTOR:
            {
                Preceptor p = (Preceptor) usuario;
                this.crearPanelInfoPreceptor(p);
            }
            break;
            case DIRECTIVO:
            {
                Directivo d = (Directivo) usuario;
                this.crearPanelInfoDirectivo(d);
            }
        }
    }
    
    //funciones    
    public void configurarJFrame() {
        this.padre.setTitle("Informacion completa");
        this.padre.setSize(this.getPreferredSize());
        this.padre.setResizable(false);
        this.padre.setLocationRelativeTo(null);
    }
    
    public void configurarBotonEliminar() {
        //boton eliminar unicamente visible para directivo
        Directivo directivo = new Directivo();
        
        if (this.padre.getUsuarioLogueado().getClass()!=directivo.getClass()) {
            this.botonEliminar.setVisible(false);
            this.botonModificar.setVisible(false);
        }
        if (this.padre.getUsuarioLogueado().equals(this.usuario)) {
            this.botonEliminar.setEnabled(false);
            this.botonModificar.setEnabled(false);
        }
        
    }
    
    public void configurarInformacion() {
        this.labelNombreYApellido.setText("Nombre y Apellido: "+usuario.getNombreyApellido());
        this.labelDNI.setText("DNI: "+usuario.getDni());
        this.labelDomicilio.setText("Domicilio: "+usuario.getDomicilio());
        this.labelUsuario.setText("Usuario: "+usuario.getNombreDeUsuario());
        DefaultTableModel modeloTablaHorariosTrabajo = new javax.swing.table.DefaultTableModel(
            new Object [][] {
            
            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        );
        
        for (Horario hora : usuario.getHorarioTrabajo().getRutina()){
            modeloTablaHorariosTrabajo.addRow(new String[] {hora.pasarDiaAEspañol(),hora.getHoraDeInicio().toString(),hora.getHoraDeFin().toString()});
        }
        this.TablaHorariosTrabajo.setModel(modeloTablaHorariosTrabajo);
    }
    
    public void crearPanelInfoPasante(Pasante pasante) {
        PanelMostrarPasante nuevo = new PanelMostrarPasante(pasante);
        
        nuevo.setSize(this.PanelTipoUsuario.getSize());
        
        this.PanelTipoUsuario.removeAll();
        this.PanelTipoUsuario.setLayout(new CardLayout());
        this.PanelTipoUsuario.add(nuevo);
        
        
        this.padre.validate();
    }
    
    public void crearPanelInfoProfesor(Profesor profesor) {
        PanelMostrarProfesor nuevo = new PanelMostrarProfesor(profesor);
        
        nuevo.setSize(this.PanelTipoUsuario.getSize());
        
        this.PanelTipoUsuario.removeAll();
        this.PanelTipoUsuario.setLayout(new CardLayout());
        this.PanelTipoUsuario.add(nuevo);
        
        this.padre.validate();
    }
    
    public void crearPanelInfoSecretario(Secretario secretario) {
        PanelMostrarSecretario nuevo = new PanelMostrarSecretario(secretario);
        
        nuevo.setSize(this.PanelTipoUsuario.getSize());
        
        this.PanelTipoUsuario.removeAll();
        this.PanelTipoUsuario.setLayout(new CardLayout());
        this.PanelTipoUsuario.add(nuevo);
        
        this.padre.validate();
    }
    
    public void crearPanelInfoPreceptor(Preceptor preceptor) {
        PanelMostrarPreceptor nuevo = new PanelMostrarPreceptor(preceptor);
        
        nuevo.setSize(this.PanelTipoUsuario.getSize());
        
        this.PanelTipoUsuario.removeAll();
        this.PanelTipoUsuario.setLayout(new CardLayout());
        this.PanelTipoUsuario.add(nuevo);
        
        this.padre.validate();
    }
    
    
    public void crearPanelInfoDirectivo(Directivo directivo) {
        PanelMostrarDirectivo nuevo = new PanelMostrarDirectivo(directivo);
        
        nuevo.setSize(this.PanelTipoUsuario.getSize());
        
        this.PanelTipoUsuario.removeAll();
        this.PanelTipoUsuario.setLayout(new CardLayout());
        this.PanelTipoUsuario.add(nuevo);
        
        this.padre.validate();
    }
    
    public void accionBotonRegresar() {
        this.setVisible(false);
    }
    
    public void accionBotonEliminar() {
        int eleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminarlo del sistema?","Confirmación",JOptionPane.YES_NO_OPTION);
        
        if (eleccion == 0) {
            String nombre = usuario.getNombreyApellido(),dni = Integer.toString(usuario.getDni());
            
            this.padre.lista.remove(this.padre.lista.buscarPersonaPorDni(usuario.getDni()));
            
            JOptionPane.showMessageDialog(null, "Se ha eliminado exitosamente a "+nombre+", con DNI:"+dni);
            this.accionBotonRegresar();
        }
    }
    
    public void accionBotonModificar() {
        PanelAgregarPersonal modificar = new PanelAgregarPersonal(this.padre, this.usuario);
        
        //actualiza los datos despues de salir de la ventana de modificar la informacion
        JPanel estePanel = this;
        modificar.addComponentListener(new ManejadorPanelComponente(this.padre, this, this.getPreferredSize())
                {
                    @Override
                    public void componentHidden(ComponentEvent e) {
                    padre.setContentPane(estePanel);
                    padre.setSize(getPreferredSize());
                    padre.setLocationRelativeTo(null);
                    configurarInformacion();
                    estePanel.setVisible(true);
                    }
                }
        );
        
        this.setVisible(false);
        this.padre.setContentPane(modificar);
        modificar.setVisible(true);
    }
    
    //resto del codigo
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        PanelGenerico = new javax.swing.JPanel();
        labelNombreYApellido = new javax.swing.JLabel();
        labelDNI = new javax.swing.JLabel();
        labelDomicilio = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        ScrollPaneTabla = new javax.swing.JScrollPane();
        TablaHorariosTrabajo = new javax.swing.JTable();
        labelHorarios = new javax.swing.JLabel();
        PanelTipoUsuario = new javax.swing.JPanel();
        botonRegresar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();

        labelTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Información");

        labelNombreYApellido.setText("Nombre y Apellido:");

        labelDNI.setText("DNI: ");

        labelDomicilio.setText("Domicilio");

        labelUsuario.setText("Usuario");

        TablaHorariosTrabajo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        ));
        TablaHorariosTrabajo.setToolTipText("Tabla de horarios de Trabajo");
        TablaHorariosTrabajo.setEnabled(false);
        ScrollPaneTabla.setViewportView(TablaHorariosTrabajo);

        labelHorarios.setText("Horarios de Trabajo");

        javax.swing.GroupLayout PanelGenericoLayout = new javax.swing.GroupLayout(PanelGenerico);
        PanelGenerico.setLayout(PanelGenericoLayout);
        PanelGenericoLayout.setHorizontalGroup(
            PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
            .addGroup(PanelGenericoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelUsuario)
                    .addComponent(labelDNI)
                    .addComponent(labelNombreYApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHorarios))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelGenericoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelDNI, labelDomicilio, labelHorarios, labelNombreYApellido, labelUsuario});

        PanelGenericoLayout.setVerticalGroup(
            PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGenericoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNombreYApellido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDNI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDomicilio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelHorarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelGenericoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelDNI, labelDomicilio, labelHorarios, labelNombreYApellido, labelUsuario});

        javax.swing.GroupLayout PanelTipoUsuarioLayout = new javax.swing.GroupLayout(PanelTipoUsuario);
        PanelTipoUsuario.setLayout(PanelTipoUsuarioLayout);
        PanelTipoUsuarioLayout.setHorizontalGroup(
            PanelTipoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelTipoUsuarioLayout.setVerticalGroup(
            PanelTipoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 339, Short.MAX_VALUE)
        );

        botonRegresar.setText("Regresar");
        botonRegresar.setToolTipText("Vuelve a la ventana anterior");
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        botonEliminar.setText("Eliminar");
        botonEliminar.setToolTipText("Permite eliminar la persona del sistema");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonModificar.setText("Modificar Información");
        botonModificar.setToolTipText("Permite modificar la información registrada de la persona y guardar los cambios en el sistema");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelGenerico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(PanelTipoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botonRegresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(labelTitulo)
                .addGap(18, 18, 18)
                .addComponent(PanelGenerico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTipoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonRegresar))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.accionBotonRegresar();
    }//GEN-LAST:event_botonRegresarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        this.accionBotonEliminar();
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        this.accionBotonModificar();
    }//GEN-LAST:event_botonModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelGenerico;
    private javax.swing.JPanel PanelTipoUsuario;
    private javax.swing.JScrollPane ScrollPaneTabla;
    private javax.swing.JTable TablaHorariosTrabajo;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonRegresar;
    private javax.swing.JLabel labelDNI;
    private javax.swing.JLabel labelDomicilio;
    private javax.swing.JLabel labelHorarios;
    private javax.swing.JLabel labelNombreYApellido;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUsuario;
    // End of variables declaration//GEN-END:variables
}
