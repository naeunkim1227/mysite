<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/ejs/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var messageBox = function(title, message, callback){
	$("dialog-message").attr('title', title)
	$("dialog-message p ").text(message);
	
	$( "#dialog-message" ).dialog({
	      modal: true,
	      buttons: {
	        "확인" : function() {
	          $( this ).dialog( "close" );
	        }
	      },
	      close: callback
	    });
}

var listEJS = new EJS({
	url: '${pageContext.request.contextPath }/ejs/list-template.ejs'
});

//listLoad AJAX
var startNo;
var listLoad = function(){
	var url = '${pageContext.request.contextPath }/api/guestbook/list' +(startNo? ('?sn=' + startNo) : '');
	console.log(url);
	$.ajax({v,bg
		url: url,
		dataType: 'json',
		type: 'get',
		success: function(response){
			console.log(response)
			
			
			var html = listItemEJS.render(response.data);
			$("#list-guestbook").append(html);
			
			startNo = $("#list-guestbook li").last().data('no') || 0;
			console.log(startNo);
			
		}
	
});
	
	}

$(function(){
	//삭제 다이얼로그
	var dialogDelete = $("#dialog-delete-form").dialog({
		autoOpen: false,
		modal: true,
		buttons: {
			"삭제" : function(){
				//ajax 삭제
				console.log("삭제")
				
				$.ajax({
					
					
				});
				
				
			},
			"취소": function(){
				$(this).dialog('close');
			}
		}
	});
	
	//dialogDelete.dialog('open');
	
	//글 삭제 버튼(Live Event)
	$(document).on('click',"#list-guestbook li a" , function(event){
		//document에게 위임한다. 데이터가 새로 생기면..실행하게 하는...
		event.preventDefault();

		var no =$(this).data('no');
		$('#hidden-no').val(no);
		
		
		console.log(no);
		
		dialogDelete.dialog('open');
		
	});
	
	
	//form validation
	$("#add-form").submit(function(evnet){
		event.preventDefault();
		
		//이름
		var name = $("#input-name").val();
		if(!name){
			messageBox("새글 작성", '이름은 반드시 입력해야 합니다.',function(){
				$("#input-name").focus();
	        });
			//$("#input-name").focus(); 확인을 누르는 messageBox함수에서 끝나기 때문에,
			//앞의 명령이 먹지 않는다. 따라서 callback함수로 다시 돌아와 함수를 실행하도록 해주자
			return;
		}
		
		//비밀번호
		var password = $("#input-password").val();
		if(!password){
			messageBox("새글 작성", '비밀번호는 반드시 입력해야 합니다.',function(){
				$("#input-password").focus();
	        });		
			return;
		}
		
		//내용
		
		console.log("submit 완료")
		console.log("ajax insert")
		
	});
	
	
});


//실행 함수
$(function(){
	//리스트 가져오기
	listLoad();
	
	});
</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">
									
				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p>이름을 반드시 입력해야 합니다.</p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>