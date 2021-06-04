package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;

/**
 * Canal representa un canal de Twitch
 *
 * @author alumne
 */
@XmlRootElement
public class Canal {

    private int id;
    private String nom;
    private String tema;
    private int numVideos;
    private final ArrayList<String> subscriptors = new ArrayList<>();
    private String nomPropietari;

    public Canal() {

    }

    public Canal(int id, String nom, String tema, int numVideos, String[] llistatSubscriptors, String nickPropietari) {
        this.id = id;
        this.nom = nom;
        this.tema = tema;
        this.numVideos = numVideos;
        this.subscriptors.addAll(Arrays.asList(llistatSubscriptors));
        this.nomPropietari = nickPropietari;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @XmlElement
    public int getNumVideos() {
        return numVideos;
    }

    public void setNumVideos(int numVideos) {
        this.numVideos = numVideos;
    }

    @XmlElement
    public String getNomPropietari() {
        return nomPropietari;
    }

    public void setNomPropietari(String n) {
        this.nomPropietari = n;
    }

    @XmlElementWrapper(name = "subscriptors")
    @XmlElement(name = "subscriptor")
    public ArrayList<String> getSubscriptors() {
        return subscriptors;
    }

    public void setSubscriptors(List<String> subscriptors) {
        this.subscriptors.clear();
        this.subscriptors.addAll(subscriptors);
    }

}
