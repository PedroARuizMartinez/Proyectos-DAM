/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eac2.ex1.gestors;

/**
 * Situacio excepcional produida en el sistema de persistencia
 * @author professor
 */
public class GestorException extends Exception{

    public GestorException(String message) {
        super(message);
    }
    

}
