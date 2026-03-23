package org.example.gui;

import org.example.businesslogic.TasksManagement;
import org.example.model.ComplexTask;
import org.example.model.Employee;
import org.example.model.SimpleTask;
import org.example.model.Task;

import java.util.HashMap;
import java.util.Map;



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
    private JTextArea taskOutputArea;
    private JPanel employeesPanel;
    private JPanel taskPanel;
    private JList<Employee> employeesList;
    private JList<Task> tasksList;
    private JButton assignTaskButton;
    private JButton modifyStatusButton;
    private JButton calculateDurationButton;
    private JTextArea assignOutputArea;
    private JTextArea tasksOutputArea;

    private Map<Integer, Task> allTasks;
    private DefaultListModel<Employee> employeesListModel;
    private DefaultListModel<Task> tasksListModel;


    public MainFrame(TasksManagement tasksManagement) {
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

        addEmployeeButton.addActionListener(e -> addEmployee());
        createSimpleTaskButton.addActionListener(e -> createSimpleTask());
        createComplexTaskButton.addActionListener(e -> createComplexTask());

        assignTaskButton.addActionListener(e -> assignTask());
        modifyStatusButton.addActionListener(e -> modifyTaskStatus());
        calculateDurationButton.addActionListener(e -> calculateDuration());
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

            tasksOutputArea.append("Complex task: " + task + " created!\n");

            complexTaskIdField.setText("");
            complexTaskStatusField.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void assignTask() {

    }

    private void modifyTaskStatus() {

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


}
