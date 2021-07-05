package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ToDo
 */
@Entity
public class ToDo {
    
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date open;
    @Temporal(TemporalType.DATE)
    private Date due;
    private String reflexion;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
        
    


    private boolean archived = false;
    private boolean done = false;


    @JsonIgnore
    private String owner;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Date return the open
     */
    public Date getOpen() {
        return open;
    }

    /**
     * @param open the open to set
     */
    public void setOpen(Date open) {
        this.open = open;
    }

    /**
     * @return Date return the due
     */
    public Date getDue() {
        return due;
    }

    /**
     * @param due the due to set
     */
    public void setDue(Date due) {
        this.due = due;
    }

    /**
     * @return String return the reflexion
     */
    public String getReflexion() {
        return reflexion;
    }

    /**
     * @param reflexion the reflexion to set
     */
    public void setReflexion(String reflexion) {
        this.reflexion = reflexion;
    }

    /**
     * @return String return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }


    /**
     * @return StatusEnum return the status
     */
    public StatusEnum getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    /**
     * @return CategoryEnum return the category
     */
    public CategoryEnum getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(CategoryEnum category) {
        this.category = category;
    }


    /**
     * @return boolean return the archived
     */
    public boolean isArchived() {
        return archived;
    }

    /**
     * @param archived the archived to set
     */
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    /**
     * @return boolean return the done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @param done the done to set
     */
    public void setDone(boolean done) {
        this.done = done;
    }

}