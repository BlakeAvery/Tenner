package ufoproducts.util;

/**
 * This class is meant to be a class that stores employees, like a kotlin
 * data class. Only fields are name of employee and ID number.
 */

public class Employee {
    public String name;
    public int id;
    public Employee(String n, int i) {
        name = n;
        id = i;
    }
}
