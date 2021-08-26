package ventanas2.mostrarEspecificos.PanelesAgregar;

import integrador.horarios.Hora;
import integrador.horarios.Horario;
import integrador.horarios.HorarioSemana;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.DayOfWeek;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PanelAgregarPreceptor extends javax.swing.JPanel {

    public final String LUNES = "LUNES", MARTES = "MARTES", MIERCOLES = "MIERCOLES", JUEVES = "JUEVES", VIERNES = "VIERNES", SABADO = "SABADO", DOMINGO = "DOMINGO";
    private DefaultTableModel modeloTablaHorariosTrabajo;
    private HorarioSemana horariosTrabajo;
    
    public PanelAgregarPreceptor() {
        initComponents();
        horariosTrabajo = new HorarioSemana();
        inicializarTablaHorariosTrabajo();
        inicializarManejadorTablaHorarios();
    }
    
    public PanelAgregarPreceptor(HorarioSemana horariosSupervicion) {
        initComponents();
        horariosTrabajo = horariosSupervicion;
        inicializarTablaHorariosTrabajo();
        inicializarManejadorTablaHorarios();
        llenarTablaHorariosTrabajo();
    }
    
    public void setHorarioDeSupervicion(HorarioSemana horarioSupervicion) {
        this.horariosTrabajo = horarioSupervicion;
    }
    
    public HorarioSemana getHorarioDeSupervision(){
        return horariosTrabajo;
    }

    public void agregarHorarioTrabajo() {
        String dia = this.comboBoxDia.getItemAt(this.comboBoxDia.getSelectedIndex());
        DayOfWeek diaHorario = null;
        
        switch (dia) {
            case LUNES:
            {
                diaHorario = DayOfWeek.MONDAY;
            }
                break;
            case MARTES:
            {
                diaHorario = DayOfWeek.TUESDAY;
            }
            break;
            case MIERCOLES:
            {
                diaHorario = DayOfWeek.WEDNESDAY;
            }
            break;
            case JUEVES:
            {
                diaHorario = DayOfWeek.THURSDAY;
            }
            break;
            case VIERNES:
            {
                diaHorario = DayOfWeek.FRIDAY;
            }
            break;
            case SABADO:
            {
                diaHorario = DayOfWeek.SATURDAY;
            }
            break;
            case DOMINGO:
            {
                diaHorario = DayOfWeek.SUNDAY;
            }
            break;
            
        }
        
        Horario nuevo = new Horario(diaHorario, new Hora((Integer)this.spinnerHoraIngreso.getValue(),(Integer)this.spinnerMinutoIngreso.getValue()), new Hora((Integer)this.spinnerHoraSalida.getValue(), (Integer)this.spinnerMinutoSalida.getValue()));
        
        Iterator<Horario> iterador = this.horariosTrabajo.iteratorHorario();
        boolean agregar = true;
        
        
        if (nuevo.getHoraDeInicio().esMayor(nuevo.getHoraDeFin())) {
            JOptionPane.showMessageDialog(null, "El horario de entrada es mayor al horario de salida","Horario no valido",JOptionPane.INFORMATION_MESSAGE);
            agregar = false;
        }
        
        while (iterador.hasNext()) {
            Horario temporal = iterador.next();
            
            if (temporal.isSuperpuesto(nuevo)) {
                JOptionPane.showMessageDialog(null, "El horario que esta queriendo ingresar se superpone con otro\n"+nuevo.toString(),"Horario no valido",JOptionPane.INFORMATION_MESSAGE);
                agregar = false;
                break;
            }
            
        }
        
        if (agregar){ 
            this.horariosTrabajo.getRutina().add(nuevo);
            this.llenarTablaHorariosTrabajo(); 
        }
    }
    
    public void llenarTablaHorariosTrabajo() {
        Iterator<Horario> iterador = this.horariosTrabajo.getRutina().iterator();
        
        inicializarTablaHorariosTrabajo();
        
        while (iterador.hasNext()) {
            Horario temporal = iterador.next();
            
            this.modeloTablaHorariosTrabajo.addRow(new String[] {temporal.pasarDiaAEspañol(),temporal.getHoraDeInicio().toString(),temporal.getHoraDeFin().toString()});
        }
    }
    
    public void inicializarTablaHorariosTrabajo() {
        this.modeloTablaHorariosTrabajo = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        ) {
        @Override
            public boolean isCellEditable(int row, int column) {
            return false;
        }};
        
        
        this.TablaHorariosTrabajo.setModel(modeloTablaHorariosTrabajo);
        
        this.TablaHorariosTrabajo.setRowSelectionAllowed(true);
        this.TablaHorariosTrabajo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.TablaHorariosTrabajo.setColumnSelectionAllowed(false);
    }
    
    public void inicializarManejadorTablaHorarios() {
        this.TablaHorariosTrabajo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                if (e.getClickCount()==2 && e.getSource().equals(TablaHorariosTrabajo)) {
                    int eleccion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el horario?", "Eliminar horario", JOptionPane.YES_NO_OPTION);
                    
                    if (eleccion==0) {
                        horariosTrabajo.getRutina().remove(TablaHorariosTrabajo.getSelectedRow());
                        modeloTablaHorariosTrabajo.removeRow(TablaHorariosTrabajo.getSelectedRow());
                    }
                    
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
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollPaneTabla = new javax.swing.JScrollPane();
        TablaHorariosTrabajo = new javax.swing.JTable();
        panelAgregarHorario = new javax.swing.JPanel();
        labelDia = new javax.swing.JLabel();
        comboBoxDia = new javax.swing.JComboBox<>();
        labelHoraIngreso = new javax.swing.JLabel();
        spinnerHoraIngreso = new javax.swing.JSpinner();
        spinnerMinutoIngreso = new javax.swing.JSpinner();
        labelHoraSalida = new javax.swing.JLabel();
        spinnerHoraSalida = new javax.swing.JSpinner();
        spinnerMinutoSalida = new javax.swing.JSpinner();
        botonAgregarHorario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        TablaHorariosTrabajo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        ));
        TablaHorariosTrabajo.setToolTipText("Tabla de los horarios que el Preceptor se encuentra en la Dirección institucional.\n\n-- Realize Doble Click sobre un horario ingresado para eliminarlo");
        ScrollPaneTabla.setViewportView(TablaHorariosTrabajo);

        labelDia.setText("Dia");

        comboBoxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO" }));

        labelHoraIngreso.setText("Hora de ingreso");

        spinnerHoraIngreso.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        spinnerMinutoIngreso.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        labelHoraSalida.setText("Hora de salida");

        spinnerHoraSalida.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        spinnerMinutoSalida.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        botonAgregarHorario.setText("Agregar");
        botonAgregarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarHorarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAgregarHorarioLayout = new javax.swing.GroupLayout(panelAgregarHorario);
        panelAgregarHorario.setLayout(panelAgregarHorarioLayout);
        panelAgregarHorarioLayout.setHorizontalGroup(
            panelAgregarHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarHorarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxDia, 0, 62, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelHoraIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerHoraIngreso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerMinutoIngreso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerHoraSalida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerMinutoSalida)
                .addGap(29, 29, 29)
                .addComponent(botonAgregarHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelAgregarHorarioLayout.setVerticalGroup(
            panelAgregarHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarHorarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAgregarHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDia)
                    .addComponent(comboBoxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHoraIngreso)
                    .addComponent(spinnerHoraIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerMinutoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHoraSalida)
                    .addComponent(spinnerHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerMinutoSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAgregarHorario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Horario de Supervision");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneTabla)
            .addComponent(panelAgregarHorario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAgregarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonAgregarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarHorarioActionPerformed
        agregarHorarioTrabajo();
    }//GEN-LAST:event_botonAgregarHorarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPaneTabla;
    private javax.swing.JTable TablaHorariosTrabajo;
    private javax.swing.JButton botonAgregarHorario;
    private javax.swing.JComboBox<String> comboBoxDia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelDia;
    private javax.swing.JLabel labelHoraIngreso;
    private javax.swing.JLabel labelHoraSalida;
    private javax.swing.JPanel panelAgregarHorario;
    private javax.swing.JSpinner spinnerHoraIngreso;
    private javax.swing.JSpinner spinnerHoraSalida;
    private javax.swing.JSpinner spinnerMinutoIngreso;
    private javax.swing.JSpinner spinnerMinutoSalida;
    // End of variables declaration//GEN-END:variables
}
