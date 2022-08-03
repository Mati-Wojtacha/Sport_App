package hibernate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Serializer;
public class XMLWriter{
    Element ip = new Element("ip");
    Element port = new Element("port");
    Element db = new Element("db");
    Element user = new Element("user");

    XMLWriter(String ip, int port, String db, String user) throws UnsupportedEncodingException,
            IOException{

        Element root = new Element("settings");
        Document doc = new Document(root);

        this.ip.appendChild(ip);
        this.user.appendChild(user);
        this.db.appendChild(db);
        this.port.appendChild(String.valueOf(port));

        root.appendChild(this.ip);
        root.appendChild(this.port);
        root.appendChild(this.db);
        root.appendChild(this.user);

        File file = new File("settings.xml");
        if (!file.exists()){
            file.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        Serializer serializer = new Serializer(fileOutputStream, "UTF-8");
        serializer.setIndent(4);
        serializer.write(doc);
    }
}