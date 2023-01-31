let index = {
		
		init: function() {
			$("#btn-save").on("click", () =>{
				this.save();
			});
		},
		
		save: function() {
			// alert("save버튼 함수 호출됨");
			let data = {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val()
			}

			// console.log(data);

			// ajax요청 3개의 데이터를 json으로 변경해 insert 요청 ajax기본꼴 $.ajax( {오브젝트} ).done(
			// 함수{} ).fail( 함수{} )
			$.ajax({
				type:"POST",
				url:"/auth/joinProc",
				data:JSON.stringify(data),				// 자바스크립트 오브젝트를 json으로 변환
				contentType:"application/json; charset-utf-8",
				dataType:"json"				// dataType이 json일 경우 서버로 요청을한후 응답이 왔을때
											// 기본적으로 문자열, (생긴게 json이라면 )
											// =>javascript 오브젝트로 변경해줌
			}	
			).done(function(res) {
				console.log(res);
				
				if(res.status == 500){
					alert("회원가입실패")
				}
				else{
					alert("회원가입완료");
				}
				location.href="/";
			}).fail(function(error) {
				alert(JSON.stringify(error))
			}
			);
		},
};


index.init();