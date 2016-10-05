package com.runhuaoil.broadcastoffline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by RunHua on 2016/9/28.
 */

public class LoginActivity extends BaseActivity {

    private EditText edit_account;
    private EditText edit_psw;
    private  String account,password;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        Button button_login = (Button) findViewById(R.id.login);
        edit_account = (EditText) findViewById(R.id.account);
        edit_psw = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.check_box);

        Boolean isRemember = pref.getBoolean("isRemember", false);
        if (isRemember){
            checkBox.setChecked(true);
            edit_account.setText( pref.getString("account","") );
            edit_psw.setText( pref.getString("password","") );
        }


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = edit_account.getText().toString();
                password = edit_psw.getText().toString();
                Log.d("Test",account + " " + password);
                if (account.equals("admin") && password.equals("123456")){

                    editor = pref.edit();
                    if (checkBox.isChecked()){
                        editor.putBoolean("isRemember",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else {
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(intent);

                    finish();

                }else{
                    Toast.makeText(LoginActivity.this, "账号或密码错误，Try again", Toast.LENGTH_SHORT).show();
                    edit_account.setText("");
                    edit_psw.setText("");
                }
            }
        });

    }
}
