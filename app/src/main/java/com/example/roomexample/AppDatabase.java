package com.example.roomexample;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
}
