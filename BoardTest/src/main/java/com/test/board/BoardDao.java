package com.test.board;

import java.util.List;

public interface BoardDao {
	int totalListCnt() throws Exception;
	
	List<BoardDto> selectListAll() throws Exception;

	List<BoardDto> selectList(PagingDto pagingDto) throws Exception;
	
	BoardDto select(Integer bno) throws Exception;

	int insert(BoardDto boardDto) throws Exception;

	int update(BoardDto boardDto) throws Exception;

	int viewCnt(Integer bno) throws Exception;

	int deleteAll();

	int delete(Integer bno) throws Exception;

}