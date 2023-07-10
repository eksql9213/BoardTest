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
	<form method="post" action="/board/boardCRUD" onsubmit="return formCheck(this)">
		<input type="hidden" name="pageNum" value="${pagingDto.pageNum }">
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
					<td><a href="<c:url value='/boardCRUD?mode=read&bno=${board.bno }&pageNum=${pagingDto.pageNum }'/>">${board.title }</a></td>
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
					<input class="btn btn-default" type="button" value="글쓰기" onclick="location.href='/board/boardCRUD?mode=create&pageNum=${pagingDto.pageNum }'">
				</td>
			</tr>
		</table>
	</form>
	<table id="pageNavi">
		<tr>
			<td><a href="<c:url value='/?pageNum=${pagingDto.startPage-1 }'/>">${pagingDto.prev? "&lt;" : ""}</a></td>
			<c:forEach var="i" begin="${pagingDto.startPage }" end="${pagingDto.endPage }">
				<td><a href="<c:url value='/?pageNum=${i }'/>">${i }</a></td>
			</c:forEach>
			<td><a href="<c:url value='/?pageNum=${pagingDto.endPage+1 }'/>">${pagingDto.next? "&gt;" : "" }</a></td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	function formCheck(frm) {
		if(document.getElementsByName("bno_list").length != 0){
			return true;
		}
		
		return false;
	}
</script>
</html>