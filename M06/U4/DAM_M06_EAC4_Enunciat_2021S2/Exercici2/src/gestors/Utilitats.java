/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestors;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.Canal;

/**
 * Classe amb mètodes per fer la transformació 
 * @author professor
 */
public class Utilitats {

    static JAXBContext jaxbContext;
    static Marshaller jaxbMarshaller;
    static Unmarshaller jaxbUnmarshaller;    

    static  {
        try {
            jaxbContext = JAXBContext.newInstance(Canal.class);
            jaxbMarshaller = jaxbContext.createMarshaller();    
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException ex) {
            System.err.println(ex.getMessage());
        }
    }
  


    
    
/**
 * Obte la representacio XML d'un canal en forma d'String.
 * S'assumeix que la representacio es correcta.
 * @param canal canal a representar en XML
 * @return representacio XML de l'canal indicat pel parametre
 * @throws gestors.GestorException si es produeix algun error en la conversio
 */
    
    public static String formaCanalXML(Canal canal) throws GestorException {
        
        try {
            StringWriter sw = new StringWriter();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
           
            jaxbMarshaller.marshal(canal, sw);
            
            return sw.toString();
            
        } catch (JAXBException ex) {
            throw new GestorException(ex.getMessage());
        }

    }
 /**
  * Obte un objecte Canal a partir de la seva representacio en XML continguda en un String.
  * @param dades representacio XML d'un canal
  * @return objecte dades corresponent a la representacio XML continguda al parametre
  * @throws gestors.GestorException si es produeix algun error en la conversio
  */   
    public static Canal obteCanal(String dades) throws GestorException {
        
        try {
            StringReader reader = new StringReader(dades);
            jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
            Canal c = (Canal) jaxbUnmarshaller.unmarshal(reader);
            return c;
            
        } catch (JAXBException ex) {
            throw new GestorException(ex.getMessage());
        }
    
    }

}