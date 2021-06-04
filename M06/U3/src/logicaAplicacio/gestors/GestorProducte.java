/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicaAplicacio.gestors;

import logicaAplicacio.model.Producte;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Classe per abstreure diferents accions sobre els objectes de tipus Producte
 * @author professor
 */
public class GestorProducte {

    private EntityManager em = null;

    /**
     * Constructor que associa el gestor a un EntityManager
     * @param em EntityManager al qual s'associa el gestor
     */
    public GestorProducte(EntityManager em) {
        this.em = em;
    }

    /**
     * Consultar tots els productes de la base de dades
     * @return Llista amb tots els productes de la base de dades
     */
    public List<Producte> obtenirProductes() {
       //TODO completar el metode
        return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
     /**
     * Esborra el producte de la base de dades que te un determinat codi
     * @param id codi que identifica el producte a esborrar
     * @throws logicaAplicacio.gestors.GestorException si el codi no correspon a cap producte de la base de dades
     * o hi ha un error en aquesta
     */
    public void eliminar(int id) throws GestorException {
        //TODO completar el metode
   
    }

    /**
     * Obte el producte de la base de dades amb un codi determinat
     * @param id codi del producte a obtenir
     * @return producte o null en cas de no haver-hi cap producte persistent amb aquest codi
     */
    public Producte obtenirProducte(int id) {
        //TODO completar el metode
        return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }

    /**
     * Dona d'alta un producte en la base de dades. Si ja n'hi ha algun amb el seu mateix codi, llenca una excepcio.
     * @param prod producte a inserir
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Producte prod) throws GestorException {
        //TODO completar el metode

    }

   /**
     * Modifica un producte de la base de dades. Si no n'hi ha cap amb el seu codi, llenca una excepcio.
     * @param prd producte a actualitzar
     * @throws logicaAplicacio.gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau inexistent.
    */
    public void modificar(Producte prd) throws GestorException{
       //TODO completar el metode
    }
    
}
