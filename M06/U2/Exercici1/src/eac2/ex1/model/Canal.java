

package eac2.ex1.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que representa un canal d'un servei de streaming
 * @author professor
 */
public class Canal {

    private int id;
    private String nom;
    private String tema;
    private int numVideos;
    private final List<String> subscriptors = new ArrayList<>();
    private String nick;
    private String nomUsuari;
    private boolean majorEdat;
    private String residencia;

    
    public Canal() {
    }

    public Canal(int id, String nom, String tema, int numVideos, String [] llistatSubscriptors, String nick, String nomUsuari, boolean majorEdat, String residencia) {
        this.id = id;
        this.nom = nom;
        this.tema = tema;
        this.numVideos = numVideos;
        this.subscriptors.addAll(Arrays.asList(llistatSubscriptors));
        this.nick = nick;
        this.nomUsuari = nomUsuari;
        this.majorEdat = majorEdat;
        this.residencia = residencia;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public int getNumVideos() {
		return numVideos;
	}

	public void setNumVideos(int numVideos) {
		this.numVideos = numVideos;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNomUsuari() {
		return nomUsuari;
	}

	public void setNomUsuari(String nomUsuari) {
		this.nomUsuari = nomUsuari;
	}

	public boolean isMajorEdat() {
		return majorEdat;
	}

	public void setMajorEdat(boolean majorEdat) {
		this.majorEdat = majorEdat;
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	public List<String> getSubscriptors() {
		return subscriptors;
	}
    
	public void setSubscriptors(List<String> subscriptors) {
		this.subscriptors.clear();
		this.subscriptors.addAll(subscriptors);
	}

    
    

}
