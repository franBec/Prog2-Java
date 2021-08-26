//si la componente del panel finaliza en 4, pertenece a la parte "horario atencion al publico"
//si no, la componente pertenece a la parte "horario en direccion"
//no recuerdo porque lo de la finalizacion en 4, pero ya quedó asi

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

public class PanelAgregarDirectivo extends javax.swing.JPanel {

    public final String LUNES = "LUNES", MARTES = "MARTES", MIERCOLES = "MIERCOLES", JUEVES = "JUEVES", VIERNES = "VIERNES", SABADO = "SABADO", DOMINGO = "DOMINGO";
    private DefaultTableModel modeloTablaHorariosTrabajo, modeloTablaHorariosTrabajo4;
    private HorarioSemana horariosTrabajo, horariosTrabajo4;
    
    public PanelAgregarDirectivo() {
        initComponents();
        horariosTrabajo = new HorarioSemana();
        horariosTrabajo4 = new HorarioSemana();
        inicializarTablaHorariosTrabajo();
        inicializarTablaHorariosTrabajo4();
        
        inicializarManejadorTablaHorarios();
        inicializarManejadorTablaHorarios4();
        
    }
    
    public HorarioSemana getHorarioEnDireccion(){
        return horariosTrabajo;
    }

    public HorarioSemana getHorarioDeAtencion(){
        return horariosTrabajo4;
    }
    
    public void setHorarioEnDireccion(HorarioSemana horarioDireccion){
        this.horariosTrabajo = horarioDireccion;
    }

