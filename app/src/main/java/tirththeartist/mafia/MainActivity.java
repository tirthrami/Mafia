package tirththeartist.mafia;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static java.lang.Double.*;

public class MainActivity extends AppCompatActivity {

    EditText players, mafias, detectives, doctors, villagers;
    Button begin, textBegin, computeGame;
    CheckBox storyteller;
    TextWatcher tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    private void init() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-1348715311045774/2194698646");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Typeface myTypeface;
        TextView myTextView;
        begin = (Button) findViewById(R.id.button);
        textBegin = (Button) findViewById(R.id.button2);
        computeGame = (Button) findViewById(R.id.computeGame);

        myTypeface = Typeface.createFromAsset(getAssets(), "fonts/script.ttf");
        myTextView = (TextView) findViewById(R.id.players);
        myTextView.setTypeface(myTypeface);
        myTextView = (TextView) findViewById(R.id.doctors);
        myTextView.setTypeface(myTypeface);
        myTextView = (TextView) findViewById(R.id.detectives);
        myTextView.setTypeface(myTypeface);
        myTextView = (TextView) findViewById(R.id.mafias);
        myTextView.setTypeface(myTypeface);
        myTextView = (TextView) findViewById(R.id.villagers);
        myTextView.setTypeface(myTypeface);
        myTextView = (TextView) findViewById(R.id.storyteller);
        myTextView.setTypeface(myTypeface);

        players = (EditText) findViewById(R.id.id_players);
        mafias = (EditText) findViewById(R.id.id_mafias);
        detectives = (EditText) findViewById(R.id.id_detectives);
        doctors = (EditText) findViewById(R.id.id_doctors);
        villagers = (EditText) findViewById(R.id.id_villagers);
        storyteller = (CheckBox) findViewById(R.id.id_storyteller);

        players.setTypeface(myTypeface);
        mafias.setTypeface(myTypeface);
        detectives.setTypeface(myTypeface);
        doctors.setTypeface(myTypeface);
        villagers.setTypeface(myTypeface);
        begin.setTypeface(myTypeface);
        textBegin.setTypeface(myTypeface);
        computeGame.setTypeface(myTypeface);

        tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (storyteller.isChecked())
                    players.setText("" + getTotalPlayers() + 1);
                else players.setText("" + getTotalPlayers());
            }
        };
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        init();
        players.setText("0");
        computeGame();
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();


        /*players.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!players.getText().toString().matches("")) {
                    computeGame();
                } else {
                    mafias.setText("");
                    detectives.setText("");
                    doctors.setText("");
                    villagers.setText("");
                }
            }
        });
        mafias.addTextChangedListener(tw);
        detectives.addTextChangedListener(tw);
        doctors.addTextChangedListener(tw);
        villagers.addTextChangedListener(tw); */

        computeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!players.getText().toString().matches(""))
                    computeGame();
                else Toast.makeText(getApplicationContext(),"Enter Number of Players First", Toast.LENGTH_SHORT).show();
            }
        });


        begin.setOnClickListener(new View.OnClickListener() {
            public int getMafia() {
                return (int) parseDouble(mafias.getText().toString());
            }

            public int getDoctor() {
                return (int) parseDouble(doctors.getText().toString());
            }

            public int getDetective() {
                return (int) parseDouble(detectives.getText().toString());
            }

            public int getVillager() {
                return (int) parseDouble(villagers.getText().toString());
            }

            @Override
            public void onClick(View view) {
                if(mafias.getText().toString().matches("") || mafias.getText().equals(null)){
                    Toast.makeText(MainActivity.this, "Press Compute Game", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (players.getText().toString().equals("") || getTotalPlayers() == 0) {
                    Toast.makeText(MainActivity.this, "How many players?", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(getBaseContext(), PlayerAssign.class);
                    intent.putExtra("PLAYER_COUNT", "" + getTotalPlayers());
                    intent.putExtra("MAFIA", "" + getMafia());
                    intent.putExtra("DOCTOR", "" + getDoctor());
                    intent.putExtra("DETECTIVE", "" + getDetective());
                    intent.putExtra("VILLAGER", "" + getVillager());
                    intent.putExtra("STORYTELLER", storyteller.isChecked());
                    startActivity(intent);
                    finish();
                }
            }
        });

        textBegin.setOnClickListener(new View.OnClickListener() {

            public int getMafia() {
                return (int) parseDouble(mafias.getText().toString());
            }

            public int getDoctor() {
                return (int) parseDouble(doctors.getText().toString());
            }

            public int getDetective() {
                return (int) parseDouble(detectives.getText().toString());
            }

            public int getVillager() {
                return (int) parseDouble(villagers.getText().toString());
            }

            @Override
            public void onClick(View view) {
                if(mafias.getText().toString().matches("") || mafias.getText().equals(null) ){
                    Toast.makeText(MainActivity.this, "Press Compute Game", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (players.getText().toString().equals("") || getTotalPlayers() == 0 || (int)(Double.parseDouble(mafias.getText().toString())) == 0) {
                    Toast.makeText(MainActivity.this, "Not a valid number of players", Toast.LENGTH_SHORT).show();
                } else {

                    //TODO: Change class to Auto Assign Game Mode
                    Intent intent = new Intent(getBaseContext(), PlayerAssign.class);
                    intent.putExtra("PLAYER_COUNT", "" + getTotalPlayers());
                    intent.putExtra("MAFIA", "" + getMafia());
                    intent.putExtra("DOCTOR", "" + getDoctor());
                    intent.putExtra("DETECTIVE", "" + getDetective());
                    intent.putExtra("VILLAGER", "" + getVillager());
                    intent.putExtra("STORYTELLER", storyteller.isChecked());
                    startActivity(intent);
                    finish();
                }
            }
        });


    }


    private int getTotalPlayers() {
        int numPlayers;
        numPlayers = (int) (parseDouble(mafias.getText().toString()) + parseDouble(detectives.getText().toString()) +
                parseDouble(doctors.getText().toString()) + parseDouble(villagers.getText().toString()));

        return numPlayers;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void computeGame() {

        double numPlayers = parseDouble(players.getText().toString());
        double numMafias;
        double numDoctors;
        double numDetectives;
        double numVillagers;

        if (storyteller.isChecked()) numPlayers -= 1;

        numVillagers = Math.ceil(numPlayers / 2);
        int others = (int) (numPlayers - numVillagers);
        numMafias = others * .65;
        numDetectives = others * .25;
        numDoctors = others * .1;

        numMafias = Math.floor(numMafias);
        numDoctors = Math.ceil(numDoctors);
        numDetectives = others - (numMafias + numDoctors);

        mafias.setText((int) numMafias + "");
        detectives.setText((int) numDetectives + "");
        doctors.setText((int) numDoctors + "");
        villagers.setText((int) numVillagers + "");


    }

    @Override
    protected void onStop() {
        super.onStop();
        LinearLayout layout = (LinearLayout) findViewById(R.id.bg);
        ImageView imageView = (ImageView) findViewById(R.id.imageView6);
        layout.setBackgroundDrawable(null);
        imageView.setImageDrawable(null);
        setContentView(new View(this));
    }

}
