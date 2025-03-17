package org.example.model;

public non-sealed class SimpleTask extends Task {
    private int startHour;
    private int endHour;

    public SimpleTask(String description, int startHour, int endHour) {
        super(description);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public int estimateDuration() {
        return endHour - startHour;
    }
}
