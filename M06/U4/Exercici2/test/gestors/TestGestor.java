package gestors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import model.Canal;
import net.xqj.basex.BaseXXQDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 *
 * @author professor
 */
public class TestGestor {
    
    private final String SERVER_NAME="localhost";
    private final String USER="admin";
    private final String PASSWORD="admin";
    private final String PORT="1984";
   
    private GestorCanal gestor;
    
    
    private XQConnection con=null;
    
    
    private final Canal[] canals = {
            new Canal(1, "elXokas", "World of Warcraft", 420, new String[]{"choux","plumero","ibaillanos"}, "Joaquín Domínguez"),
            new Canal(2, "MiquelMontoro", "Vida Rural", 67, new String[]{"ibaillanos","ambush","kinrolls"}, "Miquel Montoro"),
            new Canal(3, "elMillor", "LoL", 4303, new String[]{"alejita"}, "Elm Cherto"),
            new Canal(4, "elRubius", "Rust", 897, new String[]{"choux","alejita"}, "Rubén Doblas"),
            new Canal(5, "Ibai", "varios", 10000, new String[]{"elXokas","montoromiquel","choux","plumero","ambush","kinrolls"}, "Ibai Llanos"),
    };
    
    
    @Before
    public void setUp() throws GestorException, XQException {
        obrir();
        esborrarDades();
        ferAltes();
        
    }
    
    @After
    public void tearDown() throws XQException  {
        tancar();
    }
    
    
    private void tancarIObrir() throws XQException, GestorException{
        tancar();
        obrir();
        
    }
    
    
    private void obrir() throws GestorException, XQException{
     XQDataSource xqs = new BaseXXQDataSource();
        xqs.setProperty("serverName", SERVER_NAME);
        xqs.setProperty("port", PORT);
        con = xqs.getConnection(USER, PASSWORD);
        gestor = new GestorCanal(con);
     
    }
    
    private void tancar() throws XQException{
        con.close();
    }
    
    private void esborrarDades() throws XQException{
        esborrarTots();
    }


    private void esborrarTots() throws XQException {

            String ordre = "delete node "+GestorCanal.ARREL+"canals/canal";
            
            XQExpression expr=con.createExpression();
                    
            expr.executeQuery(ordre);

            expr.close();
    }

    
    private void ferAltes() throws GestorException, XQException {
        for(Canal c : canals){
            gestor.inserir(c);
        }
    }
    
    
    
    public boolean comparaCanals(Canal p1, Canal p2){
        return  p1.getId()==p2.getId() &&
                p1.getNom().equals(p2.getNom()) &&
                p1.getTema().equals(p2.getTema()) &&
                p1.getNumVideos() == p2.getNumVideos() &&
                p1.getNomPropietari().equals(p2.getNomPropietari()) &&
                comparaArraysCadena(p1.getSubscriptors(),p2.getSubscriptors());
    }
    
    public boolean comparaArraysCadena(List<String> l1, List<String> l2){
        
        if(l1.size()!=l2.size()){
            return false;
        }
        
        for(String s:l1){
            if(!l2.contains(s)){
                System.out.println("error, "+l2+" no conte "+s);
                return false;
            }
        }
        return true;
    }
    
    // les altes es fan cada vegada. Si hi ha les altes correctes es comprova 
    // als metodes provaConsultaPerCodiTots i provaConsultaPerCodiCap
    @Test
    public void provaAltes(){
        
    }
    
    @Test 
    public void provaConsultaPerCodiTots() throws XQException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers
 
        Canal a=gestor.obtenirCanal(3);
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
    public void provaConsultaPerIdCap() throws XQException, GestorException {
        tancarIObrir();  //ens assegurem que s'han gravat els buffers
        assertNull(gestor.obtenirCanal(40));
        assertNull(gestor.obtenirCanal(-3));
        assertNull(gestor.obtenirCanal(70));

    }
    
    @Test
    public void provaObtenirCanalsPerSubscriptor() throws XQException, GestorException {
        tancarIObrir();
        
        List<Canal> l=gestor.obtenirCanalPerSubscriptor("choux");
        
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
    public void provaEliminar() throws XQException, GestorException {
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
