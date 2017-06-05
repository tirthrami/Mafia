package tirththeartist.mafia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by tirthrami on 9/1/16.
 */
public class RulesDisplay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.display_rules);
        String data = null;
        TextView textView = (TextView) findViewById(R.id.rules_TV);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getString("SECTION");
        }

        switch (data){
            case "INTRO":
                textView.setText(R.string.intro);
                break;
            case "SETUP":
                textView.setText(R.string.setup);
                break;
            case "NIGHT":
                textView.setText(R.string.night);
                break;
            case "DAY":
                textView.setText(R.string.day);
                break;
        }

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LinearLayout layout = (LinearLayout) findViewById(R.id.displl);
        ScrollView tw = (ScrollView) findViewById(R.id.sv);
        TextView textView = (TextView) findViewById(R.id.rules_TV);
        textView.setText("");
        layout.setBackgroundDrawable(null);
        tw.setBackgroundDrawable(null);
        setContentView(new View(this));


    }
}
