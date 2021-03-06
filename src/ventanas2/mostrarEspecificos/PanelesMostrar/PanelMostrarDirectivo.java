/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas2.mostrarEspecificos.PanelesMostrar;

/**
 *
 * @author franb
 */


import integrador.horarios.Horario;
import integrador.horarios.HorarioSemana;
import integrador.roles.Directivo;
import javax.swing.table.DefaultTableModel;

public class PanelMostrarDirectivo extends javax.swing.JPanel {

    /**
     * Creates new form PanelInfoDirectivo
     */
    
    private HorarioSemana horarioEnDireccion, horarioAtencionPublico;
    
    public PanelMostrarDirectivo() {
        initComponents();
        
        horarioAtencionPublico = new HorarioSemana();
        horarioEnDireccion = new HorarioSemana();
        
        
        DefaultTableModel modeloTablaHorariosAtencionPublico = new javax.swing.table.DefaultTableModel(
            new Object [][] {
            
            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        );

        this.TablaHorariosAtencionPublico.setModel(modeloTablaHorariosAtencionPublico);
        
        DefaultTableModel modeloTablaHorariosEnDireccion = new javax.swing.table.DefaultTableModel(
            new Object [][] {
            
            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        );

        this.TablaHorariosEnDireccion.setModel(modeloTablaHorariosEnDireccion);
    }
    
    
    public PanelMostrarDirectivo(Directivo directivo) {
        initComponents();
        
        this.horarioAtencionPublico = directivo.getHorarioAtencionPublico();
        this.horarioEnDireccion = directivo.getHorarioEnDireccion();
        
        DefaultTableModel modeloTablaHorariosAtencionPublico = new javax.swing.table.DefaultTableModel(
            new Object [][] {
            
            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        );
        
        for (Horario hora : this.horarioAtencionPublico.getRutina() ) {
            modeloTablaHorariosAtencionPublico.addRow(new String[] {hora.pasarDiaAEspa??ol(),hora.getHoraDeInicio().toString(),hora.getHoraDeFin().toString()});
        }
        this.TablaHorariosAtencionPublico.setModel(modeloTablaHorariosAtencionPublico);
        
        DefaultTableModel modeloTablaHorariosEnDireccion = new javax.swing.table.DefaultTableModel(
            new Object [][] {
            
            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        );
        
        for (Horario hora : this.horarioEnDireccion.getRutina() ) {
            modeloTablaHorariosEnDireccion.addRow(new String[] {hora.pasarDiaAEspa??ol(),hora.getHoraDeInicio().toString(),hora.getHoraDeFin().toString()});
        }
        this.TablaHorariosEnDireccion.setModel(modeloTablaHorariosEnDireccion);
        
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelHorarioAtencionPublico = new javax.swing.JLabel();
        ScrollPaneTabla = new javax.swing.JScrollPane();
        TablaHorariosAtencionPublico = new javax.swing.JTable();
        ScrollPaneTabla1 = new javax.swing.JScrollPane();
        TablaHorariosEnDireccion = new javax.swing.JTable();
        labelHorarioEnDireccion = new javax.swing.JLabel();

        labelHorarioAtencionPublico.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelHorarioAtencionPublico.setText("Horarios de Atenci??n al P??blico:");
        labelHorarioAtencionPublico.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        TablaHorariosAtencionPublico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        ));
        TablaHorariosAtencionPublico.setEnabled(false);
        ScrollPaneTabla.setViewportView(TablaHorariosAtencionPublico);

        TablaHorariosEnDireccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        ));
        TablaHorariosEnDireccion.setEnabled(false);
        ScrollPaneTabla1.setViewportView(TablaHorariosEnDireccion);

        labelHorarioEnDireccion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelHorarioEnDireccion.setText("Horarios en Direcci??n:");
        labelHorarioEnDireccion.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelHorarioAtencionPublico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ScrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(labelHorarioEnDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ScrollPaneTabla1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelHorarioAtencionPublico, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelHorarioEnDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPaneTabla1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPaneTabla;
    private javax.swing.JScrollPane ScrollPaneTabla1;
    private javax.swing.JTable TablaHorariosAtencionPublico;
    private javax.swing.JTable TablaHorariosEnDireccion;
    private javax.swing.JLabel labelHorarioAtencionPublico;
    private javax.swing.JLabel labelHorarioEnDireccion;
    // End of variables declaration//GEN-END:variables
}
