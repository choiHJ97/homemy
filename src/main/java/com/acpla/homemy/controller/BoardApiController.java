package com.acpla.homemy.controller;

import com.acpla.homemy.model.Board;
import com.acpla.homemy.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class BoardApiController {

    @Autowired
    private BoardRepository repository;


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/boards")
    List<Board> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/boards")
    Board newBoard(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    // Single item

    @GetMapping("/boards/{B_ID}")
    Board one(@PathVariable Long B_ID) {

        return repository.findById(B_ID).orElse(null);
    }

    @PutMapping("/boards/{B_ID}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long B_ID) {

        return repository.findById(B_ID)
                .map(Board -> {
                    Board.setTitle(newBoard.getTitle());
                    Board.setContent(newBoard.getContent());
                    return repository.save(Board);
                })
                .orElseGet(() -> {
                    newBoard.setB_ID(B_ID);
                    return repository.save(newBoard);
                });
    }

    @DeleteMapping("/boards/{B_ID}")
    void deleteBoard(@PathVariable Long B_ID) {
        repository.deleteById(B_ID);
    }
}