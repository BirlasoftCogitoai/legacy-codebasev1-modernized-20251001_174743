```java
package com.legacy.egp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.soap.*;
import java.net.URL;

/**
 * Modernized SOAP Web Service Client
 * Demonstrates typical SOAP client code
 * 
 * @version 2.0
 * @since 2023
 */
public class LegacySoapClient {

    private static final Logger logger = LoggerFactory.getLogger(LegacySoapClient.class);
    private static final String SOAP_ENDPOINT = "http://legacy-services.internal.corp:8080/LegacyService";
    private static final String NAMESPACE = "http://service.legacy/";

    public static void main(String[] args) {
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), new URL(SOAP_ENDPOINT));
            logger.info("Response SOAP Message:");
            soapResponse.writeTo(System.out);

            soapConnection.close();
        } catch (Exception e) {
            logger.error("Error occurred while sending SOAP Request", e);
        }
    }

    private static SOAPMessage createSOAPRequest() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("ser", NAMESPACE);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("getLegacyData", "ser");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("param1", "ser");
        soapBodyElem1.addTextNode("value");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", NAMESPACE + "getLegacyData");

        soapMessage.saveChanges();

        return soapMessage;
    }
}
```