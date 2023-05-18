package com.icia.shop.controller;

import com.icia.shop.dto.MemberDTO;
import com.icia.shop.dto.ProductDTO;
import com.icia.shop.service.InquireService;
import com.icia.shop.service.MemberService;
import com.icia.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProductService productService;

    @GetMapping("/productSave")
    public String productForm(HttpSession session, Model model){
        String loginEmail = (String)session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        System.out.println("상품등륵 버튼 memberDTO = " + memberDTO);
        model.addAttribute("member", memberDTO);
        return "productPages/Productsave";


    }
    @PostMapping("/productSave")
    public String productSave(HttpSession session, @ModelAttribute ProductDTO productDTO) throws IOException {
        String loginEmail = (String)session.getAttribute("loginEmail");
        System.out.println("여긴 찍히나 ? loginEmail = " + loginEmail);
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        productDTO.setMemberId(memberDTO.getId());
        System.out.println("야야야 productDTO = " + productDTO);
        productService.save(productDTO);
        System.out.println("여긴 session = " + session + ", productDTO = " + productDTO);
        System.out.println("productDTO = " + productDTO);
        return "redirect:/";
    }

}
