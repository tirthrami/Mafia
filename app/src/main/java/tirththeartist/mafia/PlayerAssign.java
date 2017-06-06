package tirththeartist.mafia;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by tirthrami on 8/3/16.
 */
public class PlayerAssign extends AppCompatActivity {
    private int numClicked, numPlayers, numMafia, numDoctor, numDetective, numVillager,
            numMafiaAdded,numDoctorAdded, numDetectiveAdded, numVillagerAdded;

    TextView numCount;
    Button show, next;
    ImageView myPlayer;
    private boolean storyteller;
    final int[] playerTypes = new int[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void init(){
        Typeface myTypeface;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString("PLAYER_COUNT");
            numPlayers = Integer.parseInt(data);
            numMafia = Integer.parseInt(extras.getString("MAFIA"));
            numDetective = Integer.parseInt(extras.getString("DETECTIVE"));
            numDoctor = Integer.parseInt(extras.getString("DOCTOR"));
            numVillager = Integer.parseInt(extras.getString("VILLAGER"));
            storyteller = extras.getBoolean("STORYTELLER");
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.assign_player);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-1348715311045774/6485297440");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        myTypeface = Typeface.createFromAsset(getAssets(), "fonts/script.ttf");
        myPlayer = (ImageView) findViewById(R.id.playerAssignImage);
        myPlayer.setTag(R.drawable.contenthidden);
        show = (Button) findViewById(R.id.show);
        next = (Button) findViewById(R.id.next);
        numCount = (TextView) findViewById(R.id.numcount);
        numCount.setTypeface(myTypeface);
        show.setTypeface(myTypeface);
        next.setTypeface(myTypeface);

        numClicked = 0;
        playerTypes[0] = R.drawable.mafia;
        playerTypes[1] = R.drawable.doctor;
        playerTypes[2] = R.drawable.detective;
        playerTypes[3] = R.drawable.villager;
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
        show.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                //TODO: Fix this algorithm to choose player with storyteller

                if(!myPlayer.getTag().equals(R.drawable.contenthidden)){
                    Toast.makeText(PlayerAssign.this, "Press Next Player", Toast.LENGTH_SHORT).show();
                } else if(numClicked <= numPlayers){
                    numClicked++;
                    Log.d("numClicked", "" + numClicked);
                    if((numClicked<=numPlayers))numCount.setText("" + numClicked);
                    int rand = (int) (Math.random()*100);
                    Log.d("rand",""+rand);
                    if(rand<25 && numMafia != numMafiaAdded){
                        myPlayer.setImageResource(playerTypes[0]);
                        myPlayer.setTag(playerTypes[0]);
                        numMafiaAdded++;
                        Toast.makeText(PlayerAssign.this, "Press Next Player before passing the phone.", Toast.LENGTH_SHORT).show();
                    }else if (rand<50 && numDoctor != numDoctorAdded){
                        myPlayer.setImageResource(playerTypes[1]);
                        myPlayer.setTag(playerTypes[1]);
                        numDoctorAdded++;
                        Toast.makeText(PlayerAssign.this, "Press Next Player before passing the phone.", Toast.LENGTH_SHORT).show();
                    } else if (rand<75 && numDetective !=numDetectiveAdded){
                        myPlayer.setImageResource(playerTypes[2]);
                        myPlayer.setTag(playerTypes[2]);
                        numDetectiveAdded++;
                        Toast.makeText(PlayerAssign.this, "Press Next Player before passing the phone.", Toast.LENGTH_SHORT).show();
                    }else if (rand<100 && numVillager != numVillagerAdded){
                        myPlayer.setImageResource(playerTypes[3]);
                        myPlayer.setTag(playerTypes[3]);
                        numVillagerAdded++;
                        Toast.makeText(PlayerAssign.this, "Press Next Player before passing the phone.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    myPlayer.setImageResource(R.drawable.setupcomplete);
                    myPlayer.setTag(R.drawable.setupcomplete);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numClicked <= numPlayers){
                    myPlayer.setImageResource(R.drawable.contenthidden);
                    myPlayer.setTag(R.drawable.contenthidden);
                }
                else myPlayer.setImageResource(R.drawable.setupcomplete);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        LinearLayout layout = (LinearLayout) findViewById(R.id.ll);
        ImageView imageView = (ImageView) findViewById(R.id.playerAssignImage);
        layout.setBackgroundDrawable(null);
        imageView.setImageDrawable(null);
        imageView.setBackgroundDrawable(null);
        setContentView(R.layout.screen_home);
    }

}

