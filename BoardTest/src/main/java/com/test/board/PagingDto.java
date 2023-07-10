package com.test.board;

public class PagingDto {
	private int pageNum;
	private int listSize;
	private int totalListCnt;
	private int pageSize;
	private int totalPageCnt;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int rowNum = (pageNum-1)*listSize;
	
	public PagingDto() {
		pageNum = 1;
		listSize = 10;
		pageSize = 10;
	}

	public PagingDto(int pageNum) {
		super();
		this.pageNum = pageNum;
		listSize = 10;
		pageSize = 10;
		setRowNum();
		
	}

	public PagingDto(int pageNum, int totalListCnt) {
		super();
		this.pageNum = pageNum;
		this.totalListCnt = totalListCnt;
		listSize = 10;
		pageSize = 10;

		pagingSetting();
		setRowNum();
	}

	public PagingDto(int pageNum, int listSize, int totalListCnt, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.listSize = listSize;
		this.totalListCnt = totalListCnt;
		this.pageSize = pageSize;

		pagingSetting();
		setRowNum();
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;		
		pagingSetting();
		setRowNum();
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;		
		pagingSetting();
		setRowNum();
	}

	public int getTotalListCnt() {
		return totalListCnt;
	}

	public void setTotalListCnt(int totalListCnt) {
		this.totalListCnt = totalListCnt;		
		pagingSetting();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;		
		pagingSetting();
	}

	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	public void setTotalPageCnt() {
		totalPageCnt = totalListCnt/listSize + (totalListCnt%listSize == 0? 0 : 1);
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage() {
		startPage = (pageNum-1)/pageSize*pageSize + 1;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage() {
		endPage = Math.min(startPage+pageSize-1, totalPageCnt);
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev() {
		prev = startPage > 1;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext() {
		next = endPage < totalPageCnt;
	}
	
	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum() {
		rowNum = (pageNum-1)*listSize;
	}

	public void pagingSetting() {
		setTotalPageCnt();
		setStartPage();
		setEndPage();
		setPrev();
		setNext();
	}

	@Override
	public String toString() {
		return "PagingDto [pageNum=" + pageNum + ", listSize=" + listSize + ", totalListCnt=" + totalListCnt
				+ ", pageSize=" + pageSize + ", totalPageCnt=" + totalPageCnt + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", rowNum=" + rowNum + "]";
	}

}
