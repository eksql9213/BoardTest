<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${mode=="create"? "게시판 글쓰기": boardDto.title }</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<style>
	h1 {
		text-align: center;
		margin-top: 50px;
	}
	form {
	    display: flex;
	    flex-direction: column;
    	align-items: center;
    }
	#title {
		width: 500px;
	}
	#content {
		width: 500px;
		height: 200px;
	}
	#writer {
		width: 500px;
	}
	.buttons {
		margin-top: 20px;
		width: 500px;
		display: flex;
		justify-content: end;
	}
</style>
</head>
<body>
	<h1>게시판 ${mode=="create"? "글쓰기": mode=="read"? "읽기" : "수정"  }</h1>
	<form method="post" action="/board/boardCRUD" onsubmit="return formCheck(this)">
		<input type="hidden" name="mode" value="${mode }">
		<input type="hidden" name="pageNum" value=${mode=="create"? "1": boardDto.pageNum }>
		
		<c:if test="${mode=='create' }">
			<input type="text" class="form-control" id="title" name="title" placeholder="제목">
			<input type="text" class="form-control" id="content" name="content" placeholder="내용">
			<input type="text" class="form-control" id="writer" name="writer" placeholder="작성자">
		
			<div class="buttons">
				<input class="btn btn-default" type="submit" value="등록">
				<input class="btn btn-default" type="button" value="취소" onclick="location.href='/board'">
			</div>
		</c:if>
		<c:if test="${mode=='read' }">
			<input type="hidden" name="bno" value="${boardDto.bno }">
			<input type="text" class="form-control" id="title" name="title" value="${boardDto.title }" disabled="disabled">
			<input type="text" class="form-control" id="content" name="content" value="${boardDto.content }" disabled="disabled">
			<input type="text" class="form-control" id="writer" name="writer" value="${boardDto.writer }" disabled="disabled">
		
			<div class="buttons">
				<input class="btn btn-default" type="button" value="수정" onclick="location.href='/board/boardCRUD?mode=update&bno=${boardDto.bno}&pageNum=${boardDto.pageNum }&option=${boardDto.option }&keyword=${boardDto.keyword }'">
				<input class="btn btn-default" type="button" value="삭제" onclick="location.href='/board/boardCRUD?mode=delete&bno=${boardDto.bno}&pageNum=${boardDto.pageNum }&option=${boardDto.option }&keyword=${boardDto.keyword }'">
				<input class="btn btn-default" type="button" value="목록" onclick="location.href='/board?pageNum=${boardDto.pageNum }&option=${boardDto.option }&keyword=${boardDto.keyword }'">
			</div>
		</c:if>
		<c:if test="${mode=='update' }">
			<input type="hidden" name="bno" value="${boardDto.bno }">
			<input type="text" class="form-control" id="title" name="title" value="${boardDto.title }">
			<input type="text" class="form-control" id="content" name="content" value="${boardDto.content }">
			<input type="text" class="form-control" id="writer" name="writer" value="${boardDto.writer }">
		
			<div class="buttons">
				<input class="btn btn-default" type="submit" value="완료">
				<input class="btn btn-default" type="button" value="삭제" onclick="location.href='/board/boardCRUD?mode=delete&bno=${boardDto.bno}&pageNum=${boardDto.pageNum }&option=${boardDto.option }&keyword=${boardDto.keyword }'">
				<input class="btn btn-default" type="button" value="취소" onclick="location.href='/board?pageNum=${boardDto.pageNum }&option=${boardDto.option }&keyword=${boardDto.keyword }'">
			</div>
		</c:if>
	</form>
</body>
<script type="text/javascript">
	function formCheck(frm) {
		if(frm.title.value == ""){
			alert("제목을 입력하세요.");
			return false;
		}
		else if(frm.content.value == ""){
			alert("내용을 입력하세요.");
			return false;
		}
		else if(frm.writer.value == ""){
			alert("작성자를 입력하세요.");
			return false;
		}
		return true;
	}
</script>
</html>