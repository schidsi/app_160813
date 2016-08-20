package com.example.gyeom.app_160813.mypage;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.gyeom.app_160813.R;
import com.example.gyeom.app_160813.member.MemberBean;
import com.example.gyeom.app_160813.member.MemberService;
import com.example.gyeom.app_160813.member.MemberServiceImpl;

import java.util.ArrayList;

public class SearchActivity extends Activity implements View.OnClickListener {


    EditText et_search;
    Button bt_list;
    ListView lv_memberlist;
    MemberService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        service = new MemberServiceImpl(this.getApplicationContext());
        ArrayList<MemberBean> list = service.findByName(getIntent().getStringExtra("keyword"));

        bt_list = (Button)findViewById(R.id.bt_list);

        lv_memberlist = (ListView) findViewById(R.id.lv_memberlist);
        lv_memberlist.setAdapter(new MemberAdapter(this, list)); // context = 현재 Activity의 모든 정보를 넘김
        lv_memberlist.setOnItemClickListener(new AdapterView.OnItemClickListener() { // ListView는 하나의 ID에 여러개의 사진이 있으므로, ItemClickListener, Click이 하나므로 inner에 바로 Click 선언
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = lv_memberlist.getItemAtPosition(position);
                MemberBean member = (MemberBean) o;

                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra("id", member.getId());
                startActivity(intent);
            }
        });
        bt_list.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            startActivity(new Intent(SearchActivity.this, ListActivity.class));
    }
}
