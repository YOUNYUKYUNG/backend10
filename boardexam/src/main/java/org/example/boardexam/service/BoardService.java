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

    @Transactional(readOnly = true)//페이징된 게시물 목록을 반환
    public Page<Board> findAllBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)//특정 ID를 가진 게시물을 반환
    public Board findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    @Transactional//새 게시물을 저장
    public void saveBoard(Board board) {
        board.setCreatedAt(LocalDateTime.now());//생성시간
        board.setUpdatedAt(LocalDateTime.now());//업뎃시간
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)//비밀번호가 맞는지 확인
    public boolean verifyPassword(Long id, String password) {
        return boardRepository.findById(id)
                .map(board -> board.getPassword().equals(password))
                .orElse(false);
    }

    @Transactional//특정 ID를 가진 게시물을 삭제
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional//게시물 업데이트
    public void updateBoard(Board updatedBoard) {
        Board board = boardRepository.findById(updatedBoard.getId())//데이터베이스에서 업데이트할 게시물을 조회
                //데이터베이스에서 해당 ID의 게시물 찾아
                .orElseThrow(() -> new IllegalArgumentException("errror board Id:" + updatedBoard.getId()));
        board.setName(updatedBoard.getName());//게시물의 이름, 제목, 내용
        board.setTitle(updatedBoard.getTitle());
        board.setContent(updatedBoard.getContent());
        board.setUpdatedAt(LocalDateTime.now());//업뎃시간 현재시간으로
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)//모든 게시물 목록 반환
    public List<Board> findAll() {
        return (List<Board>) boardRepository.findAll();
    }
}
