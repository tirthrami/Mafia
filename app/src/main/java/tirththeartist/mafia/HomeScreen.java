package tirththeartist.mafia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by tirthrami on 8/13/16.
 */
public class HomeScreen extends AppCompatActivity {
    private boolean howToSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.screen_home);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-1348715311045774/5287765841");
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/script.ttf");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button play = (Button) findViewById(R.id.play);
        final Button howTo = (Button) findViewById(R.id.howto);
        howTo.setTypeface(myTypeface);
        play.setTypeface(myTypeface);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                howToSelect = false;
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        howTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HowToPlay.class);
                startActivity(intent);
                howToSelect = true;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        ImageView imageView = (ImageView) findViewById(R.id.logo);
        LinearLayout layout = (LinearLayout) findViewById(R.id.bgmain);
        layout.setBackgroundDrawable(null);
        imageView.setImageDrawable(null);
        imageView.setBackgroundDrawable(null);
        setContentView(new View(this));

    }

}
