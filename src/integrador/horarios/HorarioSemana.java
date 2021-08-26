package integrador.horarios;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Iterator;

public class HorarioSemana {
    
    //variables privadas
    private ArrayList <Horario> rutina;

    //constructores
    public HorarioSemana(ArrayList<Horario> rutina) {
        this.rutina = rutina;
    }
    
    public HorarioSemana(){
        rutina = new ArrayList<>();
    }

    //getters y setters
    public ArrayList<Horario> getRutina() {
        return rutina;
    }

    public void setRutina(ArrayList<Horario> rutina) {
        this.rutina = rutina;
    }

    //String toString
    @Override
    public String toString() {
        return "HorarioSemana{" + "rutina=" + rutina + '}';
    }
    
    //funciones auxiliares
    public void addNuevoHorario(Horario h){
        rutina.add(h);
    }
    
    public void eliminarHorario(Horario h){
        rutina.remove(h);
    }
    
    public Iterator<Horario> iteratorHorario() {
        return rutina.iterator();
    }

    public void cargarDesdeLinea(String reglon) {
        //carga nuevos horarios consecutivos desde un reglon con el formato:
        //DIA - HORAINICIO - MINUTOINICIO - HORAFIN - MINUTOFIN
        //no hay control de la integridad del reglon pasado por parametro
        // hay que implementar Excepciones con try y catch para evitar salirse del arreglo
        String[] listaAuxiliar = reglon.split(" ");
        for (int i = 0 ; i < listaAuxiliar.length;i+=5) {
            DayOfWeek dia; int horaIn, horaFin, minIn, minFin;
            dia = DayOfWeek.of(new Integer(listaAuxiliar[i]));
            horaIn = new Integer(listaAuxiliar[i+1]);
            minIn = new Integer(listaAuxiliar[i+2]);
            horaFin = new Integer(listaAuxiliar[i+3]);
            minFin = new Integer(listaAuxiliar[i+4]);
            rutina.add(new Horario(dia, new Hora(horaIn, minIn), new Hora(horaFin, minFin)));  
        }
    }
    
    public String rutinaToRenglon (){
        String renglon = "";//no estoy seguro si retornar "" o null 
        Iterator<Horario> iterador = this.rutina.iterator();
        
        while (iterador.hasNext()) {
            renglon = renglon.concat(iterador.next().toStringParaTxt());
        }
        
        
        return renglon;  
    }
}
