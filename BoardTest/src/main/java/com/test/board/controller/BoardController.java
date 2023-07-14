package com.test.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.board.dto.BoardDto;
import com.test.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/")
	public String board_list(BoardDto boardDto, Model model) throws Exception {
		System.out.println("!!!!!!!!!!!!!board_list page " + boardDto.getPageNum() + " open!!!!!!!!!!!!!");
		
		List<BoardDto> board_list = boardService.board_list(boardDto);
		
		model.addAttribute("board_list", board_list);
		model.addAttribute("boardDto", boardDto);
		return "board_list";
	}
	
	@PostMapping("/search")
	public String board_list_search(BoardDto boardDto, Model model) throws Exception {
		System.out.println("!!!!!!!!!!!!!board_list_search page " + boardDto.getPageNum() + " open!!!!!!!!!!!!!");
		
		List<BoardDto> board_list = boardService.board_list(boardDto);
		
		model.addAttribute("board_list", board_list);
		model.addAttribute("boardDto", boardDto);
		return "board_list";
	}

	@GetMapping("/boardCRUD")
	public String board_CRUD(String mode, BoardDto boardDto, Model model) throws Exception {
		
		switch(mode) {
			case "create" :
				model.addAttribute("boardDto", boardDto);
				System.out.println("!!!!!!!!!!!!!board_create page open!!!!!!!!!!!!!");
				break;
				
			case "read" :
			case "update" :
				model.addAttribute("boardDto", boardService.board_read(mode, boardDto));
				System.out.println("!!!!!!!!!!!!!board bno." + boardDto.getBno() + " page open!!!!!!!!!!!!!");
				break;
			
			case "delete" :
				boardService.board_delete(boardDto.getBno());
				System.out.println("!!!!!!!!!!!!!board_delete done!!!!!!!!!!!!!");
				return "redirect:/?pageNum="+boardDto.getPageNum();
		}
		
		model.addAttribute("mode", mode);
		return "board";
	}
	
	@PostMapping("/boardCRUD")
	public String board_CRUD(String mode, BoardDto boardDto, Integer[] bno_list, Model model) throws Exception {
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
				boardService.board_delete(bno_list);
				System.out.println("!!!!!!!!!!!!!board_deleteList done!!!!!!!!!!!!!");
		}

		return "redirect:/?pageNum="+boardDto.getPageNum();
	}
}
