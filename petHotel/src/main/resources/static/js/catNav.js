function checkLoggedInStatus() {
    	  if (localStorage.getItem('myUserId') === '' || localStorage.getItem('myUserId') === null) {
    	    $("#loginbtn").text('Login');
    	  } else {
    	    $("#loginbtn").text('Login');
    	    alert('已登出');
    	    localStorage.removeItem('myUserId');
			localStorage.removeItem('AdminMaster');
    	  }
    	}
     
    
      	 //註冊鈕、使用者資訊 狀態切換
 		 function signAndDatailsShow(){
 				 if(!(localStorage.getItem('myUserId'))){
        	  		$("#signUpShow").show();
        	  		$("#userDatails").hide();
          		}else{
        	 		 $("#signUpShow").hide();
        	 	     $("#userDatails").show();
         		 	}
 	   		 }   
	
      	 //管理者 頁面按鈕顯示
      	 function AdminShow(){
      		 if(localStorage.getItem('AdminMaster')==='1'){
      			$("#Admin").show();
      		 }else{
      			$("#Admin").hide();
      		 }
      	 }

	
      $(document).ready(function() {
    	  
    	  AdminShow();
    	  signAndDatailsShow();
    	  
    	  //每隔一秒叫一次signAndDatailsShow()、 AdminShow();
    	  setInterval(function() {
    		  signAndDatailsShow();
    		  AdminShow();
    		}, 1000); 
    	  
    	  
    	  
    	  
    	  
    	  //login按鈕事件
    	  $("#loginbtn").click(function() {
        	  event.preventDefault();
        	  
        	  checkLoggedInStatus();// 呼叫checkLoggedInStatus()
        	});
    	 
    	  
    	  //進來就先判斷session內是不是有值，有代表有使用者登入了，就顯示logout
    	  var myUserId = localStorage.getItem('myUserId');
    	  if (myUserId) {
    	    $("#loginbtn").text('Logout');
    	  }
    	  
    	  
    	 
    	 
    	  
  		$('#formLogin').submit(function(event) {
  			event.preventDefault();

  			
  			var email = $("#email").val();
  			
  			var password=$("#pwd").val();
  			
  			
  			

  			var data = {
  				"email" : email,
  				"password" : password,
  			};
  			
  			$.ajax({
  			   url:'Pet',
  			    type: 'POST',
  			    data: data,
  			    success: function(resp, textStatus, xhr) {
  			    	
  			    	
 			        if (xhr.status === 200) { 
 			        	
 			            // 登入成功，顯示相應的訊息

 			          	
 			           localStorage.setItem('myUserId', resp.user.email);//後端回應的資料取出user、email，設置到前端的localStorage內，以供日後備用
 			           localStorage.setItem('myUserName',resp.user.chinesName);
 			           localStorage.setItem('AdminMaster',resp.user.permission_id);
 			          	//alert(myUserId);
 			          	alert('登入成功！');
 			          	$("#loginbtn").text('Logout');
 			          	console.log(localStorage.getItem('myUserId'));//取用localStorage
 			        } else {
 			        	
 			        	console.log('登入失敗，請檢查您的帳號密碼');
 			        	
 	
 			            alert('登入失敗，請檢查您的帳號密碼！');
 			        }
  			    },
  			    error: function(xhr, textStatus, errorThrown) {
  			     
  			      alert('登入失敗，請檢查您的帳號密碼！');
  			    }
  			});


  		});
  	});