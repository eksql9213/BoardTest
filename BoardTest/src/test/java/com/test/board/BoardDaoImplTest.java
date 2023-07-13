package com.test.board;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.board.dao.BoardDao;
import com.test.board.dto.BoardDto;
import com.test.board.dto.PagingDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDaoImplTest {
	
	@Autowired
	private BoardDao boardDao;
	private BoardDto boardDto;
	private int board_cnt = 200;
	private PagingDto pagingDto;
	
	@Before
	public void setUp() {
		boardDao.deleteAll();
		boardDto = new BoardDto("test title", "test content", "test writer");
		pagingDto = new PagingDto();
	}

	@Test
	public void insertTest() throws Exception {
		boardDto = new BoardDto("test title", "test content", "test writer");
		for(int i=0; i<board_cnt; i++) {
			assertTrue(boardDao.insert(boardDto) == 1);
		}
	}

	@Test
	public void selectAllTest() throws Exception {
		insertTest();
		
		assertTrue(board_cnt == boardDao.totalListCnt());
	}

	@Test
	public void selectTest() throws Exception {
		insertTest();
		
		Integer bno = boardDao.selectListAll(pagingDto).get(0).getBno();
		boardDto.setBno(bno);
		
		System.out.println("boardDto = " + boardDto);
		System.out.println("boardDao.select(bno) = " + boardDao.select(bno));
		
		assertTrue(boardDao.select(bno).equals(boardDto));
	}

	@Test
	public void updateTest() throws Exception {
		insertTest();
		
		Integer bno = boardDao.selectListAll(pagingDto).get(0).getBno();
		System.out.println("bno:" + bno + " Before update boardDto = " + boardDao.select(bno));
		
		boardDto.setBno(bno);
		boardDto.setTitle("update test title");
		
		assertTrue(boardDao.update(boardDto) == 1);
		System.out.println("bno:" + bno + " After update boardDto = " + boardDao.select(bno));
	}

	@Test
	public void viewCntTest() throws Exception {
		insertTest();
		
		int board_view =10;
		Integer bno = boardDao.selectListAll(pagingDto).get(0).getBno();
		
		for(int i=0; i<board_view; i++) {
			boardDao.viewCnt(bno);
		}

		assertTrue(boardDao.select(bno).getView_cnt() == board_view);
	}

	@Test
	public void deleteTest() throws Exception {
		insertTest();
		
		int delete_cnt = 5;
		Integer bno = boardDao.selectListAll(pagingDto).get(0).getBno();
		
		for(int i=0; i<delete_cnt; i++) {
			boardDao.delete(bno);
			bno = boardDao.selectListAll(pagingDto).get(0).getBno();
		}
		
		assertTrue(boardDao.totalListCnt() == board_cnt-delete_cnt);
	}

	@Test
	public void deleteAllTest() throws Exception {
		insertTest();
		assertTrue(boardDao.deleteAll() == board_cnt);
	}
}
