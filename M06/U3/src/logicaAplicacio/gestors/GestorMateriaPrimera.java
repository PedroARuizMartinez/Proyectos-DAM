/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaAplicacio.gestors;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaAplicacio.model.MateriaPrimera;
import logicaAplicacio.model.ProducteElaborat;

/**
 * Classe que gestiona la persistencia dels objectes de la classe logicaAplicacio.model.MateriaPrimera
 * @author professor
 */
public class GestorMateriaPrimera {
    private EntityManager em = null;

    /**
     * Crea un gestor de materia primera que treballara amb l'EntityManager em
     * @param em context on es fan persistents materia primera
     */
    public GestorMateriaPrimera(EntityManager em) {
       this.em = em;
    }

    /**
     * Consultar totes les materies primeres de la base de dades
     * @return Llista amb tots les materies primeres de la base de dades
     */
    public List<MateriaPrimera> obtenirMateriesPrimeres() {
       //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
   
    /**
     * Obte una llista amb tots les materies primeres de la base de dades amb una prio determinada
     * @param prioritat prioritat de les materies primeres a obtenir
     * @return llistat amb les materies primeres a la base de dades amb una prioritat determinada
     */
    public List<MateriaPrimera> obtenirMateriaPrimeraPerPrioritat(int prioritat) {
       //TODO completar el metode
       return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
    
    /**
     * Obté una llista amb tots els productes elaborats amb aquesta materia primera
     * @param IdMat identificador del producte tipus materia primera
     * @return llistat dels empleats tipus producte elaborat, que s'hagin elaborat amb aquesta materia primera
     */
    public List<ProducteElaborat> obtenirProductesElaboratsAmbMateriaPrimera(int IdMat) {
        //TODO completar el metode
        return null; // nomes esta perque no doni error de compilacio; probablement s'haura d'eliminar o canviar
    }
    
     /**
     * Augmenta la prioritat de totes les materies primeres. puja en 1 punt la prioritat
     */
    public void AugmentaPrioritatTotes(){
        //TODO completar el metode
      
    }
    

}
