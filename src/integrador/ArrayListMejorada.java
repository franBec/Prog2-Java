//ArrayList que incorpora funciones adicionales para trabajar con personas

package integrador;
import integrador.roles.PersonaComparator;
import integrador.roles.Persona;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayListMejorada extends ArrayList<Persona> {
    
    //Variables privadas
    private Comparator<Persona> comparador;
    
    //constructores
    public ArrayListMejorada() {
        super();
        this.comparador = new PersonaComparator();
    }
    
    public ArrayListMejorada(int initialCapacity) {
        super(initialCapacity);
    }
    
    public ArrayListMejorada(Comparator<Persona> comparador) {
        super();
        this.comparador = comparador;
    }
    
    public ArrayListMejorada(Collection c) {
        super(c);
    }

    //getter y setters comparador
    public Comparator<Persona> getComparador() {
        return comparador;
    }

    public void setComparador(Comparator<Persona> comparador) {
        this.comparador = comparador;
    }

    //funciones de la lista
    public Persona buscarPersonaPorDni(int dni) {
        //Retorna una persona si se encuentra en la lista
        //Retorna NULL si no es encontrada
        
        Iterator<Persona> iteratorA = this.iterator();
        boolean encontrado = false;
        Persona auxiliar = null;
        while (iteratorA.hasNext()) {
            Persona temporal = iteratorA.next();
            if (temporal.getDni()==dni) {
                encontrado = true;
                auxiliar = temporal;
                break;
            }
        }
        if (encontrado)
            return auxiliar;
        else
            return null;
    }
         
    public boolean contienePersona(Persona persona){
        //funcion similar a contains pero usando el comparador de personas
        Iterator<Persona> iteradorLista = this.iterator();
        Persona auxiliar = null;    //para almacenar los elementos del iterator
        boolean encontrado = false; //se almacena la salida
        //PersonaComparator comparador = new PersonaComparator();
        while (iteradorLista.hasNext()) {
            auxiliar = iteradorLista.next();
            if (comparador.compare(persona, auxiliar)==1){
                encontrado = true;
                break;
            }//si se encuentra en la lista, encontrado cambia a verdadero
        }
        return encontrado;  
    }
    
    public int indexOfPersona(Persona persona) {
        //funcion para obtener el indice de un objeto pasado por parametro en una lista
        //utiliza la clase PersonaComparator para determinar si dos personas son iguales
        Iterator<Persona> iteradorLista = this.iterator();
        Persona auxiliar = null;    //para almacenar los elementos del iterator
        int encontrado = -1; //se almacena la salida
        //PersonaComparator comparador = new PersonaComparator();
         while (iteradorLista.hasNext()) {
            auxiliar = iteradorLista.next();
            if (comparador.compare(persona, auxiliar)==1)
                encontrado = this.indexOf(auxiliar);    //si se encuentra en la lista, se busca el indice en la misma lista
        }
        return encontrado;
    }  
}
