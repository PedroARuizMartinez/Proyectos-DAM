/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicaAplicacio.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import static javax.persistence.DiscriminatorType.CHAR;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Classe Producte de la jerarquia de lògica de l'aplicació
 * @author alumne
 */
//TODO posar les anotacions necessaries a la classe
public class Producte implements Serializable {
  private int idProd;
  private String nom;
  private boolean refrigerat;


   /**
    * Constructor per defecte: construeix un nou producte amb els valors per defecte de Java
    * (es necessari perque pugui funcionar JPA correctament)
    */
   public Producte() {
   }   
   
   
   /**
    * Constructor parametritzat: construeix un nou producte amb els paramtres especificats
    * @param codi identificador del producte
    * @param nom nom del producte
    * @param refrigerat si el producte necessita o no refrigeració
    */
   protected Producte(int codi, String nom, boolean refrigerat) {
        this.idProd = codi;
        this.nom = nom;
        this.refrigerat = refrigerat;
        
   }
   
   /**
    * Obte el codi que identifica el producte
    * @return idProd que identifica el producte
    */
   public int getIdProd() {
        return idProd;
   }

   /**
    * Actualitza el codi que identifica el producte
    * @param codi nou valor per al idProd que identifica el producte
    */
   public void setIdProd(int codi) {
        this.idProd = codi;
   }   

   /**
    * Obte el nom del producte
    * @return nom del producte
    */
   public String getNom() {
        return nom;
   }

   /**
    * Actualitza el nom del producte
    * @param nom nou valor per al nom del producte
    */
   public void setNom(String nom) {
        this.nom = nom;
   }

   /**
    * Obte l'estatus de refrigeracio del producte
    * @return estatus de refrigeracio del producte
    */
   public boolean getRefrigerat() {
        return refrigerat;
   }

   /**
    * Actualitza l'estatus de refrigeracio del producte
    * @param sou nou valor per a l'estatus de refrigeracio del producte
    */
   public void setRefrigerat(boolean ref) {
        this.refrigerat = ref;
   }
}