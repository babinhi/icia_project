package com.icia.shop.service;

import com.icia.shop.dto.CommentDTO;
import com.icia.shop.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void save(CommentDTO commentDTO) {
        commentRepository.save(commentDTO);
    }

    public List<CommentDTO> findAll(Long productId) {
        System.out.println("여기는 레포짓 productId = " + productId);
        return commentRepository.findAll(productId);
    }

    public void delete(Long id) {
        commentRepository.delete(id);
    }
}
