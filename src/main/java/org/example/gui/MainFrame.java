package org.example.gui;

import org.example.businesslogic.TasksManagement;
import org.example.model.Employee;

import javax.swing.*;

public class MainFrame extends JFrame {
    private TasksManagement tasksManagement;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField employeeIdField;
    private JTextField employeeNameField;
    private JButton addEmployeeButton;
    private JTextArea employeesOutputArea;

    public MainFrame(TasksManagement tasksManagement) {
        this.tasksManagement = tasksManagement;

        setTitle("Task Management");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        addEmployeeButton.addActionListener(e -> addEmployee());
    }

    private void addEmployee() {
        try{
            int id = Integer.parseInt(employeeIdField.getText());
            String name = employeeNameField.getText();

            Employee employee = new Employee(id, name);
            tasksManagement.addEmployee(employee);

            //pastrez istoricul
            employeesOutputArea.append("Employee: " + employee + " added successfully!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
