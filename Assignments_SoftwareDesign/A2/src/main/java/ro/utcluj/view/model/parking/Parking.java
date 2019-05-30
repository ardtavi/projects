package ro.utcluj.view.model.parking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Parking {

    private static final File                 xmlFile = new File("src/main/resources/Requests.xml");
    private static       Parking              instance;
    private              ObservableList<Request> Requests; // because of this field from javafx.collections, this is actually a viewmodel

    private Parking() {

        Requests = FXCollections.observableArrayList();
        readRequestsFromFile();
    }

    public static Parking getInstance() {
        if (instance == null) {
            instance = new Parking();
        }
        return instance;
    }

    private void readRequestsFromFile() {
        try {
            NodeList nList = readRequestsNodeList();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Request Request = convertNodeToRequest((Element) nNode);
                    Requests.add(Request);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private NodeList readRequestsNodeList() throws ParserConfigurationException, SAXException, IOException {
        Document doc = buildDocument();
        return doc.getElementsByTagName("Request");
    }

    private Document buildDocument() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    private Request convertNodeToRequest(Element nNode) {
        Element eElement = nNode;
        String request = getContent(eElement, "request");
        String name = getContent(eElement, "name");
        String status = getContent(eElement, "status");
        String date = getContent(eElement, "date");
        return new Request(request, name, date, status);
    }

    private String getContent(Element eElement, String request) {
        return eElement.getElementsByTagName(request).item(0).getTextContent();
    }

    private void persist() {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element rootElement = doc.getDocumentElement();
            NodeList nList = doc.getElementsByTagName("Request");
            int temp;
            for (temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    Request Request = Requests.get(temp);
                    eElement.getElementsByTagName("request").item(0).setTextContent(Request.getRequest());
                    eElement.getElementsByTagName("name").item(0).setTextContent(Request.getName());
                    eElement.getElementsByTagName("status").item(0).setTextContent(Request.getStatus() + "");
                    eElement.getElementsByTagName("date").item(0).setTextContent(Request.getDate());

                }
            }

            if (Requests.size() > nList.getLength()) {
                for (; temp < Requests.size(); temp++) {
                    Request Request = Requests.get(temp);

                    Element RequestElement = doc.createElement("Request");
                    rootElement.appendChild(RequestElement);

                    Element request = doc.createElement("request");
                    request.appendChild(doc.createTextNode(Request.getRequest()));
                    RequestElement.appendChild(request);

                    Element auth = doc.createElement("name");
                    auth.appendChild(doc.createTextNode(Request.getName()));
                    RequestElement.appendChild(auth);

                    Element status = doc.createElement("status");
                    status.appendChild(doc.createTextNode(Request.getStatus() + ""));
                    RequestElement.appendChild(status);

                    Element date = doc.createElement("date");
                    date.appendChild(doc.createTextNode(Request.getDate()));
                    RequestElement.appendChild(date);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Parking [Requests=" + Requests + "]";
    }

    public ObservableList<Request> getRequests() {
        return Requests;
    }

//    public boolean sell(Request Request, int amount) {
//        for (Request i : Requests) {
//            if (i.equals(Request)) {
//                int stock = i.getQuantity();
//                if (stock >= amount) {
//                    i.setQuantity(stock - amount);
//                    persist();
//                    return true;
//                }
//                break;
//            }
//        }
//
//        return false;
//    }
    
    public boolean update(Request Request, int amount) {
      Requests.remove(Request);
      Requests.add(Request);
   
      updateRequest(Request);
        return true;
    }
    public boolean delete(Request Request) {
    	Requests.remove(Request);
      deleteRequest(Request);

        return true;
    }
    
    public boolean find(String string,String string2,String string3) {
    
    	findRequest(string, string3, string3);

        return true;
    }

    public void addRequest(Request Request) {
        Requests.add(Request);
        persistRequest(Request);
    }
    
    

    private void persistRequest(Request Request) {
        try {
            Document doc = buildDocument();
            NodeList nList = doc.getElementsByTagName("Request");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Request Request2 = convertNodeToRequest((Element) nNode);
                    if (Request2.equals(Request)) {
                        return;
                    }
                }
            }

            Element rootElement = doc.getDocumentElement();

            Element RequestElement = doc.createElement("Request");
            rootElement.appendChild(RequestElement);

            Element request = doc.createElement("request");
            request.appendChild(doc.createTextNode(Request.getRequest()));
            RequestElement.appendChild(request);

            Element auth = doc.createElement("name");
            auth.appendChild(doc.createTextNode(Request.getName()));
            RequestElement.appendChild(auth);

            Element status = doc.createElement("status");
            status.appendChild(doc.createTextNode(Request.getStatus() + ""));
            RequestElement.appendChild(status);

            Element date = doc.createElement("date");
            date.appendChild(doc.createTextNode(Request.getDate()));
            RequestElement.appendChild(date);


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRequest(Request Request) {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Request");
            Element rootElement = doc.getDocumentElement();

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String request = getContent(eElement, "request");
                    String name = getContent(eElement, "name");
                    String status = getContent(eElement, "status");
//                    if (request.equalsIgnoreCase(Request.getRequest()) && name.equalsIgnoreCase(Request.getName()) && status == Request.getStatus()) {
//                        rootElement.removeChild(nNode);
//                    }
                    if (request.equalsIgnoreCase(Request.getRequest())) {
                        rootElement.removeChild(nNode);
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
     	Requests.remove(Request);
        
    }
    public void updateRequest(Request Request) {
        try {
            Document doc = buildDocument();
            NodeList nList = doc.getElementsByTagName("Request");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Request Request2 = convertNodeToRequest((Element) nNode);
                    if (Request2.equals(Request)) {
                        return;
                    }
                }
            }

            Element rootElement = doc.getDocumentElement();

            Element RequestElement = doc.createElement("Request");
            rootElement.appendChild(RequestElement);

            Element request = doc.createElement("request");
            request.appendChild(doc.createTextNode(Request.getRequest()));
            RequestElement.appendChild(request);

            Element auth = doc.createElement("name");
            auth.appendChild(doc.createTextNode(Request.getName()));
            RequestElement.appendChild(auth);

            Element status = doc.createElement("status");
            status.appendChild(doc.createTextNode(Request.getStatus() + ""));
            RequestElement.appendChild(status);

            Element date = doc.createElement("date");
            date.appendChild(doc.createTextNode(Request.getDate()));
            RequestElement.appendChild(date);


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Request findRequest(String request, String name, String status) {
        Request e = null;
        for (Request Request : Requests) {
            if (request.equalsIgnoreCase(Request.getRequest()) && name.equalsIgnoreCase(Request.getName()) && status == Request.getStatus()) {
                e = Request;
                break;
            }
        }
        return e;
    }
    public Request findRequestbyname( String name) {
        Request e = null;
        for (Request Request : Requests) {
            if ( name.equals(Request.getName())) {
                e = Request;
                break;
            }
        }
        return e;
    }
}
