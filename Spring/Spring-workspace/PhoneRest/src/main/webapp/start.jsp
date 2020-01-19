<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>RESTful 웹서비스 클라이언트(JSON)</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	phoneList();//모든 사용자 정보 요청
	phoneSelect();//한 사람 선택시 처리할 이벤트 등록
	phoneDelete();//한 사람 삭제시 처리할 이벤트 등록
	phoneUpdate();//한 사람 정보 수정시 처리할 이벤트 등록
	phoneInsert();//새 고객 정보 추가 시 처리할 이벤트 등록
	login();
	logout();
	init();
});

//모든 사용자 목록 조회 요청
function phoneList() {//REST 서버에 모든 고객정보에 대한 ajax 요청 보내기
	$.ajax({
		url:'phone',
		type:'get',
		dataType:'json',//서버가 보내주는 데이터 타입
		success: function(data){
			phoneListResult(data);
		},
		error: function(xhr, status, msg){
			alert("상태값:"+status+" http 에러 메세지: "+msg);
		}
	});	
}	
function phoneListResult(data){
	$('tbody').empty();
	$.each(data, function(idx, item){
		$('<tr>').append($('<td>').html(item.num))
				 .append($('<td>').html(item.model))
				 .append($('<td>').html(item.price))
				 .append($('<td>').html(item.vcode))
				 .append($('<td>').html('<button id="btnSelect">조회</button>'))
				 .append($('<td>').html('<button id="btnDelete">삭제</button>'))
				 .append($('<input type="hidden" id="hidden_num">').val(item.num))
				 .appendTo('tbody');
	});
}

//사용자 조회 요청
function phoneSelect() {
	$('body').on('click','#btnSelect',function(){
		var num=$(this).closest('tr').find('#hidden_num').val();
		$.ajax({
			url: 'phone/'+num,
			type: 'get',
			dataType: 'json',
			success: phoneSelectResult
		});
	});	
}
function phoneSelectResult(data){
	$('#num').val(data.num);
	$('#model').val(data.model);
	$('#price').val(data.price);
	$('#vcode').val(data.vcode);
}

//로그인
function login() {
	$('body').on('click','#btnLogin',function(){
		var id=$('#id').val();
		var pw=$('#pw').val();
		$.ajax({
			url: 'login/'+id,
			type: 'get',
			dataType: 'json',
			success: function(data){
				loginResult(data);
			},
			error: function(xhr, status, msg){
				alert("상태값:"+status+" http 에러 메세지: "+msg);
			}
		});
	});
}
function loginResult(data){
	$('#loginForm').empty();
	$('<div>').append($('<span>').html(data.id+"님 로그인 되었습니다.</span>"))
			  .append($('<span>').html('<input type="button" id="btnLogout" value="로그아웃">'))
			  .append($('<span>').html('<input type="hidden" id="logout_id" value='+data.id+'>'))
			  .appendTo("#loginForm");
}

//로그아웃
function logout(){
	$('body').on('click', '#btnLogout', function(){
		var id=$('#logout_id').val();
		$.ajax({
			url: 'logout/'+id,
			type: 'get',
			success: function(data){
				logoutResult(data);
			},
			error: function(xhr, status, msg){
				alert("상태값:"+status+" http 에러 메세지: "+msg);
			}
		});
	});
}
function logoutResult(data){
	$('#loginForm').empty();
	$('<div class="form-group">').append($('<label>ID:</label><input type="text" class="form-control" id="id">')).appendTo("#loginForm");
	$('<div class="form-group">').append($('<label>PW:</label><input type="password" class="form-control" id="pw">')).appendTo("#loginForm");
	$('<div class="btn-group">').append($('<input type="button" class="btn btn-primary" value="로그인" id="btnLogin" />')).appendTo("#loginForm");
}

//사용자 삭제 요청
function phoneDelete() {
	$('body').on('click','#btnDelete',function(){
		var num=$(this).closest('tr').find('#hidden_num').val();
		$.ajax({
			url: 'phone/'+num,
			type: 'delete',
			success: function(){
				phoneList();
				clear();
			}
		});
	});
}

//사용자 수정 요청
function phoneUpdate() {
	$('#btnUpdate').on('click',function(){
		var num=$('#num').val();
		var model=$('#model').val();
		var price=$('#price').val();
		var vcode=$('#vcode').val();
		$.ajax({
			url: 'phone/',
			type: 'put',
			dataType: 'json',
			data: JSON.stringify({
				num:num,
				model:model,
				price:price,
				vcode:vcode	
			}),
			contentType:'application/json',
			success: function(){
				phoneList();
				clear();
			}
		});
	});
}

//사용자 등록 요청
function phoneInsert() {
	$('#btnInsert').on('click',function(){
		var num=$('#num').val();
		var model=$('#model').val();
		var price=$('#price').val();
		var vcode=$('#vcode').val();
		$.ajax({
			url: 'phone/',
			type: 'post',
			dataType: 'json',//from server
			data : JSON.stringify({//data to server
				num:num,
				model:model,
				price:price,
				vcode:vcode
			}),
			contentType: 'application/json',//to server
			success: function(){
				phoneList();
				clear();
			},
			error: function(xhr, status, msg){
				alert("상태값:"+status+" http 에러 메세지: "+msg);
			}
		});
	});
}

//초기화
function init() {
	$('#btnInit').on('click', function() {	
		clear();
	});
}
function clear(){
	$('#num').val('');
	$('#model').val('');
	$('#price').val('');
	$('#vcode').val('');
}
</script>
</head>
<body>
	<div class="container">
		<h2>Phone Management</h2>
		<form id="loginForm" class="form-horizontal">
			<div class="form-group">
				<label>ID:</label><input type="text" class="form-control" id="id">
			</div>
			<div class="form-group">
				<label>PW:</label><input type="password" class="form-control" id="pw">
			</div>
			<div class="btn-group">
				<input type="button" class="btn btn-primary" value="로그인" id="btnLogin" /> 
			</div>
		</form>
		<hr>
		<form id="form1" class="form-horizontal">
			<div class="form-group">
				<label>번호:</label> <input type="text" class="form-control"	id="num">
			</div>
			<div class="form-group">
				<label>모델:</label> <input type="text" class="form-control"	id="model">
			</div>

			<div class="form-group">
				<label>가격:</label> <input type="text" class="form-control" id="price">
			</div>
			<div class="form-group">
				<label>제조사:</label> <input type="text" class="form-control" id="vcode">
			</div>

			<div class="btn-group">
				<input type="button" class="btn btn-primary" value="등록"	 id="btnInsert" /> 
				<input type="button" class="btn btn-primary" value="수정" id="btnUpdate" /> 
				<input type="button" class="btn btn-primary" value="초기화" id="btnInit" />
			</div>
		</form>
	</div>
	<hr />
	<div class="container">
		<h2>핸드폰 목록</h2>
		<table class="table text-center">
			<thead>
				<tr>
					<th class="text-center">번호</th>
					<th class="text-center">모델</th>
					<th class="text-center">가격</th>
					<th class="text-center">제조사</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</body>
</html>