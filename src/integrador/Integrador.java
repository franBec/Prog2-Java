package integrador;

import integrador.roles.Archivo;
import java.io.IOException;
import javax.swing.JOptionPane;
import ventanas2.MainVentana;

public class Integrador {

    public static void main(String[] args) {
        //Definicion de array de personas
        ArrayListMejorada personal = new ArrayListMejorada();
        //Lectura de archivos
        Archivo directivos, pasantes, preceptores, profesores, secretarios;
        directivos = new Archivo("directivos.txt");
        pasantes = new Archivo("pasantes.txt");
        preceptores = new Archivo("preceptores.txt");
        profesores = new Archivo("profesores.txt");
        secretarios = new Archivo("secretarios.txt");
        //Carga de personal al arreglo
        //Hay que manejar la excepcion en caso de que no se pueda leer el archivo
        try {            
        personal.addAll(directivos.cargarDirectivosDesdeArchivo());
        personal.addAll(pasantes.cargarPasantesDesdeArchivo());
        personal.addAll(preceptores.cargarPreceptorDesdeArchivo());
        personal.addAll(profesores.cargarProfesoresDesdeArchivo());
        personal.addAll(secretarios.cargarSecretarioDesdeArchivo());
        }
        catch (IOException e){
            System.out.println("Error en la lectura de los archivos");
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null,"Error en la lectura de los archivos n"+e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException e) {
            System.out.println("Error en la lectura de los archivos");
            System.out.println("El formato de los archivos no es correcto o estan corruptos");
            JOptionPane.showMessageDialog(null, e.toString(),"Archivos corruptos\nSe crearan archivos nuevos",JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        //INVOCACION A LA VENTANA DE LOGUEO
        MainVentana ventanaPrincipal = new MainVentana(personal, directivos, pasantes, preceptores, profesores, secretarios);
        ventanaPrincipal.setVisible(true);
    }
}
