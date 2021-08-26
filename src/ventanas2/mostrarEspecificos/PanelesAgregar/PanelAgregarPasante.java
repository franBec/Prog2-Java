package ventanas2.mostrarEspecificos.PanelesAgregar;

public class PanelAgregarPasante extends javax.swing.JPanel {

    public PanelAgregarPasante() {
        initComponents();
    }
    public PanelAgregarPasante(String tareas) {
        initComponents();
        
        setTareas(tareas);
    }
    public String getTareas(){
        return textFieldTareas.getText();
    }
    
    public void setTareas(String tareas) {
        this.textFieldTareas.setText(tareas);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldTareas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        textFieldTareas.setToolTipText("Ingrese las tarear que realizará el nuevo Pasante");

        jLabel1.setText("Agregar Tareas a Realizar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textFieldTareas)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 144, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(textFieldTareas, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField textFieldTareas;
    // End of variables declaration//GEN-END:variables
}
