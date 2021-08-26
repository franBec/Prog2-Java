package ventanas2;

import integrador.roles.Secretario;
import javax.swing.DefaultListModel;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class PanelAdministrarActas extends javax.swing.JPanel {

    private DefaultListModel<String> modelo;
    private MainVentana padre;
    private Secretario s;
    
    public PanelAdministrarActas() {
        initComponents();
    }
    
    public PanelAdministrarActas(MainVentana padre){
        initComponents();
        this.padre = padre;
        s = (Secretario)this.padre.getUsuarioLogueado();
        modelo = new DefaultListModel<>();
        this.CodigoLibroActas.setText(Integer.toString(s.getCodigoLibroActas()));
        llenadodeLista();
        ListaCodigos.setModel(modelo);
        
    }
    
    public void controlarNumerosCodigo(String numero) throws NumberFormatException {
        new Integer(numero);
    }

    public void llenadodeLista(){
        
        ArrayList<Integer> codigos = s.getCodigosAlMinisterio();
        for (int i : codigos)
            modelo.addElement(Integer.toString(i));  
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaCodigos = new javax.swing.JList<>();
        BotonAgregar = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        CodigoLibroActas = new javax.swing.JTextField();
        BotonVolver = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        CodigoaAgregar = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Administador de actas");

        jScrollPane1.setViewportView(ListaCodigos);

        BotonAgregar.setText("Agregar");
        BotonAgregar.setToolTipText("Permite agregar a la lista de códigos el número indicado en el campo a la izquierda");
        BotonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarActionPerformed(evt);
            }
        });

        BotonEliminar.setText("Eliminar");
        BotonEliminar.setToolTipText("Elimine el código para el Ministerio seleccionado en la lista");
        BotonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarActionPerformed(evt);
            }
        });

        jLabel2.setText("Numero del libro de actas:");

        CodigoLibroActas.setToolTipText("Permite cambiar el número del Libro de Actas a cargo");
        CodigoLibroActas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoLibroActasActionPerformed(evt);
            }
        });

        BotonVolver.setText("Volver");
        BotonVolver.setToolTipText("Regresa a la ventana anterior...");
        BotonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonVolverActionPerformed(evt);
            }
        });

        jLabel3.setText("Agregar codigo:");

        CodigoaAgregar.setToolTipText("Código de nota enviada al Ministerio");
        CodigoaAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoaAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CodigoLibroActas)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(64, 64, 64))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(BotonVolver)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CodigoaAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotonAgregar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotonEliminar)
                                .addGap(4, 4, 4)))
                        .addGap(35, 35, 35))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CodigoLibroActas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonEliminar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CodigoaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonAgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(BotonVolver)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BotonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarActionPerformed
        try {
        
        this.controlarNumerosCodigo(CodigoaAgregar.getText());
        if(!modelo.contains(CodigoaAgregar.getText()))
            this.modelo.addElement(CodigoaAgregar.getText());
        
        else
            JOptionPane.showMessageDialog(null, "No se pueden agregar codigos repetidos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El código de nota al Ministerio debe ser un número entero","Código invalido",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BotonAgregarActionPerformed

    private void BotonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonVolverActionPerformed
        ArrayList <Integer> arr = new ArrayList<>();
        while(!modelo.isEmpty())
            arr.add(new Integer(modelo.remove(0)));
        s.setCodigosAlMinisterio(arr);
        s.setCodigoLibroActas(new Integer(this.CodigoLibroActas.getText()));
        this.padre.lista.set(this.padre.lista.indexOfPersona(s), s);
        this.padre.setUsuarioLogueado(s);
        this.setVisible(false);
    }//GEN-LAST:event_BotonVolverActionPerformed

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarActionPerformed
       try{
           this.modelo.remove(this.ListaCodigos.getSelectedIndex());
       }
       catch (ArrayIndexOutOfBoundsException e){
           System.out.println(e.toString());
       }
    }//GEN-LAST:event_BotonEliminarActionPerformed

    private void CodigoaAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoaAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodigoaAgregarActionPerformed

    private void CodigoLibroActasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoLibroActasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodigoLibroActasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregar;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonVolver;
    private javax.swing.JTextField CodigoLibroActas;
    private javax.swing.JTextField CodigoaAgregar;
    private javax.swing.JList<String> ListaCodigos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
