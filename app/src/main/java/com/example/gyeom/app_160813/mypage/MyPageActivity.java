package com.example.gyeom.app_160813.mypage;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyeom.app_160813.R;
import com.example.gyeom.app_160813.member.MemberBean;
import com.example.gyeom.app_160813.member.MemberService;
import com.example.gyeom.app_160813.member.MemberServiceImpl;

public class MyPageActivity extends Activity implements View.OnClickListener {
    ImageView iv_profile;
    TextView tv_name, tv_id, tv_phone, tv_email, tv_addr;
    Button bt_call, bt_map;

    MemberService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        service = new MemberServiceImpl(this.getApplicationContext());
        MemberBean member = service.findById(getIntent().getStringExtra("id"));

        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_addr = (TextView) findViewById(R.id.tv_addr);
        tv_name.setText(member.getName());
        tv_id.setText(member.getId());
        tv_phone.setText(member.getPhone());
        tv_email.setText(member.getEmail());
        tv_addr.setText(member.getAddr());



        bt_call = (Button) findViewById(R.id.bt_call);
        bt_map = (Button) findViewById(R.id.bt_map);

        bt_call.setOnClickListener(this);
        bt_map.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }
}
