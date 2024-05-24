package org.example.boardexam.service;

import lombok.RequiredArgsConstructor;
import org.example.boardexam.domain.Board;
import org.example.boardexam.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<Board> findAllBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveBoard(Board board) {
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public boolean verifyPassword(Long id, String password) {
        return boardRepository.findById(id)
                .map(board -> board.getPassword().equals(password))
                .orElse(false);
    }

    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(Board updatedBoard) {
        Board board = boardRepository.findById(updatedBoard.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + updatedBoard.getId()));
        board.setName(updatedBoard.getName());
        board.setTitle(updatedBoard.getTitle());
        board.setContent(updatedBoard.getContent());
        board.setUpdatedAt(LocalDateTime.now());
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public List<Board> findAll() {
        return (List<Board>) boardRepository.findAll();
    }
}
