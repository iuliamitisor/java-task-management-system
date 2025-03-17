package org.example.persistence;

import org.example.model.Employee;
import org.example.model.Task;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignmentsData {
    private static final String TASK_ASSIGNMENTS_FILE = "task_assignments.dat";

    public void saveTaskAssignments(Map<Employee, List<Task>> taskAssignmentMap) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TASK_ASSIGNMENTS_FILE))) {
            oos.writeObject(taskAssignmentMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Employee, List<Task>> loadTaskAssignments() {
        File file = new File(TASK_ASSIGNMENTS_FILE);

        if (!file.exists()) {
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                return (Map<Employee, List<Task>>) obj;
            } else {
                return new HashMap<>();
            }
        } catch (EOFException e) {
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }
}

