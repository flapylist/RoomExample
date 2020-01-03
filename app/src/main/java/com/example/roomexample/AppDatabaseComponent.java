package com.example.roomexample;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppDatabaseModule.class})
public interface AppDatabaseComponent {
    AppDatabase getAppDatabase();
}
