package ventanas2.mostrarEspecificos.PanelesMostrar;

import integrador.roles.Pasante;

public class PanelMostrarPasante extends javax.swing.JPanel {
    
    private String tareas;
    //constructores
    public PanelMostrarPasante() {
        initComponents();
        tareas = "";
    }
    
    public PanelMostrarPasante(Pasante pasante) {
        initComponents();
        tareas = pasante.getTareasARealizar();
        this.labelTareas.setText("Tareas: "+tareas);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTareas = new javax.swing.JLabel();

        labelTareas.setText("jLabel1");
        labelTareas.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTareas, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTareas, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelTareas;
    // End of variables declaration//GEN-END:variables
}
