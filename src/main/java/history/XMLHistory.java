package history;

import message.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class XMLHistory {
    private static final String STORAGE_LOCATION = System.getProperty("user.home") +  File.separator + "history.xml"; // history.xml will be located in the home directory
    private static final String MESSAGES = "messages";
    private static final String MESSAGE = "message";
    private static final String ID = "id";
    private static final String DESCRIPTION = "description";
    private static final String USER = "user";
    private static final String DELETE ="delete";

    private XMLHistory() {
    }

    public static synchronized void createStorage() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement(MESSAGES);
        doc.appendChild(rootElement);

        Transformer transformer = getTransformer();

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(STORAGE_LOCATION));
        transformer.transform(source, result);
    }


    public static synchronized void addData(Message message) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        System.out.println(message + " vot real message");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(STORAGE_LOCATION);
        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement(); // Root <tasks> element

        Element taskElement = document.createElement(MESSAGE);
        root.appendChild(taskElement);

        taskElement.setAttribute(ID, message.getId());
        /*Element id = document.createElement(ID);
        id.appendChild(document.createTextNode(message.getId()));
        taskElement.appendChild(id);
*/
        Element description = document.createElement(DESCRIPTION);
        description.appendChild(document.createTextNode(message.getDescription()));
        taskElement.appendChild(description);

        Element user = document.createElement(USER);
        user.appendChild(document.createTextNode(message.getUser()));
        taskElement.appendChild(user);

        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        Element time = document.createElement("date");
        time.appendChild(document.createTextNode(format1.format(new Date())));
        taskElement.appendChild(time);

        Element delete = document.createElement(DELETE);
        delete.appendChild(document.createTextNode(Boolean.toString(message.isDeleted())));
        taskElement.appendChild(delete);

        DOMSource source = new DOMSource(document);

        Transformer transformer = getTransformer();

        StreamResult result = new StreamResult(STORAGE_LOCATION);
        transformer.transform(source, result);
    }

    public static synchronized void updateData(Message message) throws ParserConfigurationException, SAXException, IOException, TransformerException, XPathExpressionException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(STORAGE_LOCATION);
        document.getDocumentElement().normalize();
        Node taskToUpdate = getNodeById(document, message.getId());
        System.out.println(message+" vot MESSAGE ");
        System.out.println(message.getId());
        System.out.println(taskToUpdate+" vot NODE ");
        if (taskToUpdate != null) {

            NodeList childNodes = taskToUpdate.getChildNodes();

            for (int i = 0; i < childNodes.getLength(); i++) {

                Node node = childNodes.item(i);

                if (DESCRIPTION.equals(node.getNodeName())) {
                    node.setTextContent(message.getDescription());
                }

                if (USER.equals(node.getNodeName())) {
                    node.setTextContent(message.getUser());
                }
                if (DELETE.equals(node.getNodeName())){
                    node.setTextContent(Boolean.toString(message.isDeleted()));
                }
            }

            Transformer transformer = getTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(STORAGE_LOCATION));
            transformer.transform(source, result);
        } else {
            throw new NullPointerException();
        }
    }

    public static synchronized boolean doesStorageExist() {
        File file = new File(STORAGE_LOCATION);
        return file.exists();
    }

    public static synchronized List<Message> getMessages() throws SAXException, IOException, ParserConfigurationException {
        List<Message> tasks = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(STORAGE_LOCATION);
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement(); // Root <tasks> element
        NodeList taskList = root.getElementsByTagName(MESSAGE);
        for (int i = 0; i < taskList.getLength(); i++) {
            Element taskElement = (Element) taskList.item(i);
            String  id = taskElement.getAttribute(ID);
            String description = taskElement.getElementsByTagName(DESCRIPTION).item(0).getTextContent();
            String  user = taskElement.getElementsByTagName(USER).item(0).getTextContent();
            boolean deleted = Boolean.valueOf(taskElement.getElementsByTagName(DELETE).item(0).getTextContent());
            tasks.add(new Message(id,description,user,deleted));
        }
        return tasks;
    }

    public static synchronized int getStorageSize() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(STORAGE_LOCATION);
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement(); // Root <tasks> element
        return root.getElementsByTagName(MESSAGE).getLength();
    }

    private static Node getNodeById(Document doc, String id) throws XPathExpressionException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        System.out.println(xpath+" vot xpath");
        XPathExpression expr = xpath.compile("//" + MESSAGE + "[@id='" + id + "']");
        System.out.println(expr+" vot expr");
        return (Node) expr.evaluate(doc, XPathConstants.NODE);
    }

    private static Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        // Formatting XML properly
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return transformer;
    }

}
