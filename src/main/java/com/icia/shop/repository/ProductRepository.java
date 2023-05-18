package com.icia.shop.repository;

import com.icia.shop.dto.ProductDTO;
import com.icia.shop.dto.ProductFileDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
