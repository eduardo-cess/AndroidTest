package com.example.cess.myapplication;

import android.provider.BaseColumns;

/**
 * Created by cess on 07/12/16.
 */

public class DaoFraseContract {
    private DaoFraseContract() {}

    /* Inner class that defines the table contents */
    public static class FraseEntry implements BaseColumns {
        public static final String TABLE_NAME = "frase";
        public static final String COLUMN_CONTEUDO = "conteudo";
        public static final String COLUMN_DATE = "date";
    }

}
