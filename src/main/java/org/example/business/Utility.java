package org.example.business;

import org.example.model.ComplexTask;
import org.example.persistence.EmployeeData;
import org.example.persistence.TaskData;
import org.example.model.Employee;
import org.example.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utility {
    private TasksManagement tasksManagement;
    private EmployeeData employeeData;
    private TaskData taskData;

    public Utility() {
        tasksManagement = new TasksManagement();
        employeeData = new EmployeeData();
        taskData = new TaskData();
    }

    public List<Employee> filterEmployees(List<Employee> employees) {
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            // Add only employees whose total work duration exceeds 40 hours.
            if (tasksManagement.calculateEmployeeWorkDuration(employee.getId()) > 40) {
                filteredEmployees.add(employee);
            }
        }
        // Sort by work duration
        filteredEmployees.sort((e1, e2) -> tasksManagement.calculateEmployeeWorkDuration(e1.getId())
                - tasksManagement.calculateEmployeeWorkDuration(e2.getId()));
        return filteredEmployees;
    }

    public Map<String, Map<String, Integer>> calculateTaskStatus(List<Employee> employees) {
        Map<String, Map<String, Integer>> taskStatusMap = new HashMap<>();
        for (Employee employee : employees) {

            // Get the list of tasks assigned to the employee or an empty list if the employee has no tasks assigned.
            tasksManagement.getTaskAssignmentMap().putIfAbsent(employee, new ArrayList<>());
            List<Task> tasksList = tasksManagement.getTaskAssignmentMap().get(employee);
            int completedTasks = 0;
            int uncompletedTasks = 0;

            // Count the number of completed and uncompleted tasks.
            for (Task task : tasksList) {
                if ("Completed".equals(task.getStatus())) {
                    completedTasks++;
                } else {
                    uncompletedTasks++;
                }
            }

            Map<String, Integer> statusMap = new HashMap<>();
            statusMap.put("Completed", completedTasks);
            statusMap.put("Uncompleted", uncompletedTasks);
            taskStatusMap.put(employee.getName(), statusMap);
        }
        return taskStatusMap;
    }

    public List<Employee> loadAllEmployees() {
        return employeeData.loadEmployees();
    }

    public List<Task> loadAllTasks() {
        return taskData.loadTasks();
    }

    public void saveTask(Task newTask) {
        taskData.addTask(newTask);
    }

    public void saveEmployee(Employee newEmployee) {
        employeeData.addEmployee(newEmployee);
    }

    public Task findTaskById(int taskId) {
        return taskData.findTaskById(taskId);
    }
}
