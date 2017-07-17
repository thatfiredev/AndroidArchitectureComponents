package io.github.rosariopfernandes.aac;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rosariopfernandes on 7/17/17.
 */

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {
    private List<Tarefa> tarefas;

    public TarefaAdapter(List<Tarefa> tarefas)
    {
        this.tarefas = tarefas;
    }

    public void setList(List<Tarefa> tarefas){
        this.tarefas = tarefas;
    }

    @Override
    public TarefaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_tarefa, parent, false);
        return new TarefaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TarefaViewHolder holder, int i) {
        Tarefa tarefa = tarefas.get(i);
        holder.txtTitulo.setText(tarefa.getTitulo());
        holder.txtTarefa.setText(tarefa.getTarefa());
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitulo, txtTarefa;

        public TarefaViewHolder(View v){
            super(v);
            txtTitulo = (TextView) v.findViewById(R.id.txtTitulo);
            txtTarefa = (TextView) v.findViewById(R.id.txtTarefa);
        }
    }
}
