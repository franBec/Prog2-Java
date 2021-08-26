package integrador.roles;

public class Pasante extends Persona{
    
    //variables privadas
    private String TareasARealizar;
    private final Roles rol = Persona.Roles.PASANTE;

    //constructores
    public Pasante(String nombreyApellido, int dni, String domicilio) {
        super(nombreyApellido, dni, domicilio);
    }

    public Pasante() {
        super();
    }
    
    //getters y setters
    public String getTareasARealizar() {
        return TareasARealizar;
    }

    public void setTareasARealizar(String TareasARealizar) {
        this.TareasARealizar = TareasARealizar;
    }
    
    @Override
    public Roles getRol() {
        return rol;
    }

    //String toString
    @Override
    public String toString() {
        return super.toString() + this.getHorarioTrabajo().toString() + "Pasante{" + "TareasARealizar=" + TareasARealizar + '}';
    } 
}
