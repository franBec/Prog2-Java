package integrador.roles;

import integrador.horarios.HorarioSemana;

public class Directivo extends PersonalAdministrativo{
    
    //variables privadas
    private HorarioSemana horarioEnDireccion, horarioAtencionPublico;
    private final Roles rol = Persona.Roles.DIRECTIVO;

    //constructores
    public Directivo(String nombre, int dni, String direccion){
        super(nombre, dni, direccion);
        this.horarioEnDireccion = new HorarioSemana();
        this.horarioAtencionPublico = new HorarioSemana();
    }
    
    public Directivo() {
        super();
        this.horarioEnDireccion = new HorarioSemana();
        this.horarioAtencionPublico = new HorarioSemana();
    }

    //getters y setters
    public HorarioSemana getHorarioEnDireccion() {
        return horarioEnDireccion;
    }

    public void setHorarioEnDireccion(HorarioSemana horarioEnDireccion) {
        this.horarioEnDireccion = horarioEnDireccion;
    }

    public HorarioSemana getHorarioAtencionPublico() {
        return horarioAtencionPublico;
    }

    public void setHorarioAtencionPublico(HorarioSemana horarioAtencionPublico) {
        this.horarioAtencionPublico = horarioAtencionPublico;
    }
    
    public String getHorarioEnDireccionToRenglon(){
        return horarioEnDireccion.rutinaToRenglon();
    }
    
    public String getHorarioAtencionAlPublicoToRenglon(){
        return horarioAtencionPublico.rutinaToRenglon();
    }
    
    @Override
    public Roles getRol() {
        return rol;
    }
    
    //String toString
    @Override
    public String toString(){
        return super.toString() + getHorarioTrabajo().toString() + getHorarioEnDireccion().toString() + getHorarioAtencionPublico().toString();
    }
}
