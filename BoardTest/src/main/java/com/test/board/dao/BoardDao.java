package com.test.board.dao;

import java.util.List;

import com.test.board.dto.BoardDto;
import com.test.board.dto.PagingDto;

public interface BoardDao {
	int totalListCnt() throws Exception;
	
	List<BoardDto> selectListAll(PagingDto pagingDto) throws Exception;

	List<BoardDto> selectList() throws Exception;
	
	BoardDto select(Integer bno) throws Exception;

	int insert(BoardDto boardDto) throws Exception;

	int update(BoardDto boardDto) throws Exception;

	int viewCnt(Integer bno) throws Exception;

	int deleteAll();

	int delete(Integer bno) throws Exception;

}