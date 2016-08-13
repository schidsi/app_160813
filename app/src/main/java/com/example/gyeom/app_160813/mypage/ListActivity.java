package com.example.gyeom.app_160813.mypage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gyeom.app_160813.R;
import com.example.gyeom.app_160813.member.LoginActivity;
import com.example.gyeom.app_160813.member.MemberBean;
import com.example.gyeom.app_160813.member.MemberService;
import com.example.gyeom.app_160813.member.MemberServiceImpl;

import java.util.List;

public class ListActivity extends Activity implements View.OnClickListener {

    EditText et_search;
    Button bt_mypage, bt_findByName, bt_findById;
    MemberService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        service = new MemberServiceImpl(this.getApplicationContext());

        et_search = (EditText) findViewById(R.id.et_search);
        bt_mypage = (Button) findViewById(R.id.bt_mypage);
        bt_findByName = (Button) findViewById(R.id.bt_findByName);
        bt_findById = (Button) findViewById(R.id.bt_findById);

        bt_mypage.setOnClickListener(this);
        bt_findByName.setOnClickListener(this);
        bt_findById.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String keyword = et_search.getText().toString();

        switch(v.getId()){
            case R.id.bt_mypage:
                break;
            case R.id.bt_findByName:
                if(keyword.equals("")){
                    Toast.makeText(ListActivity.this,"검색어를 입력하세요", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ListActivity.this,"검색어 : " + keyword ,Toast.LENGTH_LONG).show();
                    List<MemberBean> list = service.findByName(keyword);
                }

                break;
            case R.id.bt_findById:
                if(keyword.equals("")){
                    Toast.makeText(ListActivity.this,"검색어를 입력하세요", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ListActivity.this,"검색어 : " + keyword ,Toast.LENGTH_LONG).show();
                    MemberBean member = service.findById(keyword);
                    if(member.getId().equals("None")){
                        Toast.makeText(ListActivity.this,"해당 아이디가 존재하지 않습니다." ,Toast.LENGTH_LONG).show();
                    }else{
                        Intent intent = new Intent(this.getApplicationContext(), DetailActivity.class);
                        intent.putExtra("id", member.getId());
                        startActivity(intent);
                    }
                }

                break;
        }

    }
}
