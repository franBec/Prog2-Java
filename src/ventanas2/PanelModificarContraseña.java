package ventanas2;
import integrador.ContraseñaSizeException;
import integrador.roles.Persona;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class PanelModificarContraseña extends javax.swing.JPanel {
    //variables del panel
    private MainVentana padre;
    
    //constructores
    public PanelModificarContraseña() {
        initComponents();
    }

    public PanelModificarContraseña(MainVentana padre){
        initComponents();
        this.padre = padre;
        this.padre.setSize(this.getPreferredSize());
        this.padre.setTitle("Modificar contraseña");
    }

    //funciones del panel
    public Persona buscarUsuario(String usuario) {
        Iterator<Persona> iterador = this.padre.lista.iterator();
        Persona temporal = null;
        while (iterador.hasNext()) {
            Persona tempo = iterador.next();
            if (tempo.getNombreDeUsuario().equals(this.textFieldUsuario.getText())) {
                temporal = tempo;
                break;
            }
        }
        return temporal;
    }
    
    public boolean verificarContraseña(Persona persona) throws NumberFormatException, ContraseñaSizeException {
        String temporal = new String(this.textFieldContraseña.getPassword());
        if(temporal.length()!=4)
            throw new ContraseñaSizeException();
        int contraseña = new Integer(temporal);
        return persona.getContrasena() == contraseña;
    }
    
    public boolean compararContraseñasNuevas() throws NumberFormatException {
        String primera = new String(this.textFieldNuevaContraseña.getPassword()),
        segunda = new String(this.textFieldConfirmarNuevaContraseña.getPassword());
        int primeraContraseña = new Integer(primera);
        int segundaContraseña = new Integer(segunda);
        return primeraContraseña == segundaContraseña;
    }
    
    private void accionBotonAceptar() {
            try {
            Persona usuario = this.buscarUsuario(this.textFieldUsuario.getText());
            if (usuario != null) {
                if (this.verificarContraseña(usuario)) {
                    if (this.compararContraseñasNuevas()) {
                        //si todo es correcto, aplica la nueva contraseña
                        String nuevaContraseña = new String(this.textFieldNuevaContraseña.getPassword());
                        int contraseñaInt = new Integer(nuevaContraseña);
                        int indice = this.padre.lista.indexOfPersona(usuario);  //buscar usuario
                        this.padre.lista.remove(indice);                        //sacar usuario
                        usuario.setContrasena(contraseñaInt);
                        this.padre.lista.add(usuario);                          //meter usuario nuevo
                        //this.padre.lista.get(this.padre.lista.indexOfPersona(usuario)).setContrasena(contraseñaInt);
                        this.setVisible(false);                                 //sale de la ventana
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Las contraseñas son distintas","Error",JOptionPane.ERROR_MESSAGE);  
                }
                else
                    JOptionPane.showMessageDialog(null, "Verifique la contraseña","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
            }
            else 
                JOptionPane.showMessageDialog(null, "El usuario ingresado no existe","Usuario invalido",JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Las contraseñas solo pueden estar compuestas por números","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
        }
        catch (ContraseñaSizeException e){
            JOptionPane.showMessageDialog(null, "La contraseña es un pin numerico de longitud 4","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
        } 
    }
    //resto del codigo
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelContraseña = new javax.swing.JLabel();
        labelNuevaContraseña = new javax.swing.JLabel();
        labelConfirmarNuevaContraseña = new javax.swing.JLabel();
        textFieldContraseña = new javax.swing.JPasswordField();
        textFieldNuevaContraseña = new javax.swing.JPasswordField();
        textFieldConfirmarNuevaContraseña = new javax.swing.JPasswordField();
        botonCancelar = new javax.swing.JButton();
        botonAceptar = new javax.swing.JButton();
        labelTitulo = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        textFieldUsuario = new javax.swing.JTextField();

        labelContraseña.setText("Contraseña");

        labelNuevaContraseña.setText("Nueva Contraseña");

        labelConfirmarNuevaContraseña.setText("Confirmar nueva contraseña");

        textFieldContraseña.setToolTipText("Ingrese aquí su antigua contraseña");

        textFieldNuevaContraseña.setToolTipText("Escriba aquí su nueva contraseña");

        textFieldConfirmarNuevaContraseña.setToolTipText("Confirme su nueva contraseña");

        botonCancelar.setText("Cancelar");
        botonCancelar.setToolTipText("Cancelar la operación");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonAceptar.setText("Aceptar");
        botonAceptar.setToolTipText("Finaliza la operación");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        labelTitulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Modificar contraseña");

        labelUsuario.setText("Usuario");

        textFieldUsuario.setToolTipText("Ingrese aquí su nombre de usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelNuevaContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelConfirmarNuevaContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldNuevaContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldConfirmarNuevaContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelConfirmarNuevaContraseña, labelContraseña, labelNuevaContraseña, labelUsuario});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {textFieldConfirmarNuevaContraseña, textFieldContraseña, textFieldNuevaContraseña, textFieldUsuario});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonAceptar, botonCancelar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelContraseña))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNuevaContraseña)
                    .addComponent(textFieldNuevaContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelConfirmarNuevaContraseña)
                    .addComponent(textFieldConfirmarNuevaContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCancelar))
                .addGap(50, 50, 50))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelConfirmarNuevaContraseña, labelContraseña, labelNuevaContraseña, labelUsuario});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {textFieldConfirmarNuevaContraseña, textFieldContraseña, textFieldNuevaContraseña, textFieldUsuario});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botonAceptar, botonCancelar});

    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        this.accionBotonAceptar();
    }//GEN-LAST:event_botonAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JLabel labelConfirmarNuevaContraseña;
    private javax.swing.JLabel labelContraseña;
    private javax.swing.JLabel labelNuevaContraseña;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPasswordField textFieldConfirmarNuevaContraseña;
    private javax.swing.JPasswordField textFieldContraseña;
    private javax.swing.JPasswordField textFieldNuevaContraseña;
    private javax.swing.JTextField textFieldUsuario;
    // End of variables declaration//GEN-END:variables
}
