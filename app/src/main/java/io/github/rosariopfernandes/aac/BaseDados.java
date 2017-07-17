package io.github.rosariopfernandes.aac;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by rosariopfernandes on 6/15/17.
 */

@Database(entities = {Tarefa.class}, version = 1)
public abstract class BaseDados extends RoomDatabase {

    private static BaseDados INSTANCE;

    public static BaseDados getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            BaseDados.class,
                            "arch_components_db")
                            .build();
        }
        return INSTANCE;
    }

    public abstract TarefaDAO tarefaDao();

}