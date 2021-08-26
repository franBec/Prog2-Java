package ventanas2.mostrarEspecificos.PanelesAgregar;

public class PanelAgregarSecretario extends javax.swing.JPanel {

    public PanelAgregarSecretario() {
        initComponents();
    }

    public PanelAgregarSecretario(int codigoLibro) {
        initComponents();
        
        setCodigoDeLibro(codigoLibro);
    }

    public String getCodigoDeLibro(){
        return TextFieldCodigoDelLibro.getText();
    }
    
    public void setCodigoDeLibro(int codigoLibro) {
        this.TextFieldCodigoDelLibro.setText(Integer.toString(codigoLibro));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TextFieldCodigoDelLibro = new javax.swing.JTextField();

        jLabel1.setText("Agregar el código del libro de actas:");

        TextFieldCodigoDelLibro.setToolTipText("Ingrese el número del Libro de Actas a cargo del Secretario");
        TextFieldCodigoDelLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldCodigoDelLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextFieldCodigoDelLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextFieldCodigoDelLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TextFieldCodigoDelLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldCodigoDelLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldCodigoDelLibroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TextFieldCodigoDelLibro;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
