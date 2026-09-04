package com.baeldung.lju.domain.model;

public enum TaskStatus {

    TO_DO("To Do"),
    IN_PROGRESS("In Progress"),
    ON_HOLD("On Hold"),
    DONE ("Done");

    private String value;
    TaskStatus(String value) {}
}
