package com.icia.shop.repository;

import com.icia.shop.dto.MemberDTO;
import com.icia.shop.dto.MemberFileDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @Autowired
    private SqlSessionTemplate sql;


    public MemberDTO save(MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO +"repository");
        sql.insert("Member.save", memberDTO);
        System.out.println("memberDTO = " + memberDTO +"repository2");
        return memberDTO;
    }

    public void saveFile(MemberFileDTO memberFileDTO) {
        sql.insert("Member.saveFile", memberFileDTO);
    }
}
