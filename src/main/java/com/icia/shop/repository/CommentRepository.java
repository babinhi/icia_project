package com.icia.shop.repository;

import com.icia.shop.dto.CommentDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {
    @Autowired
    private SqlSessionTemplate sql;
    public void save(CommentDTO commentDTO) {
        sql.insert("Comment.save", commentDTO);
    }

    public List<CommentDTO> findAll(Long productId) {
        System.out.println("여기는 서비스 productId = " + productId);
        return sql.selectList("Comment.findAll", productId);

    }

    public CommentDTO findById(Long id) {
        return sql.selectOne("Comment.findById",id);
    }

    public void delete(Long id) {
        sql.delete("Comment.delete", id);
    }
}
