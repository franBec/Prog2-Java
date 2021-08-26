package integrador.roles;

import integrador.ArrayListMejorada;
import integrador.horarios.HorarioSemana;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Archivo {
    
    //variables privadas
    private String nombreArchivo;

    //costructor
    public Archivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    //Dejar un txt en blanco (resulta util para las funciones de salvado)
    public void borrarTodoTxt() throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(nombreArchivo);
        writer.print("");
        writer.close();
    }

    //funciones de carga (USADAS AL ARRANCAR EL PROGRAMA)
    //LAS FUNCIONES DE CARGA DE ARCHIVO PUEDEN GENERAR ERROR DE TIPO java.lang.NullPointerException
    
    //cargar pasante
    public ArrayList<Pasante> cargarPasantesDesdeArchivo() throws IOException , NumberFormatException {
        ArrayList<Pasante> lista = new ArrayList<>();                           //se almacenan temporalmente los pasantes leidos
        File archivo = new File(nombreArchivo);
        FileReader archivoReader= new FileReader(archivo);
        BufferedReader archivoBuffered = new BufferedReader(archivoReader);

        /*
        El archivo a leer debe tener los datos puerstos en el siguiente orden:
            -USUARIO
            -CONTRASEÑA
            -NOMBRE Y APELLIDO
            -DNI
            -DOMICILIO
            -HORARIO
            -TAREAS DE PASANTE
        */

        while (archivoBuffered.ready()) {
            String usuario = archivoBuffered.readLine();                        //carga usuario
            if (usuario != null) {                                              //si lo que leo es distinto de null, pues entonces hay alguien para cargar
                int contrasenia = new Integer(archivoBuffered.readLine());      //carga contraseña
                String nombre = archivoBuffered.readLine();                     //carga nombre
                int dni = new Integer(archivoBuffered.readLine());              //carga dni
                String domicilio = archivoBuffered.readLine();                  //carga domicilio
                HorarioSemana horarios = new HorarioSemana();                   
                horarios.cargarDesdeLinea(archivoBuffered.readLine());          //carga los horarios desde el reglon
                String tareaARealizar = archivoBuffered.readLine();             //carga las tareas del pasante
                Pasante temporal = new Pasante(nombre, dni, domicilio);         //se crea el pasante
                temporal.setNombreDeUsuario(usuario);
                temporal.setContrasena(contrasenia);
                temporal.setTareasARealizar(tareaARealizar);
                temporal.setHorarioTrabajo(horarios);
                lista.add(temporal);                                            //se agrega a la lista
            }
            else 
                break;                                                          //si llego aca es porque estaba leyendo un renglon sin nada y no habia mas gente
        }
        archivoBuffered.close();
        return lista;                                                           //se devuelve los pasantes en un arreglo
    }

    //cargar directivo
    public ArrayList<Directivo> cargarDirectivosDesdeArchivo() throws IOException , NumberFormatException {
        ArrayList<Directivo> lista = new ArrayList<>();                         //se almacenan temporalmente los directivos leidos
        File archivo = new File(nombreArchivo);
        FileReader archivoReader= new FileReader(archivo);
        BufferedReader archivoBuffered = new BufferedReader(archivoReader);

        /*
        El archivo a leer debe tener los datos puerstos en el siguiente orden:
            -USUARIO
            -CONTRASEÑA
            -NOMBRE Y APELLIDO
            -DNI
            -DOMICILIO
            -HORARIO DE TRABAJO
            -HORARIO EN DIRECCION
            -HORARIO EN ATENCION AL PUBLICO
        */

        while (archivoBuffered.ready()) {
            String usuario = archivoBuffered.readLine();                        //carga usuario
            if (usuario != null){                                               //si lo que leo es distinto de null, pues entonces hay alguien para cargar
                int contrasenia = new Integer(archivoBuffered.readLine());      //carga contraseña
                String nombre = archivoBuffered.readLine();                     //carga nombre
                int dni = new Integer(archivoBuffered.readLine());              //carga dni
                String domicilio = archivoBuffered.readLine();                  //carga domicilio
                //horarios de trabajo
                HorarioSemana horarioTrabajo = new HorarioSemana();                   
                horarioTrabajo.cargarDesdeLinea(archivoBuffered.readLine());    //carga los horarios desde el reglon
                //horario de direccion
                HorarioSemana horarioDireccion = new HorarioSemana();
                horarioDireccion.cargarDesdeLinea(archivoBuffered.readLine());
                //horario de atencion al publico
                HorarioSemana horarioAtencion = new HorarioSemana();
                horarioAtencion.cargarDesdeLinea(archivoBuffered.readLine());
            
                Directivo temporal = new Directivo(nombre, dni, domicilio);     //Se crea el directivo
                temporal.setNombreDeUsuario(usuario);
                temporal.setContrasena(contrasenia);
                temporal.setHorarioTrabajo(horarioTrabajo);
                temporal.setHorarioEnDireccion(horarioDireccion);
                temporal.setHorarioAtencionPublico(horarioAtencion);
                lista.add(temporal);                                            //se agrega a la lista
            }
            else
                break;                                                          //si llego aca es porque estaba leyendo un renglon sin nada y no habia mas gente
        }
        archivoBuffered.close();
        return lista;                                                           //se devuelve los directivos en un arreglo
    }
    
    //cargar profesor
    public ArrayList<Profesor> cargarProfesoresDesdeArchivo() throws IOException , NumberFormatException {

        ArrayList<Profesor> lista = new ArrayList<>();                      //se almacenan temporalmente los profesores leidos
        File archivo = new File(nombreArchivo);
        FileReader archivoReader= new FileReader(archivo);
        BufferedReader archivoBuffered = new BufferedReader(archivoReader);

        /*
        El archivo a leer debe tener los datos puerstos en el siguiente orden:
            -USUARIO
            -CONTRASEÑA
            -NOMBRE Y APELLIDO
            -DNI
            -DOMICILIO
            -HORARIO DE TRABAJO
            -TITULO
            -MATERIA
        */

        while (archivoBuffered.ready()) {
            String usuario = archivoBuffered.readLine();                        //carga usuario
            if (usuario != null){                                               //si lo que leo es distinto de null, pues entonces hay alguien para cargar
                int contrasenia = new Integer(archivoBuffered.readLine());      //carga contraseña
                String nombre = archivoBuffered.readLine();                     //carga nombre
                int dni = new Integer(archivoBuffered.readLine());              //carga dni
                String domicilio = archivoBuffered.readLine();                  //carga domicilio
                //horarios de trabajo
                HorarioSemana horarioTrabajo = new HorarioSemana();                   
                horarioTrabajo.cargarDesdeLinea(archivoBuffered.readLine());    //carga los horarios desde el reglon
                //titulo
                String titulo = archivoBuffered.readLine();
                //materia
                String materia = archivoBuffered.readLine();
                Profesor temporal = new Profesor(nombre, dni, domicilio, titulo, materia);//Se crea el profesor
                temporal.setNombreDeUsuario(usuario);
                temporal.setContrasena(contrasenia);
                temporal.setHorarioTrabajo(horarioTrabajo);
                lista.add(temporal);                                            //se agrega a la lista
            }
            else
                break;                                                          //si llego aca es porque estaba leyendo un renglon sin nada y no habia mas gente
        }
        archivoBuffered.close();
        return lista;                                                           //se devuelve los profesores en un arreglo
    }
    
    //cargar preceptor
    public ArrayList<Preceptor> cargarPreceptorDesdeArchivo() throws IOException , NumberFormatException {

        ArrayList<Preceptor> lista = new ArrayList<>();                         //se almacenan temporalmente los preceptores leidos
        File archivo = new File(nombreArchivo);
        FileReader archivoReader= new FileReader(archivo);
        BufferedReader archivoBuffered = new BufferedReader(archivoReader);

        /*
        El archivo a leer debe tener los datos puerstos en el siguiente orden:
            -USUARIO
            -CONTRASEÑA
            -NOMBRE Y APELLIDO
            -DNI
            -DOMICILIO
            -HORARIO DE TRABAJO
            -HORARIO DE SUPERVISION
        */

        while (archivoBuffered.ready()) {

            String usuario = archivoBuffered.readLine();                        //carga usuario
            if (usuario != null){                                               //si lo que leo es distinto de null, pues entonces hay alguien para cargar
                int contrasenia = new Integer(archivoBuffered.readLine());      //carga contraseña
                String nombre = archivoBuffered.readLine();                     //carga nombre
                int dni = new Integer(archivoBuffered.readLine());              //carga dni
                String domicilio = archivoBuffered.readLine();                  //carga domicilio
                //horarios de trabajo
                HorarioSemana horarioTrabajo = new HorarioSemana();                   
                horarioTrabajo.cargarDesdeLinea(archivoBuffered.readLine());    //carga los horarios desde el reglon
                //horario de SUPERVISION
                HorarioSemana horarioSupervision = new HorarioSemana();
                horarioSupervision.cargarDesdeLinea(archivoBuffered.readLine());
                Preceptor temporal = new Preceptor(nombre, dni, domicilio);      //Se crea el preceptor
                temporal.setNombreDeUsuario(usuario);
                temporal.setContrasena(contrasenia);
                temporal.setHorarioTrabajo(horarioTrabajo);
                temporal.setHorarioSupervicion(horarioSupervision);
                lista.add(temporal);                                            //se agrega a la lista
            }
            else
                break;                                                          //si llego aca es porque estaba leyendo un renglon sin nada y no habia mas gente
        }
        archivoBuffered.close();
        return lista;                                                           //se devuelve los preceptores en un arreglo
    }
    
    //cargar secretario
    public ArrayList<Secretario> cargarSecretarioDesdeArchivo() throws IOException , NumberFormatException{

        ArrayList<Secretario> lista = new ArrayList<>();                        //se almacenan temporalmente los secretarios leidos
        File archivo = new File(nombreArchivo);
        FileReader archivoReader= new FileReader(archivo);
        BufferedReader archivoBuffered = new BufferedReader(archivoReader);

        /*
        El archivo a leer debe tener los datos puerstos en el siguiente orden:
            -USUARIO
            -CONTRASEÑA
            -NOMBRE Y APELLIDO
            -DNI
            -DOMICILIO
            -HORARIO DE TRABAJO
            -CODIGO DEL LIBRO DE ACTAS
            -ARREGLO DE CODIGOS AL MINISTERIO
        */

        while (archivoBuffered.ready()) {
            String usuario = archivoBuffered.readLine();                        //carga usuario
            if (usuario != null){                                               //si lo que leo es distinto de null, pues entonces hay alguien para cargar
                int contrasenia = new Integer(archivoBuffered.readLine());      //carga contraseña
                String nombre = archivoBuffered.readLine();                     //carga nombre
                int dni = new Integer(archivoBuffered.readLine());              //carga dni
                String domicilio = archivoBuffered.readLine();                  //carga domicilio
                //horarios de trabajo
                HorarioSemana horarioTrabajo = new HorarioSemana();                   
                horarioTrabajo.cargarDesdeLinea(archivoBuffered.readLine());    //carga los horarios desde el reglon
                //codigo del libro de actas
                int codigoActas = new Integer(archivoBuffered.readLine()); 
               //codigos al ministerio
               String lineaDeCodigo = archivoBuffered.readLine();
               String[] codigos = lineaDeCodigo.split(" ");
               ArrayList <Integer> cod = new ArrayList<>();
               for(String s : codigos){
                   cod.add(new Integer(s));
               }
                Secretario temporal = new Secretario (nombre, dni, domicilio);  //Se crea el secretario
                temporal.setNombreDeUsuario(usuario);
                temporal.setContrasena(contrasenia);
                temporal.setHorarioTrabajo(horarioTrabajo);
                temporal.setCodigoLibroActas(codigoActas);
                temporal.setCodigosAlMinisterio(cod);
                lista.add(temporal);                                            //se agrega a la lista
            }
            else
                break;                                                          //si llego aca es porque estaba leyendo un renglon sin nada y no habia mas gente
        }
        archivoBuffered.close();
        return lista;                                                           //se devuelve los secretarios en un arreglo
    }

    //funciones de guardado (USADAS AL CERRAR EL PROGRAMA)
    //DE MOMENTO ARROJAN IOException, verificar si no ocurre otro tipo de error
    
    //salvar pasante
    public void pasantesToTxt(ArrayListMejorada lista) throws IOException{
        borrarTodoTxt();
        File archivo = new File(nombreArchivo);
        FileWriter archivoWriter = new FileWriter(archivo,true);
        BufferedWriter buffer = new BufferedWriter(archivoWriter);
        
        for (Persona pasante : lista){
            if (pasante.getRol()==Persona.Roles.PASANTE){
               Pasante p = (Pasante)pasante;    //downcast
               buffer.append(p.getNombreDeUsuario()+"\n");
               buffer.flush();
               buffer.append(Integer.toString(p.getContrasena())+"\n");
               buffer.flush();
               buffer.append(p.getNombreyApellido()+"\n");
               buffer.flush();
               buffer.append(Integer.toString(p.getDni())+"\n");
               buffer.flush();
               buffer.append(p.getDomicilio()+"\n");
               buffer.flush();
               buffer.append(p.getRutinaEnRenglon()+"\n");
               buffer.flush();
               buffer.append(p.getTareasARealizar()+"\n");               
               buffer.flush();
            }
        }
        buffer.close();
    }
    
    //salvar directivo
    public void directivosToTxt(ArrayListMejorada lista) throws IOException{
        borrarTodoTxt();
        File archivo = new File(nombreArchivo);
        FileWriter archivoWriter = new FileWriter(archivo,true);
        BufferedWriter buffer = new BufferedWriter(archivoWriter);
        
        for (Persona directivo : lista){
            if (directivo.getRol()==Persona.Roles.DIRECTIVO){
               Directivo d = (Directivo)directivo;    //downcast
               buffer.append(d.getNombreDeUsuario()+"\n");
               buffer.flush();
               buffer.append(Integer.toString(d.getContrasena())+"\n");
               buffer.flush();
               buffer.append(d.getNombreyApellido()+"\n");
               buffer.flush();
               buffer.append(Integer.toString(d.getDni())+"\n");
               buffer.flush();
               buffer.append(d.getDomicilio()+"\n");
               buffer.flush();
               buffer.append(d.getRutinaEnRenglon()+"\n");
               buffer.flush();
               buffer.append(d.getHorarioEnDireccionToRenglon()+"\n");
               buffer.flush();
               buffer.append(d.getHorarioAtencionAlPublicoToRenglon()+"\n");
            }
        }
        buffer.close();
    }
    
    //salvar profesor
    public void profesoresToTxt(ArrayListMejorada lista) throws IOException{
        borrarTodoTxt();
        File archivo = new File(nombreArchivo);
        FileWriter archivoWriter = new FileWriter(archivo,true);
        BufferedWriter buffer = new BufferedWriter(archivoWriter);
        
        for (Persona profesor : lista){
            if (profesor.getRol()==Persona.Roles.PROFESOR){
               Profesor p = (Profesor)profesor;    //downcast
               buffer.append(p.getNombreDeUsuario()+"\n");
               buffer.flush();
               buffer.append(Integer.toString(p.getContrasena())+"\n");
               buffer.flush();
               buffer.append(p.getNombreyApellido()+"\n");
               buffer.flush();
               buffer.append(Integer.toString(p.getDni())+"\n");
               buffer.flush();
               buffer.append(p.getDomicilio()+"\n");
               buffer.flush();
               buffer.append(p.getRutinaEnRenglon()+"\n");
               buffer.flush();
               buffer.append(p.getTitulo()+"\n");               
               buffer.flush();
               buffer.append(p.getMateria()+"\n");               
               buffer.flush();
            }
        }
        buffer.close();
    }
    
    //salvar preceptor
    public void preceptoresToTxt(ArrayListMejorada lista) throws IOException{
        borrarTodoTxt();
        File archivo = new File(nombreArchivo);
        FileWriter archivoWriter = new FileWriter(archivo,true);
        BufferedWriter buffer = new BufferedWriter(archivoWriter);
        
        for (Persona preceptor : lista){
            if (preceptor.getRol()==Persona.Roles.PRECEPTOR){
               Preceptor p = (Preceptor)preceptor;    //downcast
               buffer.append(p.getNombreDeUsuario()+"\n");
               buffer.flush();
               buffer.append(Integer.toString(p.getContrasena())+"\n");
               buffer.flush();
               buffer.append(p.getNombreyApellido()+"\n");
               buffer.flush();
               buffer.append(Integer.toString(p.getDni())+"\n");
               buffer.flush();
               buffer.append(p.getDomicilio()+"\n");
               buffer.flush();
               buffer.append(p.getRutinaEnRenglon()+"\n");
               buffer.flush();
               buffer.append(p.getHorariosDeSupervisionToRenglon()+"\n");               
               buffer.flush();
            }
        }
        buffer.close();
    }
    
    //salvar secretario
    public void secretariosToTxt(ArrayListMejorada lista) throws IOException{
        borrarTodoTxt();
        File archivo = new File(nombreArchivo);
        FileWriter archivoWriter = new FileWriter(archivo,true);
        BufferedWriter buffer = new BufferedWriter(archivoWriter);
        
        for (Persona secretario : lista){
            if (secretario.getRol()==Persona.Roles.SECRETARIO){
               Secretario s = (Secretario)secretario;    //downcast
               buffer.append(s.getNombreDeUsuario()+"\n");
               buffer.flush();
               buffer.append(Integer.toString(s.getContrasena())+"\n");
               buffer.flush();
               buffer.append(s.getNombreyApellido()+"\n");
               buffer.flush();
               buffer.append(Integer.toString(s.getDni())+"\n");
               buffer.flush();
               buffer.append(s.getDomicilio()+"\n");
               buffer.flush();
               buffer.append(s.getRutinaEnRenglon()+"\n");
               buffer.flush();
               buffer.append(Integer.toString(s.getCodigoLibroActas())+"\n");               
               buffer.flush();
               buffer.append(s.getCodigosAlMinisterioToRenglon()+"\n");
               buffer.flush();
            }
        }
        buffer.close();
    }
    
}