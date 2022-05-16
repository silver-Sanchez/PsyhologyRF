package com.example.psyhologyrf;

public class EmployeeInfo {

    // string variable for
    // storing employee name.
    private String employeeName;

    // string variable for storing
    // employee contact number
    private String employeeContactNumber;

    private int employeePersColor;

    // string variable for storing
    // employee address.
    private String employeeAddress;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public EmployeeInfo() {

    }

    // created getter and setter methods
    // for all our variables.
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeContactNumber() {
        return employeeContactNumber;
    }

    public void setEmployeeContactNumber(String employeeContactNumber) {
        this.employeeContactNumber = employeeContactNumber;
    }

    public int getEmployeePersColor() {
        return employeePersColor;
    }

    public void setEmployeePersColor(int employeePersColor) {
        this.employeePersColor = employeePersColor;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }
}
