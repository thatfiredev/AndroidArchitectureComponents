package io.github.rosariopfernandes.aac;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by rosariopfernandes on 6/15/17.
 */
@Dao
public interface TarefaDAO {

    @Insert
    void criarTarefa(Tarefa tarefa);

    @Query("SELECT * FROM Tarefa")
    LiveData<List<Tarefa>> lerTarefas();

    @Query("SELECT * FROM Tarefa WHERE id = :id")
    Tarefa lerTarefaPeloId(int id);

    @Update
    void actualizarTarefa(Tarefa tarefa);

    @Delete
    void eliminarTarefa(Tarefa tarefa);
}
