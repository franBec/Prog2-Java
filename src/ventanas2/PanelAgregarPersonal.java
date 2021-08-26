package ventanas2;

import integrador.CamposVaciosException;
import integrador.DNIExistenteException;
import integrador.HorariosNoCompatibleException;
import integrador.HorariosVaciosException;
import integrador.horarios.Hora;
import integrador.horarios.Horario;
import integrador.horarios.HorarioSemana;
import integrador.roles.Directivo;
import integrador.roles.Pasante;
import integrador.roles.Persona;
import integrador.roles.Persona.Roles;
import static integrador.roles.Persona.Roles.*;
import integrador.roles.PersonaOrdenAlfabetico;
import integrador.roles.Preceptor;
import integrador.roles.Profesor;
import integrador.roles.Secretario;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.DayOfWeek;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collections;
import ventanas2.mostrarEspecificos.PanelesAgregar.PanelAgregarDirectivo;
import ventanas2.mostrarEspecificos.PanelesAgregar.PanelAgregarPasante;
import ventanas2.mostrarEspecificos.PanelesAgregar.PanelAgregarPreceptor;
import ventanas2.mostrarEspecificos.PanelesAgregar.PanelAgregarProfesor;
import ventanas2.mostrarEspecificos.PanelesAgregar.PanelAgregarSecretario;

public class PanelAgregarPersonal extends javax.swing.JPanel {

    private MainVentana padre;
    private ButtonGroup grupo;
    private DefaultTableModel modeloTablaHorariosTrabajo;
    private HorarioSemana horariosTrabajo;
    private PanelAgregarDirectivo panelDirectivo;
    private PanelAgregarPasante panelPasante;
    private PanelAgregarPreceptor panelPreceptor;
    private PanelAgregarProfesor panelProfesor;
    private PanelAgregarSecretario panelSecretario;
    
    private Persona personaModificando;
    
    public final String LUNES = "LUNES", MARTES = "MARTES", MIERCOLES = "MIERCOLES", JUEVES = "JUEVES", VIERNES = "VIERNES", SABADO = "SABADO", DOMINGO = "DOMINGO";
    
    
    public PanelAgregarPersonal() {
        initComponents();
        
        padre = null;
        grupo = new ButtonGroup();
        horariosTrabajo = null;
        personaModificando = null;
    }
    
    public PanelAgregarPersonal(MainVentana padre) {
        initComponents();
        this.padre = padre;
        this.personaModificando = null;
        horariosTrabajo = new HorarioSemana();
        configurarJFrame();
        
        //oculta el label "usuario", ya que al cargar una nueva persona este campo nunca se llena
        this.labelUsuario.setVisible(false);
        
        
        inicializarRadioButtons();
        inicializarTablaHorariosTrabajo();
        inicializarManejadorTablaHorarios();
        
        inicializarPanelEspecifico();
        inicializarManejadorDeRadioButton();
    }
    
    public PanelAgregarPersonal(MainVentana padre, Persona personaModificando) {
        initComponents();
        this.padre = padre;
        this.personaModificando = personaModificando;
        horariosTrabajo = new HorarioSemana();
        configurarJFrame();
        
        inicializarRadioButtons();
        inicializarTablaHorariosTrabajo();
        inicializarManejadorTablaHorarios();
        
        inicializarPanelEspecifico();
        inicializarManejadorDeRadioButton();
        
        inicializarDatosPrecargados(personaModificando);
    }
    
    
    public void configurarJFrame() {
        this.padre.setTitle("Agregar nuevo personal");
        this.padre.setSize(this.getPreferredSize());
    }
    
