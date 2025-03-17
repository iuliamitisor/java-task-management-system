package org.example.model;

public non-sealed class ComplexTask extends Task {
    private Task[] tasks;
    public ComplexTask(String description, Task[] tasks) {
        super(description);
        this.tasks = tasks;
    }

    public void addTaskToCpxTask(Task subtask) {

    }


    @Override
    public int estimateDuration() {
        int sum = 0;
        for (Task task : tasks) {
            sum += task.estimateDuration();
        }
        return sum;
    }

}
