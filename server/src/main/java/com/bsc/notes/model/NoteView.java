package com.bsc.notes.model;

import java.time.LocalDateTime;

/**
 * This class represents a model which is publicly exposed through the API
 * as (we don't want to expose Entity {@link javax.persistence.Entity} classes
*/
public class NoteView {
    private long id;
    private String title;
    private boolean finished;
    private LocalDateTime created;
    private LocalDateTime lastUpdated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