    public void inicializarDatosPrecargados(Persona persona) {
        //cambiar constantes
        this.botonGuardar.setText("Guardar cambios");
        this.labelTitulo.setText("Modificar información");
        this.padre.setTitle("Modificar personal");
        
        //llena datos genericos
        this.textFieldNombreYApellido.setText(persona.getNombreyApellido());
        this.textFieldDNI.setText(Integer.toString(persona.getDni()));
        this.textFieldDomicilio.setText(persona.getDomicilio());
        this.labelUsuario.setText("Usuario:\t\t"+persona.getNombreDeUsuario());
        
        this.horariosTrabajo = persona.getHorarioTrabajo();
        this.llenarTablaHorariosTrabajo();  //actualiza la lista
        
        //castea segun el rol de la persona
        switch(persona.getRol()) {
            case PASANTE:
            {   
                Pasante p = (Pasante) persona;
                
                mostrarPanelEspecifico(PASANTE);
                this.panelPasante.setTareas(p.getTareasARealizar());
                this.radioPasante.setSelected(true);
            }
            break;
            case PROFESOR:
            {
                Profesor p = (Profesor) persona;
                
                mostrarPanelEspecifico(PROFESOR);
                this.panelProfesor.setTitulo(p.getTitulo());
                this.panelProfesor.setMateria(p.getMateria());
                this.radioProfesor.setSelected(true);
            }
            break;
            case SECRETARIO:
            {
                Secretario s = (Secretario) persona;
                
                mostrarPanelEspecifico(SECRETARIO);
                this.panelSecretario.setCodigoDeLibro(s.getCodigoLibroActas());
                this.radioSecretario.setSelected(true);
            }
            break;
            case PRECEPTOR:
            {
                Preceptor p = (Preceptor) persona;
                
                mostrarPanelEspecifico(PRECEPTOR);
                this.panelPreceptor.setHorarioDeSupervicion(p.getHorarioSupervicion());
                this.panelPreceptor.llenarTablaHorariosTrabajo();   //actualiza la lista
                this.radioPreceptor.setSelected(true);
            }
            break;
            case DIRECTIVO:
            {
                Directivo d = (Directivo) persona;
                
                mostrarPanelEspecifico(DIRECTIVO);
                this.panelDirectivo.setHorarioDeAtencion(d.getHorarioAtencionPublico());
                this.panelDirectivo.llenarTablaHorariosDeAtencion();    //actualiza la tabla
                this.panelDirectivo.setHorarioEnDireccion(d.getHorarioEnDireccion());
                this.panelDirectivo.llenarTablaHorariosEnDireccion();   //actualiza la tabla
                this.radioDirectivo.setSelected(true);
            }
        }
        
    }
    
