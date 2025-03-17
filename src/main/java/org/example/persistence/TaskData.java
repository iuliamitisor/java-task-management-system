package org.example.persistence;

import org.example.model.Employee;
import org.example.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskData implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String TASKS_FILE = "tasks.dat";
    private static final String TASK_ASSIGNMENTS_FILE = "task_assignments.dat";
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

    // Updated: Save task assignments using Employee as the key
    public void saveTaskAssignments(Map<Employee, List<Task>> taskAssignmentMap) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TASK_ASSIGNMENTS_FILE))) {
            oos.writeObject(taskAssignmentMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Updated: Load task assignments using Employee as the key
    public Map<Employee, List<Task>> loadTaskAssignments() {
        File file = new File(TASK_ASSIGNMENTS_FILE);

        if (!file.exists()) {
            System.out.println("Task assignment file not found. Returning empty map.");
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                return (Map<Employee, List<Task>>) obj;
            } else {
                System.out.println("Invalid file format. Returning empty map.");
                return new HashMap<>();
            }
        } catch (EOFException e) {
            System.out.println("Task assignment file is empty. Returning empty map.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }
}
