package org.example.gui;

import org.example.businesslogic.TasksManagement;
import org.example.model.ComplexTask;
import org.example.model.Employee;
import org.example.model.SimpleTask;
import org.example.model.Task;
import org.example.businesslogic.Utility;
import org.example.dataaccess.SerializationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;



import javax.swing.*;

public class MainFrame extends JFrame {
    private TasksManagement tasksManagement;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField employeeIdField;
    private JTextField employeeNameField;
    private JButton addEmployeeButton;
    private JTextArea employeesOutputArea;
    private JTextField simpleTaskIdField;
    private JTextField simpleTaskStatusField;
    private JTextField startHourField;
    private JTextField endHourField;
    private JButton createSimpleTaskButton;
    private JTextField complexTaskIdField;
    private JTextField complexTaskStatusField;
    private JButton createComplexTaskButton;
    //private JTextArea taskOutputArea;
    private JPanel employeesPanel;
    private JPanel taskPanel;
    private JList<Employee> employeesList;
    private JList<Task> tasksList;
    private JButton assignTaskButton;
    private JButton modifyStatusButton;
    private JButton calculateDurationButton;
    private JTextArea assignOutputArea;
    private JButton showEmployeesOver40Button;
    private JButton showTaskStatisticsButton;
    private JTextArea statisticsOutputArea;
    private JTextArea tasksOutputArea;
    private JButton saveButton;
    private JButton loadButton;
    private JComboBox<Task> complexTaskComboBox;
    private JComboBox<Task> subTaskComboBox;
    private JButton addSubtaskButton;

    private Map<Integer, Task> allTasks;
    private DefaultListModel<Employee> employeesListModel;
    private DefaultListModel<Task> tasksListModel;
    private DefaultComboBoxModel<Task> subTaskComboBoxModel;
    private DefaultComboBoxModel<Task> complexTaskComboBoxModel;


    public MainFrame(TasksManagement tasksManagement) {
        complexTaskComboBoxModel = new DefaultComboBoxModel<>();
        subTaskComboBoxModel = new DefaultComboBoxModel<>();


        this.tasksManagement = tasksManagement;
        this.allTasks = new HashMap<>();

        setTitle("Task Management");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        employeesListModel = new DefaultListModel<>();
        tasksListModel = new DefaultListModel<>();

        employeesList.setModel(employeesListModel);
        tasksList.setModel(tasksListModel);
        complexTaskComboBox.setModel(complexTaskComboBoxModel);
        subTaskComboBox.setModel(subTaskComboBoxModel);

        addEmployeeButton.addActionListener(e -> addEmployee());
        createSimpleTaskButton.addActionListener(e -> createSimpleTask());
        createComplexTaskButton.addActionListener(e -> createComplexTask());

        assignTaskButton.addActionListener(e -> assignTask());
        modifyStatusButton.addActionListener(e -> modifyTaskStatus());
        calculateDurationButton.addActionListener(e -> calculateDuration());

        showEmployeesOver40Button.addActionListener(e -> showEmployeesOver40());
        showTaskStatisticsButton.addActionListener(e -> showTaskStatistics());

        addSubtaskButton.addActionListener(e -> addSubtaskToComplexTask());

        saveButton.addActionListener(e -> saveData());
        loadButton.addActionListener(e -> loadData());
    }

