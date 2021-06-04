/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaAplicacio.gestors;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaAplicacio.model.ProducteElaborat;

/**
 * Classe que gestiona la persistencia dels objectes de la classe logicaAplicacio.model.ProducteElaborat
 * @author professor
 */
public class GestorProducteElaborat {
    private EntityManager em = null;

    /**
     * Crea un gestor de productes elaborats que treballara amb l'EntityManager em
     * @param em context on es fan persistents els cap
     */
    public GestorProducteElaborat (EntityManager em) {
       this.em = em;
    }

    /**
     * Consultar tots els productes elaborats de la base de dades
     * @return Llista amb tots els productes elaborats de la base de dades
     */
    public List<ProducteElaborat> obtenirProductesElaborats() {
       //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
   
    /**
     * Obte una llista amb tots els productes elaborats de la base de dades amb un preu igual o superior al donat
     * @param preu preu dels productes
     * @return llistat amb els productes elaborats a la base de dades amb un preu igual o superior al donat
     */
    public List<ProducteElaborat> obtenirProducteElaboratPerPreu(float preu) {
       //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
    
    /**
     * Obté una llista amb totes les materies primeres necessaries per aquest producte elaborat
     * @param IdPE identificador del producte elaborat
     * @return llista amb totes les materies primeres necessaries per aquest producte elaborat
     */
    public List<ProducteElaborat> obtenirMateriesPrimeresDelProducteElaborat(int IdPE) {
        //TODO completar el metode
        return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
    
     /**
     * Incrementa el preu de tots els productes elaborats de la base de dades en un percentatge determinat
     * @param percentantge tant per cent (%) d'increment 
     */
    public void IncrementaPreu(float percentantge){
        //TODO completar el metode
      
    }

}
