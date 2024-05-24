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
        Page<Board> boardPage = boardService.findAllBoards(pageable);
        model.addAttribute("boardPage", boardPage);
        return "list";
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") Long id, @RequestParam(defaultValue = "1") int page, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        model.addAttribute("page", page);
        return "view";
    }


    @PostMapping("/write")
    public String write(@ModelAttribute Board board) {
        boardService.saveBoard(board);
        return "redirect:/list";
    }

    @GetMapping("/writeform")
    public String writeForm() {
        return "writeform";
    }

    @GetMapping("/deleteform")
    public String deleteForm(@RequestParam("id") Long id, @RequestParam(defaultValue = "1") int page, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("page", page);
        return "deleteform";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id, @RequestParam("password") String password, @RequestParam(defaultValue = "1") int page, Model model) {
        if (boardService.verifyPassword(id, password)) {
            boardService.deleteById(id);
            return "redirect:/list?page=" + page;
        } else {
            model.addAttribute("id", id);
            model.addAttribute("page", page);
            model.addAttribute("error", "Invalid password");
            return "deleteform";
        }
    }

    @GetMapping("/updateform")
    public String updateForm(@RequestParam("id") Long id, @RequestParam(defaultValue = "1") int page, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        model.addAttribute("page", page);
        return "updateform";
    }

    @PostMapping("/update")
    public String update(Board board, @RequestParam String password, @RequestParam(defaultValue = "1") int page) {
        if (boardService.verifyPassword(board.getId(), password)) {
            boardService.updateBoard(board);
            return "redirect:/view?id=" + board.getId() + "&page=" + page;
        }
        return "updateform";
    }
}
