package com.axual.parser;

import com.axual.exception.APIException;
import com.axual.exception.Error;
import com.axual.model.FailedTransaction;
import com.axual.model.Record;
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
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class XMLParser implements Parser {

    public static final String REFERENCE = "reference";
    public static final String ACCOUNT_NUMBER = "accountNumber";
    public static final String DESCRIPTION = "description";
    public static final String START_BALANCE = "startBalance";
    public static final String MUTATION = "mutation";
    public static final String END_BALANCE = "endBalance";
    public static final String RECORD = "record";
    public static final String YES = "yes";
    public static final String RECORDS = "records";

    @Override
    public List<Record> parse(String filePath) throws APIException {
        validateFile(filePath);

        List<Record> records = new ArrayList<>();

        try {
            Document document = getDocument(filePath);
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String reference = node.getAttributes().getNamedItem(REFERENCE).getNodeValue();

                    String accountNumber = element.getElementsByTagName(ACCOUNT_NUMBER).item(0)
                            .getChildNodes().item(0).getNodeValue();
                    String description = element.getElementsByTagName(DESCRIPTION).item(0)
                            .getChildNodes().item(0).getNodeValue();
                    String startBalance = element.getElementsByTagName(START_BALANCE)
                            .item(0).getChildNodes().item(0).getNodeValue();
                    String mutation = element.getElementsByTagName(MUTATION)
                            .item(0).getChildNodes().item(0).getNodeValue();
                    String endBalance = element.getElementsByTagName(END_BALANCE)
                            .item(0).getChildNodes().item(0).getNodeValue();

                    records.add(buildRecord(reference, accountNumber, description, startBalance, endBalance, mutation));
                }
            }
        } catch (ParserConfigurationException | SAXException ex) {
            throw new APIException(Error.INVALID_PARSER_CONFIGURATION, ex);
        } catch (IOException ex) {
            throw new APIException(Error.FILE_READING_EXCEPTION, ex);
        }
        return records;
    }

    private Document getDocument(String filePath) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(filePath));
    }

    @Override
    public boolean write(String filePath, Set<FailedTransaction> failedTransactions) {

        try {
            Document doc = createDocument();
            Element root = doc.createElementNS(null, RECORDS);
            doc.appendChild(root);

            for (FailedTransaction failed : failedTransactions) {
                root.appendChild(createRecord(doc, failed));
            }

            transformToFile(filePath, doc);
        } catch (TransformerException ex) {
            throw new APIException(Error.INVALID_TRANSFORMER_CONFIGURATION, ex);
        } catch (ParserConfigurationException ex) {
            throw new APIException(Error.INVALID_PARSER_CONFIGURATION, ex);
        }
        return Boolean.TRUE;
    }

    private void transformToFile(String filePath, Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, YES);
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        File myFile = new File(filePath.replace(".xml", "_failed.xml"));
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(myFile);

        transformer.transform(source, console);
        transformer.transform(source, file);
    }

    private Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.newDocument();
    }

    private static Node createRecord(Document doc, FailedTransaction failedTransaction) {
        Element transaction = doc.createElement(RECORD);
        transaction.setAttribute(REFERENCE, failedTransaction.getReference().toString());
        transaction.appendChild(createUserElement(doc, failedTransaction.getDescription()));
        return transaction;
    }

    private static Node createUserElement(Document doc, String value) {
        Element node = doc.createElement(DESCRIPTION);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private Record buildRecord(String reference, String accountNumber, String description, String startBalance, String endBalance, String mutation) {
        return Record
                .builder()
                .trxReference(Long.valueOf(reference))
                .accountNumber(accountNumber)
                .startBalance(Double.valueOf(startBalance))
                .mutation(Double.valueOf(mutation))
                .description(description)
                .endBalance(Double.valueOf(endBalance))
                .build();
    }
}
