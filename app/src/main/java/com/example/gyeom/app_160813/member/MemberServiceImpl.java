package com.example.gyeom.app_160813.member;


import android.content.Context;
import android.util.Log;

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
    public void join(MemberBean member) {
        Log.d("서비스:JOIN - ID 체크",member.getId());
        dao.join(member);
    }
}


