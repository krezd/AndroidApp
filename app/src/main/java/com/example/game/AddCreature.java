package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class AddCreature extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton monster;
    private RadioButton player;
    private EditText name;
    private EditText attackPoint;
    private EditText protectPoint;
    private EditText healthPoint;
    private EditText minDamage;
    private EditText maxDamage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_creature);
        radioGroup = findViewById(R.id.creature);
        player = findViewById(R.id.Player);
        monster = findViewById(R.id.Monster);
        name = findViewById(R.id.name);
        attackPoint = findViewById(R.id.attackPoint);
        protectPoint = findViewById(R.id.protectPoint);
        healthPoint = findViewById(R.id.healthPoint);
        minDamage = findViewById(R.id.minDamage);
        maxDamage = findViewById(R.id.maxDamage);
    }

    public void showInfo(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private ArrayList<Creature> creatures = new ArrayList<Creature>();

    public void createCreature(View view) {
        String nameText = name.getText().toString();
        String attackPointText = attackPoint.getText().toString();
        String protectPointText = protectPoint.getText().toString();
        String healthPointText = healthPoint.getText().toString();
        String minDamageText = minDamage.getText().toString();
        String maxDamageText = maxDamage.getText().toString();
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (nameText.equals("") || attackPointText.equals("") || protectPointText.equals("") || healthPointText.equals("") || minDamageText.equals("") || maxDamageText.equals("") || selectedId == -1) {
            showInfo("Не все поля заполнены");
        } else {
            showInfo("Существо создано");
            Intent intent = new Intent(this, MainActivity.class);
            if (selectedId == player.getId()) {
                Player player = new Player(nameText, Integer.parseInt(attackPointText), Integer.parseInt(protectPointText), Integer.parseInt(healthPointText), Integer.parseInt(minDamageText), Integer.parseInt(maxDamageText));
                creatures.add(player);

            } else {
                Monster monster = new Monster(nameText, Integer.parseInt(attackPointText), Integer.parseInt(protectPointText), Integer.parseInt(healthPointText), Integer.parseInt(minDamageText), Integer.parseInt(maxDamageText));
                creatures.add(monster);
            }
        }
    }

    public void goBack(View view) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Creature", creatures);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}