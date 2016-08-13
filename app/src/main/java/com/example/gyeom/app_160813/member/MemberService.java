package com.example.gyeom.app_160813.member;

// Data 추상화
public interface MemberService {
    public MemberBean login(MemberBean member);
    public void join(MemberBean member);
}
