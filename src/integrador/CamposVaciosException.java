/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador;

import java.awt.Component;

/**
 *
 * @author franb
 */
public class CamposVaciosException extends Exception {

    public CamposVaciosException() {
    }

    public CamposVaciosException(String message) {
        super(message);
    }
    
    public String mensajeDeErrorComun() {
        return "Uno de los campos de texto esta vacio";
    }
    
    public String mensajeConOrigen(Component com) {
        return "El componente "+com.getName()+" contiene campos vacios";
    }
    
}