    public void setHorarioDeAtencion(HorarioSemana horarioAtencion){
        this.horariosTrabajo4 = horarioAtencion;
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
            this.llenarTablaHorariosEnDireccion(); 
        }
    }
    
    public void llenarTablaHorariosEnDireccion() {
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
    
    public void inicializarManejadorTablaHorarios4() {
        this.TablaHorariosTrabajo1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                if (e.getClickCount()==2 && e.getSource().equals(TablaHorariosTrabajo1)) {
                    int eleccion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el horario?", "Eliminar horario", JOptionPane.YES_NO_OPTION);
                    
                    if (eleccion==0) {
                        horariosTrabajo4.getRutina().remove(TablaHorariosTrabajo1.getSelectedRow());
                        modeloTablaHorariosTrabajo4.removeRow(TablaHorariosTrabajo1.getSelectedRow());
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
       
    public void agregarHorarioTrabajo4() {
        String dia = this.comboBoxDia4.getItemAt(this.comboBoxDia4.getSelectedIndex());
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
        
        Horario nuevo = new Horario(diaHorario, new Hora((Integer)this.spinnerHoraIngreso4.getValue(),(Integer)this.spinnerMinutoIngreso4.getValue()), new Hora((Integer)this.spinnerHoraSalida4.getValue(), (Integer)this.spinnerMinutoSalida4.getValue()));
        
        Iterator<Horario> iterador = this.horariosTrabajo4.iteratorHorario();
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
            this.horariosTrabajo4.getRutina().add(nuevo);
            this.llenarTablaHorariosDeAtencion(); 
        }
    }
    
    public void llenarTablaHorariosDeAtencion() {
        Iterator<Horario> iterador = this.horariosTrabajo4.getRutina().iterator();
        
        inicializarTablaHorariosTrabajo4();
        
        while (iterador.hasNext()) {
            Horario temporal = iterador.next();
            
            this.modeloTablaHorariosTrabajo4.addRow(new String[] {temporal.pasarDiaAEspañol(),temporal.getHoraDeInicio().toString(),temporal.getHoraDeFin().toString()});
        }
    }
    
    public void inicializarTablaHorariosTrabajo4() {
        this.modeloTablaHorariosTrabajo4 = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        ) {
        @Override
            public boolean isCellEditable(int row, int column) {
            return false;
        }};
        
        
        this.TablaHorariosTrabajo1.setModel(modeloTablaHorariosTrabajo4);
        this.TablaHorariosTrabajo1.setRowSelectionAllowed(true);
        this.TablaHorariosTrabajo1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.TablaHorariosTrabajo1.setColumnSelectionAllowed(false);
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
        botonAgregar = new javax.swing.JButton();
        ScrollPaneTabla4 = new javax.swing.JScrollPane();
        TablaHorariosTrabajo1 = new javax.swing.JTable();
        panelAgregarHorario4 = new javax.swing.JPanel();
        labelDia4 = new javax.swing.JLabel();
        comboBoxDia4 = new javax.swing.JComboBox<>();
        labelHoraIngreso4 = new javax.swing.JLabel();
        spinnerHoraIngreso4 = new javax.swing.JSpinner();
        spinnerMinutoIngreso4 = new javax.swing.JSpinner();
        labelHoraSalida4 = new javax.swing.JLabel();
        spinnerHoraSalida4 = new javax.swing.JSpinner();
        spinnerMinutoSalida4 = new javax.swing.JSpinner();
        botonAgregar4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        TablaHorariosTrabajo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        ));
        TablaHorariosTrabajo.setToolTipText("Tabla de los horarios que el Personal Directivo se encuentra en la Dirección institucional.\n\n-- Realize Doble Click sobre un horario ingresado para eliminarlo");
        ScrollPaneTabla.setViewportView(TablaHorariosTrabajo);

        labelDia.setText("Dia");

        comboBoxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO" }));
        comboBoxDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDiaActionPerformed(evt);
            }
        });

        labelHoraIngreso.setText("Hora de ingreso");

        spinnerHoraIngreso.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        spinnerMinutoIngreso.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        labelHoraSalida.setText("Hora de salida");

        spinnerHoraSalida.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        spinnerMinutoSalida.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
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
                .addComponent(comboBoxDia, 0, 95, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelHoraIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerHoraIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerMinutoIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerHoraSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerMinutoSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
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
                    .addComponent(botonAgregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TablaHorariosTrabajo1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        ));
        TablaHorariosTrabajo1.setToolTipText("Tabla de los horarios que el Personal Directivo realiza atención al publico en la institución.\n\n-- Realize Doble Click sobre un horario ingresado para eliminarlo");
        ScrollPaneTabla4.setViewportView(TablaHorariosTrabajo1);

        labelDia4.setText("Dia");

        comboBoxDia4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO" }));

        labelHoraIngreso4.setText("Hora de ingreso");

        spinnerHoraIngreso4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        spinnerMinutoIngreso4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        labelHoraSalida4.setText("Hora de salida");

        spinnerHoraSalida4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        spinnerMinutoSalida4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        botonAgregar4.setText("Agregar");
        botonAgregar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregar4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAgregarHorario4Layout = new javax.swing.GroupLayout(panelAgregarHorario4);
        panelAgregarHorario4.setLayout(panelAgregarHorario4Layout);
        panelAgregarHorario4Layout.setHorizontalGroup(
            panelAgregarHorario4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarHorario4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDia4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxDia4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelHoraIngreso4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerHoraIngreso4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerMinutoIngreso4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelHoraSalida4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerHoraSalida4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerMinutoSalida4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonAgregar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelAgregarHorario4Layout.setVerticalGroup(
            panelAgregarHorario4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarHorario4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAgregarHorario4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDia4)
                    .addComponent(comboBoxDia4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHoraIngreso4)
                    .addComponent(spinnerHoraIngreso4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerMinutoIngreso4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHoraSalida4)
                    .addComponent(spinnerHoraSalida4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerMinutoSalida4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAgregar4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Horarios de atención al publico");

        jLabel2.setText("Horarios en direccion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneTabla)
            .addComponent(panelAgregarHorario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ScrollPaneTabla4)
            .addComponent(panelAgregarHorario4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(ScrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAgregarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPaneTabla4, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAgregarHorario4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        agregarHorarioTrabajo();
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void botonAgregar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregar4ActionPerformed
        agregarHorarioTrabajo4();
    }//GEN-LAST:event_botonAgregar4ActionPerformed

    private void comboBoxDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDiaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPaneTabla;
    private javax.swing.JScrollPane ScrollPaneTabla4;
    private javax.swing.JTable TablaHorariosTrabajo;
    private javax.swing.JTable TablaHorariosTrabajo1;
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonAgregar4;
    private javax.swing.JComboBox<String> comboBoxDia;
    private javax.swing.JComboBox<String> comboBoxDia4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelDia;
    private javax.swing.JLabel labelDia4;
    private javax.swing.JLabel labelHoraIngreso;
    private javax.swing.JLabel labelHoraIngreso4;
    private javax.swing.JLabel labelHoraSalida;
    private javax.swing.JLabel labelHoraSalida4;
    private javax.swing.JPanel panelAgregarHorario;
    private javax.swing.JPanel panelAgregarHorario4;
    private javax.swing.JSpinner spinnerHoraIngreso;
    private javax.swing.JSpinner spinnerHoraIngreso4;
    private javax.swing.JSpinner spinnerHoraSalida;
    private javax.swing.JSpinner spinnerHoraSalida4;
    private javax.swing.JSpinner spinnerMinutoIngreso;
    private javax.swing.JSpinner spinnerMinutoIngreso4;
    private javax.swing.JSpinner spinnerMinutoSalida;
    private javax.swing.JSpinner spinnerMinutoSalida4;
    // End of variables declaration//GEN-END:variables
}
