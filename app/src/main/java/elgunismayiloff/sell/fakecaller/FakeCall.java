package elgunismayiloff.sell.fakecaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class FakeCall extends AppCompatActivity {

    EditText number, name;

    /////////////////////////////////Elgun Ismayiloff
    //Bu yazilar siline biler
    //Sadece front-a el gezdirmek lazimdir. Zeng qebul olunanda intent ile yeni activity-e kecid etmek olar
    ///Problem yaranarsa insta @elgunismayiloff elaqe saxlayin.
    //////Bu yazilima tam istifade huquqi verilir. Istediyiniz kimi duzelisler ede bilersiniz.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_call);
        setTitle("Fake Call");

        Button call   = (Button)findViewById(R.id.BtnCall);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetEditText();
                if (Validation()){
                    String FakeNumber = number.getText().toString();
                    String FakeName = name.getText().toString();
                    FakeRing(FakeNumber, FakeName);
                }
            }
        });
    }

    private void FakeRing(String fakeNumber, String fakeName) {
        Intent FakeRing = new Intent(FakeCall.this, FakeCallRinging.class);
        FakeRing.putExtra("fakeNumber", fakeNumber);
        FakeRing.putExtra("fakeName", fakeName);
        startActivity(FakeRing);
    }



    private Boolean Validation() {
        Boolean result = true;

        if (number.getText().length()  == 0 || name.getText().length() == 0) {
            number.setError("Enter Number to call");
            name.setError("Enter Name to call");
            return false;
        }
        return result;
    }

    private void SetEditText() {
        number = (EditText) findViewById(R.id.TxtNumber);
        name = (EditText) findViewById(R.id.TxtName);
    }
}
