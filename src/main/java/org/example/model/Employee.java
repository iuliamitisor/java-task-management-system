package org.example.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Employee implements Serializable {
    private final int idEmployee;
    private final String name;

    public Employee(String name) {
        this.idEmployee = UUID.randomUUID().hashCode();
        this.name = name;
    }

    public int getId() {
        return idEmployee;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return idEmployee == employee.idEmployee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee);
    }
}
