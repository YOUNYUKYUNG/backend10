package org.example.boardexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.boardexam.domain.Board;
import org.example.boardexam.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        int size = 5;
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Board> boardPage = boardService.findAllBoards(pageable);// 페이징된 게시물 목록
        model.addAttribute("boardPage", boardPage);//객체를 모델에 추가하여 뷰로 전달
        return "list";
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") Long id, @RequestParam(defaultValue = "1") int page, Model model) {
        Board board = boardService.findById(id);//해당 ID의 게시물을 가져옴
        model.addAttribute("board", board);
        model.addAttribute("page", page);
        return "view";
    }

    //새 게시물을 작성하고 저장
    @PostMapping("/write")
    public String write(@ModelAttribute Board board) {
        boardService.saveBoard(board);
        return "redirect:/list";
    }


    //게시물 작성 폼
    @GetMapping("/writeform")
    public String writeForm() {
        return "writeform";
    }

    //게시물 삭제 확인 폼
    @GetMapping("/deleteform")
    public String deleteForm(@RequestParam("id") Long id, @RequestParam(defaultValue = "1") int page, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("page", page);
        return "deleteform";
    }

    //특정 게시물을 삭제
    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id, @RequestParam("password") String password, @RequestParam(defaultValue = "1") int page, Model model) {
        if (boardService.verifyPassword(id, password)) {
            boardService.deleteById(id);
            return "redirect:/list?page=" + page;
        } else {
            model.addAttribute("id", id);
            model.addAttribute("page", page);
            model.addAttribute("error", "error password");//비밀번호가 틀려서 다시 deleteform이동
            return "deleteform";
        }
    }

    //게시물 수정 폼
    @GetMapping("/updateform")
    public String updateForm(@RequestParam("id") Long id, @RequestParam(defaultValue = "1") int page, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        model.addAttribute("page", page);
        return "updateform";
    }

    //게시물을 수정
    @PostMapping("/update")
    public String update(Board board, @RequestParam String password, @RequestParam(defaultValue = "1") int page) {
        if (boardService.verifyPassword(board.getId(), password)) {//비밀번호를 확인
            boardService.updateBoard(board);
            return "redirect:/view?id=" + board.getId() + "&page=" + page;
        }
        return "updateform";
    }
}
