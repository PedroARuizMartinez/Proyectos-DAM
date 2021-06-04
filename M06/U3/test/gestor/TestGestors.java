package gestor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import logicaAplicacio.gestors.*;
import logicaAplicacio.model.*;
import static clonador.Clonador.clona;
import comparador.Comparador;
import static comparador.Comparador.compara;
import static comparador.Comparador.comparaLlistes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author professor
 */
public class TestGestors {
    
    private  EntityManagerFactory emf;
    private  EntityManager em;
    private  final String PU = "jpa2021s1";
    private  GestorProducte gestorProducte;
    private  GestorProducteElaborat gestorPE;
    private  GestorMateriaPrimera gestorMP;
    
    private final ArrayList totesAltes=new ArrayList();
    
     
    private final ArrayList<Producte> totsProductes = new ArrayList<>(),
    // Resultat de consultar els professors despres de fer diferents proves
                                    totsMP=new ArrayList<>(),   // tots els MP
                                    totsPE=new ArrayList<>(),   // tots els PE
                                    totsProductesExcepteEl4 = new ArrayList<>(),  // despres d'eliminar el producte amb codi 4
                                    totsProductesCanviatEl3 = new ArrayList<>(),  // despres de modificar el producte amb codi 3
                                    PE20mescar= new ArrayList<>(),   // depres d'incrementar un 20% el sou als PE
                                    MPPrio2 = new ArrayList<>(),            // MPs amb prio 2
                                    elsDeDosEuros=new ArrayList<>(),             // PE superiors a 2 euros
                                    PEAmbFarina=new ArrayList<>(),        //PEs que duen farina
                                    MPDeCroissant=new ArrayList<>();        //MPs d'un croissant
                                    
    
    
    /**
     * Abans de cada test neteja la base de dades i les omplim amb les dades de prova
     * @throws GestorException 
     */
    @Before
    public void setUp() throws GestorException {
        obre();
        creaDadesPrincipals();  //omplim a memoria les llistes anteriors
        // ho esborrem tot
        
        
        
        List<Producte> auxLlistaProd=gestorProducte.obtenirProductes();
        

        for(Producte b:auxLlistaProd){
            gestorProducte.eliminar(b.getIdProd());
        }
        
        fesAltes();//fem l'alta de les dades de prova
        
    } 
    
    /**
     * En acabar els tests, tanca l'entityManager i la factoria d'entities manager
     */
    
    @After()
    public  void classEnds(){
        tanca();
    }
    
    
    // metodes que proven coses; abans de cadascun s'executa setUp i despres classEnds
    @Test
    public void provaConsultaTotsPE(){
        assertTrue(Comparador.comparaLlistes(gestorPE.obtenirProductesElaborats(),totsPE,1));        
    }
    
    @Test
    public void provaConsultaTotsProductes(){
        assertTrue(Comparador.comparaLlistes(gestorProducte.obtenirProductes(),totsProductes,1));        
    }

    @Test
    public void provaEliminarProducte4() throws GestorException{
        gestorProducte.eliminar(4);
        assertTrue(comparaLlistes(gestorProducte.obtenirProductes(),totsProductesExcepteEl4,1));
    }
    
    /**
     *
     * @throws GestorException
     */
    @Test(expected=GestorException.class)
    public void provaEliminarProducte88() throws GestorException{
        gestorProducte.eliminar(88);
    }

    
    @Test
    public void provaModificarProducte3() throws GestorException{
        Producte a=gestorProducte.obtenirProducte(3);
        a.setNom("###");  a.setRefrigerat(false);
        gestorProducte.modificar(a);
        assertTrue(comparaLlistes(gestorProducte.obtenirProductes(),totsProductesCanviatEl3,1));
    }

    /**
     *
     * @throws GestorException
     */
    @Test(expected=GestorException.class)
    public void provaModificarProfessor88() throws GestorException{
        //Tecnics(int codi, String nom, float sou, Institut centre, String formacio)
        Producte a = (Producte) new ProducteElaborat(88,"###",true,0.0f );
        gestorProducte.modificar(a);
    }
    
    @Test
    public void provaConsultarProfessor5() throws GestorException{
        Producte a=gestorProducte.obtenirProducte(5);
        assertTrue(compara(a,totsProductes.get(4),2));
    }

    @Test
    public void provaConsultarProfessor88() throws GestorException{
        assertNull(gestorProducte.obtenirProducte(88));
    }
    
    @Test
    public void provaConsultarMPPerPrio() throws GestorException{
        assertTrue(comparaLlistes(gestorMP.obtenirMateriaPrimeraPerPrioritat(2),MPPrio2,1));
    }      

    @Test
    public void provaConsultarPEPerPreu() throws GestorException{
        assertTrue(comparaLlistes(gestorPE.obtenirProducteElaboratPerPreu(2),elsDeDosEuros,1));
    } 
    
    @Test
    public void provaConsultarMPDeProducte() throws GestorException{
        assertTrue(comparaLlistes(gestorPE.obtenirMateriesPrimeresDelProducteElaborat(5),MPDeCroissant,1));
    } 

