package com.example.cess.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();

        DaoFrase daoFrase = new DaoFrase(this);
        final Cursor cursor = daoFrase.selectAll();

        ListView lista = (ListView) findViewById(R.id.listView);
        String[] nomeCampos = new String[] {"_id", DaoFraseContract.FraseEntry.COLUMN_CONTEUDO};
        int[] idViews = new int[] {R.id.idFrase, R.id.conteudo};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),R.layout.activity_display_message,cursor,nomeCampos,idViews, 0);

        lista.setAdapter(adaptador);
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

//        TextView textView = new TextView(this);
//        textView.setTextSize(60);
//        textView.setText(message+" hehe");
//        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
//        layout.addView(textView);


    }
}
