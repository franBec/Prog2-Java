package integrador.roles;

public class Profesor extends Persona{
    
    //variables privadas
    private String titulo;
    private String materia;
    private final Roles rol = Persona.Roles.PROFESOR;

    //constructores
    public Profesor(String nombreyApellido, int dni, String domicilio,String titulo, String materia) {
        super(nombreyApellido, dni, domicilio);
        this.titulo = titulo;
        this.materia = materia;
    }

    public Profesor() {
        super();
        this.titulo = "";
    }

    //getters y setters
    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Profesor(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public Roles getRol() {
        return rol;
    }
    
    //String toString
    @Override
    public String toString() {
        return super.toString() + "\nProfesor{" + "titulo=" + titulo + ", materia=" + materia + '}';
    }
}
