package util;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlDOMParser {

    private static Document document;

    public XmlDOMParser(String filePath) {
        try {
            // ��������� �������, ����� ������������ �������� ������ ����������.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // �������� �� ������� ������, ������� ������ XML, ������� ��������� Document � ���� �������������� ������.
            DocumentBuilder builder = factory.newDocumentBuilder();

            // ��������� XML, ������ ��������� Document. ������ � ��� ���� ������ �� ���� ���������, ����� ��� �����.
            document = builder.parse(new File(filePath));

        } catch (ParserConfigurationException|IOException|SAXException e){
            e.printStackTrace();
        }
    }

    /**
     * �������� �������� ������������� �������� � �������������� � ����� ����
     *
     * @param fileName      ��� �����, � ������� ���������� �������� ����� xml ��������
     * @param parentTagName ������������ ���, ������ �������� ������������� n �����, ���������� ���������
     * @param childTagName  ���, ���������� ���������
     * @param value         ����� �������� �������� ���� � ������� String
     */
    public void changeTagValue(String fileName,
                               String parentTagName,
                               String parentTagValue,
                               String childTagName,
                               String value,
                               int index) {

        document.getDocumentElement().normalize();
        if (parentTagValue != null) {
            updateElementValueWithParentValue(parentTagName, parentTagValue, childTagName, value, index);
        }
        else {
            updateElementValueWithParentTag(parentTagName, childTagName, value, index);
        }
        document.getDocumentElement().normalize();
        transformResultIntoFile(fileName);
    }

    /**
     * ��������� �������� ������������� ��������
     *
     * @param parentTagName ������������ ���, ������ �������� ������������� n �����, ���������� ���������
     * @param childTagName  ���, ���������� ���������
     * @param value         ����� �������� �������� ���� � ������� String
     * @param index ���������� ����� ����, ���������� ���������
     */
    private void updateElementValueWithParentValue(String parentTagName,
                                                   String parentTagValue,
                                                   String childTagName,
                                                   String value,
                                                   int index) {

        getAllNodeElementsByNeighbourTagValue(parentTagName, parentTagValue, childTagName)
                .item(index).getFirstChild().setNodeValue(value);
    }

    private void updateElementValueWithParentValue(String parentTagName,
                                                   String parentTagValue,
                                                   String childTagName,
                                                   String value) {

        getAllNodeElementsByNeighbourTagValue(parentTagName, parentTagValue, childTagName)
                .item(0).setNodeValue(value);
    }

    private static void updateElementValueWithParentTag(String parentTagName,
                                                        String childTagName,
                                                        String value,
                                                        int index) {

        NodeList nodes = document.getElementsByTagName(parentTagName);
        Element element = null;

        for (int i = 0; i < nodes.getLength(); i++) {
            element = (Element) nodes.item(i);
            Node node = element.getElementsByTagName(childTagName).item(index).getFirstChild();
            node.setNodeValue(value);
        }
    }

    private static void updateElementValueWithParentTag(String parentTagName,
                                                        String childTagName,
                                                        String value) {

        NodeList nodes = document.getElementsByTagName(parentTagName);
        Element element = null;

        for (int i = 0; i < nodes.getLength(); i++) {
            element = (Element) nodes.item(i);
            Node node = element.getElementsByTagName(childTagName).item(0).getFirstChild();
            node.setNodeValue(value);
        }
    }

    /**
     * ��������� ����� ������� � XML ��������
     *
     * @param parentTagName       ������������ ���, ������ �������� ����� ������������� ����� ���
     * @param newElementName      ��� ������ ����
     * @param newElementTextValue �������� ������ ���� � ������� String
     */
    private static void addElement(String parentTagName,
                                   String newElementName,
                                   String newElementTextValue) {

        NodeList nodes = document.getElementsByTagName(parentTagName);
        Element element = null;

        for (int i = 0; i < nodes.getLength(); i++) {
            element = (Element) nodes.item(i);
            Element newElement = document.createElement(newElementName);
            newElement.appendChild(document.createTextNode(newElementTextValue));
            element.appendChild(newElement);
        }
    }

    /**
     * �������� ������ ���� ��������� �� ����� ������ ��������� ��������
     * (getDocumentElement ���������� ROOT ������� XML �����)
     *
     * @param tagName ��� ����(��), �������� ������� ���������� ��������
     * @return ������ �������� ����� � ������� String
     */
    public ArrayList<String> getListElementsByTagName(String tagName) {

        NodeList elements = document.getDocumentElement().getElementsByTagName(tagName);
        return createList(elements);
    }

    public String getStringByNeighbourValue(String neighbourTagName,
                                            String neighbourTagValue,
                                            String childTagName,
                                            int index){

        return getAllNodeElementsByNeighbourTagValue(neighbourTagName, neighbourTagValue, childTagName)
                .item(index).getTextContent();
    }

    public String getStringByNeighbourValue(String neighbourTagName,
                                            String neighbourTagValue,
                                            String childTagName){

        return getAllNodeElementsByNeighbourTagValue(neighbourTagName, neighbourTagValue, childTagName)
                .item(0).getTextContent();
    }

    public List<String> getListByNeighbourValue(String neighbourTagName,
                                                String neighbourTagValue,
                                                String childTagName){

        NodeList list = getAllNodeElementsByNeighbourTagValue(neighbourTagName, neighbourTagValue, childTagName);
        return createList(list);
    }

    public List<String> getListByNeighbourAttrValue(String neighbourTagName,
                                                    int attrIndex,
                                                    String attrName,
                                                    String attrValue,
                                                    String childTagName){

        NodeList list = getAllNodeElementsByNeighbourAttrValue(neighbourTagName, attrIndex, attrName, attrValue, childTagName);
        return createList(list);
    }

    public NamedNodeMap getNodeAttributes(String tagName, int nodeIndex) {
        NodeList nodes = document.getElementsByTagName(tagName);
        Element element = (Element) nodes.item(nodeIndex);
        NamedNodeMap attributes = element.getAttributes();


        return attributes;
    }

    /**
     * �������� NodeList ��������� �� ����� ��������� ��� ������������� ��������
     *
     * @param neighbourTagName ��� ����(��), �� �������� �������� ������������ �����
     * @param neighbourTagValue �������� ����, �� �������� ������������ �����
     * @param childTagName ���, ���� �������� ���������� �����
     * @return ������ �����
     */
    private NodeList getAllNodeElementsByNeighbourTagValue(String neighbourTagName,
                                                           String neighbourTagValue,
                                                           String childTagName) {

        NodeList nodes = document.getElementsByTagName(neighbourTagName);
        NodeList nodeList = null;
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getTextContent().equals(neighbourTagValue)) {
                nodeList = createNodeList(nodes.item(i), childTagName);
            }
        }
        return nodeList;
    }

    /**
     * �������� NodeList ��������� �� ��������� ��������� ��� ������������� ��������
     *
     * @param neighbourTagName ��� ����(��), �� �������� �������� ������������ �����
     * @param attrName ������������ �������� ����, �� �������� ������������ �����
     * @param attrValue �������� �������� ����, �� �������� ������������ �����
     * @param childTagName ���, ���� �������� ���������� �����
     * @return ������ �����
     */
    private NodeList getAllNodeElementsByNeighbourAttrValue(String neighbourTagName,
                                                            int attrIndex,
                                                            String attrName,
                                                            String attrValue,
                                                            String childTagName) {

        NodeList nodes = document.getElementsByTagName(neighbourTagName);
        NodeList nodeList = null;
        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            Node node = element.getAttributes().item(attrIndex);
            if (node.getNodeName().equals(attrName) && node.getNodeValue().equals(attrValue)) {
                nodeList = createNodeList(nodes.item(i), childTagName);
            }
        }
        return nodeList;
    }

    private void transformResultIntoFile(String fileName) {

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new File(fileName)));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> createList(NodeList nodeList){

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++){
            arrayList.add(nodeList.item(i).getTextContent());
        }
        return arrayList;
    }

    private NodeList createNodeList(Node node, String childTagName){

        Element element = (Element) node;
        NodeList nodeList = null;
        nodeList = element.getElementsByTagName(childTagName);

        if (nodeList.getLength() < 1) {
            element = (Element) element.getParentNode();
            nodeList = element.getElementsByTagName(childTagName);
        }
        return nodeList;
    }
}
