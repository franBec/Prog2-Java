package integrador.roles;

import integrador.horarios.HorarioSemana;

public class Preceptor extends PersonalAdministrativo{
    
    //variables privadas
    private HorarioSemana horarioSupervicion;
    private final Roles rol = Persona.Roles.PRECEPTOR;

    //constructores
    public Preceptor(String nombre, int dni, String direccion){
        super(nombre, dni, direccion);
        this.horarioSupervicion = new HorarioSemana();
    }
    
    public Preceptor() {
        super();
        this.horarioSupervicion = new HorarioSemana();
    }

    //getters y setters
    public HorarioSemana getHorarioSupervicion() {
        return horarioSupervicion;
    }

    public void setHorarioSupervicion(HorarioSemana horarioSupervicion) {
        this.horarioSupervicion = horarioSupervicion;
    }
    
    public String getHorariosDeSupervisionToRenglon(){
        return horarioSupervicion.rutinaToRenglon();
}
        
    @Override
    public Roles getRol() {
        return rol;
    }
    
    //string toString
    @Override public String toString(){
        return super.toString();
    }
}