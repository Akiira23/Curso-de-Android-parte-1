package com.example.agenda;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de alunos");
        setContentView(R.layout.activity_main);
        List<String> alunos = new ArrayList<>(Arrays.asList("Alex", "Fran", "José", "Maria", "Ana"));

        ListView listaDeAlunos = findViewById(R.id.activity_main_lista_de_alunos);
        listaDeAlunos.setAdapter(new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, alunos));
    }
}
