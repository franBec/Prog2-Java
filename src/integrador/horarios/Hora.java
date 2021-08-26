package integrador.horarios;

public class Hora {
   
    //variables privadas
    private int hora, minuto;

    //constructores
    public Hora(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }
    
    public Hora(){
        hora = 0;
        minuto = 0;
    }
    
    //getters y setters
    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
    
    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
    
    public boolean esMayor(Hora hora) {
        //devuelve verdadero si this.hora es mayor a la hora pasada por parametro
        if (this.hora > hora.getHora()) {
            return true;
        }
        else {
            if (this.hora ==  hora.getHora() && this.minuto > hora.getMinuto()) return true;
            else return false;
        }
    }
    
    public boolean esMayorIgual (Hora hora) {
        //devuelve verdadero si this.hora es mayor igual a la hora pasada por parametro
        if (this.hora > hora.getHora()) {
            return true;
        }
        else {
            if (this.hora ==  hora.getHora() && this.minuto >= hora.getMinuto()) return true;
            else return false;
        }
    }
    
    public boolean esMenor(Hora hora) {
        //devuelve verdadero si this.hora es menor a la hora pasada por parametro
        if (this.hora < hora.getHora()) {
            return true;
        }
        else {
            if (this.hora ==  hora.getHora() && this.minuto < hora.getMinuto()) return true;
            else return false;
        }
    }
        
    public boolean esMenorIgual(Hora hora) {
        //devuelve verdadero si this.hora es menor igual a la hora pasada por parametro
        if (this.hora < hora.getHora()) {
            return true;
        }
        else {
            if (this.hora ==  hora.getHora() && this.minuto <= hora.getMinuto()) return true;
            else return false;
        }
    
    }
    
 
    //String toString
    @Override
    public String toString() {
        //return "Hora{" + "hora=" + hora + ", minuto=" + minuto + '}';
        String horaString = Integer.toString(hora);
        String minutoString = Integer.toString(minuto);
        if (hora<10) horaString = "0"+Integer.toString(hora);
        if (minuto<10) minutoString = "0" + Integer.toString(minuto);
        
        
        return horaString + ":"+ minutoString;
    }
}
