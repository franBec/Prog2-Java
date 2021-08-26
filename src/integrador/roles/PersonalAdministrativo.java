package integrador.roles;

public abstract class PersonalAdministrativo extends Persona{

    //constructores
    public PersonalAdministrativo(String nombreyApellido, int dni, String domicilio) {
        super(nombreyApellido, dni, domicilio);
    }

    public PersonalAdministrativo() {
        super();
    }
        
    /*FUNCIONES DE PERSONAL ADMINISTRATIVO*/
    /*
    public Persona buscarPersona(ArrayList personal, int dni) {
        //hacer contenido
        //de caso de uso "Buscando miembros de institucion"
    }*/
    
    //String toString
    @Override
    public String toString() {
        return super.toString();
    }  
}
