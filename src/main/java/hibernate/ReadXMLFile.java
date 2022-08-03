package hibernate;
import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class ReadXMLFile {

    String read[]=new String[5];
    ReadXMLFile(File file) {
        try {
           // File file = new File(file_xml);
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            if (document.hasChildNodes()) {
                printNodeList(document.getChildNodes());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void printNodeList(NodeList nodeList) {
        String read = null;
        for (int count = 0; count < nodeList.getLength(); count++) {

            Node elemNode = nodeList.item(count);
            if (elemNode.getNodeType() == Node.ELEMENT_NODE) {
                read=elemNode.getTextContent();
                System.out.println(read);
            }
        }
        Scanner scanner = new Scanner(read);
            scanner.nextLine();
           for(int i=0; i<4; i++) {
               this.read[i] = scanner.nextLine();
               this.read[i]=this.read[i].replaceAll("\\s+","");
               System.out.println(this.read[i]);
           }
    }
}