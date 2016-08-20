package com.example.gyeom.app_160813.mypage;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gyeom.app_160813.R;
import com.example.gyeom.app_160813.member.MemberBean;
import com.example.gyeom.app_160813.member.MemberService;
import com.example.gyeom.app_160813.member.MemberServiceImpl;

public class UpdateActivity extends Activity implements View.OnClickListener {

    EditText et_pass, et_email, et_addr;
    Button bt_confirm, bt_cancel;

    MemberService service;
    MemberBean member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        service = new MemberServiceImpl(this.getApplicationContext());
        member = service.findById(getIntent().getStringExtra("id"));

        et_pass = (EditText) findViewById(R.id.et_pass);
        et_email = (EditText) findViewById(R.id.et_email);
        et_addr = (EditText) findViewById(R.id.et_addr);
        bt_confirm = (Button) findViewById(R.id.bt_confirm);
        bt_cancel = (Button) findViewById(R.id.bt_cancel);


        bt_confirm.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt_confirm:
                member.setPw(et_pass.getText().toString());
                member.setEmail(et_email.getText().toString());
                member.setAddr(et_pass.getText().toString());

                service.update(member);
                Intent intent = new Intent(UpdateActivity.this, MyPageActivity.class);
                intent.putExtra("id", member.getId() );
                break;
            case R.id.bt_cancel:
                Intent intent2 = new Intent(UpdateActivity.this, MyPageActivity.class);
                intent2.putExtra("id", member.getId() );
                break;

        }

    }
}
