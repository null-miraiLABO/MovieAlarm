package com.example.moviealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moviealarm.db.TimeContract;
import com.example.moviealarm.db.TimeDbHelper;

import java.util.ArrayList;



public class Alarm extends AppCompatActivity {
    ListView listView;

    private static final Object Tag="Alarm";
    private TimeDbHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Button MakeTimerBt = findViewById(R.id.MakeTimerBt);
        MakeTimeLintener lintener = new MakeTimeLintener();
        MakeTimerBt.setOnClickListener(lintener);
        mHelper=new TimeDbHelper(this);
        mTaskListView = (ListView) findViewById(R.id.list_alarm);
        updateUI();
    }

    protected void onStart(){
        super.onStart();
        /*
        listView = (ListView) findViewById(R.id.list_alarm);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView listView = (ListView) adapterView;
                Intent intent = new Intent(Alarm.this, AlarmActivity2.class);
                intent.putExtra("FileName", (String) listView.getItemAtPosition(i));
                startActivity(intent);
            }
        });
        lodeList();

         */
    }

    private void updateUI(){
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TimeContract.TimeEntry.TABLE,
                new String[]{TimeContract.TimeEntry._ID,TimeContract.TimeEntry.COL_TASK_TITLE},
                null,null,null,null,null);
        while(cursor.moveToNext()){
            int idx = cursor.getColumnIndex(TimeContract.TimeEntry.COL_TASK_TITLE);
            taskList.add(cursor.getString(idx));
        }

        if (mAdapter==null){
            mAdapter=new ArrayAdapter<>(this,
                    R.layout.item_alarm,
                    R.id.time_title,
                    taskList);
            mTaskListView.setAdapter(mAdapter);
        }else{
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
        cursor.close();
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //項目の追加
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add_time:
                Log.d((String) Tag,"Todo App 項目の追加");

                final EditText taskEditText =new EditText(this);
                AlertDialog dialog = new  AlertDialog.Builder(this)
                        .setTitle("追加")
                        .setMessage("何する？")
                        .setView(taskEditText)
                        .setPositiveButton("追加", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task=String.valueOf(taskEditText.getText());
                                Log.d((String) Tag,"追加された項目: "+task);

                                SQLiteDatabase db=mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(TimeContract.TimeEntry.COL_TASK_TITLE,task);
                                db.insertWithOnConflict(TimeContract.TimeEntry.TABLE,
                                        null,
                                        values,
                                        SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                                updateUI();
                            }
                        })
                        //.setPositiveButton("追加",null)
                        .setNegativeButton("キャンセル",null)
                        .create();
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //タスクの消去
    public void deleteTask(View view){
        View patent=(View)view.getParent();
        TextView taskTextView=(TextView) patent.findViewById(R.id.task_title);
        String task=String.valueOf(taskTextView.getText());
        SQLiteDatabase db=mHelper.getWritableDatabase();
        db.delete(TimeContract.TimeEntry.TABLE,
                TimeContract.TimeEntry.COL_TASK_TITLE+" = ?",
                new String[]{task});
        db.close();
        updateUI();
    }

    public void clickTimer2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clickAlarm2(View view) {

    }

    private class MakeTimeLintener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            MakeTimeDialogFragment dialogFragment = new MakeTimeDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"MakeTimeDialogFragment");
        }
    }
}