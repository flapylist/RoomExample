package com.example.roomexample;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Room;

import org.w3c.dom.Text;

@Entity(tableName = "Employees")
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "employee_name")
    private String name;

    @ColumnInfo
    private int salary;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
