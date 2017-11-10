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
            numMafiaAdded, numDoctorAdded, numDetectiveAdded, numVillagerAdded;

    TextView numCount;
    Button show, next;
    ImageView myPlayer;
    private boolean storyteller = false, storytellerAdded = false;
    final int[] playerTypes = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Initial Setup of the Activity, such as storing data from previous activity, and instantiating objects
     */
    private void init() {
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
        playerTypes[4] = R.drawable.storyteller;

        //add 1 to numPlayers of storyteller is to be randomized
        if(storyteller) numPlayers++;
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
        show.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if (!myPlayer.getTag().equals(R.drawable.contenthidden)) {
                    Toast.makeText(PlayerAssign.this, "Press Next Player", Toast.LENGTH_SHORT).show();
                } else if (numClicked < numPlayers) {
                    numClicked++;
                    Log.d("numClicked", "" + numClicked);
                    if ((numClicked <= numPlayers)) numCount.setText("" + numClicked);

                    //TODO: use ArrayList and randomly choose from that instead of this algorithm
                    while (true) {
                        int randNum;
                        if (storyteller) randNum = (int) (Math.random() * 5);
                        else randNum = (int) (Math.random() * 4);
                        Log.d("randomNum", "" + randNum);

                        if (randNum == 0 && numMafia != numMafiaAdded) {
                            myPlayer.setImageResource(playerTypes[0]);
                            myPlayer.setTag(playerTypes[0]);
                            numMafiaAdded++;
                            break;

                        } else if (randNum == 1 && numDoctor != numDoctorAdded) {
                            myPlayer.setImageResource(playerTypes[1]);
                            myPlayer.setTag(playerTypes[1]);
                            numDoctorAdded++;
                            break;

                        } else if (randNum == 2 && numDetective != numDetectiveAdded) {
                            myPlayer.setImageResource(playerTypes[2]);
                            myPlayer.setTag(playerTypes[2]);
                            numDetectiveAdded++;
                            break;

                        } else if (randNum == 3 && numVillager != numVillagerAdded) {
                            myPlayer.setImageResource(playerTypes[3]);
                            myPlayer.setTag(playerTypes[3]);
                            numVillagerAdded++;
                            break;

                        } else if (randNum == 4 && !storytellerAdded) {
                            myPlayer.setImageResource(playerTypes[4]);
                            myPlayer.setTag(playerTypes[4]);
                            storytellerAdded = true;
                            break;
                        }

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
                if (numClicked <= numPlayers) {
                    myPlayer.setImageResource(R.drawable.contenthidden);
                    myPlayer.setTag(R.drawable.contenthidden);
                } else myPlayer.setImageResource(R.drawable.setupcomplete);
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

