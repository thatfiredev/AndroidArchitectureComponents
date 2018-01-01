package io.github.rosariopfernandes.aac;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private RecyclerView rv;
    private TarefaAdapter adapter;
    private List<Tarefa> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        rv = findViewById(R.id.rv);

        tarefas = new ArrayList<>();

        getLifecycle().addObserver(new LifecycleObserver() {
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            void resumiu()
            {
                Toast.makeText(MainActivity.this,
                        "Activity resumida", Toast.LENGTH_SHORT).show();
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            void terminou()
            {
                Toast.makeText(MainActivity.this,
                        "Activity terminou", Toast.LENGTH_SHORT).show();
                getLifecycle().removeObserver(this);
            }
        });

        ViewModelProviders.of(this)
                            .get(TarefaModel.class)
                            .getTarefas()
                            .observe(this, new Observer<List<Tarefa>>() {
                                @Override
                                public void onChanged(@Nullable List<Tarefa> tarefas) {
                                    //Colocar as tarefas na tela do utilizador
                                    adapter.setList(tarefas);
                                    rv.getAdapter().notifyDataSetChanged();
                                }
                            });

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TarefaAdapter(tarefas);
        rv.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TarefaDialog dialog = new TarefaDialog();
                dialog.show(getFragmentManager(), "CriarTarefa");
            }
        });

    }


}
