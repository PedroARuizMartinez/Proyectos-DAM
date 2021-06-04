package eac2.ex1.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import eac2.ex1.model.Canal;
import eac2.ex1.solucio.GestorCanal;
import eac2.ex1.gestors.GestorException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 *
 * @author alumne
 */
public class TestGestor {
    
    private final String TAULA="canal";
    
    private final String URL="jdbc:postgresql://localhost:5432/eac2_2021s2";
    private final String USER="ioc";
    private final String PSW="ioc";
   
    private GestorCanal gestor;
    
    
    private Connection connexio;
    
    
    private final Canal[] canals = {
            new Canal(1, "elXokas", "World of Warcraft", 420, new String[]{"choux","plumero","ibaillanos"}, "elXokas", "Joaquín Domínguez", true, "Madrid"),
            new Canal(2, "MiquelMontoro", "Vida Rural", 67, new String[]{"ibaillanos","ambush","kinrolls"}, "montorom", "Miquel Montoro", false, "Mallorca"),
            new Canal(3, "elMillor", "LoL", 4303, new String[]{"alejita"}, "ElmilloR", "Elm Cherto", true, "Andorra"),
            new Canal(4, "elRubius", "Rust", 897, new String[]{"choux","alejita"}, "elRubius", "Rubén Doblas", true, "Andorra"),
            new Canal(5, "Ibai", "varios", 10000, new String[]{"elXokas","montoromiquel","choux","plumero","ambush","kinrolls"}, "ibaillanos", "Ibai Llanos", true, "Cerdanyola"),
    };
    
    
    @Before
    public void setUp() throws SQLException, GestorException {
        obrir();
        buidarTaula();
        ferAltes();
        
    }
    
    @After
    public void tearDown() throws SQLException {
        tancar();
    }
    
    
    private void tancarIObrir() throws SQLException{
        tancar();
        obrir();
        
    }
    
    
    private void obrir() throws SQLException{
        connexio = DriverManager.getConnection(URL, USER, PSW);
        connexio.setAutoCommit(true);
        gestor = new GestorCanal(connexio);
     
    }
    
    private void tancar() throws SQLException{
        connexio.close();
    }
    
    private void buidarTaula() throws SQLException{
        PreparedStatement ps=connexio.prepareStatement("DELETE FROM "+TAULA);
        
        ps.executeUpdate();
    }
    
    private void ferAltes() throws GestorException {
        for(Canal c : canals){
            gestor.inserir(c);
        }
    }
    
    
    public boolean comparaCanals(Canal p1, Canal p2){
        return  p1.getId()==p2.getId() &&
                p1.getNom().equals(p2.getNom()) &&
                p1.getTema().equals(p2.getTema()) &&
                p1.getNumVideos() == p2.getNumVideos() &&
                p1.getNick().equals(p2.getNick()) &&
                p1.getNomUsuari().equals(p2.getNomUsuari()) &&
                p1.getResidencia().equals(p2.getResidencia()) &&
                p1.isMajorEdat()==p2.isMajorEdat() &&
                comparaArraysCadena(p1.getSubscriptors(),p2.getSubscriptors());
    }
    
    public boolean comparaArraysCadena(List<String> l1, List<String> l2){
        
        if(l1.size()!=l2.size()){
            return false;
        }
        
        for(String s:l1){
            if(!l2.contains(s)){
                return false;
            }
        }
        return true;
    }
    
    // les altes es fan cada vegada en el Before, amb el que no és necessari aquest mètode. Si hi ha les altes correctes es comprova 
    // als metodes provaConsultaPerCodiTots i provaConsultaPerCodiCap
    @Test
    public void provaAltes() throws GestorException{
    }
    
    @Test 
    public void provaConsultaPerCodiTots() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers
        
        Canal a=gestor.obtenirCanal(3);
        System.out.println(a);
        assertTrue(comparaCanals(a,canals[2]));
        
        a=gestor.obtenirCanal(1);
        assertTrue(comparaCanals(a,canals[0]));
        
        a=gestor.obtenirCanal(4);
        assertTrue(comparaCanals(a,canals[3]));
        
        a=gestor.obtenirCanal(2);
        assertTrue(comparaCanals(a,canals[1]));
        
        a=gestor.obtenirCanal(5);
        assertTrue(comparaCanals(a,canals[4]));
    }
    
    //provem a fer consultes per codis innexistents
    
    @Test 
    public void provaConsultaPerIdCap() throws SQLException, GestorException {
        tancarIObrir();  //ens assegurem que s'han gravat els buffers

        assertNull(gestor.obtenirCanal(40));
        assertNull(gestor.obtenirCanal(-3));
        assertNull(gestor.obtenirCanal(70));

    }
    
    @Test
    public void provaObtenirCanalsPerSubscriptor() throws SQLException, GestorException {
        tancarIObrir();
        
        List<Canal> l=gestor.obtenirCanalPerSubscriptor("choux");
        
        System.out.println(l);
        
        assertTrue(l.size()==3 && comprovaCanal(l,1)&& comprovaCanal(l,4)&& comprovaCanal(l,5));
        
        l=gestor.obtenirCanalPerSubscriptor("alejita");
        
        assertTrue(l.size()==2 && comprovaCanal(l,3)&& comprovaCanal(l,4));
        
        l=gestor.obtenirCanalPerSubscriptor("kinrolls");
        
        assertTrue(l.size()==2 && comprovaCanal(l,2)&& comprovaCanal(l,5));
        
        l=gestor.obtenirCanalPerSubscriptor("elXokas");
        
        assertTrue(l.size()==1 && comprovaCanal(l,5));

    }
    
    private boolean comprovaCanal(List<Canal> l, int id){
        for(Canal p:l){
            if(p.getId()==id && comparaCanals(p,canals[id-1])){ // aprofitem que posicio = id - 1
                return true;
            }
        }
        return false;
    }
    
    @Test
    public void provaEliminar() throws SQLException, GestorException {
        gestor.eliminar(3);
        tancarIObrir();
        
        assertNull(gestor.obtenirCanal(3));
        
        Canal a=gestor.obtenirCanal(1);
        assertTrue(comparaCanals(a,canals[0]));
        
        a=gestor.obtenirCanal(4);
        assertTrue(comparaCanals(a,canals[3]));
        
        a=gestor.obtenirCanal(2);
        assertTrue(comparaCanals(a,canals[1]));


    }
    
    
    
}
