package com.proudpet.ipet.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.proudpet.ipet.classes.Vacina;
import com.proudpet.ipet.database.converter.ConversorCalendar;
import com.proudpet.ipet.database.dao.VacinasDAO;

@Database(entities = {Vacina.class}, version = 3, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class VacinasDatabase extends RoomDatabase{
    public static final String NOME_BANCO_DE_DADOS = "Vacinas.db";
    public abstract VacinasDAO getRoomVacinaDAO();

    public static VacinasDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, VacinasDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
