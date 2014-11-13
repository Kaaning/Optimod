/**
 * 
 */
package bibliothequesTiers;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Classe de validation d'un fichier XML à partir d'un fichier XSD
 * @author MohamedRiadh
 *
 */
public class XMLValidateur {
	
	/**
	 * Méthode static qui permet de vérifier si un fichier XML respecte une feuille XSD
	 * @param fichierXML String nom du fichier XML à faire valider
	 * @param fichierXSD String fichier XSD sur lequel on se base pour valider ou non le fichier XML
	 * @return boolean true si le fichier XML est valide selon le fichier XSD false sinon  
	 * @throws ParserConfigurationException 
	 *
	 */
	public static boolean validerXML(String fichierXML, String fichierXSD) throws SAXException, IOException, ParserConfigurationException { 
		// parse an XML document into a DOM tree
		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = parser.parse(new File(fichierXML));

		// create a SchemaFactory capable of understanding WXS schemas
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		// load a WXS schema, represented by a Schema instance
		Source schemaFile = new StreamSource(new File(fichierXSD));
		Schema schema = factory.newSchema(schemaFile);

		// create a Validator instance, which can be used to validate an instance document
		Validator validator = schema.newValidator();
		// validate the DOM tree
		try {
		    validator.validate(new DOMSource(document));
			return true;		
		} catch (SAXException e) {
			return false;
		    // instance document is invalid!
		}
    }
}
