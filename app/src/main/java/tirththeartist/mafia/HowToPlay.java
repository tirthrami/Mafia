package tirththeartist.mafia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by tirthrami on 8/23/16.
 */
public class HowToPlay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.play_howto);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/script.ttf");
        Button intro,set,night,day;

        intro = (Button) findViewById(R.id.introduction);
        set = (Button) findViewById(R.id.setup);
        night = (Button) findViewById(R.id.night);
        day = (Button) findViewById(R.id.days);

        intro.setTypeface(myTypeface);
        set.setTypeface(myTypeface);
        night.setTypeface(myTypeface);
        day.setTypeface(myTypeface);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RulesDisplay.class);
                intent.putExtra("SECTION", "INTRO");
                startActivity(intent);
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RulesDisplay.class);
                intent.putExtra("SECTION","SETUP");
                startActivity(intent);
            }
        });

        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RulesDisplay.class);
                intent.putExtra("SECTION","NIGHT");
                startActivity(intent);
            }
        });

        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RulesDisplay.class);
                intent.putExtra("SECTION","DAY");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        LinearLayout layout = (LinearLayout) findViewById(R.id.instruct);
        layout.setBackgroundDrawable(null);
        setContentView(new View(this));
    }
}
