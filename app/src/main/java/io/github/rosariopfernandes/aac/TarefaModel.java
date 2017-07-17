package io.github.rosariopfernandes.aac;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rosariopfernandes on 6/9/17.
 */

public class TarefaModel extends AndroidViewModel {
    private LiveData<List<Tarefa>> tarefas;
    private BaseDados bd;

    public TarefaModel(Application application)
    {
        super(application);
        bd = BaseDados.getDatabase(application.getApplicationContext());
        carregarDados();
    }

    public LiveData<List<Tarefa>> getTarefas() {
        if(tarefas == null) {
            //Transformations.map()
            tarefas = Transformations.map(tarefas, new Function<List<Tarefa>,
                    List<Tarefa>>() {
                @Override
                public List<Tarefa> apply(List<Tarefa> novasTarefas) {
                    novasTarefas = new ArrayList<>();
                    novasTarefas.add(new Tarefa( "Bem-vindo",
                            "Bem-vindo ao mundo dos Architecture Components"));
                    return novasTarefas;
                }
            });
        }
        return tarefas;
    }

    private void carregarDados()
    {
        //Carregar os dados da nossa Base de dados e armazenar no LiveData
        tarefas = bd.tarefaDao().lerTarefas();
    }
}
