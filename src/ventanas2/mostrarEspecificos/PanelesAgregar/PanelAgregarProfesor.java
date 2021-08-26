
package ventanas2.mostrarEspecificos.PanelesAgregar;

public class PanelAgregarProfesor extends javax.swing.JPanel {

    public PanelAgregarProfesor() {
        initComponents();
    }
    
    public PanelAgregarProfesor(String titulo, String materia) {
        initComponents();
        
        setTitulo(titulo);
        setMateria(materia);
    }
    
    public void setTitulo(String titulo) {
        this.TextFieldTitulo.setText(titulo);
    }

    public void setMateria(String materia) {
        this.TextFieldMateria.setText(materia);
    }
    
    public String getTitulo(){
        return TextFieldTitulo.getText();
    }
    
    public String getMateria(){
        return TextFieldMateria.getText();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TextFieldTitulo = new javax.swing.JTextField();
        TextFieldMateria = new javax.swing.JTextField();

        jLabel1.setText("Ingrese titulo: ");

        jLabel2.setText("Ingrese materia a cargo");

        TextFieldTitulo.setToolTipText("Ingrese el título del profesor");
        TextFieldTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldTituloActionPerformed(evt);
            }
        });

        TextFieldMateria.setToolTipText("Ingrese la materia que tendrá a cargo el Profesor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(47, 47, 47))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(TextFieldMateria)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TextFieldMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TextFieldTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldTituloActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TextFieldMateria;
    private javax.swing.JTextField TextFieldTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
