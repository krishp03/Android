package chess53;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.chessandroid.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends Activity {
    public Button newButton, openButton, sortByDateButton, sortByNameButton;

    public Game selected;
    public ListView gameListView;
    public static List<Game> games = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        newButton = findViewById(R.id.newGameButton);
        openButton = findViewById(R.id.openGameButton);
        sortByNameButton = findViewById(R.id.sortByNameButton);
        sortByDateButton = findViewById(R.id.sortByDateButton);
        openButton.setEnabled(false);
        gameListView = findViewById(R.id.gameListView);
        gameListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        loadGames();


        newButton.setOnClickListener(v -> {
            Intent newGameIntent = new Intent(MainActivity.this, PlayActivity.class);
            startActivityForResult(newGameIntent, 1);
        });

        openButton.setOnClickListener(v -> {
            ReplayActivity.currReplay=selected;
            Intent replayIntent = new Intent(MainActivity.this, ReplayActivity.class);
            startActivity(replayIntent);
        });

        sortByDateButton.setOnClickListener(v -> {
            games.sort(Comparator.comparing(Game::getDate));
            gameListView.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, games));
        });
        sortByNameButton.setOnClickListener(v -> {
            games.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
            gameListView.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, games));
        });

        gameListView.setOnItemClickListener((parent, view, position, id) -> {
            if(position <0 || games.get(position)==null) {
                selected =null;
                return;
            }
            openButton.setEnabled(true);
            selected = games.get(position);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode==RESULT_OK){
            try {
                Game.save(MainActivity.this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadGames() {
        try {
            Game.load(this);
        } catch(Exception e) {
            e.printStackTrace();
        }

        gameListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, games));
    }

    protected void onStart() {

        super.onStart();

        loadGames();

        gameListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, games));
    }
    protected void onStop(){
        super.onStop();
        try {
            Game.save(this);
            Log.i("A", "Entered Save On Stop");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        try {
            Game.save(this);
            Log.i("A", "Entered Save on Destroy");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
