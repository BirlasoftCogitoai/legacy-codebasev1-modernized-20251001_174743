```java
package com.legacy.egp.dao;

import com.legacy.egp.entity.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Data Access Object for Notes.
 */
@Stateless
public class NotesDAO {

    private static final Logger logger = LoggerFactory.getLogger(NotesDAO.class);

    @PersistenceContext(unitName = "egp-pu")
    private EntityManager entityManager;

    /**
     * Finds a Note by its ID.
     *
     * @param id the ID of the Note
     * @return the Note, or null if not found
     */
    public Note findById(Long id) {
        try {
            return entityManager.find(Note.class, id);
        } catch (NoResultException e) {
            logger.warn("Note with ID {} not found.", id);
            return null;
        } catch (Exception e) {
            logger.error("Error finding Note with ID {}", id, e);
            throw new RuntimeException("Error finding Note", e);
        }
    }

    /**
     * Retrieves all Notes.
     *
     * @return a list of Notes
     */
    public List<Note> findAll() {
        try {
            return entityManager.createQuery("SELECT n FROM Note n", Note.class).getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving all Notes", e);
            throw new RuntimeException("Error retrieving all Notes", e);
        }
    }

    /**
     * Saves or updates a Note.
     *
     * @param note the Note to save or update
     */
    public void saveOrUpdate(Note note) {
        try {
            if (note.getId() == null) {
                entityManager.persist(note);
            } else {
                entityManager.merge(note);
            }
        } catch (Exception e) {
            logger.error("Error saving or updating Note", e);
            throw new RuntimeException("Error saving or updating Note", e);
        }
    }

    /**
     * Deletes a Note.
     *
     * @param note the Note to delete
     */
    public void delete(Note note) {
        try {
            entityManager.remove(entityManager.contains(note) ? note : entityManager.merge(note));
        } catch (Exception e) {
            logger.error("Error deleting Note", e);
            throw new RuntimeException("Error deleting Note", e);
        }
    }
}
```