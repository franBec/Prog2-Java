package integrador.roles;

import integrador.horarios.HorarioSemana;

public abstract class Persona extends SuperUsuario{
    
    //variables privadas
    private String nombreyApellido;
    private int dni;
    private String domicilio;
    private HorarioSemana horarioTrabajo;
    
    //enum roles
    public enum Roles{
        PASANTE, DIRECTIVO, PRECEPTOR, PROFESOR, SECRETARIO;
    }

    //constructores
    public Persona(String nombreyApellido, int dni, String domicilio) {
        
        super();
        this.nombreyApellido = nombreyApellido;
        this.dni = dni;
        this.domicilio = domicilio;
        String aux = nombreyApellido;
        aux = aux.replace(" ", "");
        aux= aux.toLowerCase();
        this.setNombreDeUsuario(aux);   //asgna como nombre de usuario el nombre y apellid todo en minuscula y seguido
        this.setContrasena(dni%10000);  //asigna como contraseña los ultimos 4 cifras del documento
        this.horarioTrabajo = new HorarioSemana();
    }
    
    public Persona() {
        super();
        this.nombreyApellido = "";
        this.dni = 0;
        this.domicilio = "";
        this.horarioTrabajo = new HorarioSemana();
    }

    //getters y setters
    public HorarioSemana getHorarioTrabajo() {
        return horarioTrabajo;
    }

    public void setHorarioTrabajo(HorarioSemana horarioTrabajo) {
        this.horarioTrabajo = horarioTrabajo;
    }

    public String getNombreyApellido() {
        return nombreyApellido;
    }

    public void setNombreyApellido(String nombreyApellido) {
        this.nombreyApellido = nombreyApellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    public String getRutinaEnRenglon(){
        return horarioTrabajo.rutinaToRenglon();
    }
    
    public abstract Roles getRol();

    //String toString
    @Override
    public String toString() {
        return "Persona{" + "nombreyApellido=" + nombreyApellido + ", dni=" + dni + ", domicilio=" + domicilio + '}';
    }
}
