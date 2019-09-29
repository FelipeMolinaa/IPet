package com.proudpet.ipet.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.content.ClipData;

import com.proudpet.ipet.classes.Vacina;

import java.util.List;

@Dao
public interface VacinasDAO {

    
    @Delete
    void remove(Vacina vacina);

    @Query("SELECT * FROM vacina WHERE idAnimal = :id")
    List<Vacina> todos(int id);

    @Query("SELECT * FROM vacina WHERE id = :id")
    Vacina pegaVacina(int id);

    @Update
    void edita(Vacina vacina);

    @Insert
    void salva(Vacina vacina);
}
