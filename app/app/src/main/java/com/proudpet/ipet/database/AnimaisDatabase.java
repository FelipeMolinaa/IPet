package com.proudpet.ipet.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.proudpet.ipet.classes.Animal;
import com.proudpet.ipet.database.converter.ConversorCalendar;
import com.proudpet.ipet.database.dao.AnimaisDAO;

@Database(entities = {Animal.class}, version = 3, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class AnimaisDatabase extends RoomDatabase {

    private static final String NomeBancoDados = "Animais.db";
    public abstract AnimaisDAO getRoomAnimalDAO();

    public static AnimaisDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AnimaisDatabase.class, NomeBancoDados)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