    public void inicializarRadioButtons() {
        grupo = new ButtonGroup();
        
        grupo.add(radioDirectivo);
        grupo.add(radioPasante);
        grupo.add(radioPreceptor);
        grupo.add(radioProfesor);
        grupo.add(radioSecretario);
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
        
        boolean agregar = true;
        
        
        if (nuevo.getHoraDeInicio().esMayor(nuevo.getHoraDeFin())) {
            JOptionPane.showMessageDialog(null, "El horario de entrada es mayor al horario de salida","Horario no valido",JOptionPane.INFORMATION_MESSAGE);
            agregar = false;
        }
        
        Iterator<Horario> iterador = this.horariosTrabajo.iteratorHorario();
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
    
    public void inicializarPanelEspecifico(){
        JPanel panelVacio = new JPanel();
        panelPasante = new PanelAgregarPasante();
        panelProfesor = new PanelAgregarProfesor();
        panelPreceptor = new PanelAgregarPreceptor();
        panelDirectivo = new PanelAgregarDirectivo();
        panelSecretario = new PanelAgregarSecretario();
        
        //pasante.setSize(this.panelTipoPersona.getSize());
        this.panelTipoPersona.removeAll();
        this.panelTipoPersona.setLayout(new CardLayout());
        this.panelTipoPersona.add(panelVacio,"vacio");
        this.panelTipoPersona.add(panelPasante, PASANTE.toString());
        this.panelTipoPersona.add(panelProfesor, PROFESOR.toString());
        this.panelTipoPersona.add(panelPreceptor, PRECEPTOR.toString());
        this.panelTipoPersona.add(panelSecretario, SECRETARIO.toString());
        this.panelTipoPersona.add(panelDirectivo, DIRECTIVO.toString());
        
        this.padre.validate();
    }
    
    public void mostrarPanelEspecifico(Roles rol) {
        CardLayout card = (CardLayout)this.panelTipoPersona.getLayout();
        
        card.show(this.panelTipoPersona, rol.toString());
        this.validate();
    }
    
    public void inicializarManejadorDeRadioButton(){
        this.radioPasante.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                mostrarPanelEspecifico(PASANTE);
            }
        });  
        this.radioProfesor.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                mostrarPanelEspecifico(PROFESOR);
            }
        });
        this.radioSecretario.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                mostrarPanelEspecifico(SECRETARIO);
            }
        });
        this.radioPreceptor.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                mostrarPanelEspecifico(PRECEPTOR);
            }
        });
        this.radioDirectivo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                mostrarPanelEspecifico(DIRECTIVO);
            }
        });
    }
    
    public void llenarTablaHorariosTrabajo() {
        Iterator<Horario> iterador = this.horariosTrabajo.getRutina().iterator();
        
        inicializarTablaHorariosTrabajo();
        
        while (iterador.hasNext()) {
            Horario temporal = iterador.next();
            
            this.modeloTablaHorariosTrabajo.addRow(new String[] {temporal.pasarDiaAEspañol(),temporal.getHoraDeInicio().toString(),temporal.getHoraDeFin().toString()});
        }
    }
    
    public void accionBotonGuardar(){
        if (this.radioDirectivo.isSelected()){
            guardarDirectivo();
        }
        else if (this.radioPasante.isSelected()){
            guardarPasante();
        }
        else if (this.radioPreceptor.isSelected()){
            guardarPreceptor();
        }
        else if (this.radioProfesor.isSelected()){
            guardarProfesor();
        }
        else if (this.radioSecretario.isSelected()){
            guardarSecretario();
        }
        else
            JOptionPane.showMessageDialog(null, "Debe seleccionar alguien para ingresarlo", "Error al guardar", JOptionPane.ERROR_MESSAGE);
    }
    
    public void modificarUnaPersona(Persona cambiar) {
        int indice = this.padre.lista.indexOfPersona(this.personaModificando);
        
        this.padre.lista.set(indice, cambiar);
    }
    
    public void controlarCamposVacios() throws CamposVaciosException {
        boolean boolNombre = textFieldNombreYApellido.getText().isEmpty(),
                boolDNI = textFieldDNI.getText().isEmpty(),
                boolDomicilio = textFieldDomicilio.getText().isEmpty();
        
        if (boolDNI || boolNombre || boolDomicilio ) throw new CamposVaciosException();
        
    }
    
    public void controlarHorariosVacios() throws HorariosVaciosException {
        if (horariosTrabajo.getRutina().isEmpty()) throw new HorariosVaciosException();
    }
    
    public void controlarDocumentosIguales(Persona persona) throws DNIExistenteException {
        Iterator<Persona> iterador = padre.lista.iterator();
        boolean encontrado = false;
        int dni = persona.getDni();
        
        while (iterador.hasNext())
        {
            Persona temporal = iterador.next();
            if (temporal.getDni()==dni && padre.lista.indexOf(temporal)!=padre.lista.indexOf(this.personaModificando)) {
                encontrado = true;
                break;
            }
            
        }
        
        if (encontrado) throw new DNIExistenteException();
    }
    
    public void controlarHorarioIsInHorario(Preceptor p) throws HorariosNoCompatibleException{
        for (Horario supervision : p.getHorarioSupervicion().getRutina())
            for (Horario trabajo : p.getHorarioTrabajo().getRutina())
                if (!supervision.isSumergido(trabajo))
                    throw new HorariosNoCompatibleException();
                
    }
    
    public void controlarHorarioIsInHorario(Directivo d) throws HorariosNoCompatibleException{
        for (Horario atencion : d.getHorarioAtencionPublico().getRutina())
            for (Horario trabajo : d.getHorarioTrabajo().getRutina())
                if (!atencion.isSumergido(trabajo))
                    throw new HorariosNoCompatibleException();
        
        for (Horario enDireccion : d.getHorarioAtencionPublico().getRutina())
            for (Horario trabajo : d.getHorarioTrabajo().getRutina())
                if (!enDireccion.isSumergido(trabajo))
                    throw new HorariosNoCompatibleException();
    
    }
    
    public void guardarDirectivo(){
        try {
        this.controlarCamposVacios();
        this.controlarHorariosVacios();
        
        
        String nombre = textFieldNombreYApellido.getText();
        int dni = new Integer(textFieldDNI.getText());
        String domicilio = textFieldDomicilio.getText();
        //HorarioSemana h = this.horariosTrabajo;
        Directivo d = new Directivo(nombre, dni, domicilio);
        d.setHorarioTrabajo(horariosTrabajo);
        d.setHorarioAtencionPublico(panelDirectivo.getHorarioDeAtencion());
        d.setHorarioEnDireccion(panelDirectivo.getHorarioEnDireccion());
        
        this.controlarHorarioIsInHorario(d);
        this.controlarDocumentosIguales(d);
        
        //flujo si se esta modificando una persona
        if (personaModificando != null) {
            d.setContrasena(this.personaModificando.getContrasena());
            d.setNombreDeUsuario(this.personaModificando.getNombreDeUsuario());
            this.modificarUnaPersona(d);
        }
        else {
            this.padre.lista.add(d);
        }
        JOptionPane.showMessageDialog(null, "Exito en el guardado","Directivo guardado exitosamente",JOptionPane.INFORMATION_MESSAGE);
        accionBotonSalir();
        } catch (CamposVaciosException e) {
            JOptionPane.showMessageDialog(null, e.mensajeDeErrorComun(),"Error",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La contraseña solo puede estar compuesta por numeros","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
        } catch (HorariosVaciosException e) {
            JOptionPane.showMessageDialog(null, "Los horarios de trabajo se encuentra vacio","Horarios vacios",JOptionPane.ERROR_MESSAGE);
        } catch (DNIExistenteException e) {
            JOptionPane.showMessageDialog(null, "El DNI ingresado ya existe en el sistema","DNI existente",JOptionPane.ERROR_MESSAGE);
        } catch (HorariosNoCompatibleException ex) {
            JOptionPane.showMessageDialog(null, "Los horarios de atencion deben estar dentro del horario de trabajo o\nLos horarios en direccion deben estar dentro del horario de trabajo","Error horarios",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void guardarPasante(){
        try {
        this.controlarCamposVacios();
        this.controlarHorariosVacios();
        
        String nombre = textFieldNombreYApellido.getText();
        int dni = new Integer(textFieldDNI.getText());
        String domicilio = textFieldDomicilio.getText();
        //HorarioSemana h = this.horariosTrabajo;
        String tareas = panelPasante.getTareas();
        Pasante p = new Pasante(nombre, dni, domicilio);
        p.setHorarioTrabajo(horariosTrabajo);
        p.setTareasARealizar(tareas);
        
        this.controlarDocumentosIguales(p);

        
        if (personaModificando != null) {
            p.setContrasena(this.personaModificando.getContrasena());
            p.setNombreDeUsuario(this.personaModificando.getNombreDeUsuario());
            this.modificarUnaPersona(p);
        }
        else {
            this.padre.lista.add(p);
        }
        
        JOptionPane.showMessageDialog(null, "Exito en el guardado","Pasante guardado exitosamente",JOptionPane.INFORMATION_MESSAGE);
        accionBotonSalir();
        } catch (CamposVaciosException e) {
            JOptionPane.showMessageDialog(null, e.mensajeDeErrorComun(),"Error",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La contraseña solo puede estar compuesta por numeros","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
        } catch (HorariosVaciosException e) {
            JOptionPane.showMessageDialog(null, "Los horarios de trabajo se encuentra vacio","Horarios vacios",JOptionPane.ERROR_MESSAGE);
        }catch (DNIExistenteException e) {
            JOptionPane.showMessageDialog(null, "El DNI ingresado ya existe en el sistema","DNI existente",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void guardarPreceptor(){
        try {
        this.controlarCamposVacios();
        this.controlarHorariosVacios();
        
        
        String nombre = textFieldNombreYApellido.getText();
        int dni = new Integer(textFieldDNI.getText());
        String domicilio = textFieldDomicilio.getText();
        //HorarioSemana h = this.horariosTrabajo;
        //String tareas = panelPasante.getTareas();
        Preceptor p = new Preceptor(nombre, dni, domicilio);
        p.setHorarioTrabajo(horariosTrabajo);
        p.setHorarioSupervicion(panelPreceptor.getHorarioDeSupervision());
        
        this.controlarHorarioIsInHorario(p);
        this.controlarDocumentosIguales(p);
        
        if (personaModificando != null) {
            p.setContrasena(this.personaModificando.getContrasena());
            p.setNombreDeUsuario(this.personaModificando.getNombreDeUsuario());
            this.modificarUnaPersona(p);
        }
        else {
            this.padre.lista.add(p);
        }
        
        JOptionPane.showMessageDialog(null, "Exito en el guardado","Preceptor guardado exitosamente",JOptionPane.INFORMATION_MESSAGE);
        accionBotonSalir();
        } catch (CamposVaciosException e) {
            JOptionPane.showMessageDialog(null, e.mensajeDeErrorComun(),"Error",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La contraseña solo puede estar compuesta por numeros","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
        } catch (HorariosVaciosException e) {
            JOptionPane.showMessageDialog(null, "Los horarios de trabajo se encuentra vacio","Horarios vacios",JOptionPane.ERROR_MESSAGE);
        } catch (DNIExistenteException e) {
            JOptionPane.showMessageDialog(null, "El DNI ingresado ya existe en el sistema","DNI existente",JOptionPane.ERROR_MESSAGE);
        } catch (HorariosNoCompatibleException ex) {
            JOptionPane.showMessageDialog(null, "Los horarios de supervision deben estar dentro del horario de trabajo","Error horarios",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void guardarProfesor(){
        try {
        this.controlarCamposVacios();
        this.controlarHorariosVacios();
        
        
        String nombre = textFieldNombreYApellido.getText();
        int dni = new Integer(textFieldDNI.getText());
        String domicilio = textFieldDomicilio.getText();
        //HorarioSemana h = this.horariosTrabajo;
        String titulo = panelProfesor.getTitulo();
        String materia = panelProfesor.getMateria();
        Profesor p = new Profesor(nombre, dni, domicilio, titulo, materia);
        p.setHorarioTrabajo(horariosTrabajo);
        
        this.controlarDocumentosIguales(p);
        
        if (personaModificando != null) {
            p.setContrasena(this.personaModificando.getContrasena());
            p.setNombreDeUsuario(this.personaModificando.getNombreDeUsuario());
            this.modificarUnaPersona(p);
        }
        else {
            this.padre.lista.add(p);
        }
        
        JOptionPane.showMessageDialog(null, "Exito en el guardado","Profesor guardado exitosamente",JOptionPane.INFORMATION_MESSAGE);
        accionBotonSalir();
        } catch (CamposVaciosException e) {
            JOptionPane.showMessageDialog(null, e.mensajeDeErrorComun(),"Error",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La contraseña solo puede estar compuesta por numeros","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
        } catch (HorariosVaciosException e) {
            JOptionPane.showMessageDialog(null, "Los horarios de trabajo se encuentra vacio","Horarios vacios",JOptionPane.ERROR_MESSAGE);
        } catch (DNIExistenteException e) {
            JOptionPane.showMessageDialog(null, "El DNI ingresado ya existe en el sistema","DNI existente",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void guardarSecretario(){
        try {
        this.controlarCamposVacios();
        this.controlarHorariosVacios();
        
        
        String nombre = textFieldNombreYApellido.getText();
        int dni = new Integer(textFieldDNI.getText());
        String domicilio = textFieldDomicilio.getText();
        //HorarioSemana h = this.horariosTrabajo;
        
        int codigo = new Integer(panelSecretario.getCodigoDeLibro());
        Secretario s = new Secretario(nombre, dni, domicilio);
        s.setHorarioTrabajo(horariosTrabajo);
        
        s.setCodigoLibroActas(codigo);
        
        this.controlarDocumentosIguales(s);
        
        //condicion para no perder los codigos al ministerio cuando se modifica una persona
        if (this.personaModificando != null) {
            Secretario temporal = (Secretario) this.personaModificando;
            s.setCodigosAlMinisterio(temporal.getCodigosAlMinisterio());
        }
        else {
            ArrayList <Integer> arr = new ArrayList<>();
            s.setCodigosAlMinisterio(arr);
        }
        
        
        if (personaModificando != null) {
            s.setContrasena(this.personaModificando.getContrasena());
            s.setNombreDeUsuario(this.personaModificando.getNombreDeUsuario());
            this.modificarUnaPersona(s);
        }
        else {
            this.padre.lista.add(s);
        }
        
        JOptionPane.showMessageDialog(null, "Exito en el guardado","Secretario guardado exitosamente",JOptionPane.INFORMATION_MESSAGE);
        accionBotonSalir();
        } catch (CamposVaciosException e) {
            JOptionPane.showMessageDialog(null, e.mensajeDeErrorComun(),"Error",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La contraseña solo puede estar compuesta por numeros","Contraseña invalida",JOptionPane.ERROR_MESSAGE);
        } catch (HorariosVaciosException e) {
            JOptionPane.showMessageDialog(null, "Los horarios de trabajo se encuentra vacio","Horarios vacios",JOptionPane.ERROR_MESSAGE);
        } catch (DNIExistenteException e) {
            JOptionPane.showMessageDialog(null, "El DNI ingresado ya existe en el sistema","DNI existente",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void accionBotonSalir(){
        Collections.sort(this.padre.lista, new PersonaOrdenAlfabetico());
        setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelGenerico = new javax.swing.JPanel();
        labelNombreYApellido = new javax.swing.JLabel();
        labelDNI = new javax.swing.JLabel();
        labelDomicilio = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        ScrollPaneTabla = new javax.swing.JScrollPane();
        TablaHorariosTrabajo = new javax.swing.JTable();
        labelHorarios = new javax.swing.JLabel();
        textFieldNombreYApellido = new javax.swing.JTextField();
        textFieldDNI = new javax.swing.JTextField();
        textFieldDomicilio = new javax.swing.JTextField();
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
        labelTitulo = new javax.swing.JLabel();
        panelSeleccionarPersona = new javax.swing.JPanel();
        radioDirectivo = new javax.swing.JRadioButton();
        radioPasante = new javax.swing.JRadioButton();
        radioPreceptor = new javax.swing.JRadioButton();
        radioProfesor = new javax.swing.JRadioButton();
        radioSecretario = new javax.swing.JRadioButton();
        botonSalir = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        panelTipoPersona = new javax.swing.JPanel();

        labelNombreYApellido.setText("Nombre y Apellido:");

        labelDNI.setText("DNI: ");

        labelDomicilio.setText("Domicilio");

        labelUsuario.setText("Usuario");

        TablaHorariosTrabajo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Horario de Entrada", "Horario de Salida"
            }
        ));
        TablaHorariosTrabajo.setToolTipText("Tabla de horarios de trabajo de la nueva persona.\n-- Para agregar nuevos horarios complete los campos debajo de la tabla y presione el botón agregar\n-- Para eliminar un horario ingresado en la tabla realize Doble Click sobre dicho horario. El sistema pedirá confirmación para eliminar el horario");
        ScrollPaneTabla.setViewportView(TablaHorariosTrabajo);

        labelHorarios.setText("Horarios de Trabajo");

        textFieldNombreYApellido.setToolTipText("Nombre y Apellido de la nueva persona");

        textFieldDNI.setToolTipText("Ingrese aquí el DNI de la nueva persona");

        textFieldDomicilio.setToolTipText("Ingrese aquí el domicio de la nueva persona");

        labelDia.setText("Dia");

        comboBoxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO" }));
        comboBoxDia.setToolTipText("Elija el día de la semana al cual pertenece el intervalo de tiempo que comprende el horario");

        labelHoraIngreso.setText("Hora de ingreso");

        spinnerHoraIngreso.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        spinnerHoraIngreso.setToolTipText("Hora Reloj del momento de ingreso a trabajar de la persona");

        spinnerMinutoIngreso.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        spinnerMinutoIngreso.setToolTipText("Minuto Reloj del momento de ingreso al trabajo de la nueva persona");

        labelHoraSalida.setText("Hora de salida");

        spinnerHoraSalida.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        spinnerHoraSalida.setToolTipText("Hora Reloj del momento de salida de trabajo de la nueva persona");

        spinnerMinutoSalida.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        spinnerMinutoSalida.setToolTipText("Minuto Reloj del momento de salida del trabajo de la nueva persona");

        botonAgregarHorario.setText("Agregar");
        botonAgregarHorario.setToolTipText("Agregue un nuevo horario a la tabla de horarios de la nueva persona");
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
                .addComponent(comboBoxDia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        panelAgregarHorarioLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botonAgregarHorario, comboBoxDia, labelDia, labelHoraIngreso, labelHoraSalida, spinnerHoraIngreso, spinnerHoraSalida, spinnerMinutoIngreso, spinnerMinutoSalida});

        javax.swing.GroupLayout PanelGenericoLayout = new javax.swing.GroupLayout(PanelGenerico);
        PanelGenerico.setLayout(PanelGenericoLayout);
        PanelGenericoLayout.setHorizontalGroup(
            PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneTabla)
            .addGroup(PanelGenericoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelGenericoLayout.createSequentialGroup()
                        .addGroup(PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelGenericoLayout.createSequentialGroup()
                                .addComponent(labelDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldDomicilio))
                            .addGroup(PanelGenericoLayout.createSequentialGroup()
                                .addGroup(PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelNombreYApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                    .addComponent(labelDNI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldDNI)
                                    .addComponent(textFieldNombreYApellido))))
                        .addContainerGap())
                    .addGroup(PanelGenericoLayout.createSequentialGroup()
                        .addGroup(PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelUsuario)
                            .addComponent(labelHorarios))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(panelAgregarHorario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelGenericoLayout.setVerticalGroup(
            PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGenericoLayout.createSequentialGroup()
                .addGroup(PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelGenericoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelNombreYApellido))
                    .addComponent(textFieldNombreYApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDNI)
                    .addComponent(textFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelGenericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDomicilio)
                    .addComponent(textFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelHorarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAgregarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PanelGenericoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {textFieldDNI, textFieldDomicilio, textFieldNombreYApellido});

        PanelGenericoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelDNI, labelDomicilio, labelNombreYApellido});

        labelTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Información");

        panelSeleccionarPersona.setToolTipText("Seleccione el tipo de persona que desea ingresar al sistema");

        radioDirectivo.setText("Directivo");
        radioDirectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDirectivoActionPerformed(evt);
            }
        });

        radioPasante.setText("Pasante");

        radioPreceptor.setText("Preceptor");

        radioProfesor.setText("Profesor");

        radioSecretario.setText("Secretario");

        javax.swing.GroupLayout panelSeleccionarPersonaLayout = new javax.swing.GroupLayout(panelSeleccionarPersona);
        panelSeleccionarPersona.setLayout(panelSeleccionarPersonaLayout);
        panelSeleccionarPersonaLayout.setHorizontalGroup(
            panelSeleccionarPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeleccionarPersonaLayout.createSequentialGroup()
                .addComponent(radioDirectivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioPasante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioPreceptor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioSecretario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSeleccionarPersonaLayout.setVerticalGroup(
            panelSeleccionarPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeleccionarPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(radioDirectivo)
                .addComponent(radioPasante)
                .addComponent(radioPreceptor)
                .addComponent(radioProfesor)
                .addComponent(radioSecretario))
        );

        botonSalir.setText("Salir");
        botonSalir.setToolTipText("Presione para cancelar la operación...");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        botonGuardar.setText("Guardar");
        botonGuardar.setToolTipText("Presione para finalizar la operación de Registro de la nueva persona en el sistema");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTipoPersonaLayout = new javax.swing.GroupLayout(panelTipoPersona);
        panelTipoPersona.setLayout(panelTipoPersonaLayout);
        panelTipoPersonaLayout.setHorizontalGroup(
            panelTipoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelTipoPersonaLayout.setVerticalGroup(
            panelTipoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelGenerico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSeleccionarPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(panelTipoPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonGuardar, botonSalir});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addGap(18, 18, 18)
                .addComponent(PanelGenerico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSeleccionarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTipoPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        accionBotonSalir();
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonAgregarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarHorarioActionPerformed
        agregarHorarioTrabajo();
    }//GEN-LAST:event_botonAgregarHorarioActionPerformed

    private void radioDirectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDirectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioDirectivoActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        accionBotonGuardar();
    }//GEN-LAST:event_botonGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelGenerico;
    private javax.swing.JScrollPane ScrollPaneTabla;
    private javax.swing.JTable TablaHorariosTrabajo;
    private javax.swing.JButton botonAgregarHorario;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JComboBox<String> comboBoxDia;
    private javax.swing.JLabel labelDNI;
    private javax.swing.JLabel labelDia;
    private javax.swing.JLabel labelDomicilio;
    private javax.swing.JLabel labelHoraIngreso;
    private javax.swing.JLabel labelHoraSalida;
    private javax.swing.JLabel labelHorarios;
    private javax.swing.JLabel labelNombreYApellido;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelAgregarHorario;
    private javax.swing.JPanel panelSeleccionarPersona;
    private javax.swing.JPanel panelTipoPersona;
    private javax.swing.JRadioButton radioDirectivo;
    private javax.swing.JRadioButton radioPasante;
    private javax.swing.JRadioButton radioPreceptor;
    private javax.swing.JRadioButton radioProfesor;
    private javax.swing.JRadioButton radioSecretario;
    private javax.swing.JSpinner spinnerHoraIngreso;
    private javax.swing.JSpinner spinnerHoraSalida;
    private javax.swing.JSpinner spinnerMinutoIngreso;
    private javax.swing.JSpinner spinnerMinutoSalida;
    private javax.swing.JTextField textFieldDNI;
    private javax.swing.JTextField textFieldDomicilio;
    private javax.swing.JTextField textFieldNombreYApellido;
    // End of variables declaration//GEN-END:variables
}
