package com.example.gyeom.app_160813.member;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gyeom.app_160813.R;
import com.example.gyeom.app_160813.mypage.ListActivity;

import java.lang.reflect.Member;

public class AddActivity extends Activity implements View.OnClickListener {

    MemberService service;
    EditText et_name, et_phone, et_email, et_addr;
    Button bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        service = new MemberServiceImpl(this.getApplicationContext());

        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_email = (EditText) findViewById(R.id.et_email);
        et_addr = (EditText) findViewById(R.id.et_addr);

        bt_add = (Button) findViewById(R.id.bt_add);
        bt_add.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        MemberBean member = new MemberBean();
        member.setId(String.valueOf((Math.random() * 9999) + 1000)); // ID는 PK이기때문에 값이 들어가야함

        member.setName(et_name.getText().toString());
        member.setPhone(et_phone.getText().toString());
        member.setEmail(et_email.getText().toString());
        member.setAddr(et_addr.getText().toString());
        service.join(member);
        startActivity(new Intent(this.getApplicationContext(), ListActivity.class));
    }
}
