package org.example.model;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
    private int idEmployee;
    private String name;


    public Employee(){

    }

    public Employee(int idEmployee, String name) {
        this.idEmployee = idEmployee;
        this.name = name;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if( this == o ) return true;
        if(!(o instanceof Employee employee)) return false;
        return idEmployee == employee.idEmployee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", name='" + name + '\'' +
                '}';
    }
}
