package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;


public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_BAR_NOVO_ALUNO = "Novo Aluno";
    private static final String TITULO_BAR_EDITA_ALUNO = "Edita Aluno";
    private EditText campoNome;
//    private EditText campoSobrenome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private AlunoDAO dao;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        dao = database.getRoomAlunoDAO();

        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_BAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_BAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
//        campoSobrenome.setText(aluno.getSobrenome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
        } else {
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
//        campoSobrenome = findViewById(R.id.activity_formulario_aluno_sobrenome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }


    private void preencheAluno() {
        String nome = campoNome.getText().toString();
//        String sobrenome = campoSobrenome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
//        aluno.setSobrenome(sobrenome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}