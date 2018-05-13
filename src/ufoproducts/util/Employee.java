package ufoproducts.util;

/**
 * This class is meant to be a class that stores employees, like a kotlin
 * data class.All fields are public
 */

public class Employee {
    public String name;
    public int id;
    public boolean isManager;
    public Employee(String n, int i, boolean iM) {
        name = n;
        id = i;
        isManager = iM;
    }
}
