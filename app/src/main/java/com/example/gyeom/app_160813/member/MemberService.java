package com.example.gyeom.app_160813.member;

import java.util.ArrayList;
import java.util.List;

// Data 추상화
public interface MemberService {
    // Create
    public void join(MemberBean member);

    // Read
    public MemberBean login(MemberBean member); // 로그인
    public MemberBean findById(String id); // 아이디 조회
    public int count(); // 전체 회원 수 조회
    public ArrayList<MemberBean> list(); // 전체 조회
    public List<MemberBean> findByName(String name); // 이름으로 검색(중복이름 있을 수 있으므로 List)

    // Update
    public void update(MemberBean member);

    // Delete
    public void delete(String id);
}
