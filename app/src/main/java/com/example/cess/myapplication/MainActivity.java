package com.example.cess.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_MESSAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getListViewFrases();
    }



    public void sendMessage(View view) {
        //Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText1);
        String message = editText.getText().toString();
        if(message.length() < 1){
            Toast.makeText(this, "Escreve alguma coisa aê, vacilão!!", Toast.LENGTH_SHORT).show();
        }
        else {
            DaoFrase daoFrase = new DaoFrase(this);
            daoFrase.insertFrase(message);

            getListViewFrases();
            editText.setText("");
            //intent.putExtra(EXTRA_MESSAGE, message);
        }
        //startActivity(intent);
    }

    public void getListViewFrases(){
        DaoFrase daoFrase = new DaoFrase(this);

        final Cursor cursor = daoFrase.selectAll();

        ListView lista = (ListView) findViewById(R.id.listView);
        String[] nomeCampos = new String[] {DaoFraseContract.FraseEntry.COLUMN_CONTEUDO};
        int[] idViews = new int[] { R.id.conteudo};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),R.layout.activity_display_message,cursor,nomeCampos,idViews, 0);

        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                String codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                String conteudo = cursor.getString(cursor.getColumnIndexOrThrow("conteudo"));
                Intent intent = new Intent(MainActivity.this, MessagePage.class);
                intent.putExtra("id", codigo);
                intent.putExtra("conteudo", conteudo);
                startActivity(intent);
                finish();
            }
        });
    }

}
