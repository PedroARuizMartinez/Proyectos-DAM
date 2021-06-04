/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package todo;



import gestors.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xquery.*;
import model.Canal;

/**
 * Classe que gestiona la persistencia dels objectes de la classe tipus model.Canal
 * @author alumne
 */
public class GestorCanal {


    protected static final String CLAU_DUPLICADA = "CLAU DUPLICADA";
    protected static final String CLAU_INEXISTENT = "CLAU INEXISTENT";
    protected static final String ARREL = "doc(\"canals/canals.xml\")/collection(\"canals\")//"; //arrel del document (permet simplificar les expressions)
    
    private XQConnection con=null;

    /**
     * Crea un gestor de canal que treballara amb la connexio connex
     * @param con connexio a traves de la qual es fan persistents els canals
     */
    public GestorCanal(XQConnection con) {
       this.con = con;
    }
    
    
    /**
     * Dona d'alta un Canal en la base de dades. Si ja n'hi ha alguna amb el seu mateix id, llenca una excepcio.
     * @param nouCanal canal a donar d'alta
     * @throws gestors.GestorException en cas d'error a la base de dades que pot ser, entre altres, clau duplicada.
     */
    public void inserir(Canal nouCanal) throws GestorException, XQException  {
       //TODO 
    }

    
    
    /**
     * Esborra de la base de dades un canal amb un id determinat
     * @param idCanal id del canal  a esborrar
     * @throws gestors.GestorException si el codi no correspon a cap canal de la base de dades
     * o hi ha un error en l'acces a la base de dades
     */
    public void eliminar(int idCanal) throws GestorException {
       //TODO 
    }


    
    /**
     * Obte el canal de la base de dades amb un determinat id.
     * @param idCanal identificador del canal a obtenir
     * @return canal amb idCanal o bé null si no hi ha cap canal a la base de dades
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    public Canal obtenirCanal(int idCanal) throws GestorException  {
        //TODO 
	return null;  //només per a que no doni errors de compilacio
    }

    
    /**
     * Obté una llista amb els canals que compten amb aquest subscriptor
     * @param subs subs utilitzat per a obtenir la llista dels subscriptors
     * @return Llista amb els canal de la base de dades que contenen el subscriptor demanat
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    public List<Canal> obtenirCanalPerSubscriptor(String subs) throws GestorException  {
        //TODO 
       return null;  //només per a que no doni errors de compilacio
    }

}