package com.test.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.board.dto.BoardDto;
import com.test.board.dto.PagingDto;
import com.test.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/")
	public String board_list(Integer pageNum, Model model) throws Exception {
		System.out.println("!!!!!!!!!!!!!board_list page open!!!!!!!!!!!!!");
		
		if(pageNum == null) pageNum=1;
		
		PagingDto pagingDto = new PagingDto(pageNum);
		List<BoardDto> board_list = boardService.board_list(pagingDto);
		
		model.addAttribute("board_list", board_list);
		model.addAttribute("pagingDto", pagingDto);
		return "board_list";
	}
	
	@GetMapping("/boardCRUD")
	public String board_CRUD(String mode, Integer bno, BoardDto boardDto, Integer pageNum, Model model) throws Exception {
		
		switch(mode) {
			case "create" :
				System.out.println("!!!!!!!!!!!!!board_create page open!!!!!!!!!!!!!");
				break;
				
			case "read" :
			case "update" :
				model.addAttribute("boardDto", boardService.board_read(mode, bno));
				System.out.println("!!!!!!!!!!!!!board bno." + bno + " page open!!!!!!!!!!!!!");
				break;
			
			case "delete" :
				boardService.board_delete(bno);
				System.out.println("!!!!!!!!!!!!!board_delete done!!!!!!!!!!!!!");
				return "redirect:/?pageNum="+pageNum;
		}
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("mode", mode);
		return "board";
	}
	
	@PostMapping("/boardCRUD")
	public String board_CRUD_execute(String mode, BoardDto boardDto, Integer[] bno_list, Integer pageNum, Model model) throws Exception {
		Integer bno = boardDto.getBno();
		
		switch(mode) {
			case "create" :
				boardService.board_create(boardDto);
				System.out.println("!!!!!!!!!!!!!board_create bno." + bno + " done!!!!!!!!!!!!!");
				break;
				
			case "update" :
				boardService.board_update(boardDto);
				System.out.println("!!!!!!!!!!!!!board_update bno." + bno + " done!!!!!!!!!!!!!");
				break;
			
			case "delete" :
				boardService.board_deleteList(bno_list);
				System.out.println("!!!!!!!!!!!!!board_deleteList done!!!!!!!!!!!!!");
		}

		return "redirect:/?pageNum="+pageNum;
	}
}
