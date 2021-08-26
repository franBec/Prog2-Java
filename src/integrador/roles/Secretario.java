package integrador.roles;

import java.util.ArrayList;
import java.util.Iterator;

public class Secretario extends PersonalAdministrativo {
    
    //variables privadas
    private int codigoLibroActas;
    private ArrayList <Integer> codigosAlMinisterio;
    private final Roles rol = Persona.Roles.SECRETARIO;
    
    //constructores
    public Secretario(String nombreyApellido, int dni, String domicilio) {
        super(nombreyApellido, dni, domicilio);
        this.codigoLibroActas = 0;
        this.codigosAlMinisterio = new ArrayList<>();
    }

    public Secretario() {
        super();
        this.codigoLibroActas = 0;
        this.codigosAlMinisterio = new ArrayList<>();
    }
    
    //getters y setters
    public int getCodigoLibroActas() {
        return codigoLibroActas;
    }

    public void setCodigoLibroActas(int codigoLibroActas) {
        this.codigoLibroActas = codigoLibroActas;
    }

    public ArrayList <Integer> getCodigosAlMinisterio() {
        return codigosAlMinisterio;
    }

    public void setCodigosAlMinisterio(ArrayList <Integer> cod){
        codigosAlMinisterio = cod;  
    }
    
    public String getCodigosAlMinisterioToRenglon(){
        StringBuilder sb = new StringBuilder();
        for (int i : codigosAlMinisterio)
            sb.append(i).append(" ");
        return sb.toString();
    }
    
    @Override
    public Roles getRol() {
        return rol;
    }
    
    //String toString
    @Override
    public String toString() {
        return "Secretario{" + "codigoLibroActas=" + codigoLibroActas + ", codigosAlMinisterio=" + codigosAlMinisterio + '}';
    }
}
