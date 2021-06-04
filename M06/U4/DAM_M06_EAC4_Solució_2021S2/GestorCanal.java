/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestors;



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
       try {
            if(obtenirCanal(nouCanal.getId())==null){  // no hi es: correcte
                XQExpression expr=con.createExpression();
                expr.executeQuery("insert node "+Utilitats.formaCanalXML(nouCanal)+" into "+ARREL+"canals");
                expr.close();
            }
            else {
                throw new GestorException (CLAU_DUPLICADA);
            }
        } catch (XQException ex) {
                throw new GestorException (ex.getMessage());
        } 
    }

    
    
    /**
     * Esborra de la base de dades un canal amb un id determinat
     * @param idCanal id del canal  a esborrar
     * @throws gestors.GestorException si el codi no correspon a cap canal de la base de dades
     * o hi ha un error en l'acces a la base de dades
     */
    public void eliminar(int idCanal) throws GestorException {
       try {
            if(obtenirCanal(idCanal)==null){
                throw new GestorException (CLAU_INEXISTENT);
            }

            // existeix: l'esborrem
            XQExpression expr=con.createExpression();            
            expr.executeQuery("delete node "+ARREL+"canals/canal[id = \""+idCanal+"\" ]");
            expr.close();
       } catch (XQException ex) {
            throw new GestorException (ex.getMessage());
       }
    }


    
    /**
     * Obte el canal de la base de dades amb un determinat id.
     * @param idCanal identificador del canal a obtenir
     * @return canal amb idCanal o bé null si no hi ha cap canal a la base de dades
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    public Canal obtenirCanal(int idCanal) throws GestorException  {


	try {
            XQExpression expr=con.createExpression();
            XQResultSequence query= expr.executeQuery("for $h in "+ARREL+"canals/canal[id = \""+idCanal+"\"] return $h");
            
            String primerText=null;
            while(query.next()){
                primerText=query.getItemAsString(null);
            }
            expr.close();
            if(primerText==null){
                return null;
            }else{
                return Utilitats.obteCanal(primerText);
        }} catch (XQException ex) {
            throw new GestorException (ex.getMessage());
        }
    }

    
    /**
     * Obté una llista amb els canals que compten amb aquest subscriptor
     * @param subs subs utilitzat per a obtenir la llista dels subscriptors
     * @return Llista amb els canal de la base de dades que contenen el subscriptor demanat
     * @throws gestors.GestorException en cas d'error a la base de dades
     */
    public List<Canal> obtenirCanalPerSubscriptor(String subs) throws GestorException  {
        try {
            XQExpression expr=con.createExpression();
            XQResultSequence query=(XQResultSequence) expr.executeQuery("for $h in "+ARREL+"canals/canal[subscriptors/subscriptor = \""+subs+"\"] return $h");

            List<Canal> llista = new ArrayList<>();
            
            while(query.next()){
                llista.add(Utilitats.obteCanal(query.getItemAsString(null)));
            }
            expr.close();
            return llista;
        } catch (XQException ex) {
            throw new GestorException (ex.getMessage());
        }
    }

}