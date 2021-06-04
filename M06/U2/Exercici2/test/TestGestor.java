

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import eac2.ex2.gestors.GestorException;
import eac2.ex2.solucio.GestorGrafica;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import eac2.ex2.solucio.TargetaGrafica;
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
    
    private final String PU = "ObjectDB";

   
    private GestorGrafica gestor;
    
    private EntityManagerFactory gfxFactory;
    private EntityManager gfx;
    
    
    private final TargetaGrafica[] grafiques = {
        new TargetaGrafica(1,"Nvidia 3070", 1.67F, "Ampere", "5888 CUDA core", 8, true),
	new TargetaGrafica(2,"Nvidia 3060", 1.78F, "Ampere", "3584 CUDA core", 12, true),
	new TargetaGrafica(3,"Nvidia 1070", 1.506F, "Pascal", "1920 core", 8, false),
	new TargetaGrafica(4,"Geforce 720", 0.797F, "Geforce", "192 CUDA core", 2, false),
	new TargetaGrafica(5,"Radeon 6600X", 1.489F, "RadeonX", "1792 shading unit", 6, true)
    };
    
    private final TargetaGrafica as4 = new TargetaGrafica(5,"RADEON HD 6870", 0.9F, "RadeonHD", "152 shading unit", 1, false), as5 =  new TargetaGrafica(5,"RADEON HD 6870", 0.9F, "RadeonHD", "152 shading unit", 1, false);
    
    @Before
    public void setUp() throws SQLException, GestorException {
        obrir();
        buidarBaseDades();
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
        
        gfxFactory = Persistence.createEntityManagerFactory(PU);
        gfx = gfxFactory.createEntityManager();        
        
        gestor = new GestorGrafica(gfx);
     
    }
    
    private void tancar() throws SQLException{
        gfx.close();
        gfxFactory.close();
    }
    
    private void buidarBaseDades() throws SQLException{
        
        
        Query query = gfx.createQuery("DELETE FROM TargetaGrafica");
        gfx.getTransaction().begin();
        query.executeUpdate();
        gfx.getTransaction().commit();
    }
    
    private void ferAltes() throws GestorException{
        for(TargetaGrafica a:grafiques){
            gestor.inserir(a);
        }
    }
    
    
    public boolean comparaGFXs(TargetaGrafica p1, TargetaGrafica p2){
        return  p1.getNombreSerie()==p2.getNombreSerie() &&
                p1.getModel().equals(p2.getModel()) &&
                p1.getArquitectura().equals(p2.getArquitectura()) &&
                p1.getNucli().equals(p2.getNucli()) &&
                p1.getFrequenciaProcessador()==p2.getFrequenciaProcessador() &&
                p1.getMemoriaRam()==p2.getMemoriaRam() &&
                p1.isRaytrace()==p2.isRaytrace();
    }
    
    // les altes es fan cada vegada. Si hi ha les altes correctes es comprova 
    // als mètodes provaConsultaPerCodiTotes i provaConsultaPerCodiCap
    @Test
    public void provaAltes(){
        
    }  
    
    @Test 
    public void provaConsultaPerRefTotes() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers
        
        TargetaGrafica a=gestor.obtenirTargetaGrafica(3);
        assertTrue(comparaGFXs(a,grafiques[2]));
        
        a=gestor.obtenirTargetaGrafica(1);
        assertTrue(comparaGFXs(a,grafiques[0]));
        
        a=gestor.obtenirTargetaGrafica(4);
        assertTrue(comparaGFXs(a,grafiques[3]));
        
        a=gestor.obtenirTargetaGrafica(2);
        assertTrue(comparaGFXs(a,grafiques[1]));
    }
    
    //provem a fer consultes per codis innexistents
    
    @Test 
    public void provaConsultaPerRefCap() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers

        assertNull(gestor.obtenirTargetaGrafica(35));
        assertNull(gestor.obtenirTargetaGrafica(-1));
        assertNull(gestor.obtenirTargetaGrafica(8));

    }

    @Test 
    public void provaModificacio() throws SQLException, GestorException{
        tancarIObrir();  //ens assegurem que s'han gravat els buffers
        
        TargetaGrafica a=gestor.obtenirTargetaGrafica(3);
        assertTrue(comparaGFXs(a,grafiques[2]));
        
        a=gestor.obtenirTargetaGrafica(1);
        assertTrue(comparaGFXs(a,grafiques[0]));
        
        gestor.modificar(as4);
        a=gestor.obtenirTargetaGrafica(5);
                
        assertTrue(comparaGFXs(a,as5));
        
        a=gestor.obtenirTargetaGrafica(2);
        assertTrue(comparaGFXs(a,grafiques[1]));
    }
    
    
    @Test
    public void provaEliminar() throws SQLException, GestorException {
        gestor.eliminar(3);
        tancarIObrir();
        
        assertNull(gestor.obtenirTargetaGrafica(3));
        
        TargetaGrafica a=gestor.obtenirTargetaGrafica(1);
        assertTrue(comparaGFXs(a,grafiques[0]));
        
        a=gestor.obtenirTargetaGrafica(4);
        assertTrue(comparaGFXs(a,grafiques[3]));
        
        a=gestor.obtenirTargetaGrafica(2);
        assertTrue(comparaGFXs(a,grafiques[1]));


    }
    
}
