package com.icia.shop.controller;

import com.icia.shop.dto.MemberDTO;
import com.icia.shop.dto.PageDTO;
import com.icia.shop.dto.ProductDTO;
import com.icia.shop.service.InquireService;
import com.icia.shop.service.MemberService;
import com.icia.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProductService productService;

    @GetMapping("/productSave")
    public String productForm(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        System.out.println("상품등륵 버튼 memberDTO = " + memberDTO);
        model.addAttribute("member", memberDTO);
        return "productPages/Productsave";


    }

    @PostMapping("/productSave")
    public String productSave(HttpSession session, @ModelAttribute ProductDTO productDTO) throws IOException {
        String loginEmail = (String) session.getAttribute("loginEmail");
        System.out.println("여긴 찍히나 ? loginEmail = " + loginEmail);
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        productDTO.setMemberId(memberDTO.getId());
        System.out.println("야야야 productDTO = " + productDTO);
        productService.save(productDTO);
        System.out.println("여긴 session = " + session + ", productDTO = " + productDTO);
        System.out.println("productDTO = " + productDTO);
        return "redirect:/";
    }

    @GetMapping("/productList")
    public String productList(Model model) {
        List<ProductDTO> productDTOList = productService.findAll();
        model.addAttribute("productlist", productDTOList);
        return "productPages/ProductList";
    }

    @GetMapping("/paging")
    public String paging(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "q", required = false, defaultValue = "") String q,
            @RequestParam(value = "type", required = false, defaultValue = "boardTitle") String type,
            Model model) {
        System.out.println("페이징 page = " + page + ", q = " + q + ",type= " + type);
        List<ProductDTO> productDTOList = null;
        PageDTO pageDTO = null;

        //검색어 q값이 없으면 일반 페이징 처리를해라
        if (q.equals("")) {
            // 사용자가 요청한 페이지에 해당하는 글 목록 데이터
            // 사용자가 3페이지를 보고싶다 하면 3페이지에 해당하는 내용 목록들
            productDTOList = productService.pagingList(page);
            // 하단에 보여줄 페이지 번호 목록 데이터
            pageDTO = productService.pagingParam(page);

            //검색어 q값이 있으면 검색어가 포함된 페이징 처리를 해라
        } else {
            // 검색어가 있으면 검색어가 포함된 페이징 처리를 해라
            productDTOList = productService.searchList(page, type, q);
            pageDTO = productService.pagingSearchParam(page, type, q);
        }
        // 페이지에 들어가는 글 목록들
        model.addAttribute("productList", productDTOList);
        // 하단에 보여줄 페이지 목록들
        model.addAttribute("paging", pageDTO);
        model.addAttribute("q", q);
        model.addAttribute("type", type);
        return "productPages/ProductPaging";
    }
}
