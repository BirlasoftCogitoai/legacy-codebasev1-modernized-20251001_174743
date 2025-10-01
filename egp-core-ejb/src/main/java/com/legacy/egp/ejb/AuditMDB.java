```java
package com.legacy.egp.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Message-Driven Bean for processing audit messages from JMS queue.
 */
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/AuditQueue")
        }
)
public class AuditMDB implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(AuditMDB.class);

    @PersistenceContext(unitName = "egp-pu")
    private EntityManager entityManager;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                logger.info("Received audit message: {}", text);
                // Process the message, e.g., store it in the database
                // entityManager.persist(new AuditRecord(text));
            } else {
                logger.warn("Received unsupported message type: {}", message.getClass().getName());
            }
        } catch (JMSException e) {
            logger.error("Error processing JMS message", e);
        } catch (Exception e) {
            logger.error("General error processing message", e);
        }
    }
}
```