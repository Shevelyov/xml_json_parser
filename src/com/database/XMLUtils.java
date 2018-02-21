package com.database;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLUtils {

    public static boolean XMLValidater()
    {
        boolean success = false;
        try{

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setValidating(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            dBuilder.setErrorHandler(new org.xml.sax.ErrorHandler() {
                //Ignore the fatal errors
                public void fatalError(SAXParseException exception)throws SAXException
                { }
                //Validation errors
                public void error(SAXParseException e)throws SAXParseException {
                    System.out.println("Error at " +e.getLineNumber() + " line.");
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
                //Show warnings
                public void warning(SAXParseException err)throws SAXParseException{
                    System.out.println(err.getMessage());
                    System.exit(0);
                }
            });

            File fXmlFile = new File("hospital.xml");
            Document doc = dBuilder.parse(fXmlFile);

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(System.out);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "hospital.dtd");
            transformer.transform(source, result);
            success = true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return success;
    }// end validater

    public static void XMLParser() {
        try {

            File fXmlFile = new File("hospital.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);


            System.out.println("\n\nRoot element :" + doc.getDocumentElement().getNodeName());

            NodeList hospitalList = doc.getElementsByTagName("hospital");
            System.out.println("----------------------------");
            for (int temp = 0; temp < hospitalList.getLength(); temp++) {
                Node nNode = hospitalList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Hospital id : " + eElement.getAttribute("id"));
                    System.out.println("Hospital Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Hospital Location : " + eElement.getElementsByTagName("location").item(0).getTextContent());
                }
            }

            NodeList docList = doc.getElementsByTagName("doctor");
            System.out.println("----------------------------");
            for (int temp = 0; temp < docList.getLength(); temp++) {
                Node nNode = docList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Doctor id : " + eElement.getAttribute("id"));
                    System.out.println("Doctor Name : " + eElement.getElementsByTagName("docName").item(0).getTextContent());
                    System.out.println("Doctor type : " + eElement.getElementsByTagName("type").item(0).getTextContent());
                }
            }

            NodeList patientList = doc.getElementsByTagName("patient");
            System.out.println("----------------------------");
            for (int temp = 0; temp < docList.getLength(); temp++) {
                Node nNode = patientList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Patient id : " + eElement.getAttribute("id"));
                    System.out.println("Patient Name : " + eElement.getElementsByTagName("patientName").item(0).getTextContent());
                    System.out.println("Date of birth : " + eElement.getElementsByTagName("dob").item(0).getTextContent());
                    System.out.println("Illness : " + eElement.getElementsByTagName("illness").item(0).getTextContent());
                    System.out.println("Hospital attending : " + eElement.getElementsByTagName("attendingHospital").item(0).getTextContent());
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }// end parser
}
