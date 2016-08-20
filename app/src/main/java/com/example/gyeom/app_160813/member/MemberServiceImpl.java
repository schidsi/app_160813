package com.example.gyeom.app_160813.member;


import android.content.Context;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MemberServiceImpl implements MemberService{
    MemberDAO dao;

    public MemberServiceImpl(Context context) {
        this.dao = new MemberDAO(context);
    }
    @Override
    public MemberBean login(MemberBean member) {
        Log.d("서비스:LOGIN - ID 체크",member.getId());
        MemberBean result = dao.login(member); // 값이 바로 담기므로 new를 사용안함
        return result;

    }

    @Override
    public MemberBean findById(String id) {
        Log.d("서비스:findById",id);
        return dao.findById(id);
    }

    @Override
    public int count() {
        Log.d("서비스:count", "count");
        return dao.count();
    }

    @Override
    public ArrayList<MemberBean> list() {
        Log.d("서비스:list", "list");
        return dao.list();
    }

    @Override
    public ArrayList<MemberBean> findByName(String name) {
        Log.d("서비스:findByname","listname");
        return dao.findByName(name);
    }

    @Override
    public void update(MemberBean member) {
        Log.d("서비스:UPDATE","update");

    }

    @Override
    public void delete(String id) {
        Log.d("서비스:DELETE","delete");

    }

    @Override
    public void join(MemberBean member) {
        Log.d("서비스:JOIN", "join");
        dao.join(member);
    }
}


