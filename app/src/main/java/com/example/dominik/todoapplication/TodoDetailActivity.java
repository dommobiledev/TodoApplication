package com.example.dominik.todoapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class TodoDetailActivity extends AppCompatActivity {

    private int mTodoIndex;
    private static final String TODO_INDEX = "com.example.dominik.todoIndex"
            , IS_TODO_COMPLETE = "com.example.dominik.isTodoComplete";

    public static Intent newIntent(Context packageContext, int todoIndex) {
        Intent intent = new Intent(packageContext, TodoDetailActivity.class);
        intent.putExtra(TODO_INDEX, todoIndex);
        return intent;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(TODO_INDEX, mTodoIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX, 0);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        int todoIndex = getIntent().getIntExtra(TODO_INDEX, 0);
        updateTextViewTodoDetail(todoIndex);

        CheckBox checkBoxIsComplete;
        checkBoxIsComplete = findViewById(R.id.checkBoxIsComplete);
        checkBoxIsComplete.setOnClickListener(mTodoListener);

    }

    private void updateTextViewTodoDetail(int mTodoIndex) {
        final TextView textViewTodoDetail;
        textViewTodoDetail = findViewById(R.id.textViewTodoDetail);

        Resources res = getResources();
        String[] todoDetails = res.getStringArray(R.array.todo_detail);
        textViewTodoDetail.setText(todoDetails[mTodoIndex]);
    }

    private View.OnClickListener mTodoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.checkBoxIsComplete:
                    CheckBox checkBoxIsComplete = findViewById(R.id.checkBoxIsComplete);
                    setIsComplete(checkBoxIsComplete.isChecked());
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    private void setIsComplete(boolean isChecked) {
        if(isChecked) {
            Toast.makeText(TodoDetailActivity.this, "Seems like it works.", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(TodoDetailActivity.this, "Just do it tomorrow!", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent();
        intent.putExtra(IS_TODO_COMPLETE, isChecked);
        setResult(RESULT_OK, intent);
    }
}
