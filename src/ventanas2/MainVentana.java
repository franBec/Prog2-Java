package ventanas2;

import Manejadores.ManejadorPanelComponente;
import Manejadores.ManejadorVentanaCerrada;
import integrador.ArrayListMejorada;
import integrador.ContraseñaSizeException;
import integrador.roles.Persona;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.JOptionPane;
import integrador.roles.Archivo;
import java.io.IOException;

public class MainVentana extends javax.swing.JFrame {

    //variables del panel
    public static ArrayListMejorada lista; //creo que si lo hacemos private andaria ahora
    private Persona usuarioLogueado;
    private Archivo directivos;
    private Archivo pasantes;
    private Archivo preceptores;
    private Archivo profesores;
    private Archivo secretarios;

    //constructores
    public MainVentana() {
        initComponents();
        this.setTitle("Ingresando al Sistema");
        this.setResizable(false);
        this.setLocationRelativeTo(null); 
        this.usuarioLogueado = null;
    }
    
    public MainVentana (ArrayListMejorada lista, Archivo directivos, Archivo pasantes, Archivo preceptores, Archivo profesores, Archivo secretarios) {
        initComponents();
        
        //atributos del jframe
        this.addWindowListener(new ManejadorVentanaCerrada(this));
        
        
        this.lista = lista;
        this.directivos = directivos;
        this.pasantes = pasantes;
        this.preceptores = preceptores;
        this.profesores = profesores;
        this.secretarios = secretarios;
        configurarJFrame();
        this.usuarioLogueado = null;
        this.textFieldUsuario.addActionListener(
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionBotonAceptar();
            }
        });
        this.textFieldContraseña.addActionListener(
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionBotonAceptar();
            }
        });   
    }

    //getters y setters
    public ArrayListMejorada getLista() {
        return lista;
    }

    public void setLista(ArrayListMejorada lista) {
        this.lista = lista;
    }

    public Persona getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Persona usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

   //funciones del panel
    public void accionBotonAceptar() {
        //Captura la persona correspondiente al usuario
        Persona logueando = this.buscarUsuario(this.textFieldUsuario.getText());
        try {
            //comprueba si existe el usuario
            if (logueando != null) {
                    if (this.verificarContraseña(logueando)) {
                        usuarioLogueado = logueando;
                        accionIngresandoABienvenida();
                    }
                    else 
                        JOptionPane.showMessageDialog(null, "Verifique que la contraseña sea correcta","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
                }
            else 
                JOptionPane.showMessageDialog(null, "No existe el usuario","Error",JOptionPane.ERROR_MESSAGE);
            } 
        //capturo el error en caso de que el usuario ingrese letras en vez de numeros
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La contraseña solo puede estar compuesta por numeros","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
        }
        catch (ContraseñaSizeException e){
            JOptionPane.showMessageDialog(null, "La contraseña es un pin numerico de longitud 4","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
        }            
    }
    
    public Persona buscarUsuario(String usuario) {
        Iterator<Persona> iterador = this.lista.iterator();
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
     
    public void accionIngresandoABienvenida(){
        this.textFieldContraseña.setText("");//esto garantiza que cuando el usuario salga de su sesion, no quede su contraseña escrita
        PanelBienvenida ventana = new PanelBienvenida(this);
        ventana.addComponentListener(new ManejadorPanelComponente(this, this.PanelPrincipal));
        this.PanelPrincipal.setVisible(false);
        this.setContentPane(ventana);
}

    public void configurarJFrame(){
        this.setTitle("Ingresando al Sistema");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(this.PanelPrincipal.getPreferredSize());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    public void accionBotonSalir(){
        if (JOptionPane.showConfirmDialog(null, "¿Está seguro de salir del programa?", "SALIENDO", JOptionPane.YES_NO_OPTION)==0){
            try{
                directivos.directivosToTxt(lista);
                pasantes.pasantesToTxt(lista);
                preceptores.preceptoresToTxt(lista);
                profesores.profesoresToTxt(lista);
                secretarios.secretariosToTxt(lista);
            }
            catch (IOException e){
                JOptionPane.showMessageDialog(null, "Algo salio mal", "ups", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        }        
    };
    
    //resto del codigo
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelEtiqueta = new javax.swing.JLabel();
        textFieldUsuario = new javax.swing.JTextField();
        labelContraseña = new javax.swing.JLabel();
        textFieldContraseña = new javax.swing.JPasswordField();
        botonAceptar = new javax.swing.JButton();
        botonCambiarContraseña = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelPrincipal.setPreferredSize(new java.awt.Dimension(500, 350));

        labelTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTitulo.setText("Bienvenido al sistema de Administración Institucional");

        labelEtiqueta.setText("Usuario");

        textFieldUsuario.setToolTipText("Ingrese el nombre de usuario");
        textFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUsuarioActionPerformed(evt);
            }
        });

        labelContraseña.setText("Contraseña");

        textFieldContraseña.setToolTipText("Ingrese su contraseña.\nLa contraseña solo puede estar compuesta por 4 digitos numéricos.");
        textFieldContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldContraseñaActionPerformed(evt);
            }
        });

        botonAceptar.setText("Aceptar");
        botonAceptar.setToolTipText("Presione Aceptar para iniciar sesión.");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });
        botonAceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonAceptarKeyPressed(evt);
            }
        });

        botonCambiarContraseña.setText("Cambiar contraseña");
        botonCambiarContraseña.setToolTipText("Permite cambiar la contraseña por una nueva.");
        botonCambiarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCambiarContraseñaActionPerformed(evt);
            }
        });

        botonSalir.setText("Salir");
        botonSalir.setToolTipText("Salir del sistema....");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(botonCambiarContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEtiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textFieldContraseña)
                            .addComponent(textFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(127, 127, 127))
        );

        PanelPrincipalLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {textFieldContraseña, textFieldUsuario});

        PanelPrincipalLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelContraseña, labelEtiqueta});

        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEtiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelContraseña)
                    .addComponent(textFieldContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonCambiarContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PanelPrincipalLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {textFieldContraseña, textFieldUsuario});

        PanelPrincipalLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelContraseña, labelEtiqueta});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldContraseñaActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        this.accionBotonAceptar();
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonAceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonAceptarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAceptarKeyPressed

    private void botonCambiarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCambiarContraseñaActionPerformed
        // CODIGO DEDICADO AL CAMBIO DE UNA CONTRASEÑA
        PanelModificarContraseña mod = new PanelModificarContraseña(this);
        Persona temporal = null;
        mod.addComponentListener(new ManejadorPanelComponente(this, this.PanelPrincipal));
        this.PanelPrincipal.setVisible(false);
        this.setContentPane(mod);
    }//GEN-LAST:event_botonCambiarContraseñaActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        accionBotonSalir();
    }//GEN-LAST:event_botonSalirActionPerformed

    private void textFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUsuarioActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainVentana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCambiarContraseña;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel labelContraseña;
    private javax.swing.JLabel labelEtiqueta;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPasswordField textFieldContraseña;
    private javax.swing.JTextField textFieldUsuario;
    // End of variables declaration//GEN-END:variables
}
