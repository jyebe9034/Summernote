<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writer</title>
<link href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Cute+Font|Noto+Serif+KR:700|Do+Hyeon|Sunflower:300|Jua|Nanum+Gothic|Nanum+Gothic+Coding&display=swap" rel="stylesheet">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>


<style>
	body{
		font-family: "Nanum Gothic";
		height: 100%;
		text-align: center;
	}
	.progress{
		width: 200px;
	}
	.progress-bar{
		background-color: orange;
	}
	.wrapper{
		display: inline-block;
	}
	.anker{
		font-weight: bold;
	}
	.anker:hover{
		color: #000000;
	}
	.nav-ul{
		margin-left: 300px;
	}
	.nav-li{
		width: 130px;
	}
	#blank{
		display: inline-block;
		width: 200px;
	}
	#image{
		max-height: 700px;
	}
	#wrapper{
		width: 700px;
		margin: auto;
		margin-top: 50px;
	}
	#all-btns{
		height: 50px;
	}
	.noti{
		font-size: 20px;
		color: #195190;
	}
	.btns{
        text-align: right;
        margin-top: 10px;
        margin-bottom: 10px;
        margin-right: 10px;
        float: right;
    }
    hr{
    	border : thin solid #19519050;
        margin-bottom: 10px;
        margin-top: 10px;
    }
    #footer{
        height: 200px;
        width: 100%;
        background-color: #2d3f53;
        align-items: center;
        position: relative;
        margin-top: 30px;
	}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light navbar-fixed-top">
		<div id="blank"></div>
		<div class="logo">
			<a class="navbar-brand anker" href="Main.members" style="font-family: 'Cute Font', cursive;"><h1>도움닿기</h1></a>
		</div>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav nav-ul">
				<li class="nav-item nav-li"><a class="nav-link anker" href="Introduce.members">소개</a></li>
				<li class="nav-item nav-li"><a class="nav-link anker" href="write.board">후원해 주세요</a></li>
				<li class="nav-item nav-li"><a class="nav-link anker" href="textList.board?currentPage=1">후원 게시판</a></li>

				<c:choose>
					<c:when test="${sessionScope.loginEmail != null || navercontents.name != null}">
						<li class="nav-item nav-li"><a class="nav-link anker" href="Logout.members">로그아웃</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item nav-li"><a class="nav-link anker" href="LoginForm.members">로그인</a></li>
						<li class="nav-item nav-li"><a class="nav-link anker" href="JoinForm.members">회원가입</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
	<hr>
	<div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
		<div class="carousel-inner">
		    <div class="carousel-item active">
		        <img id="image" src="foryou.jpg" class="d-block w-100" alt="이미지를 찾을 수 없습니다.">
		    </div>
		</div>
	</div>
	<div id="wrapper">	
		<form action="writer.temp" method="post" id="myform" enctype="multipart/form-data" accept-charset="UTF-8">
			<div class="form-group">
				<div class="noti mb-2">제목은 한눈에 쏙 들어오게!</div> 
				<input type="text" class="form-control" name="title" placeholder="제목을 입력해 주세요 :)" required>
			</div>
			<div class="form-group"> 
				<div class="noti mb-2">대표 사진은 신중하게 골라서!</div>
				<div class="custom-file mb-1">
			      <input type="file" class="custom-file-input" id="customFile" name="filename">
			      <label class="custom-file-label text-left" for="customFile">용량은 10MB까지만 올릴 수 있어요:)</label>
			    </div>
			</div>
			<div class="form-group">
				<div class=" noti mb-2">작성자는 실명으로!</div>
				<input type="text" class="form-control" name="writer" placeholder="신뢰를 바탕으로 모금액이 모이기 때문에 실명을 입력해 주세요 :)" required>
			</div>
			<div class="form-group">
				<div class=" noti mb-2">목표 금액은 최소 10,000원 이상!</div>
				<input type="number" class="form-control" name="amount" placeholder="한번 입력된 금액은 변경할 수 없어요 :)" min="10000" max="10000000" required>
			</div>
			<div class="form-group">
				<div class=" noti mb-2">마감일은 내가 원하는 대로!</div>
				<input type="date" class="form-control"  name="dueDate" required>
			</div>
			<div class="form-group">
				<div class="input-group mb-3">
				  <div class="input-group-prepend">
				    <label class="mt-1 mr-2 noti">후원받을 계좌는 본인 계좌로!(본인계좌가 아니면 모금이 불가능합니다.)</label>
				  </div>
				  <select class="custom-select" name="select">
				    <option value="신한" selected>신한</option>
					<option value="국민">국민</option>
					<option value="농협">농협</option>
					<option value="우리">우리</option>
					<option value="기업">기업</option>
					<option value="하나">하나</option>
					<option value="부산">부산</option>
					<option value="경남">경남</option>
					<option value="SC">SC</option>
					<option value="수협">수협</option>
					<option value="우체국">우체국</option>
				  </select>
				</div>
				<input type="text" class="form-control m-0" name="account" placeholder="계좌번호 '-' 제외하고 입력" required>
			</div>
			<div class="form-group">
				<div class="mb-2 noti">내용을 자유롭게 작성해 주세요 :) 사진을 추가로 올리는 것도 가능합니다.</div>
			</div>
			<div class="row">
				<div id="content" class="col-12">
					<div id="summernote" contenteditable="true"></div>
					<input id="mycontent" type="hidden" name="contents">
				</div>
			</div>
			<div id="all-btns" class="row">
				<div class="col-12">
					<div class="btns">
						<input type="button" id="sendit" class="btn btn-sm btn-outline-primary" value="등록">
					</div>
					<div class="btns">
						<input type="button" id="cancel" class="btn btn-sm btn-outline-primary" value="취소">
					</div>
				</div>
			</div>
		</form>
	</div>
	<div id="footer"></div>
	
	<script>
		$(window).on("beforeunload", function(){
	    	$("img").each(function(index, item){
	    		var src = $(this).attr("src");
	    		if(src == "foryou.jpg"){	
	    		}else{
	    			$.ajax({
						url: "deleteImage.temp",
						data: {src : src},
						type: "POST",
						cache: false
					})	
	    		}
	    	})
	    });
	
		$("#sendit").on("click", function(){
	        $("#mycontent").val($(".note-editable").html());
	        $("#myform").submit();
	    })
	     
	    $('#summernote').summernote({
			placeholder: '내용을 입력해주세요.',
		    tabsize: 2,
		    height: 500,
		      
		    callbacks: { // 값이 하나 이상이기 때문에 객체로 넣어줌.
		       	onImageUpload: function(files, editor, welEditable) { 
		       		for(var i = files.length - 1; i >= 0; i--){
		       			sendFile(files[i], this);
		       		}
		        },
	        		
			    onMediaDelete : function(target) {
			        deleteFile(target[0].src);
			    }
		    }
	 	});
		
		function sendFile(file, editor) {
			var data = new FormData();
	    	data.append("file", file);
	        $.ajax({
	        	url:"uploadImage.temp",
	        	data: data,
	        	type:"POST",
	        	cache: false,
	        	contentType: false,
	        	enctype: "multipart/form-data",
	        	processData: false
	        }).done(function(resp){
	        	$(".note-editable").append("<img src='"+resp+"'>");
	        })
        }
        
        function deleteFile(src) {
        	if(src == "foryou.jpg"){
        	}else{
        		$.ajax({
                    data: {src : src},
                    type: "POST",
                    url: "deleteImage.temp", // replace with your url
                    cache: false,
                    success: function(resp) {
                    	if(resp == "true"){
                    		console.log("정상 삭제");
                    	}else{
                    		console.log("삭제 실패");	
                    	}
                    }
                });	
        	}
        }
        
        $("#cancel").on("click", function(){
        	$("img").each(function(index, item){
        		var src = $(this).attr("src");
        		$.ajax({
    				url: "deleteImage.temp",
    				data: {src : src},
    				type: "POST",
    				cache: false
    			})
        	})
        	location.href="main.jsp";
        })
	</script>
</body>
</html>