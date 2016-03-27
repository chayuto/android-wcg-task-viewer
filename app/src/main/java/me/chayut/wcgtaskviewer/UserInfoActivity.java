package me.chayut.wcgtaskviewer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class UserInfoActivity extends AppCompatActivity {

    private final static String TAG = "UserInfoActivity";

    Button btnLogin;
    EditText etUsername,etCode;
    CheckBox cbRemember;

    SharedPreferences sharedPref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);


        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUsername = (EditText ) findViewById(R.id.etUsername);
        etCode = (EditText) findViewById(R.id.etCode);
        cbRemember = (CheckBox) findViewById(R.id.cbRemember);

        sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String username = sharedPref.getString(getString(R.string.username_key),"chayut_orapinpatipat");
        String code = sharedPref.getString(getString(R.string.verifyCode_key),"6be4a4bbf9a57b39ffd296f29e899309");

        etUsername.setText(username);
        etCode.setText(code);

    }

    public void onButtonClicked(View view){

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.username_key),etUsername.getText().toString());
        editor.putString(getString(R.string.verifyCode_key),etCode.getText().toString());
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(getString(R.string.username_key), etUsername.getText().toString());
        intent.putExtra(getString(R.string.verifyCode_key),etCode.getText().toString());
        startActivity(intent);

    }
}
