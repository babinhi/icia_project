package com.icia.shop.controller;

import com.icia.shop.dto.*;
import com.icia.shop.service.CommentService;
import com.icia.shop.service.InquireService;
import com.icia.shop.service.MemberService;
import com.icia.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private CommentService commentService;

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
            @RequestParam(value = "type", required = false, defaultValue = "productTitle") String type,
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

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         Model model) {
        //조회수를 1씩 증가시키는 메소드이다
        productService.updateHits(id);
        System.out.println(" a id = " + id);
        ProductDTO productDTO = productService.findById(id);
        System.out.println(" aqqq productDTO = " + productDTO);
        model.addAttribute("product", productDTO);
        model.addAttribute("page", page);
        System.out.println("야호호호 productDTO = " + productDTO);
        if (productDTO.getFileAttached() == 1) {
            // 파일이 있는 게시글을 선택하면
            List<ProductFileDTO> productFileDTO = productService.findFile(id);
            model.addAttribute("productFileList", productFileDTO);
            System.out.println("productFileDTO = " + productFileDTO);
        }
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        if (commentDTOList.size() == 0) {
            //댓글이 없으면 list에 null적용
            model.addAttribute("없을때 commentList", null);
        } else {
            //댓글이 있으면 서버에서 가져온 commentDTOList를 넘겨준다
            model.addAttribute("commentList", commentDTOList);
            System.out.println("pro controller commentDTOList = " + commentDTOList);
        }
        return "productPages/ProductDetail";
    }
    @GetMapping("/update")
    public String updateForm(@RequestParam("id") Long id, Model model) {
        ProductDTO productDTO = productService.findById(id);
        System.out.println("여긴 컨트롤러 productDTO = " + productDTO);
        model.addAttribute("product", productDTO);
        return "productPages/ProductUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ProductDTO productDTO, Model model) {
        productService.update(productDTO);
        ProductDTO dto = productService.findById(productDTO.getId());
        model.addAttribute("product", dto);
        return "productPages/ProductDetail";
    }
//    @GetMapping("/DeletePass")
//    public String deleteCheck(@RequestParam("id") Long id, Model model) {
//        ProductDTO productDTO = productService.findById(id);
//        model.addAttribute("board", productDTO);
//        return "productPages/DeleteCheck";
//    }

    @GetMapping("/DeletePass")
    public String deleteFrom(@RequestParam("id") Long id) {
        ProductDTO productDTO = productService.findById(id);
        System.out.println("여긴 컨트롤러 productDTO = " + productDTO);
        productService.delete(id);
        return "productPages/ProductDelete";
    }
//    @PostMapping("/DeletePass")
//    public String delete(@RequestParam("id") Long id, Model model) {
//        ProductDTO dto = productService.findById(id);
//        model.addAttribute("product", dto);
//        productService.delete(id);
//        return "productPages/ProductDetail";
//    }
}
