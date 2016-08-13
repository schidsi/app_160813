package com.example.gyeom.app_160813.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gyeom.app_160813.R;
import com.example.gyeom.app_160813.mypage.MyPageActivity;
import com.example.gyeom.app_160813.util.CalcActivity;

public class LoginActivity extends Activity implements View.OnClickListener {
    EditText et_id,et_pw;
    Button bt_login,bt_join;
    MemberService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        service = new MemberServiceImpl( this.getApplicationContext());
        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_pw);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_join = (Button) findViewById(R.id.bt_join);
        bt_login.setOnClickListener(this);
        bt_join.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login :
                String id = et_id.getText().toString();
                String pw = et_pw.getText().toString();

                MemberBean temp = new MemberBean();
                temp.setId(id);
                temp.setPw(pw);
                MemberBean result  = service.login(temp);
                Toast.makeText(LoginActivity.this," result " + result.getId() + " " + result.getPw(),Toast.LENGTH_LONG).show();


                if(result.getId().equals("None")){
                    Toast.makeText(LoginActivity.this,"ID없음",Toast.LENGTH_LONG).show();
                }else {
                    if(pw.equals(result.getPw())){
                        startActivity(new Intent(this.getApplicationContext(),MyPageActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this,"비밀번호 틀림",Toast.LENGTH_LONG).show();
                    }
                }


                break;
            case R.id.bt_join :
                startActivity(new Intent(this.getApplicationContext(),JoinActivity.class));
                break;
        }
    }
}
