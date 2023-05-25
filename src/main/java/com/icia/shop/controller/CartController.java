package com.icia.shop.controller;

import com.icia.shop.dto.CartDTO;
import com.icia.shop.dto.MemberDTO;
import com.icia.shop.service.CartService;
import com.icia.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private MemberService memberService;

    // 장바구니 목록 띄우기
    @GetMapping("/cart")
    public String listCart(HttpSession session, Model model){
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        Long memberId = memberDTO.getId();
        List<CartDTO> list = cartService.listCart(memberId); //장바구니 목록리스트
        System.out.println("컨트롤 위에 list = " + list);
        // 위에 리스트 안에는 3개의 테이블을 조인해서 가져온 장바구니id,회원id,회원이름,상품id,상품이름,장바구니수량,상품단가,(상품*수량)총금액이 담겨있다

        int sumMoney = cartService.sumMoney(memberId); //장바구니 전체금액 호출
        // 장바구니 전체 금액에 따라 배송비 구분
        // 배송료 (5만원 이상 => 무료배송, 미만=>2500원)
        // 배송료 (5만원 이상 => 무료배송, 미만=>2500원)
        // 삼항연산자 사용
        int fee = (sumMoney >= 50000) ? 0 : 2500;
        model.addAttribute("list", list); // 장바구니 정보들
        System.out.println("컨트롤 list = " + list);
        model.addAttribute("count", list.size()); // 장바구니 상품의 유무
        model.addAttribute("sumMoney", sumMoney); // 장바구니 전체금액
        System.out.println(" 컨트롤 sumMoney = " + sumMoney);
        model.addAttribute("fee", fee); // 장바구니 배송료
        System.out.println("컨트롤 fee = " + fee);
        model.addAttribute("totalSum", sumMoney + fee);
        System.out.println("totalSum fee = " + sumMoney + fee);
        // 주문상품 전체금액( 주문한 전체금액+배송료)
        System.out.println("찍히나 list = " + list);
        return "orderPages/Cartview";
    }

    @PostMapping("/cart")
    public String save(@ModelAttribute CartDTO cartDTO, HttpSession session){
//        cartDTO안에는 상품 수량과 상품 아이디가 있다
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        System.out.println("체크1. memberDTO = " + memberDTO);
        cartDTO.setMemberId(memberDTO.getId());
        cartDTO.setProductId(cartDTO.getId());
        System.out.println("체크2 cartDTO = " + cartDTO);
        // 장바구니에 기존 상품이 있는지 검사
        int count = cartService.countCart(cartDTO.getProductId(),cartDTO.getMemberId());
        System.out.println("체크다아 count = " + count);
        // 삼항조건 연산자 사용
        // count가 0인지 확인하고 0인 경우(서버에 해당 상품이 있는경우) cartService.updateCart(cartDTO)가 실행해서 수정해줌
        // count가 0이 아닌경우(서버에 해당 상품이 없는경우) cartService.insert(cartDTO)가 실행되서 추가해줌
        cartService.updateCart(cartDTO);
        cartService.insert(cartDTO);
        System.out.println("아아악 cartDTO = " + cartDTO);
        if(count == 0){
            cartService.insert(cartDTO);
            System.out.println("체크3 cartDTO = " + cartDTO);
        }else{
            cartService.updateCart(cartDTO);
            System.out.println("체크4 cartDTO = " + cartDTO);
        }
        return "redirect:/order/cart";
    }



}
