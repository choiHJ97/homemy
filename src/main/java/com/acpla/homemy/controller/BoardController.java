package com.acpla.homemy.controller;

import com.acpla.homemy.model.Board;
import com.acpla.homemy.repository.BoardRepository;
import com.acpla.homemy.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model,Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){
//        Page<Board> board = boardRepository.findAll(pageable);
        Page<Board> board = boardRepository.findByTitleContainingOrContentContaining(searchText,searchText,pageable);
        int startPage = Math.max(1, board.getPageable().getPageNumber() - 4);
        int endPage = Math.min(board.getTotalPages(), board.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute( "board", board);
        return "board/list";
    }
    @GetMapping("/form")
        public String form(Model model, @RequestParam(required = false) Long B_ID){
        if(B_ID == null){
            model.addAttribute("board",new Board());
        }else{
            Board board = boardRepository.findById(B_ID).orElse(null);

            model.addAttribute("board", board);

        }
        return "board/form";
    }
    @PostMapping("/form")
    public String greetingSubmit(@Valid Board board, BindingResult bindingResult){
        boardValidator.validate(board,bindingResult);
        if(bindingResult.hasErrors()){
            return "board/form";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }

}
