//no estoy seguro si este comparator es usado en algun lado
//originalmente era para ver que dos personas no fueran iguales
//pero para eso es suficiente con el documento

package integrador.roles;

import java.util.Comparator;

public class PersonaComparator implements Comparator<Persona> {

    @Override
    public int compare(Persona o1, Persona o2) {
        boolean nombre, dni, direccion;
        String aux1, aux2;
        
        //comparar nombres
        aux1 = o1.getNombreyApellido().toLowerCase();
        aux2 = o2.getNombreyApellido().toLowerCase();
        nombre = aux1.equals(aux2);
                
        //dni
        dni = o1.getDni() == o2.getDni();
        
        //direccion
        aux1 = o1.getDomicilio().toLowerCase();
        aux2 = o2.getDomicilio().toLowerCase();
        direccion = aux1.equals(aux2);
        
        //Devuelve 1 si son iguales, 0 en caso contrario
        if (nombre && direccion && dni)
            return 1;
        else
            return 0;
    }
}