package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private TextView textBar;
    private TextView choose1;
    private TextView choose2;
    private Button attackButton;
    private Button healButton;
    private ImageButton addButton;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    private ListView listPlayers;
    private ListView listMonsters;
    ArrayList<String> namePlayers;
    ArrayList<String> nameMonsters;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    ScrollView scrollView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBar = findViewById(R.id.textBar);
        choose1 = findViewById(R.id.choose1);
        choose2 = findViewById(R.id.choose2);
        addButton = findViewById(R.id.addButton);
        listPlayers = (ListView) findViewById(R.id.players);
        listMonsters = (ListView) findViewById(R.id.monsters);
        listPlayers.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listMonsters.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        attackButton = findViewById(R.id.attackButton);
        healButton = findViewById(R.id.healButton);
        addButton = findViewById(R.id.addButton);
        scrollView = findViewById(R.id.scrollView2);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                ArrayList<Creature> creatures = (ArrayList<Creature>) data.getSerializableExtra("Creature");
                if (creatures != null) {
                    for (Creature c : creatures) {
                        if (c instanceof Player) {
                            players.add((Player) c);
                        } else {
                            monsters.add((Monster) c);
                        }
                    }

                    namePlayers = new ArrayList<String>();
                    for (Player p : players) {
                        namePlayers.add(p.getName());
                    }
                    nameMonsters = new ArrayList<String>();
                    for (Monster p : monsters) {
                        nameMonsters.add(p.getName());
                    }
                    adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, namePlayers);
                    adapter2 = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, nameMonsters);
                    listPlayers.setAdapter(adapter);
                    listMonsters.setAdapter(adapter2);
                    choose1.setText("Выберите игрока");
                    choose2.setText("Выберите монстра");

                }
            }
        }
    }

    public void attackCreature(View view) {
        int playerId = listPlayers.getCheckedItemPosition();
        int monsterId = listMonsters.getCheckedItemPosition();
        if (playerId == -1 || monsterId == -1) {
            showInfo("Выберите существ или добавьте их через знак плюса");
        } else {
            textBar.append(players.get(playerId).hit(monsters.get(monsterId)));
            cheсkDeadMonster(monsters.get(monsterId),monsterId);
            textBar.append(monsters.get(playerId).hit(players.get(monsterId)));
            cheсkDeadPlayer(players.get(playerId),playerId);
        }
        scrollDown();

    }

    private void cheсkDeadPlayer(Player player, int id) {
        if (!player.isAlive()) {
            players.remove(id);
            namePlayers.remove(id);
            adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, namePlayers);
            listPlayers.setAdapter(adapter);
        }
    }

    private void cheсkDeadMonster(Monster monster, int id) {
        if (!monster.isAlive()) {
            monsters.remove(id);
            nameMonsters.remove(id);
            adapter2 = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, nameMonsters);
            listMonsters.setAdapter(adapter2);
        }
    }

    public void healPlayer(View view) {

        int playerId = listPlayers.getCheckedItemPosition();
        if (playerId == -1) {
            showInfo("Выберите игрока или добавьте его через знак плюса");
        } else {
            textBar.append(players.get(playerId).healing());
        }
        scrollDown();
    }


    public void showInfo(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void openAddCreature(View view) {
        Intent intent = new Intent(this, AddCreature.class);
        startActivityForResult(intent, 1);

    }
    public void scrollDown(){
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

}