    private void addEmployee() {
        try{
            int id = Integer.parseInt(employeeIdField.getText());
            String name = employeeNameField.getText();

            Employee employee = new Employee(id, name);
            tasksManagement.addEmployee(employee);
            employeesListModel.addElement(employee);


            employeeIdField.setText("");
            employeeNameField.setText("");

            //pastrez istoricul
            employeesOutputArea.append("Employee: " + employee + " added successfully!\n");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void createSimpleTask() {
        try{
            int id = Integer.parseInt(simpleTaskIdField.getText());
            String status = simpleTaskStatusField.getText();
            int start = Integer.parseInt(startHourField.getText());
            int end = Integer.parseInt(endHourField.getText());

            if (allTasks.containsKey(id)){
                throw new IllegalArgumentException("Task with id " + id + " already exists!");
            }

            SimpleTask task = new SimpleTask(id, status, start, end);
            allTasks.put(id, task);
            tasksListModel.addElement(task);

            subTaskComboBoxModel.addElement(task);
            tasksOutputArea.append("Simple task: " + task + " created!\n");

            simpleTaskIdField.setText("");
            simpleTaskStatusField.setText("");
            startHourField.setText("");
            endHourField.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void createComplexTask() {
        try{
            int id = Integer.parseInt(complexTaskIdField.getText());
            String status = complexTaskStatusField.getText();

            if(allTasks.containsKey(id)){
                throw new IllegalArgumentException("Task with id " + id + " already exists!");
            }
            ComplexTask task = new ComplexTask(id, status);
            allTasks.put(id, task);
            tasksListModel.addElement(task);

            complexTaskComboBoxModel.addElement(task);
            subTaskComboBoxModel.addElement(task);
            tasksOutputArea.append("Complex task: " + task + " created!\n");

            complexTaskIdField.setText("");
            complexTaskStatusField.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void assignTask() {
        try{
            Employee employee = employeesList.getSelectedValue();
            Task task = tasksList.getSelectedValue();

            if (employee == null) {
                throw new  IllegalArgumentException("Employee is required!");
            }

            if (task == null) {
                throw new  IllegalArgumentException("Task is required!");
            }

            tasksManagement.assignTaskToEmployee(employee.getIdEmployee(), task);

            assignOutputArea.append("Assigned task " + task.getIdTask() +
                    " to employee " + employee.getName() + "\n");

            employeesList.clearSelection();
            tasksList.clearSelection();

        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void modifyTaskStatus() {
        try{
            Employee employee = employeesList.getSelectedValue();
            Task task = tasksList.getSelectedValue();

            if (employee == null) {
                throw new  IllegalArgumentException("Employee is required!");
            }

            if (task == null) {
                throw new  IllegalArgumentException("Task is required!");
            }

            tasksManagement.modifyTaskStatus(employee.getIdEmployee(), task.getIdTask());

            assignOutputArea.append("Modified status for task " + task.getIdTask() +
                    " of employee " + employee.getName() + "\n");

            employeesList.clearSelection();
            tasksList.clearSelection();

            tasksList.repaint();
            employeesList.repaint();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void calculateDuration() {
        try{
            Employee employee = employeesList.getSelectedValue();

            if(employee == null){
                throw new IllegalArgumentException("Employee is null");
            }

            int duration = tasksManagement.calculateEmployeeWorkDuration(employee.getIdEmployee());

            assignOutputArea.append("Employee " + employee.getName() +
                    " has work duration: " + duration + "\n");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void showEmployeesOver40() {
        try{
            statisticsOutputArea.setText("");

            List<Employee> employees = Utility.filterEmployeesWorkedMoreThan40Hours(tasksManagement);

            if(employees.isEmpty()){
                statisticsOutputArea.append("No employees have worked more than 40 hours\n");
                return;
            }

            statisticsOutputArea.append("Employees with more than 40 worked hours\n");
            for(Employee employee : employees){
                statisticsOutputArea.append(employee.toString() + "\n");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void showTaskStatistics() {
        try{
            statisticsOutputArea.setText("");

            Map<String, Map<String, Integer>> statistics =
                    Utility.createTaskStatistics(tasksManagement);

            if(statistics.isEmpty()){
                statisticsOutputArea.append("No statistics available.\n");
                return;
            }

            statisticsOutputArea.append("Task statistics:\n");
            List<Map.Entry<String, Map<String, Integer>>> list =
                    new ArrayList<>(statistics.entrySet());

            list.sort((e1, e2) -> {
                int hours1 = tasksManagement.calculateEmployeeWorkDurationByName(e1.getKey());
                int hours2 = tasksManagement.calculateEmployeeWorkDurationByName(e2.getKey());
                return Integer.compare(hours1, hours2);
            });

            for(Map.Entry<String, Map<String, Integer>> entry : list){
                statisticsOutputArea.append("Employee " + entry.getKey() + ":\n");

                for(Map.Entry<String, Integer> stat : entry.getValue().entrySet()){
                    statisticsOutputArea.append("  " +  stat.getKey() + ": " + stat.getValue() + "\n");
                }
                statisticsOutputArea.append("\n");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void saveData(){
        try{
            SerializationService service = new SerializationService();
            service.save(tasksManagement, "data.ser");

            JOptionPane.showMessageDialog(this, "Data Saved!\n");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void loadData(){
        try{
            SerializationService service = new SerializationService();
            TasksManagement loaded = service.load("data.ser");

            this.tasksManagement = loaded;

            refreshUIAfterLoad();

            JOptionPane.showMessageDialog(this, "Data Loaded!\n");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void refreshUIAfterLoad(){
        employeesListModel.clear();
        tasksListModel.clear();
        allTasks.clear();

        for(Map.Entry<Employee, List<Task>> entry : tasksManagement.getEmployeeTasks().entrySet()){
            Employee employee = entry.getKey();
            employeesListModel.addElement(employee);

            for(Task task : entry.getValue()){
                allTasks.put(task.getIdTask(), task);

                if(!tasksListModel.contains(task)){
                    tasksListModel.addElement(task);
                }
            }
        }
        employeesOutputArea.setText("");
        tasksOutputArea.setText("");
        assignOutputArea.setText("");
        statisticsOutputArea.setText("");
    }

    private void addSubtaskToComplexTask(){
        try{
            Task selectedComplex = (Task) complexTaskComboBox.getSelectedItem();
            Task selectedSubtask = (Task) subTaskComboBox.getSelectedItem();

            if(selectedComplex == null)
            {
                throw new IllegalArgumentException("Select a complex task");
            }
            if(selectedSubtask == null)
            {
                throw new IllegalArgumentException("Select a subtask");
            }
            if(!(selectedComplex instanceof ComplexTask)){
                throw new IllegalArgumentException("Selected task is not a complex task");
            }
            if(selectedComplex == selectedSubtask){
                throw new IllegalArgumentException("Task cannot contain itself");
            }

            ComplexTask complexTask = (ComplexTask) selectedComplex;
            if(complexTask.getTasks().contains(selectedSubtask)){
                throw new IllegalArgumentException("Subtask already added");
            }
            //am terminat cu erorile
            complexTask.addTask(selectedSubtask);
            tasksOutputArea.append("Added task " + selectedSubtask.getIdTask() +
                    " to complex task " + complexTask.getIdTask() + "\n");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}
