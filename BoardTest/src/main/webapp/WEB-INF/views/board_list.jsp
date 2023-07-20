<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 50px;
	}
	#keywordForm {
		margin: 30px 0 20px;
		display: flex;
		justify-content: center;
	}
	#keywordForm > input[type=search] {
		width: 500px;
	}
	.table {
	    width: max-content;
	    min-width: 1000px;
	    margin: auto;
	    text-align: center;
	}
	#buttons {
		text-align: left;
	}
	#pageNavi {
	    width: 300px;
	    margin: auto;
	    text-align: center;
	}
	#pageNavi td {
	    width: 20px;
	    height: 20px;
	}
</style>
</head>
<body>
	<h1>게시판 목록</h1>
	<form id="keywordForm" method="post" action="/board/search" onsubmit="return keywordCheck(this)">
		<select class="btn btn-default" name="option">
			<option value="title" ${boardDto.option == 'title' || boardDto.option == 'title' ? "selected" : "" }>제목</option>
			<option value="content" ${boardDto.option == 'content' ? "selected" : "" }>내용</option>
			<option value="writer" ${boardDto.option == 'writer' ? "selected" : "" }>작성자</option>
		</select>
		<input class="form-control" type="search" name="keyword" placeholder="검색어를 입력하세요." value="${boardDto.keyword }"/><input class="btn btn-default" type="submit" value="검색">
	</form>
	<form method="post" action="/board/boardCRUD" onsubmit="return deleteCheck(this)">
		<input type="hidden" name="pageNum" value="${boardDto.pageNum }">
		<table class="table">
			<tr>
				<td width ="50"><input type="hidden" name="mode" value="delete"></td>
				<td width ="100">번호	</td>
				<td>제목</td>
				<td>작성</td>
				<td width ="130">등록일자</td>
				<td width ="100">조회수</td>
			</tr>
			<c:if test="${empty board_list }">
				<tr>
					<td colspan="6" >등록된 게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="board" items="${board_list}" varStatus="status">
				<tr>
					<td><input type="checkbox" name="bno_list" value=${board.bno }></td>
					<td>${board.bno }</td>
					<td>
						<a href="<c:url value='/boardCRUD?mode=read&bno=${board.bno }&pageNum=${boardDto.pageNum }&option=${boardDto.option }&keyword=${boardDto.keyword }'/>">${board.title }</a>
						(<a href="<c:url value='/boardCRUD?mode=read&bno=${board.bno }&pageNum=${boardDto.pageNum }&option=${boardDto.option }&keyword=${boardDto.keyword }'/>">${board.comment_cnt }</a>)
					</td>
					<td>${board.writer }</td>
					<td>
						<fmt:formatDate value="${board.reg_date }" pattern="yyyy-MM-dd HH:mm"/>
					</td>
					<td>${board.view_cnt }</td>
				</tr>
        	</c:forEach>
			<tr>
				<td id="buttons" colspan="6" >
					<input class="btn btn-default" type="submit" value="선택삭제">
					<input class="btn btn-default" type="button" value="글쓰기" onclick="location.href='/board/boardCRUD?mode=create&pageNum=${boardDto.pageNum }'">
				</td>
			</tr>
		</table>
	</form>
	<table id="pageNavi">
		<tr>
			<td><a href="<c:url value='/?pageNum=${boardDto.startPage-1 }&option=${boardDto.option }&keyword=${boardDto.keyword }'/>">${boardDto.prev? "&lt;" : ""}</a></td>
			<c:forEach var="i" begin="${boardDto.startPage }" end="${boardDto.endPage }">
				<td><a href="<c:url value='/?pageNum=${i }&option=${boardDto.option }&keyword=${boardDto.keyword }'/>">${i }</a></td>
			</c:forEach>
			<td><a href="<c:url value='/?pageNum=${boardDto.endPage+1 }&option=${boardDto.option }&keyword=${boardDto.keyword }'/>">${boardDto.next? "&gt;" : "" }</a></td>
		</tr>
	</table>
</body>
<script type="text/javascript">	
	function deleteCheck(frm) {
		if(document.getElementsByName("bno_list").length != 0){
			return true;
		}
		
		return false;
	}
</script>
</html>