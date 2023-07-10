package com.test.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public List<BoardDto> board_list() throws Exception {
		return boardDao.selectListAll();
	}

	@Override
	public List<BoardDto> board_list(PagingDto pagingDto) throws Exception {
		pagingDto.setTotalListCnt(boardDao.totalListCnt());
		return boardDao.selectList(pagingDto);
	}

	@Override
	public int board_create(BoardDto boardDto) throws Exception {
		return boardDao.insert(boardDto);
	}
	
	@Override
	public BoardDto board_read(String mode, Integer bno) throws Exception {
		if(mode.equals("read")) boardDao.viewCnt(bno);
		return boardDao.select(bno);
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
	public int board_deleteList(Integer[] bno_list) throws Exception {
		int delete_cnt = 0;
		for(Integer bno : bno_list) {
			boardDao.delete(bno);
			delete_cnt++;
		}
		return delete_cnt;
	}
	
}
