package integrador.roles;

import java.util.Comparator;

public class PersonaOrdenAlfabetico implements Comparator<Persona>{
    
    @Override
    public int compare(Persona p1, Persona p2) {
        return p1.getNombreyApellido().compareTo(p2.getNombreyApellido());
    }
}
