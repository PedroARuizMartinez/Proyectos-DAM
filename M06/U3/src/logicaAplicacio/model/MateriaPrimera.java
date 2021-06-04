/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicaAplicacio.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Producte materia primera
 * @author professor
 */
//TODO posar les anotacions necessaries a la classe
public class MateriaPrimera extends Producte {
    
    private int prio;
    private List<ProducteElaborat> productesElaborats=new ArrayList<>();

       
    
    /**
     * Constructor per defecte: construeix un producte amb els valors per defecte de Java
    */
    public MateriaPrimera() {
    }

   /**
    * Constructor parametritzat: construeix un nou producte amb els paramtres especificats
    * @param codi identificador del producte
    * @param nom nom del producte
    * @param ref si el producte necessita o no refrigeració
    * @param prio prioritat del producte a l'hora del reabastiment
    */
    public MateriaPrimera(int codi, String nom, boolean ref, int prio) {
        super(codi, nom, ref);
        this.prio=prio;
    }

    /**
     * Obte la prioritat de la matèria primera
     * @return prio prioritat
     */
    public int getPrio() {
        return prio;
    }

    /**
     * Actualitza la prioritat de la matèria primera
     * @param prio nou valor per la prioritat de la matèria primera
     */
    public void setPrio(int prio) {
        this.prio = prio;
    }
    
          /**
    * Llistat dels productes elaborats que es poden elaborar amb aquesta materia primera
    * @return Llistat dels productes elaborats que es poden elaborar amb aquesta materia primera
    */   
    public List<ProducteElaborat> getProductesElaborats() {
        return this.productesElaborats;
    }
    
    /**
    * Modifica el Llistat dels productes elaborats que es poden elaborar amb aquesta materia primera
     *@param mp nou Llistat dels productes elaborats que es poden elaborar amb aquesta materia primera
    */   
    public void setProductesElaborats(List<ProducteElaborat> mp) {
        this.productesElaborats=mp;
    } 

   
}
