package se.wasteland.struggle.data;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MapParser {
    /**
     * dom     Document to hold the XML node structure.
     * mapData A reference to a MapData object which stores the data retrived from the XMl file.
     */
    private Document dom;
    private MapData mapData;

    /**
     * The class constructor which requires a MapData object to store the data
     * retrived from the XML file.
     * @param mapData
     */
    public MapParser(MapData mapData) {
        this.mapData = mapData;
    }

    /**
     * This method reads a XML file and writes it to a DOM
     * object.
     * @param filePath Path to the XML file.
     */
    public void readXMLFile(String filePath) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(filePath);
        } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
        } catch (SAXException se) {
                se.printStackTrace();
        } catch (IOException ioe) {
                ioe.printStackTrace();
        }
    }

    /**
     * This method parses the Document object and calls the
     * specific read methods.
     */
    public void parseDocument() {
        // Get root node
        Element root = (Element) dom.getFirstChild();
        NodeList nodeList = root.getChildNodes();
        mapData.setMap(root.getAttribute("width"), 
                       root.getAttribute("height"),
                       root.getAttribute("dimension"));

        // Iterate through all root children
        if (nodeList != null && nodeList.getLength() > 0) {

            for (int i = 0 ; i < nodeList.getLength();i++) {
                Node node = nodeList.item(i);
                readMetadata(node);
                readLevel(node);
            }
        }
    }

    /**
     * This method checks for occurences of content nodes and 
     * returns true or false.
     */
    private boolean readContent(Node node) {
        return node.getNodeName().equals("content");
    }

    /**
     * This method checks for occurences of level nodes and writes
     * it's data to the MapData object. It then traverses down the node tree
     * and calls the readRow method.
     * @param node
     */
    private void readLevel(Node node) {
        if (node.getNodeName().equals("level")) {
            Element elm = (Element)node;
            NodeList nodeList = node.getChildNodes();
            mapData.setLevelNo(Integer.parseInt(elm.getAttribute("number")));

            // Iterate through all rows
            for (int j = 0; j < nodeList.getLength(); j++) {
                Node rowNode = nodeList.item(j);
                readRow(rowNode);
            }
        }
    }

    /**
     * This method checks for occurences of metadata nodes and writes
     * it's data to the MapData object.
     * @param node
     */
    private void readMetadata(Node node) {
        if (node.getNodeName().equals("metadata")) {
            Element elm = (Element)node;
            mapData.setMetadata(elm.getAttribute("game"),
                                elm.getAttribute("version"),
                                elm.getAttribute("date"),
                                elm.getAttribute("author"));
        }
    }
    
    /**
     * This method checks for occurences of row nodes and traverses down
     * the node tree and calls the readTile method.
     * @param node
     */
    private void readRow(Node node) {
        if (node.getNodeName().equals("row")) {
            NodeList nodeList = node.getChildNodes();

            // Iterate through all tiles
            for (int n = 0; n < nodeList.getLength(); n++) {
                Node tileNode = nodeList.item(n);
                readTile(tileNode);
            }
        }
    }
    
    /**
     * This method checks for occurences of tile nodes and writes
     * it's data to the MapData object. If there exsists content nodes
     * the readContent method is called.
     * @param node
     */
    private void readTile(Node node) {
        if (node.getNodeName().equals("tile")) {
            boolean hasContent = false;
            NodeList nodeList = node.getChildNodes();
            Element tileElm = (Element) node;
            Element contentElm = null;
            
            for (int n = 0; n < nodeList.getLength(); n++) {
                Node contentNode = nodeList.item(n);
                hasContent = readContent(contentNode);
                if (hasContent) {
                    contentElm = (Element) contentNode;
                    break;
                }
            }

            if (hasContent) {
                mapData.setTileData(tileElm.getAttribute("type"),
                                    tileElm.getAttribute("texture"),
                                    contentElm.getAttribute("type"),
                                    contentElm.getAttribute("id"),
                                    contentElm.getAttribute("depth"),
                                    contentElm.getAttribute("margin"));
            } else {
                mapData.setTileData(tileElm.getAttribute("type"),
                                    tileElm.getAttribute("texture"));
            }
        }
    }
}