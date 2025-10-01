```java
package com.legacy.egp.dao;

import com.legacy.egp.entity.CaseRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Data Access Object for Case Records.
 */
@Stateless
public class CaseDAO {

    private static final Logger logger = LoggerFactory.getLogger(CaseDAO.class);

    @PersistenceContext(unitName = "egp-pu")
    private EntityManager entityManager;

    /**
     * Finds a CaseRecord by its ID.
     *
     * @param id the ID of the CaseRecord
     * @return the CaseRecord, or null if not found
     */
    public CaseRecord findById(Long id) {
        try {
            return entityManager.find(CaseRecord.class, id);
        } catch (NoResultException e) {
            logger.warn("CaseRecord with ID {} not found.", id);
            return null;
        } catch (Exception e) {
            logger.error("Error finding CaseRecord with ID {}", id, e);
            throw new RuntimeException("Error finding CaseRecord", e);
        }
    }

    /**
     * Retrieves all CaseRecords.
     *
     * @return a list of CaseRecords
     */
    public List<CaseRecord> findAll() {
        try {
            return entityManager.createQuery("SELECT c FROM CaseRecord c", CaseRecord.class).getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving all CaseRecords", e);
            throw new RuntimeException("Error retrieving all CaseRecords", e);
        }
    }

    /**
     * Saves or updates a CaseRecord.
     *
     * @param caseRecord the CaseRecord to save or update
     */
    public void saveOrUpdate(CaseRecord caseRecord) {
        try {
            if (caseRecord.getId() == null) {
                entityManager.persist(caseRecord);
            } else {
                entityManager.merge(caseRecord);
            }
        } catch (Exception e) {
            logger.error("Error saving or updating CaseRecord", e);
            throw new RuntimeException("Error saving or updating CaseRecord", e);
        }
    }

    /**
     * Deletes a CaseRecord.
     *
     * @param caseRecord the CaseRecord to delete
     */
    public void delete(CaseRecord caseRecord) {
        try {
            entityManager.remove(entityManager.contains(caseRecord) ? caseRecord : entityManager.merge(caseRecord));
        } catch (Exception e) {
            logger.error("Error deleting CaseRecord", e);
            throw new RuntimeException("Error deleting CaseRecord", e);
        }
    }
}
```