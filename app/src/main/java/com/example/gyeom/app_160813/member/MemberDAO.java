package com.example.gyeom.app_160813.member;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;


// Database handling
public class MemberDAO extends SQLiteOpenHelper {

    public static final String ID = "id"; // 상수값은 보통 대문자로
    public static final String PW = "pw";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String ADDR = "addr";

    public MemberDAO(Context context) {
        super(context, "hanbitdb", null, 1);
        this.getWritableDatabase(); // this = MemberDAO뿐 아니라 상속받은 SQLiteOpenHelper도 가르킴, Wirtable = 편집 가능한 데이터 베이스 호출
        // 내장 DB를 사용하므로(SQLite) 단순함
        Log.d("Dao 진입 여부", "==== OK ====");
    }

    public MemberBean login(MemberBean member){
        Log.d("DAO:LOGIN - ID 체크",member.getId());
        MemberBean result = new MemberBean();
        SQLiteDatabase db = this.getReadableDatabase(); // this = SQLiteOpenHelper
        Cursor cursor = db.rawQuery("select id, pw from member where id = '" + member.getId() + "';", null); // cursor는 테이블처럼 생긴 구조

        if(cursor.moveToNext()) { // cursor안에 있는 내용을 다음으로 넘겼는데 있다면 true
            result.setId(cursor.getString(0)); // ()안에 들어가는 숫자는 select로 가져온 애들의 순서, 즉 id가 먼저 들어오고 pw가 두 번째에 있음
            result.setPw(cursor.getString(1));
            Log.d("커서 내부 아이디 체크",result.getId());
        }else{
            result.setId("None");
            Log.d("커서 내부 아이디 체크2",result.getId());
        }
        return result;

    }
    public void join(MemberBean member){
        Log.d("DAO:JOIN - ID 체크",member.getId());

        String sql = "insert into " +
                String.format("member(%s, %s, %s, %s, %s, %s) ",ID,PW,NAME,PHONE,EMAIL,ADDR) +
                String.format("values('%s','%s','%s','%s','%s','%s');",
                        member.getId(), member.getPw(), member.getName(), member.getPhone(), member.getEmail(), member.getAddr());
        SQLiteDatabase db = this.getWritableDatabase(); // this = SQLiteOpenHelper
        db.execSQL(sql);
        db.close(); // commit을 위한 메소드
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // id,pw,name,phone,email,addr
        db.execSQL("create table if not exists member(" +
                " id text primary key," +
                " pw text," +
                " name text," +
                " phone text," +
                " email text," +
                " addr text);");
        db.execSQL("insert into member(id,pw,name,phone,email,addr) " +
                "values('hong','1','gildong','010-7294-5104','schidsi@naver.com','37.4515410,127.1598210');");
        // 위도 경도는 http://mygeoposition.com/ 에서 가져옴, 나중에는 플러그인으로 사용하면 됨
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists member;");
        this.onCreate(db);
    }

    public MemberBean findById(String id) // 아이디 조회
    {
        return null;
    }

    public int count() // 전체 회원 수 조회
    {
        return 0;
    }

    public List<MemberBean> list() // 전체 조회
    {
        return null;
    }

    public List<MemberBean> findByName(String name) // 이름으로 검색(중복이름 있을 수 있으므로 List)
    {
        return null;
    }

    // Update
    public void update(MemberBean member) {

    }

    // Delete
    public void delete(String id) {

    }


}
