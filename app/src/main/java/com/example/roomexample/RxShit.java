package com.example.roomexample;

import android.app.Application;
import android.database.Observable;
import android.util.Log;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RxShit {

    private AppDatabaseComponent component=DaggerAppDatabaseComponent
            .builder()
            .appDatabaseModule(new AppDatabaseModule(MyApplication.getInstance().getApplicationContext()))
            .build();

    private  AppDatabase db=component.getAppDatabase();

    private EmployeeDao dao=db.employeeDao();

    public Single<Long> insert(Employee employee){
        return Single.create(s-> {

            dao.insert(employee);

            s.onSuccess(employee.getId());
        });
    }

    public Single<Employee> getByID(Long id){
        return Single.create(e->{
            Employee employee=dao.getById(id);

            e.onSuccess(employee);
        });
    }

}
