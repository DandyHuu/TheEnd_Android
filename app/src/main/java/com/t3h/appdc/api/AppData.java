package com.t3h.appdc.api;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.t3h.appdc.model.Pets;

//@Database(entities = {Pets.class}, version = 1)
public abstract class AppData extends RoomDatabase {
//    public static AppData data;
//
//    public static AppData getInstance(Context context){
//        if (data == null) {
//            data = Room.databaseBuilder(context,AppData.class,"pets_db").allowMainThreadQueries().build();
//
//        }
//        return data;
//    }
//
//    public abstract Pets getMyPets();
}
