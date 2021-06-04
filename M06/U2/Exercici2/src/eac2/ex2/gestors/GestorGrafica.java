/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac2.ex2.gestors;

import javax.persistence.EntityManager;
import eac2.ex2.model.TargetaGrafica;

/**
 * Classe que gestiona la persistencia dels objectes de la classe model.TargetaGrafica
 * @author professor
 */
public class GestorGrafica {
    
    
    private EntityManager gfx = null;

    
    /**
     * Crea un gestor de targetes grafiques que treballara amb l'EntityManager TargetaGrafica
     * @param dispositiu context on es fan persistentents les targetes
     */
    public GestorGrafica(EntityManager dispositiu) {
        
       this.gfx = dispositiu;
       
    }
    
    
    /**
     * Dona d'alta una grafica en la base de dades. Si ja n'hi ha alguna amb el seu mateix nombre de serie, llenca una excepcio.
     * @param fitxa fitxa de la grafica a donar d'alta
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(TargetaGrafica fitxa) throws GestorException {
      //TODO codificar el metode inserir
        
    }

    
    /**
     * Modifica la fitxa d'una grafica de la base de dades. Si no n'hi ha cap amb el seu nombre de serie, llenca una excepcio.
     * @param fitxa fitxa de la grafica actualitzat
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void modificar(TargetaGrafica fitxa) throws GestorException {
      //TODO codificar el metode modificar
    }    
    
    
    /**
     * Esborra la fitxa d'una grafica amb un determinat numero de serie
     * @param nombreSerie nombre serie de la gràfica a esborrar
     * @throws gestors.GestorException si el nombre de serie no correspon a cap grafica de la base de dades
     */
    public void eliminar(int nombreSerie) throws GestorException {
      //TODO codificar el metode eliminar

    }

   
    /**
     * Obte la grafica de la base de dades amb un numero de serie determinat
     * @param nombreSerie nombre serie de la gràfica a obtenir
     * @return fitxa de la gràfica amb aquell nombre serie o null si no hi ha cap grafica a la base de dades amb aquest numero de serie
     */
    public TargetaGrafica obtenirTargetaGrafica(int nombreSerie) {
        //TODO codificar el metode obtenirTargetaGrafica
		
		return null; // s'ha posat perque es pugui compilar; cal substituir-lo pel codi correcte  
    }
    
 
}
