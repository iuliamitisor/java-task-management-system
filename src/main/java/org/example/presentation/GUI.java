package org.example.presentation;

import org.example.persistence.TaskData;
import org.example.model.Employee;
import org.example.model.SimpleTask;
import org.example.model.ComplexTask;
import org.example.model.Task;

import org.example.business.TasksManagement;
import org.example.business.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUI {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private TasksManagement tasksManagement;
    private Utility utility;

    public GUI() {
        buildGUI();
    }

    private void buildGUI() {
        this.tasksManagement = new TasksManagement();
        this.utility = new Utility();
        JFrame frame = new JFrame("Management Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 350);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.add(Box.createVerticalGlue());

        JButton addTaskButton = new JButton("Add task");
        JButton addEmployeeButton = new JButton("Add employee");
        JButton viewEmployeesButton = new JButton("View employees");
        JButton viewTasksButton = new JButton("View tasks");
        JButton assignTaskToEmployeeButton = new JButton("Assign task to employee");
        JButton viewEmployeesTasksDurationButton = new JButton("View employees and task durations");
        JButton modifyTaskStatusButton = new JButton("Modify task status");
        JButton filterEmployeesButton = new JButton("Filter employees");
        JButton employeesStatsButton = new JButton("View employees statistics");

        addTaskButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addEmployeeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewEmployeesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewTasksButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        assignTaskToEmployeeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewEmployeesTasksDurationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifyTaskStatusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterEmployeesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        employeesStatsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        menu.add(addTaskButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(addEmployeeButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(viewEmployeesButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(viewTasksButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(assignTaskToEmployeeButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(viewEmployeesTasksDurationButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(modifyTaskStatusButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(filterEmployeesButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(employeesStatsButton);
        menu.add(Box.createVerticalGlue());

        addTaskButton.addActionListener(e -> cardLayout.show(cardPanel, "Add Task"));
        addEmployeeButton.addActionListener(e -> cardLayout.show(cardPanel, "Add Employee"));
        viewEmployeesButton.addActionListener(e -> cardLayout.show(cardPanel, "View Employees"));
        viewTasksButton.addActionListener(e -> cardLayout.show(cardPanel, "View Tasks"));
        assignTaskToEmployeeButton.addActionListener(e -> cardLayout.show(cardPanel, "Assign Task"));
        viewEmployeesTasksDurationButton.addActionListener(e -> cardLayout.show(cardPanel, "View Employees Task Duration"));
        modifyTaskStatusButton.addActionListener(e -> cardLayout.show(cardPanel, "Modify Task Status"));
        filterEmployeesButton.addActionListener(e -> cardLayout.show(cardPanel, "Filter Employees"));
        employeesStatsButton.addActionListener(e -> cardLayout.show(cardPanel, "View Employees Statistics"));

        cardPanel.add(menu, "Menu");
        cardPanel.add(createAddTaskPanel(), "Add Task");
        cardPanel.add(createAddSimpleTaskPanel(frame), "Add Simple Task");
        cardPanel.add(createAddComplexTaskPanel(frame), "Add Complex Task");
        cardPanel.add(createAddEmployeePanel(frame), "Add Employee");
        cardPanel.add(createViewEmployeesPanel(), "View Employees");
        cardPanel.add(createViewTasksPanel(), "View Tasks");
        cardPanel.add(createAssignTaskPanel(frame), "Assign Task");
        cardPanel.add(createViewEmployeesDurationsPanel(), "View Employees Task Duration");
        cardPanel.add(createModifyTaskStatusPanel(frame), "Modify Task Status");
        cardPanel.add(createFilterEmployeesPanel(), "Filter Employees");
        cardPanel.add(createEmployeesStatisticsPanel(), "View Employees Statistics");

        cardLayout.show(cardPanel, "Menu");

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private JPanel createViewTasksPanel() {
        JPanel viewTasksPanel = new JPanel();
        viewTasksPanel.setLayout(new BoxLayout(viewTasksPanel, BoxLayout.Y_AXIS));

        // Retrieve the list of tasks
        List<Task> tasks = utility.loadAllTasks();

        // Define column names
        String[] columnNames = {"Task ID", "Task Description", "Task Duration", "Task Status", "Task Type"};

        // Create data array
        Object[][] data = new Object[tasks.size()][5];
        int index = 0;
        for (Task task : tasks) {
            data[index][0] = task.getId();
            data[index][1] = task.getDescription();
            data[index][2] = task.estimateDuration();
            data[index][3] = task.getStatus();
            if (task instanceof SimpleTask) {
                data[index][4] = "Simple";
            } else if (task instanceof ComplexTask) {
                data[index][4] = "Complex";
            }
            index++;
        }

        // Create JTable
        JTable tasksTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tasksTable);
        viewTasksPanel.add(scrollPane);
        viewTasksPanel.add(Box.createVerticalStrut(10));

        // Create Back to menu button
        JButton back = new JButton("Back to menu");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewTasksPanel.add(back);
        viewTasksPanel.add(Box.createVerticalGlue());
        back.addActionListener(e -> cardLayout.show(cardPanel, "Menu"));

        return viewTasksPanel;
    }

    private JPanel createViewEmployeesPanel() {
        JPanel viewEmployeesPanel = new JPanel();
        viewEmployeesPanel.setLayout(new BoxLayout(viewEmployeesPanel, BoxLayout.Y_AXIS));

        // Retrieve the list of employees
        List<Employee> employees = utility.loadAllEmployees();

        // Define column names
        String[] columnNames = {"Employee ID", "Employee Name"};

        // Create data array
        Object[][] data = new Object[employees.size()][2];
        int index = 0;
        for (Employee employee : employees) {
            data[index][0] = employee.getId();
            data[index][1] = employee.getName();
            index++;
        }

        // Create JTable
        JTable tasksTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tasksTable);
        viewEmployeesPanel.add(scrollPane);
        viewEmployeesPanel.add(Box.createVerticalStrut(10));

        // Create Back to menu button
        JButton back = new JButton("Back to menu");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewEmployeesPanel.add(back);
        viewEmployeesPanel.add(Box.createVerticalGlue());
        back.addActionListener(e -> cardLayout.show(cardPanel, "Menu"));

        return viewEmployeesPanel;
    }

    private JPanel createAddTaskPanel() {
        JPanel addTaskPanel = new JPanel();
        addTaskPanel.setLayout(new BoxLayout(addTaskPanel, BoxLayout.Y_AXIS));
        addTaskPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton addSimpleTaskButton = new JButton("Add simple task");
        JButton addComplexTaskButton = new JButton("Add complex task");
        addSimpleTaskButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addComplexTaskButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        addSimpleTaskButton.addActionListener(e -> cardLayout.show(cardPanel, "Add Simple Task"));
        addComplexTaskButton.addActionListener(e -> cardLayout.show(cardPanel, "Add Complex Task"));

        addTaskPanel.add(addSimpleTaskButton);
        addTaskPanel.add(Box.createVerticalStrut(10));
        addTaskPanel.add(addComplexTaskButton);
        addTaskPanel.add(Box.createVerticalGlue());
        return addTaskPanel;
    }

    private JPanel createAddSimpleTaskPanel(JFrame frame) {
        JPanel addSimpleTaskPanel = new JPanel();
        addSimpleTaskPanel.setLayout(new BoxLayout(addSimpleTaskPanel, BoxLayout.Y_AXIS));
        addSimpleTaskPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descriptionLabel = new JLabel("Task description:");
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField descriptionTextField = new JTextField(20);
        descriptionTextField.setMaximumSize(new Dimension(200, 20));
        descriptionTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel startHourLabel = new JLabel("Start Hour:");
        startHourLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField startHourTextField = new JTextField(20);
        startHourTextField.setMaximumSize(new Dimension(200, 20));
        startHourTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel endHourLabel = new JLabel("End Hour:");
        endHourLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField endHourTextField = new JTextField(20);
        endHourTextField.setMaximumSize(new Dimension(200, 20));
        endHourTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton addSimpleTask = new JButton("Add Simple Task");
        addSimpleTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        addSimpleTask.addActionListener(e -> {
            int startHour = Integer.parseInt(startHourTextField.getText());
            int endHour = Integer.parseInt(endHourTextField.getText());
            String description = descriptionTextField.getText();
            utility.saveTask(new SimpleTask(description, startHour, endHour));
            JOptionPane.showMessageDialog(null, "Simple Task added successfully!");
            frame.dispose();
            buildGUI();
        });

        addSimpleTaskPanel.add(descriptionLabel);
        addSimpleTaskPanel.add(Box.createVerticalStrut(10));
        addSimpleTaskPanel.add(descriptionTextField);
        addSimpleTaskPanel.add(Box.createVerticalStrut(10));
        addSimpleTaskPanel.add(startHourLabel);
        addSimpleTaskPanel.add(Box.createVerticalStrut(10));
        addSimpleTaskPanel.add(startHourTextField);
        addSimpleTaskPanel.add(Box.createVerticalStrut(10));
        addSimpleTaskPanel.add(endHourLabel);
        addSimpleTaskPanel.add(Box.createVerticalStrut(10));
        addSimpleTaskPanel.add(endHourTextField);
        addSimpleTaskPanel.add(Box.createVerticalStrut(10));
        addSimpleTaskPanel.add(addSimpleTask);
        addSimpleTaskPanel.add(Box.createVerticalGlue());

        return addSimpleTaskPanel;
    }

    private JPanel createAddComplexTaskPanel(JFrame frame) {
        JPanel addComplexTaskPanel = new JPanel();
        addComplexTaskPanel.setLayout(new BoxLayout(addComplexTaskPanel, BoxLayout.Y_AXIS));
        addComplexTaskPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel taskLabel = new JLabel("Task Description:");
        taskLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField descriptionTextField = new JTextField(20);
        descriptionTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Load existing tasks
        List<Task> availableTasks = utility.loadAllTasks();
        // Define column names
        String[] columnNames = {"Task ID", "Task Description", "Task Duration", "Task Status", "Task Type"};
        Object[][] data = new Object[availableTasks.size()][5];
        int index = 0;
        for (Task task : availableTasks) {
            data[index][0] = task.getId();
            data[index][1] = task.getDescription();
            data[index][2] = task.estimateDuration();
            data[index][3] = task.getStatus();
            if (task instanceof SimpleTask) {
                data[index][4] = "Simple";
            } else if (task instanceof ComplexTask) {
                data[index][4] = "Complex";
            }
            index++;
        }

        JTable taskTable = new JTable(data, columnNames);
        taskTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskTable);

        JButton addComplexTask = new JButton("Add Complex Task");
        addComplexTask.setAlignmentX(Component.CENTER_ALIGNMENT);

        addComplexTask.addActionListener(e -> {
            String description = descriptionTextField.getText();
            int[] selectedRows = taskTable.getSelectedRows();
            List<Task> selectedTasks = new ArrayList<>();
            for (int row : selectedRows) {
                int taskId = (int) taskTable.getValueAt(row, 0);
                Task task = utility.findTaskById(taskId);
                if (task != null) {
                    selectedTasks.add(task);
                }
            }
            ComplexTask complexTask = new ComplexTask(description, selectedTasks.toArray(new Task[0]));
            utility.saveTask(complexTask);
            frame.dispose();
            buildGUI();

            JOptionPane.showMessageDialog(null, "Complex Task added successfully!");
        });
        addComplexTaskPanel.add(taskLabel);
        addComplexTaskPanel.add(descriptionTextField);
        addComplexTaskPanel.add(Box.createVerticalStrut(10));
        addComplexTaskPanel.add(new JLabel("Select Subtasks:"));
        addComplexTaskPanel.add(scrollPane);
        addComplexTaskPanel.add(Box.createVerticalStrut(10));
        addComplexTaskPanel.add(addComplexTask);
        addComplexTaskPanel.add(Box.createVerticalGlue());

        return addComplexTaskPanel;
    }

    private JPanel createAddEmployeePanel(JFrame frame) {
        JPanel addEmployeePanel = new JPanel();
        addEmployeePanel.setLayout(new BoxLayout(addEmployeePanel, BoxLayout.Y_AXIS));
        addEmployeePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Employee Name:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField nameTextField = new JTextField(10);
        nameTextField.setMaximumSize(new Dimension(200, 20));
        nameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton addEmployee = new JButton("Add employee");
        addEmployee.setAlignmentX(Component.CENTER_ALIGNMENT);
        addEmployee.addActionListener(e -> {
            String name = nameTextField.getText();
            utility.saveEmployee(new Employee(name));
            JOptionPane.showMessageDialog(null, "Employee added successfully!");
            frame.dispose();
            buildGUI();
        });

        addEmployeePanel.add(nameLabel);
        addEmployeePanel.add(Box.createVerticalStrut(10));
        addEmployeePanel.add(nameTextField);
        addEmployeePanel.add(Box.createVerticalStrut(10));
        addEmployeePanel.add(addEmployee);
        addEmployeePanel.add(Box.createVerticalGlue());

        return addEmployeePanel;
    }

    private JPanel createModifyTaskStatusPanel(JFrame frame) {
        JPanel modifyTaskStatusPanel = new JPanel();
        modifyTaskStatusPanel.setLayout(new BoxLayout(modifyTaskStatusPanel, BoxLayout.Y_AXIS));
        modifyTaskStatusPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel taskIdLabel = new JLabel("Task ID:");
        taskIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField taskIdTextField = new JTextField(20);
        taskIdTextField.setMaximumSize(taskIdTextField.getPreferredSize());
        taskIdTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        String[] statusOptions = {"Uncompleted", "Completed"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);
        statusComboBox.setMaximumSize(statusComboBox.getPreferredSize());
        statusComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton modifyStatusButton = new JButton("Modify Status");
        modifyStatusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifyStatusButton.addActionListener(e -> {
            int taskId = Integer.parseInt(taskIdTextField.getText());
            String status = statusComboBox.getSelectedItem().toString();
            tasksManagement.modifyTaskStatus(taskId, status);
            JOptionPane.showMessageDialog(null, "Task Status modified successfully!");
            frame.dispose();
            buildGUI();
        });

        modifyTaskStatusPanel.add(taskIdLabel);
        modifyTaskStatusPanel.add(Box.createVerticalStrut(10));
        modifyTaskStatusPanel.add(taskIdTextField);
        modifyTaskStatusPanel.add(Box.createVerticalStrut(10));
        modifyTaskStatusPanel.add(statusLabel);
        modifyTaskStatusPanel.add(Box.createVerticalStrut(10));
        modifyTaskStatusPanel.add(statusComboBox);
        modifyTaskStatusPanel.add(Box.createVerticalStrut(10));
        modifyTaskStatusPanel.add(modifyStatusButton);
        modifyTaskStatusPanel.add(Box.createVerticalGlue());

        return modifyTaskStatusPanel;
    }

    private JPanel createViewEmployeesDurationsPanel() {

        JPanel viewEmployeesDurationsPanel = new JPanel();
        viewEmployeesDurationsPanel.setLayout(new BoxLayout(viewEmployeesDurationsPanel, BoxLayout.Y_AXIS));

        // Retrieve the list of employees
        List<Employee> employees = utility.loadAllEmployees();

        // Define column names
        String[] columnNames = {"Employee ID", "Employee Name", "Total Work Duration"};

        // Create data array
        Object[][] data = new Object[employees.size()][3];
        int i = 0;
        for (Employee employee : employees) {
            int totalDuration = tasksManagement.calculateEmployeeWorkDuration(employee.getId());
            System.out.println("Total duration: " + totalDuration);
            data[i][0] = employee.getId();
            data[i][1] = employee.getName();
            data[i][2] = totalDuration;
            i++;
            if (i >= employees.size()) {
                break;
            }
        }

        // Create JTable
        JTable employeesDurationsTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(employeesDurationsTable);
        viewEmployeesDurationsPanel.add(scrollPane);
        viewEmployeesDurationsPanel.add(Box.createVerticalStrut(10));

        // Create Back to menu button
        JButton back = new JButton("Back to menu");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewEmployeesDurationsPanel.add(back);
        viewEmployeesDurationsPanel.add(Box.createVerticalGlue());
        back.addActionListener(e -> cardLayout.show(cardPanel, "Menu"));

        return viewEmployeesDurationsPanel;
    }

    private JPanel createFilterEmployeesPanel() {
        JPanel filterEmployeesPanel = new JPanel();
        filterEmployeesPanel.setLayout(new BoxLayout(filterEmployeesPanel, BoxLayout.Y_AXIS));
        filterEmployeesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<Employee> employees = utility.loadAllEmployees();
        List<Task> tasks = utility.loadAllTasks();
        List<Employee> filteredEmployees = utility.filterEmployees(employees);

        String[] columnNames = {"Employee ID", "Employee Name", "Work Duration"};

        // Populate data array
        Object[][] data = new Object[filteredEmployees.size()][3];
        int index = 0;
        for (Employee employee : filteredEmployees) {
            data[index][0] = employee.getId();
            data[index][1] = employee.getName();
            data[index][2] = tasksManagement.calculateEmployeeWorkDuration(employee.getId());
            index++;
            if (index >= filteredEmployees.size()) {
                break;
            }
        }

        // Create table with data
        JTable filteredEmployeesTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(filteredEmployeesTable);
        filterEmployeesPanel.add(scrollPane);
        filterEmployeesPanel.add(Box.createVerticalStrut(10));

        // Create Back to menu button
        JButton back = new JButton("Back to menu");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterEmployeesPanel.add(back);
        filterEmployeesPanel.add(Box.createVerticalGlue());
        back.addActionListener(e -> cardLayout.show(cardPanel, "Menu"));

        return filterEmployeesPanel;
    }

    private JPanel createEmployeesStatisticsPanel() {
        JPanel employeesStatisticsPanel = new JPanel();
        employeesStatisticsPanel.setLayout(new BoxLayout(employeesStatisticsPanel, BoxLayout.Y_AXIS));
        employeesStatisticsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<Employee> employees = utility.loadAllEmployees();
        Map<String, Map<String, Integer>> stats = utility.calculateTaskStatus(employees);

        String[] columnNames = {"Employee ID", "Employee Name", "Completed Tasks", "Uncompleted Tasks"};

        Object[][] data = new Object[employees.size()][4];
        int index = 0;
        for (Employee employee : employees) {
            data[index][0] = employee.getId();
            data[index][1] = employee.getName();
            data[index][2] = stats.get(employee.getName()).get("Completed");
            data[index][3] = stats.get(employee.getName()).get("Uncompleted");
            index++;
            if (index >= employees.size()) {
                break;
            }
        }

        JTable filteredEmployeesTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(filteredEmployeesTable);
        employeesStatisticsPanel.add(scrollPane);
        employeesStatisticsPanel.add(Box.createVerticalStrut(10));

        // Create Back to menu button
        JButton back = new JButton("Back to menu");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        employeesStatisticsPanel.add(back);
        employeesStatisticsPanel.add(Box.createVerticalGlue());
        back.addActionListener(e -> cardLayout.show(cardPanel, "Menu"));

        return employeesStatisticsPanel;
    }

    private JPanel createAssignTaskPanel(JFrame frame) {
        JPanel assignTaskPanel = new JPanel();
        assignTaskPanel.setLayout(new BoxLayout(assignTaskPanel, BoxLayout.Y_AXIS));
        assignTaskPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField employeeIdTextField = new JTextField(20);
        employeeIdTextField.setMaximumSize(new Dimension(200, 20));
        employeeIdTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel taskIdLabel = new JLabel("Task ID:");
        taskIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField taskIdTextField = new JTextField(20);
        taskIdTextField.setMaximumSize(new Dimension(200, 20));
        taskIdTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton assignTaskButton = new JButton("Assign Task");
        assignTaskButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        assignTaskButton.addActionListener(e -> {
            int employeeId = Integer.parseInt(employeeIdTextField.getText());
            int taskId = Integer.parseInt(taskIdTextField.getText());
            tasksManagement.assignTaskToEmployee(employeeId, utility.findTaskById(taskId));
            JOptionPane.showMessageDialog(null, "Task assigned successfully!");
            frame.dispose();
            buildGUI();
        });

        assignTaskPanel.add(employeeIdLabel);
        assignTaskPanel.add(Box.createVerticalStrut(10));
        assignTaskPanel.add(employeeIdTextField);
        assignTaskPanel.add(Box.createVerticalStrut(10));
        assignTaskPanel.add(taskIdLabel);
        assignTaskPanel.add(Box.createVerticalStrut(10));
        assignTaskPanel.add(taskIdTextField);
        assignTaskPanel.add(Box.createVerticalStrut(10));
        assignTaskPanel.add(assignTaskButton);
        assignTaskPanel.add(Box.createVerticalGlue());

        return assignTaskPanel;
    }
}