    @Test
    public void provaConsultarPEsAPartirDeMP() throws GestorException{
        assertTrue(comparaLlistes(gestorMP.obtenirProductesElaboratsAmbMateriaPrimera(1),PEAmbFarina,1));
    }
    
    @Test
    public void provaIncrementarSalari() throws GestorException{
        gestorPE.IncrementaPreu(20);
        assertTrue(comparaLlistes(gestorProducte.obtenirProductes(),PE20mescar,1));
    }
    
    
    private void fesAltes() throws GestorException {
        for(Object o:totesAltes){
            if(o instanceof Producte){
               if(gestorProducte.obtenirProducte(((Producte) o).getIdProd())==null){
                   gestorProducte.inserir((Producte)o);
               }
            }
            em.detach(o);

        }
    }
    
    
    //Secundaria(int codi, String nom, float sou, String titulacio)
    private  MateriaPrimera creaMP(int codi, String nom, boolean ref, int prio){
        MateriaPrimera s = new MateriaPrimera(codi, nom, ref, prio);
        
        return s;
    }
    
    //Tecnics(int codi, String nom, float sou, String formacio)
    private  ProducteElaborat creaPE(int codi, String nom, boolean ref, float preu){
        ProducteElaborat s = new ProducteElaborat(codi, nom, ref, preu);
        
        return s;
        
    }

    private  void creaDadesPrincipals(){  // s'omplen les llistes amb les dades de prova i els resultats de les consultes
        


	//MPs
        
	totsProductes.add(creaMP(1, "Llet", true, 2));
        MateriaPrimera farina = creaMP(2, "Farina", false, 0);
        totsProductes.add(farina);
        MateriaPrimera ous = creaMP(3, "Ous", false, 1);
        totsProductes.add(ous);
        totsProductes.add(creaMP(4, "XocolataNegra", true, 2));
        totsProductes.add(creaMP(5, "XocolataLlet", true, 2));
        totsProductes.add(creaMP(6, "XocolataBlanca", true, 3));
        
        
        //PE
        
        ProducteElaborat crois = creaPE(7, "Croissant", false, 1.2f);
        totsProductes.add(crois);
        totsProductes.add(creaPE(8, "Torrija", false, 0.6f));
        ProducteElaborat croisxoco = creaPE(9, "CroissantXoco", false, 1.5f);
        totsProductes.add(croisxoco);
        totsProductes.add(creaPE(10, "BombaXocolata", true, 2.3f));
        totsProductes.add(creaPE(11, "PastisXoco", true, 3.3f));


        //n a n
        farina.setProductesElaborats(new ArrayList<ProducteElaborat>(Arrays.asList(crois,croisxoco)));
        croisxoco.setMateriesPrimeres(new ArrayList<MateriaPrimera>(Arrays.asList(farina,ous)));
        totesAltes.addAll(totsProductes);
        MPDeCroissant.add(farina);MPDeCroissant.add(ous);
        PEAmbFarina.add(crois);PEAmbFarina.add(croisxoco);

        
        for(Producte a:totsProductes){
            
            
            
            if(a.getIdProd()!=4){
                totsProductesExcepteEl4.add((Producte) clona(a, 2));
            }
            
            if(a.getIdProd()<=6){
                totsMP.add((Producte) clona(a, 2));
                
            }
            if(a.getIdProd()>6){
                totsPE.add((Producte) clona(a, 2));
                
            }
            Producte aux=(Producte) clona(a, 2);
            if(aux.getIdProd()==3){
                aux.setNom("###");  aux.setRefrigerat(false);
                
            }
            
            totsProductesCanviatEl3.add(aux);
            PE20mescar.add((Producte) clona(a, 2));
            
            if(a instanceof MateriaPrimera){
                MateriaPrimera p = (MateriaPrimera)a;
                if(p.getPrio()==2){
                    MPPrio2.add(p);
                }
                
            } else {
                ProducteElaborat e =(ProducteElaborat)a;
                
                if(e.getPreu()>=2.0f) {
                    elsDeDosEuros.add(e);
                    
                }
                
            }
        } // fi del for que recorre tots els professors

        
        PE20mescar.forEach((a) -> {
            if ((a instanceof ProducteElaborat)) {
                ProducteElaborat s = (ProducteElaborat)a;
                
                    s.setPreu((float) (s.getPreu()*1.2));
                }
            }
        );

        

    }
        
        
    private void obre(){  // obrim l'EntityManager i creem els gestors
        try{
        emf = Persistence.createEntityManagerFactory(PU);
        em=emf.createEntityManager();
        
        gestorPE= new GestorProducteElaborat(em);
        gestorProducte= new GestorProducte(em);
        gestorMP= new GestorMateriaPrimera(em);
        
         
        }catch(Exception e){
            System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVV");
            System.out.println(e.getMessage());
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        }
    }
    
    private void tanca(){  //tanquem l'EntityManager
        em.close();
        emf.close();
        
    }
    
}
