package tw.com.chainsea.call_exp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tw.com.chainsea.call.SipCallManager;

/**
 * MainActivity
 * Created by andy on 16-8-18.
 */
public class MainActivity extends AppCompatActivity implements SipCallManager.Listener, View.OnClickListener {
    private EditText mTo;
    private Button mCallButton, mHangUpBtn;
    private TextView mStatus;
    private SipCallManager callManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_main);
        callManager = new SipCallManager(this, "127.0.0.1", 5035);
        mTo = (EditText) findViewById(R.id.call_to);
        mCallButton = (Button) findViewById(R.id.call_button);
        mHangUpBtn = (Button) findViewById(R.id.hang_up);
        mStatus = (TextView) findViewById(R.id.status);
        callManager.setUserInfo("1101120", "1234");
        callManager.enableLog();
        mCallButton.setOnClickListener(this);
        mHangUpBtn.setOnClickListener(this);

    }

    @Override
    public void onCalling() {
        mStatus.setText("calling");
    }

    @Override
    public void onCallEnd() {
        mStatus.setText("hang up");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call_button:
                String number = mTo.getText().toString();
                callManager.startCall(number, this);
                break;
            case R.id.hang_up:
                callManager.hangUp();
                break;
        }
    }
}
