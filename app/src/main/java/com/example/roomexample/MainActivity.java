package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class MainActivity extends AppCompatActivity {

    RxShit rxManager;
    EditText etID,etName,etSalary;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxManager=new RxShit();
        etID=findViewById(R.id.etID);
        etName=findViewById(R.id.etName);
        etSalary=findViewById(R.id.etSalary);
        CompositeDisposable compositeDisposable=new CompositeDisposable();

        btnAdd=findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((etID.getText().toString() != null && !etID.getText().toString().isEmpty()) &&
                        (etName.getText().toString() != null && !etName.getText().toString().isEmpty()) &&
                        etSalary.getText().toString() != null && !etSalary.getText().toString().isEmpty()) {

                    Employee employee = new Employee();
                    employee.id = Long.parseLong(etID.getText().toString());
                    employee.name = etName.getText().toString();
                    employee.salary = Integer.parseInt(etSalary.getText().toString());

                    Disposable insertDispose= rxManager.insert(employee)
                            .subscribeWith(new DisposableSingleObserver<Long>() {
                                @Override
                                public void onSuccess(Long aLong) {
                                   Disposable getDispose= rxManager.getByID(aLong)
                                            .subscribeWith(new DisposableSingleObserver<Employee>() {
                                                @Override
                                                public void onSuccess(Employee employee) {
                                                    Toast.makeText(getApplicationContext(),"Added employee with ID: "+employee.id,Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }
                                            });
                                    compositeDisposable.add(getDispose);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            });
                    compositeDisposable.add(insertDispose);

                }
                else Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                compositeDisposable.dispose();
            }
        });

    }
    }

