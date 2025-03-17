package org.example.model;

import java.io.Serializable;
import java.util.UUID;

sealed public abstract class Task implements Serializable permits SimpleTask, ComplexTask  {
    private final int idTask;
    private final String description;
    private String statusTask;

    public Task(String description) {
        this.idTask = UUID.randomUUID().hashCode();
        this.description = description;
        this.statusTask = "Uncompleted";
    }

    public abstract int estimateDuration();

    public int getId() {
        return idTask;
    }

    public String getStatus() {
        return statusTask;
    }

    public void setStatus(String statusTask) {
        this.statusTask = statusTask;
    }

    public String getDescription() {
        return description;
    }
}
