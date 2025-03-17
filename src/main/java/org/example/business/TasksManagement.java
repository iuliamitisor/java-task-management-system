package org.example.business;

import org.example.persistence.EmployeeData;
import org.example.persistence.AssignmentsData;
import org.example.persistence.TaskData;
import org.example.model.Employee;
import org.example.model.Task;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TasksManagement {
    private Map<Employee, List<Task>> taskAssignmentMap = new HashMap<>();
    private EmployeeData employeeData;
    private TaskData taskData;
    private AssignmentsData assignmentsData;

    public TasksManagement() {
        this.employeeData = new EmployeeData();
        this.taskData = new TaskData();
        this.assignmentsData = new AssignmentsData();
        this.taskAssignmentMap = assignmentsData.loadTaskAssignments();
    }

    public void assignTaskToEmployee(int idEmployee, Task task) {
        if (task == null) {
            JOptionPane.showMessageDialog(null, "Error: Task not found!");
            return;
        }
        Employee employee = employeeData.findEmployeeById(idEmployee);
        if (employee == null) {
            JOptionPane.showMessageDialog(null, "Error: Employee not found!");
            return;
        }
        taskAssignmentMap.putIfAbsent(employee, new ArrayList<>());
        List<Task> tasksList = taskAssignmentMap.get(employee);
        if (!tasksList.contains(task)) {
            tasksList.add(task);
            assignmentsData.saveTaskAssignments(taskAssignmentMap);
        } else {
            JOptionPane.showMessageDialog(null, "Task is already assigned to this employee.");
        }
    }

    public int calculateEmployeeWorkDuration(int idEmployee) {
        Employee employee = employeeData.findEmployeeById(idEmployee);
        if (employee == null) {
            JOptionPane.showMessageDialog(null, "Error: Employee not found!");
            return -1;
        }

        taskAssignmentMap = assignmentsData.loadTaskAssignments();
        List<Task> tasksList = taskAssignmentMap.getOrDefault(employee, new ArrayList<>());

        if (tasksList.isEmpty()) {
            return 0;
        }

        int totalDuration = 0;
        for (Task task : tasksList) {
            if ("Completed".equals(task.getStatus())) {
                totalDuration += task.estimateDuration();
            }
        }
        return totalDuration;
    }

    public void modifyTaskStatus(int idTask, String status) {
        Task task = taskData.findTaskById(idTask);
        if (task != null) {
            task.setStatus(status);
            taskData.saveTasks();
        } else {
            JOptionPane.showMessageDialog(null, "Error: Task does not exist!");
        }
    }

    public Map<Employee, List<Task>> getTaskAssignmentMap() {
        return taskAssignmentMap;
    }
}