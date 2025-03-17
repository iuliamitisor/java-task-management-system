package org.example.persistence;

import org.example.model.Employee;
import org.example.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskData implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String TASKS_FILE = "tasks.dat";
    private List<Task> tasks;

    public TaskData() {
        this.tasks = loadTasks();
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TASKS_FILE))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TASKS_FILE))) {
            return (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}

