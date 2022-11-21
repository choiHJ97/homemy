package com.acpla.homemy.controller;

import com.acpla.homemy.model.Board;
import com.acpla.homemy.repository.BoardRepository;
import com.acpla.homemy.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String list(Model model){
        List<Board> board = boardRepository.findAll();
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
