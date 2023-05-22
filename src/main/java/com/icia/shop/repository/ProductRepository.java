package com.icia.shop.repository;

import com.icia.shop.dto.ProductDTO;
import com.icia.shop.dto.ProductFileDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    @Autowired
    private SqlSessionTemplate sql;

    public ProductDTO save(ProductDTO productDTO) {
        sql.insert("Product.save", productDTO);
        return productDTO;
    }

    public void saveFile(ProductFileDTO productFileDTO) {
        sql.insert("Product.saveFile", productFileDTO);
    }

    public List<ProductDTO> findAll() {
        return sql.selectList("Product.findAll");
    }

    public List<ProductDTO> pagingList(Map<String, Integer> pagingParams) {
        return sql.selectList("Product.paging", pagingParams);
    }

    public int boardCount() {
        return sql.selectOne("Product.count");
    }

    public void updateHits(Long id) {
        sql.update("Product.updateHits", id);
    }

    public ProductDTO findById(Long id) {
        return sql.selectOne("Product.findById", id);
    }

    public List<ProductFileDTO> findFile(Long memberId) {
        return sql.selectList("Product.findFile", memberId);
    }

    public void update(ProductDTO productDTO) {
        sql.update("Product.update", productDTO);
    }

    public void delete(ProductDTO productDTO) {
        sql.delete("Product.delete",productDTO);
    }

    public List<ProductDTO> searchList(Map<String, Object> pagingParams) {
        return sql.selectList("Product.search", pagingParams);
    }

    public int productSearchCount(Map<String, Object> pagingParams) {
        return sql.selectOne("Product.searchCount", pagingParams);
    }
}
