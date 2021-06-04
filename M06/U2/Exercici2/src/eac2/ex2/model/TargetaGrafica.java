/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eac2.ex2.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Classe que representa una fitxa d'una targeta gràfica a una tenda de components
 * @author professor
 */

//TODO afegir anotacions necessaries per a fer la classe persistent amb JPA
public class TargetaGrafica {

    private int nombreSerie;
    private String model;
    private float frequenciaProcessador;
    private String arquitectura;
    private String nucli;
    private int memoriaRam;
    private boolean raytrace;
 
    /**
     * Constructor per defecte Constructors parametritzats
     */
    public TargetaGrafica() {
        
    }

    /**
     * Constructor parametritzat
     */
    public TargetaGrafica(int nombreSerie, String model, float frequenciaProcessador, String arquitectura, String nucli, int gb, boolean raytrace) {
        this.nombreSerie = nombreSerie;
        this.model = model;
        this.frequenciaProcessador = frequenciaProcessador;
        this.arquitectura = arquitectura;
        this.nucli = nucli;
        this.memoriaRam = gb;
        this.raytrace = raytrace;
        
    }

    /**
     * Obte el valor de la propietat nombreSerie. La variable nombreSerie identifica el dispositiu dins del sistema de persistencia
     *
     * @return el valor de la propietat nombreSerie
     */
    public int getNombreSerie() {
        return nombreSerie;
    }

    /**
     * Actualitza el valor de la propietat nombreSerie. La variable nombreSerie identifica el dispositiu dins del sistema de persistencia
     *
     * @param id nou valor de la propietat nombreSerie
     */
    public void setNombreSerie(int id) {
        this.nombreSerie = id;
    }    
    
    /**
     * Obte el valor de la propietat model de la gfx
     *
     * @return el valor de la propietat model
     */
    public String getModel() {
        return model;
    }

    /**
     * Actualitza el valor de la propietat model de  la gfx
     *
     * @param nom nou valor de la propietat model
     */
    public void setModel(String nom) {
        this.model = nom;
    }

    /**
     * Obte el valor de la propietat arquitectura de la gfx     *
     * @return el valor de la propietat arquitectura
     */
    public String getArquitectura() {
        return arquitectura;
    }

    /**
     * Actualitza el valor de la propietat arquitectura de la gfx
     *
     * @param arquitectura nou valor de la propietat arquitectura
     */
    public void setArquitectura(String arquitectura) {
        this.arquitectura = arquitectura;
    }
    
    
    /**
     * Obte el valor de la propietat frequenciaProcessador de la gfx
     *
     * @return el valor de la propietat frequenciaProcessador
     */
    public float getFrequenciaProcessador() {
        return frequenciaProcessador;
    }

    /**
     * Actualitza el valor de la propietat frequenciaProcessador  de la gfx
     *
     * @param frequenciaProcessador nou valor de la propietat frequenciaProcessador
     */
    public void setFrequenciaProcessador(float frequenciaProcessador) {
        this.frequenciaProcessador = frequenciaProcessador;
    }
 /**
     * Obte el valor de la propietat nucli  de la gfx
     *
     * @return el valor de la propietat nucli
     */
    public String getNucli() {
        return nucli;
    }

    /**
     * Actualitza el valor de la propietat nucli de la gfx
     *
     * @param nucli nou valor de la propietat nucli
     */
    public void setNucli(String nucli) {
        this.nucli = nucli;
    }
    
     /**
     * Obté el valor de la propietat memoria en gigues de la gfx
     *
     * @return el valor de la propietat memoria
     */
    public int getMemoriaRam() {
        return memoriaRam;
    }

    /**
     * Actualitza el valor de la propietat memoria en gigues de la gfx
     *
     * @param gigues nou valor de la propietat memoria
     */
    public void setMemoriaRam(int gigues) {
        this.memoriaRam = gigues;
    }
    
    /**
     * Obte el valor de la propietat raytrace de la gfx
     *
     * @return el valor de la propietat raytrace de la gfx
     */
    public boolean isRaytrace() {
        return raytrace;
    }

    /**
     * Actualitza el valor de la propietat raytrace de la gfx
     *
     * @param raytrace nou valor de la propietat raytrace de la gfx
     */
    public void setRaytrace(boolean raytrace) {
        this.raytrace = raytrace;
    }

}
