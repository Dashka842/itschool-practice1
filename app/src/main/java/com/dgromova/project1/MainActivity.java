package com.dgromova.project1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private int score = 0;
    TextView Text;
    ListView List;
    ArrayAdapter<String> adapter;
    ArrayList<String> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Text = findViewById(R.id.Text);
        List = findViewById(R.id.List);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, history);
        List.setAdapter(adapter);

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("SAVE_SCORE", 0);
            Text.setText(String.valueOf(score));

            history.clear();
            ArrayList<String> savedHistory = savedInstanceState.getStringArrayList("SAVE_HISTORY");
            if (savedHistory != null) {
                history.addAll(savedHistory);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("SAVE_SCORE", score);

        outState.putStringArrayList("SAVE_HISTORY", history);
    }
    public void Reset(View view) {
        Text.setText(String.valueOf(score));
        history.clear();
        score = 0;
        Text.setText(String.valueOf(score));
        adapter.notifyDataSetChanged();
    }

    public void Minus(View view) {
        Text.setText(String.valueOf(score));
        history.add(Calendar.getInstance().getTime().toString() + " - Уменьшено с " + String.valueOf(score) + " до " + String.valueOf(score - 1));
        score--;
        Text.setText(String.valueOf(score));
        adapter.notifyDataSetChanged();
    }

    public void Plus(View view) {
        Text.setText(String.valueOf(score));
        history.add(Calendar.getInstance().getTime().toString() + " - Увеличено с " + String.valueOf(score) + " до " + String.valueOf(score + 1));
        score++;
        Text.setText(String.valueOf(score));
        adapter.notifyDataSetChanged();
    }
}