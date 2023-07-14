package com.test.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.board.dao.BoardDao;
import com.test.board.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public List<BoardDto> board_list(BoardDto boardDto) throws Exception {
		List<BoardDto> board_list = new ArrayList<BoardDto>();
		System.out.println("boardService.board_list()");
		if(boardDto.getKeyword() == null || boardDto.getKeyword().equals("")) {
			boardDto.setTotalListCnt(boardDao.totalListCnt());
			board_list = boardDao.selectListAll(boardDto);
		}
		else {
			System.out.println("boardDao.searchListCnt()");
			System.out.println(boardDto);
			boardDto.setTotalListCnt(boardDao.searchListCnt());
			board_list = boardDao.selectList(boardDto);
		}
		
		return board_list;
	}
	
	@Override
	public int board_create(BoardDto boardDto) throws Exception {
		return boardDao.insert(boardDto);
	}
	
	@Override
	public BoardDto board_read(String mode, BoardDto boardDto) throws Exception {
		Integer bno = boardDto.getBno();
		if(mode.equals("read")) boardDao.viewCnt(bno);
		BoardDto readBoard = boardDao.select(bno);
		readBoard.setPageNum(boardDto.getPageNum());
		return readBoard;
	}
	
	@Override
	public int board_update(BoardDto boardDto) throws Exception {
		return boardDao.update(boardDto);
	}
	
	@Override
	public int board_delete(Integer bno) throws Exception {
		return boardDao.delete(bno);
	}

	@Override
	public int board_delete(Integer[] bno_list) throws Exception {
		int delete_cnt = 0;
		for(Integer bno : bno_list) {
			boardDao.delete(bno);
			delete_cnt++;
		}
		return delete_cnt;
	}
	
}
