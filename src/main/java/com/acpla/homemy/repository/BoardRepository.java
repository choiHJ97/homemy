package com.acpla.homemy.repository;

import com.acpla.homemy.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
