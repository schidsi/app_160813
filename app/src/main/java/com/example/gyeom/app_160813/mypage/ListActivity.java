package com.example.gyeom.app_160813.mypage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gyeom.app_160813.R;
import com.example.gyeom.app_160813.member.AddActivity;
import com.example.gyeom.app_160813.member.LoginActivity;
import com.example.gyeom.app_160813.member.MemberBean;
import com.example.gyeom.app_160813.member.MemberService;
import com.example.gyeom.app_160813.member.MemberServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends Activity implements View.OnClickListener {

    EditText et_search;
    Button bt_mypage, bt_findByName, bt_findById, bt_add;
    ListView lv_memberlist;
    MemberService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        service = new MemberServiceImpl(this.getApplicationContext());
        ArrayList<MemberBean> list = service.list();

        et_search = (EditText) findViewById(R.id.et_search);
        bt_mypage = (Button) findViewById(R.id.bt_mypage);
        bt_findByName = (Button) findViewById(R.id.bt_findByName);
        bt_findById = (Button) findViewById(R.id.bt_findById);
        bt_add = (Button) findViewById(R.id.bt_add);
        lv_memberlist = (ListView) findViewById(R.id.lv_memberlist);
        lv_memberlist.setAdapter(new MemberAdapter(this, list)); // context = 현재 Activity의 모든 정보를 넘김
        lv_memberlist.setOnItemClickListener(new AdapterView.OnItemClickListener() { // ListView는 하나의 ID에 여러개의 사진이 있으므로, ItemClickListener, Click이 하나므로 inner에 바로 Click 선언
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = lv_memberlist.getItemAtPosition(position);
                MemberBean member = (MemberBean) o;
                Log.d("선택한 이름", o.toString());
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("id", member.getId());
                startActivity(intent);

            }
        });

        bt_mypage.setOnClickListener(this);
        bt_findByName.setOnClickListener(this);
        bt_findById.setOnClickListener(this);
        bt_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String keyword = et_search.getText().toString();
        MemberBean member = service.findById(keyword);

        switch(v.getId()){
            case R.id.bt_mypage:
                String id = getIntent().getStringExtra("id");
                Intent intent = new Intent(this.getApplicationContext(), MyPageActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;

            case R.id.bt_findByName:
                if(keyword.equals("")){
                    Toast.makeText(ListActivity.this,"검색어를 입력하세요", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ListActivity.this,"검색어 : " + keyword ,Toast.LENGTH_LONG).show();

                    List<MemberBean> list = service.findByName(keyword);
                    Intent Searchintent = new Intent(this.getApplicationContext(), SearchActivity.class);
                    Searchintent.putExtra("keyword", keyword);
                    startActivity(Searchintent);

                }
                break;

            case R.id.bt_findById:
                if(keyword.equals("")){
                    Toast.makeText(ListActivity.this,"검색어를 입력하세요", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ListActivity.this,"검색어 : " + keyword ,Toast.LENGTH_LONG).show();
                    if(member.getId().equals("None")){
                        Toast.makeText(ListActivity.this,"해당 아이디가 존재하지 않습니다." ,Toast.LENGTH_LONG).show();
                    }else{
                       Intent Myintent = new Intent(this.getApplicationContext(), DetailActivity.class);
                        Myintent.putExtra("id", member.getId());
                        startActivity(Myintent);
                    }
                }
                break;
            case R.id.bt_add:
                startActivity(new Intent(this.getApplicationContext(), AddActivity.class));
                break;
        }

    }
}
