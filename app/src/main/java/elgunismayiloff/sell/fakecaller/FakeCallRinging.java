package elgunismayiloff.sell.fakecaller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class FakeCallRinging extends AppCompatActivity {

    private String networkCarrier;
    private MediaPlayer ringTone;
    TextView titleBar;
    TextView fakeNumber, fakeName;
    ImageButton answerCall;
    ImageButton rejectCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_call_ringing);

        InitialiseControl();

        GetNetworkOperatorName();
        AssignFakeNumberAndDisplay();
        StartRingTone();

        answerCall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ringTone.stop();
            }
        });

        rejectCall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ringTone.stop();
                Intent homeIntent= new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(homeIntent);
            }
        });

    }

    private void InitialiseControl() {
        fakeNumber = (TextView)findViewById(R.id.chosenfakenumber);
        fakeName = (TextView)findViewById(R.id.chosenfakename);
        answerCall = (ImageButton) findViewById(R.id.answercall);
        rejectCall = (ImageButton) findViewById(R.id.rejectcall);
    }

    private void StartRingTone() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        ringTone = MediaPlayer.create(getApplicationContext(), notification);
        ringTone.start();
    }

    private void AssignFakeNumberAndDisplay() {
        String number = getContactNumber();
        String name = getContactName();
        fakeNumber.setText(number);
        fakeName.setText(name);
    }

    private void GetNetworkOperatorName() {
        final TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        networkCarrier = tm.getNetworkOperatorName();

        titleBar = (TextView)findViewById(R.id.textView1);
        if(networkCarrier != null){
            titleBar.setText("Incoming call - " + networkCarrier);
        }else{
            titleBar.setText("Incoming call");
        }
    }

    private String getContactName(){
        String name = null;
        Intent myIntent = getIntent();
        Bundle mIntent = myIntent.getExtras();
        if(mIntent != null){
            name  = mIntent.getString("fakeName");
        }
        return name;
    }

    private String getContactNumber(){
        String contact = null;
        Intent myIntent = getIntent();
        Bundle mIntent = myIntent.getExtras();
        if(mIntent != null){
            contact  = mIntent.getString("fakeNumber");
        }
        return contact;
    }
}
