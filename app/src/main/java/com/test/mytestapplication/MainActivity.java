package com.test.mytestapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    protected TextView chat;
    protected EditText message;
    protected Button send;
    protected ImageButton clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chat = (TextView) findViewById(R.id.chat);
        message = (EditText) findViewById(R.id.message);
        send = (Button) findViewById(R.id.send);
        clear = (ImageButton) findViewById(R.id.clear);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = message.getText().toString();
                message.setText("");
                chat.append("\n<< " + question);
                chat.append("\n>> " + AnswerQuestion(question));
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat.setText("");
            }
        });
    }

    protected String AnswerQuestion(String question)
    {
        question = question.toLowerCase();

        Map<String, String> questions = new HashMap<String, String>(){{
            put("как дела", "Шикарно");
            put("чем занимаешься", "Отвечаю на дурацкие вопросы");
            put("как тебя зовут", "Меня зовут Ассистентий");
            put("в чем смысл жизни", "Ответ находится вне моей компетенции");
        }};

        List<String> result = new ArrayList<>();

        for (String quest : questions.keySet()){
            if(question.contains(quest)){
                result.add(questions.get(quest));
            }
        }

        if(question.contains("сколько времени")){
            DateFormat fmt = new SimpleDateFormat("HH:mm:ss");
            result.add("Сейчас " + fmt.format(new Date()));
        }

        if(question.contains("какой сегодня день")){
            DateFormat fmt = new SimpleDateFormat("dd MMMM yyyy");
            result.add("Сегодня " + fmt.format(new Date()) + "г");
        }

        return TextUtils.join(", ", result) + ".";
    }
}
