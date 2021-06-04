/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eac2.ex1.gestors;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import eac2.ex1.model.Canal;

/**
 * Classe que gestiona la persistencia dels objectes de la classe model.Canal
 * @author professor
 */
public class GestorCanal {

    private Connection conn = null;

    /**
     * Crea un gestor de Canal que treballara amb la connexio conn
     * @param conn connexio a traves de la qual es fan persistents dels canals
     */
    public GestorCanal(Connection conn) {
       this.conn = conn;
    }
    
    /**
     * Crea un Canal en la base de dades. Si ja n'hi ha algun amb el seu mateix codi, llenca una excepcio.
     * @param can canal a crear
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Canal can) throws GestorException  {
        //TODO codificar el metode inserir
    }

    
    /**
     * Esborra de la base de dades un canal amb un codi determinat
     * @param canId codi del canal lasse a esborrar
     * @throws gestors.GestorException si el codi no correspon a cap canal de la base de dades
     * o hi ha un error en l'acces a la base de dades
     */
   
    public void eliminar(int canId) throws GestorException {
       //TODO codificar el metode eliminar
    }


    
    /**
     * Obte el canal de la base de dades amb un determinat codi.
     * @param canId codi del canal a obtenir
     * @return canal amb canId o null si no hi ha cap canal amb aquest id a la base de dades
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
   
    public Canal obtenirCanal(int canId) throws GestorException  {
        //TODO codificar el metode obtenirCanal
		
		return null; // s'ha posat perque es pugui compilar; cal substituir-lo pel codi correcte
    }

    /**
     * Retorna una llista dels canals als quals aquest usuari esta subscrit
     * @param subscriptor subscriptor utilitzat per a obtenir la llista de canals
     * @return Llista amb els canals de la base de dades que contenen un subscriptor al seu array de llistes de subscriptors
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    
    public List<Canal> obtenirCanalPerSubscriptor(String subscriptor) throws GestorException  {
        //TODO codificar el metode obtenirClassePerAlumne
		
		return null; // s'ha posat perque es pugui compilar; cal substituir-lo pel codi correcte
    }
    

}
