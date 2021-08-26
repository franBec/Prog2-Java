package integrador.roles;
import java.util.Comparator;

public class PersonaOrdenDNI implements Comparator<Persona>{
    
    @Override
    public int compare(Persona p1, Persona p2) {
        return p1.getDni()-p2.getDni();
    }
}
