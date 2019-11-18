package com.proudpet.ipet.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.proudpet.ipet.classes.Animal;

import java.util.List;

@Dao
public interface AnimaisDAO {

    @Delete
    void remove(Animal animalEscolhido);

    @Query("SELECT * FROM animal")
    List<Animal> todos();

    @Update
    void edita(Animal animal);

    @Insert
    void salva(Animal animal);

    @Query("SELECT * FROM animal where id = :id")
    Animal pegaAnimal(int id);
}