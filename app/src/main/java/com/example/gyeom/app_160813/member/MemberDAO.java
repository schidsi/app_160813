package com.example.gyeom.app_160813.member;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Member;
import java.util.ArrayList;
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
        String sql = "select " +
                String.format("%s, %s, %s, %s, %s, %s ",ID,PW,NAME,PHONE,EMAIL,ADDR) +
                " from member " +
                String.format("where id = '%s';", id);
        SQLiteDatabase db = this.getReadableDatabase();
        MemberBean result = new MemberBean();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToNext()){
            result.setId(cursor.getString(0));
            result.setPw(cursor.getString(1));
            result.setName(cursor.getString(2));
            result.setPhone(cursor.getString(3));
            result.setEmail(cursor.getString(4));
            result.setAddr(cursor.getString(5));
        }else{
            result.setId("NONE"); // findById는 Id만 검색하므로 Id만 None으로 변경하면 됨 (다른 다섯개는 안해도 됨)
        }
        return result;
    }

    public int count() // 전체 회원 수 조회
    {
        return 0;
    }

    public ArrayList<MemberBean> list() // 전체 조회
    {
        String sql = "select " +
                String.format("%s, %s, %s, %s, %s, %s ",ID,PW,NAME,PHONE,EMAIL,ADDR) +
                " from member; ";
        ArrayList<MemberBean> temp = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            Log.d("목록조회", ": 성공 !!");
            cursor.moveToFirst(); // Select로 여러개의 행 나왔을 때, 처음의 행으로 이동해야 함
        }
        do {
            MemberBean result = new MemberBean();
            result.setId(cursor.getString(0));
            result.setPw(cursor.getString(1));
            result.setName(cursor.getString(2));
            result.setPhone(cursor.getString(3));
            result.setEmail(cursor.getString(4));
            result.setAddr(cursor.getString(5));
            temp.add(result);

        } while (cursor.moveToNext());
        return temp;
    }

    public ArrayList<MemberBean> findByName(String name) // 이름으로 검색(중복이름 있을 수 있으므로 List)
    {

        String sql = "select " +
                String.format("%s, %s, %s, %s, %s ",ID,NAME,PHONE,EMAIL,ADDR) +
                " from member " +
                " where name like '%" + name +"%';";

        ArrayList<MemberBean> temp = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            Log.d("이름으로조회", ": 성공 !!");
            cursor.moveToFirst(); // Select로 여러개의 행 나왔을 때, 처음의 행으로 이동해야 함
        }
        do {
            MemberBean result = new MemberBean();
            result.setId(cursor.getString(0));
            result.setName(cursor.getString(1));
            result.setPhone(cursor.getString(2));
            result.setEmail(cursor.getString(3));
            result.setAddr(cursor.getString(4));
            temp.add(result);

        } while (cursor.moveToNext());
        return temp;

    }

    // Update
    public void update(MemberBean member) {
        Log.d("Update 진입", member.getPw());
        String sql = "update member " +
                String.format("set pw = '%s', email = '%s', addr = '%addr' where id = '%s'",
                        member.getPw(),
                        member.getEmail(),
                        member.getAddr(),
                        member.getId());
        SQLiteDatabase db = this.getWritableDatabase(); // this = SQLiteOpenHelper
        db.execSQL(sql);
        db.close(); // commit을 위한 메소드

    }

    // Delete
    public void delete(String id) {
        Log.d("DELETE 진입", id);
        String sql = "delete from member where id = '" + id + "';";
        SQLiteDatabase db = this.getWritableDatabase(); // this = SQLiteOpenHelper
        db.execSQL(sql);
        db.close(); // commit을 위한 메소드

    }


}
