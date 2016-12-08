package com.example.cess.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MessagePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_page);

        final String id = this.getIntent().getStringExtra("id");
        String conteudo = this.getIntent().getStringExtra("conteudo");
        EditText editText = (EditText) findViewById(R.id.conteudo);
        editText.setText(conteudo);
        Button b_alterar = (Button) findViewById(R.id.b_edit);
        Button b_deletar = (Button) findViewById(R.id.b_deletar);

        b_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DaoFrase daoFrase = new DaoFrase(getApplicationContext());
                daoFrase.deleteFrase(id);
                Intent intent = new Intent(MessagePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
