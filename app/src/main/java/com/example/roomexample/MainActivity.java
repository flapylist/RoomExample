package com.example.roomexample;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RxShit rxManager;
    EditText etName,etSalary;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxManager=new RxShit();
        etName=findViewById(R.id.etName);
        etSalary=findViewById(R.id.etSalary);
        CompositeDisposable compositeDisposable=new CompositeDisposable();

        btnAdd=findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etName.getText().toString().isEmpty() && !etSalary.getText().toString().isEmpty()) {
                    Employee employee = new Employee();
                    employee.setName(etName.getText().toString());
                    employee.setSalary(Integer.parseInt(etSalary.getText().toString()));

                    rxManager.insert(employee)
                            .flatMap(aLong -> rxManager.getById(aLong))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<Employee>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    compositeDisposable.add(d);
                                }

                                @Override
                                public void onSuccess(Employee employee) {
                                    Toast.makeText(getApplicationContext(),"Added employee with id "+employee.getId(),Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            });

                }
                else Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
            }
        });

    }
    }

