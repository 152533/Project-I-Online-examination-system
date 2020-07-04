	  var websocket = null;
       
      //判断当前浏览器是否支持WebSocket
      if('WebSocket' in window){
          websocket = new WebSocket("ws://"+socketPath+"mysocket");
      }
      else{
          alert('Not support websocket')
      }
       
      //连接发生错误的回调方法
      websocket.onerror = function(){
    	  console.log('error')
      };
       
      //连接成功建立的回调方法
      websocket.onopen = function(event){
    	  //发送状态通知服务器，考试开始，开始计时
          send("start");
          console.log("send('start');");
      }
       
    //接收到消息的回调方法
      websocket.onmessage = function(e){
    	  e = e||event; 
          
    	  var data = eval('(' + e.data + ')');
    	  console.log(data.state);
    	  if (data.state=="start"){
    		  //服务器接收到开始消息，返回给客户端状态，客户端开始计时
    		  console.log("onmessage('start');");
    	  }else if (data.state=="end"){
    		  //服务器端计时结束，发送状态给客户端，客户端完毕提交试卷
    		  console.log("onmessage('end');");
    		  $("#actionSub").submit();
    	  }else if (data.state=="time"){
    		  
    		  	var EndTime= new Date(data.endtime);
    			var NowTime = new Date(data.currentime);
    		    var t =EndTime.getTime() - NowTime.getTime();
    		    
    		    var d=0;
    		    var h=0;
    		    var m=0;
    		    var s=0;
    		    if(t>=0){
    		      d=Math.floor(t/1000/60/60/24);
    		      h=Math.floor(t/1000/60/60%24);
    		      m=Math.floor(t/1000/60%60);
    		      s=Math.floor(t/1000%60);
    		    }
    		    
    		    document.getElementById("t_h").innerHTML = h;
			    document.getElementById("t_m").innerHTML = m;
			    document.getElementById("t_s").innerHTML = s;
    		    
    		   //console.log("onmessage('time'):endtime:"+data.endtime+":currentime:"+data.currentime+"_"+h+"时:"+m+"分:"+s+"秒");
    		  
    	  }
      }
      
    //连接关闭的回调方法
      websocket.onclose = function(){
    	  console(websocket.onclose);
    	  send("close");
      }
       
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = function(){
    	  console("onbeforeunload");
    	  send("close");
          websocket.close();
      }
       
    //将消息显示在网页上
      function setMessageInnerHTML(innerHTML){
          
      }
      
      function setRequestInnerHTML(innerHTML){
          
      }
    //关闭连接
      function closeWebSocket(){
          websocket.close();
      }
       
    //发送消息
      function send(message){
    	  
    	  websocket.send(message);
    	  
      }
      