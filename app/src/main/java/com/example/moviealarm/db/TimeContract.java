package com.example.moviealarm.db;

import android.provider.BaseColumns;

public class TimeContract {
    public static final String DB_NAME="com.aziflaj.todolist.db";
    public static final int DB_VERSION=1;

    public class TimeEntry implements BaseColumns{
        public static final String TABLE="times";
        public static final String COL_TASK_TITLE="title";
    }
}
