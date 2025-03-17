package org.example.model;

public non-sealed class ComplexTask extends Task {
    private Task[] tasks;
    public ComplexTask(String description, Task[] tasks) {
        super(description);
        this.tasks = tasks;
    }

    public void addTask(Task subtask) {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                tasks[i] = subtask;
                break;
            }
        }
    }

    public void deleteTask(Task subtask) {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == subtask) {
                tasks[i] = null;
                break;
            }
        }
    }

    public Task[] getSubtasks() {
        return tasks;
    }

    @Override
    public int estimateDuration() {
        int sum = 0;
        for (Task task : tasks) {
            if (task != null)
                sum += task.estimateDuration();
        }
        return sum;
    }
}
