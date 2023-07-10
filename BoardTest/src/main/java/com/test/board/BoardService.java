package com.test.board;

import java.util.List;

public interface BoardService {

	List<BoardDto> board_list() throws Exception;
	
	List<BoardDto> board_list(PagingDto pagingDto) throws Exception;
	
	int board_create(BoardDto boardDto) throws Exception;

	BoardDto board_read(String mode, Integer bno) throws Exception;

	int board_update(BoardDto boardDto) throws Exception;

	int board_delete(Integer bno) throws Exception;

	int board_deleteList(Integer[] bno_list) throws Exception;
}