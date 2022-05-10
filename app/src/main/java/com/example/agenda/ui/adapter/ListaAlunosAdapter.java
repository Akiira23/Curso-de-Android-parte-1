package com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.asynctask.BuscaPrimeiroTelefoneDoAluno;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final TelefoneDAO dao;

    public ListaAlunosAdapter(Context context) {
        dao = AgendaDatabase.getInstance(context).getTelefoneDAO();
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Aluno alunoDevolvido = alunos.get(i);
        vincula(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vincula(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNomeCompleto() + " " + aluno.dataFormatada());

        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        new BuscaPrimeiroTelefoneDoAluno(dao, telefone, aluno.getId()).execute();
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
    }

    public void atualiza (List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
