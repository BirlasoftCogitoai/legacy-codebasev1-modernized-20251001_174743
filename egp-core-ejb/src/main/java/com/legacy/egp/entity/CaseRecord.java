package com.legacy.egp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Case Record Entity
 * Represents case records in the EGP system
 */
@Entity
@Table(name = "egp_case_records")
@NamedQueries({
    @NamedQuery(
        name = "CaseRecord.findByCaseNumber",
        query = "SELECT c FROM CaseRecord c WHERE c.caseNumber = :caseNumber"
    )
})
public class CaseRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_id")
    private Long caseId;

    @Column(name = "case_number", nullable = false, unique = true)
    private String caseNumber;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(mappedBy = "caseRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Note> notes;

    // Getters and setters...

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}