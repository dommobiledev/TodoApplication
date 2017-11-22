package com.example.dominik.todoapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class TodoActivity extends AppCompatActivity {

    private String[] mTodos;
    private int mTodoIndex = 0;
    private static final String TODO_INDEX = "todoIndex", TAG = "TodoActivity"
            , IS_TODO_COMPLETE = "com.example.dominik.isTodoComplete";
    private static final int IS_SUCCESS = 0;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "*^* I am onSaveInstanceState! ^*^");
        savedInstanceState.putInt(TODO_INDEX, mTodoIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX, 0);
        }

        super.onCreate(savedInstanceState);
        Log.d(TAG, "*^* I am onCreate! ^*^");
        setContentView(R.layout.activity_todo);

        final TextView textViewTodo;
        textViewTodo = findViewById(R.id.textViewTodo);

        Resources res = getResources();
        mTodos = res.getStringArray(R.array.todo);

        textViewTodo.setText(mTodos[mTodoIndex]);

        Button buttonNext, buttonPrev, buttonDetail;
        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTodoIndex = (mTodoIndex + 1) % mTodos.length;
                textViewTodo.setText(mTodos[mTodoIndex]);
            }
        });

        buttonPrev = findViewById(R.id.buttonPrev);
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTodoIndex == 0) // when button pressed, from first goes to last
                    mTodoIndex = mTodos.length;
                mTodoIndex -= 1;
                textViewTodo.setText(mTodos[mTodoIndex]);
            }
        });

        buttonDetail = findViewById(R.id.buttonTodoDetail);
        buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TodoDetailActivity.newIntent(TodoActivity.this, mTodoIndex);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == IS_SUCCESS) {
            if(intent != null) {
                boolean isTodoComplete = intent.getBooleanExtra(IS_TODO_COMPLETE, false);
                updateTodoComplete(isTodoComplete);
            }else {
                Toast.makeText(this, R.string.back_button_pressed, Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, R.string.request_code_mismatch, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTodoComplete(boolean is_todo_complete) {
        final TextView textViewTodo;
        textViewTodo = findViewById(R.id.textViewTodo);

        if(is_todo_complete) {
            //textViewTodo.setBackground(ContextCompat.getColor(this, R.color.backgroundSuccess));
            textViewTodo.setTextColor(ContextCompat.getColor(this, R.color.colorSuccess));
            setTextViewComplete("\u2713");
        }
    }

    private void setTextViewComplete(String message) {
        final TextView textViewComplete;
        textViewComplete = findViewById(R.id.textViewComplete);
        textViewComplete.setText(message);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "*^* I am onStart! ^*^");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "*^* I am onResume! ^*^");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "*^* I am onPause! ^*^");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "*^* I am onStop! ^*^");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "*^* I am onDestroy! ^*^");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "*^* I am onRestart! ^*^");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "*^* I am onRestoreInstanceState! ^*^");
    }
}
