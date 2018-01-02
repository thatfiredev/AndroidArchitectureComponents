package io.github.rosariopfernandes.aac;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by rosariopfernandes on 7/17/17.
 */

public class TarefaDialog extends DialogFragment {
    private AlertDialog.Builder builder;
    private EditText txtTitulo, txtTarefa;

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_tarefa, null);
        txtTitulo = v.findViewById(R.id.txtTitulo);
        txtTarefa = v.findViewById(R.id.txtTarefa);
        builder.setView(v);
        builder.setTitle(R.string.dialog_title);
        builder.setPositiveButton(R.string.button_adicionar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BaseDados db = BaseDados.getDatabase(getActivity().getApplicationContext());
                Tarefa tarefa = new Tarefa(txtTitulo.getText().toString(),
                        txtTarefa.getText().toString());
                if(!tarefa.getTarefa().equalsIgnoreCase(""))
                    new InsertAsyncTask(db).execute(tarefa);
            }
        });
        builder.setNegativeButton(R.string.button_cancelar, null);
        return builder.create();
    }

    private class InsertAsyncTask extends AsyncTask<Tarefa, Void, Void> {

        private BaseDados db;

        InsertAsyncTask(BaseDados appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Tarefa... params) {
            db.tarefaDao().criarTarefa(params[0]);
            return null;
        }

    }
}
