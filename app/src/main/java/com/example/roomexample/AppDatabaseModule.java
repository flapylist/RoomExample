package com.example.roomexample;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppDatabaseModule {
    private final Context context;

    public AppDatabaseModule(Context context){
        this.context=context;
    }

    @Provides
    @Singleton
    public AppDatabase appDatabase(){
        return Room.databaseBuilder(context,AppDatabase.class,"database").build();
    }
}
