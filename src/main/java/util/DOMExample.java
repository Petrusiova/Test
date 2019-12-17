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

public class DOMExample {

    private static Document document;

    public DOMExample(String filePath) throws ParserConfigurationException, SAXException, IOException {
        // ��������� �������, ����� ����� �������� ������ ����������.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // �������� �� ������� ������, ������� ������ XML, ������� ��������� Document � ���� �������������� ������.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // ��������� XML, ������ ��������� Document. ������ � ��� ���� ������ �� ���� ���������, ����� ��� �����.
        document = builder.parse(new File(filePath));
    }

    /**
     * �������� �������� ������������� �������� � �������������� � ����� ����
     *
     * @param fileName ��� �����, � ������� ���������� �������� ����� xml ��������
     * @param parentTagName ������������ ���, ������ �������� ������������� n �����, ���������� ���������
     * @param childTagName ���, ���������� ���������
     * @param value ����� �������� �������� ���� � ������� String
     */
    public void changeTagValue(String fileName, String parentTagName, String childTagName, String value) {
        document.getDocumentElement().normalize();
        updateElementValue(parentTagName, childTagName, value);
        document.getDocumentElement().normalize();
        transformResultIntoFile(fileName);
    }

    /**
     * ��������� �������� ������������� ��������
     *
     * @param parentTagName ������������ ���, ������ �������� ������������� n �����, ���������� ���������
     * @param childTagName ���, ���������� ���������
     * @param value ����� �������� �������� ���� � ������� String
     */
    private static void updateElementValue(String parentTagName, String childTagName, String value) {

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
     * @param parentTagName ������������ ���, ������ �������� ����� ������������� ����� ���
     * @param newElementName ��� ������ ����
     * @param newElementTextValue �������� ������ ���� � ������� String
     */
    private static void addElement(String parentTagName, String newElementName, String newElementTextValue) {
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
     *
     * @return ������ �������� ����� � ������� String
     */
    public ArrayList<String> getStringElementsByTagName(String tagName) {
        NodeList elements = document.getDocumentElement().getElementsByTagName(tagName);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < elements.getLength(); i++) {
            list.add(elements.item(i).getTextContent());
        }
        return list;
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
}
