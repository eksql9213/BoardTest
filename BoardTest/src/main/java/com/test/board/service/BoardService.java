package com.test.board.service;

import java.util.List;

import com.test.board.dto.BoardDto;
import com.test.board.dto.PagingDto;

public interface BoardService {

	List<BoardDto> board_list(BoardDto boardDto) throws Exception;
	
	int board_create(BoardDto boardDto) throws Exception;

	BoardDto board_read(String mode, BoardDto boardDto) throws Exception;

	int board_update(BoardDto boardDto) throws Exception;

	int board_delete(Integer bno) throws Exception;

	int board_delete(Integer[] bno_list) throws Exception;
}