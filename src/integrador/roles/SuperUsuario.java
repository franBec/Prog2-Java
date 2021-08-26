package integrador.roles;

public abstract class SuperUsuario {
    
    //variables privadas
    private String nombreDeUsuario;
    private int contrasena;
    
    //constructor
    public SuperUsuario() {
        this.nombreDeUsuario = "";
        this.contrasena = 0;
    }

    public SuperUsuario(String nombreDeUsuario, int contrasena) {
        
        this.nombreDeUsuario = nombreDeUsuario;
        this.contrasena = contrasena;
    }

    //getters y setters
    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public int getContrasena() {
        return contrasena;
    }

    public void setContrasena(int contrasena) {
        this.contrasena = contrasena;
    }
}
