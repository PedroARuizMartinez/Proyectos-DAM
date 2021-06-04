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
 *
 * @author professor
 */
//TODO posar les anotacions necessaries a la classe
public class ProducteElaborat extends Producte {
    
    private float preu;
    private List<MateriaPrimera> materiesPrimeres=new ArrayList<>();

    
    
    /**
     * Constructor per defecte: construeix un producte elaborat amb els valors per defecte de Java
     */
    public ProducteElaborat() {
    }

    /**
    * Constructor parametritzat: construeix un nou producte amb els paramtres especificats
    * @param codi identificador del producte
    * @param nom nom del producte
    * @param refrigerat si el producte necessita o no refrigeració
    * @param preu preu de venta
     */
    public ProducteElaborat(int codi, String nom, boolean refrigerat, float preu) {
        super(codi, nom, refrigerat);
        this.preu = preu;   
    }

    /**
    * preu de venta del producte
    * @return preu de venta del producte
    */  
    public float getPreu() {
        return preu;
    }

  /**
    * Modifica el preu de venta del producte
    * @param espec nou preu de venta del producte
    */
    public void setPreu(float preu) {
        this.preu = preu;
    }
    
    
      /**
    * Llistat de les materies primeres necessaries per a elaborar aquest producte elaborat
    * @return Llistat de les materies primeres necessaries per a elaborar aquest producte elaborat
    */   
    public List<MateriaPrimera> getMateriesPrimeres() {
        return this.materiesPrimeres;
    }
    
    /**
    * Modifica el Llistat de les materies primeres necessaries per a elaborar aquest producte elaborat
     *@param mp nou Llistat de les materies primeres necessaries per a elaborar aquest producte elaborat
    */   
    public void setMateriesPrimeres(List<MateriaPrimera> mp) {
        this.materiesPrimeres=mp;
    } 
    
    
}
