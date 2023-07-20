package com.test.board.dao;

import java.util.List;

import com.test.board.dto.BoardDto;

public interface BoardDao {
	int totalListCnt() throws Exception;

	int searchListCnt(BoardDto boardDto) throws Exception;
	
	List<BoardDto> selectListAll(BoardDto boardDto) throws Exception;

	List<BoardDto> selectList(BoardDto boardDto) throws Exception;
	
	BoardDto select(Integer bno) throws Exception;

	int insert(BoardDto boardDto) throws Exception;

	int update(BoardDto boardDto) throws Exception;

	int viewCnt(Integer bno) throws Exception;

	int deleteAll();

	int delete(Integer bno) throws Exception;

}