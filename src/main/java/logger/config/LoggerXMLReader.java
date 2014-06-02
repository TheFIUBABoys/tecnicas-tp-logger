package logger.config;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;

/**
 * Created by Lucas
 * Class that extends LoggerConfigReader and reads config form Properties File
 */
public class LoggerXMLReader extends LoggerConfigReader {

    /**
     * Constructor. Calls its parent's constructor.
     *
     * @param aDelegate the property applying delegate.
     * @param filename  the filename of the configuration file.
     */
    public LoggerXMLReader(PropertyApplyingDelegate aDelegate, String filename) {
        super(aDelegate, filename);
    }

    /**
     * {@inheritDoc}
     */
    public void loadConfig() throws Exception {

        FileInputStream inputStream = new FileInputStream(configFile);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);


        for (String tagName : LoggerConfigReader.PROPERTY_CONFIG_KEYS) {
            NodeList list = document.getElementsByTagName(tagName);
            if (list.getLength() > 0) {
                Node node = list.item(0);
                if (node.hasChildNodes()) {
                    String value = node.getChildNodes().item(0).getNodeValue();
                    if (!value.equals("null")) {
                        methodMap.get(tagName).applyPropertyWithValue(tagName, value);
                    }
                }
            }

        }

    }
}
