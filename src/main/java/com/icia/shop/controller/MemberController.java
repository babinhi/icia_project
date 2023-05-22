package com.icia.shop.controller;

import com.icia.shop.dto.MemberDTO;
import com.icia.shop.dto.MemberFileDTO;
import com.icia.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {
        return "memberPages/MemberSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws IOException {
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "memberPages/MemberSaveOk";
    }

    @PostMapping("/email-check")
    public ResponseEntity emailCheck(@RequestParam("memberEmail") String memberEmail) {
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
        if (memberDTO == null) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/memberPages/MemberLogin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, Model model, HttpSession session) {
        boolean loginResult = memberService.login(memberDTO);
        if (loginResult) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "redirect:/";
        } else {
            return "memberPages/Loginerror";
        }
    }

//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.removeAttribute("loginEmail");
//        return "redirect:/";
//    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에 담긴 값 전체 삭제
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPage(@RequestParam("loginEmail") String loginEmail, Model model) {
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);
        if (memberDTO.getMemberProfile() == 1) {
            MemberFileDTO memberFileDTO = (MemberFileDTO) memberService.findFile(memberDTO.getId());
            System.out.println("memberFileDTO = " + memberFileDTO);
            model.addAttribute("memberFile", memberFileDTO);
        }
        return "memberPages/MemberMypage";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("id") Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        return "memberPages/MemberUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO, Model model) {
        memberService.update(memberDTO);
        MemberDTO dto = memberService.findById(memberDTO.getId());
        model.addAttribute("member", dto);
        return "redirect:/";
    }

    @GetMapping("/UpdatePass")
    public String updateForm(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);
        return "memberPages/UpdatePassword";
    }

    @PostMapping("/UpdatePass")
    public String updatePass(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);
        memberService.update(memberDTO);
        return "memberPages/MemberUpdate";
    }
    @GetMapping("/PasswordCheck")
    public String deleteForm(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);
        return "memberPages/PasswordCheck";
    }

    @PostMapping("/PasswordCheck")
    public String deletePass(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);
        memberService.delete(memberDTO);
        return "memberPages/MemberDelete";
    }

    @GetMapping("/managerPage")
    public String managerForm(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        System.out.println("넘어오나 체크 memberDTOList = " + memberDTOList);
        model.addAttribute("memberList", memberDTOList);
        return "memberPages/MemberManager";
    }

    @GetMapping("/memberDelete")
    public String deleteManager(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("체크용 memberDTO = " + memberDTO);
        memberService.delete(memberDTO);
        return "memberPages/MemberDelete";
    }

}

