package integrador.horarios;

import java.time.DayOfWeek;

public class Horario {
    
    //variables privadas
    private DayOfWeek dia;
    private Hora horaDeInicio, horaDeFin;
    
    //constantes de dias de semana
    public final String LUNES = "LUNES", MARTES = "MARTES", MIERCOLES = "MIERCOLES", JUEVES = "JUEVES", VIERNES = "VIERNES", SABADO = "SABADO", DOMINGO = "DOMINGO";

   //constructores
    public Horario(DayOfWeek dia, Hora horaDeInicio, Hora horaDeFin) {
        this.dia = dia;
        this.horaDeInicio = horaDeInicio;
        this.horaDeFin = horaDeFin;
    }

    public Horario() {
        dia = DayOfWeek.SUNDAY;
        horaDeInicio = new Hora();
        horaDeFin = new Hora();
    }
    
    //getters y setters
    public DayOfWeek getDia() {
        return dia;
    }

    public void setDia(DayOfWeek dia) {
        this.dia = dia;
    }

    public Hora getHoraDeInicio() {
        return horaDeInicio;
    }

    public void setHoraDeInicio(Hora horaDeInicio) {
        this.horaDeInicio = horaDeInicio;
    }

    public Hora getHoraDeFin() {
        return horaDeFin;
    }

    public void setHoraDeFin(Hora horaDeFin) {
        this.horaDeFin = horaDeFin;
    }
    
    //funciones auxiliares
    

    public boolean isHoraValida(Hora h){
        //devuelve verdadero si la hora pasada por parametro entra dentro del intervalo del horario
        if(h.getHora() > horaDeInicio.getHora() && h.getHora() < horaDeFin.getHora())
            return true;
        else
            if(h.getHora() == horaDeInicio.getHora() && h.getMinuto() > horaDeInicio.getMinuto())
                return true;
            else
                if(h.getHora() == horaDeFin.getHora() && h.getMinuto() < horaDeFin.getMinuto())
                    return true; 
                else 
                    return false;
    }
    
    

    public boolean isSuperpuesto(Horario entrada) {
        //devuelve verdadero si el horario se superpone con el pasado por parametro
        if(((this.isHoraValida(entrada.horaDeInicio))||(this.isHoraValida(entrada.horaDeFin)))&&(this.dia.equals(entrada.getDia())))
            return true;
        else{
            if(((entrada.isHoraValida(horaDeFin))||(entrada.isHoraValida(horaDeInicio)))&&(entrada.getDia().equals(this.dia)))
                return true;
            else 
                return false;
        }
    }
    
    public boolean isSumergido(Horario h){
        //devuelve true si this.isSumergido se encuentra totalmente dentro de Horario h
        return (this.horaDeInicio.esMayorIgual(h.horaDeInicio) && this.horaDeFin.esMenorIgual(h.horaDeFin) && this.dia.equals(h.getDia()));
    }
    
    public String pasarDiaAEspañol(){
        String temporal = null;
        
        switch (dia){
            case MONDAY:
                temporal = LUNES;
                break;
            case TUESDAY:
                temporal = MARTES;
                break;
            case WEDNESDAY:
                temporal = MIERCOLES;
                break;
            case THURSDAY:
                temporal = JUEVES;
                break;
            case FRIDAY:
                temporal = VIERNES;
                break;
            case SATURDAY:
                temporal = SABADO;
                break;
            case SUNDAY:
                temporal = DOMINGO;
                break;
        }
               
        return temporal;
    }
    
    
    public String toStringParaTxt() {
        String d = new Integer(this.dia.getValue()).toString();
        String ih = new Integer(this.horaDeInicio.getHora()).toString();
        String im = new Integer(this.horaDeInicio.getMinuto()).toString();
        String fh = new Integer(this.horaDeFin.getHora()).toString();
        String fm = new Integer(this.horaDeFin.getMinuto()).toString();
        
        String temporal = d+" "+ih+" "+im+" "+fh+" "+fm+" ";
        
        return temporal;
        
    }

    //String toString
    @Override
    public String toString() {
        return "Horario{" + "dia=" + dia + ", horaDeInicio=" + horaDeInicio + ", horaDeFin=" + horaDeFin + '}';
    }
}
