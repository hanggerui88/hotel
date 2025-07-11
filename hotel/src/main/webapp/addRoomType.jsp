<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script type="text/javascript">
	 $(document).ready(function(){
	 if(${j}== 1 ) {
         window.alert("成功",);
         var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);}
	 else if(${j}==0 ) {window.alert("失败",);
         var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);   }
	});
    </script>
</head>
<body>
<div class="x-body">
        <form id="addRoomType" class="layui-form" method="post" action="room_type_add" >
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>房型名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="name" name="name" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
            <div class="layui-form-item">
                <label  class="layui-form-label">
                    <span class="x-red">*</span>价格
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="rate" name="rate" required="" lay-verify="number"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

          <div class="layui-form-item layui-form-text">
              <label  class="layui-form-label">
                  备注
              </label>
              <div class="layui-input-block">
                  <textarea  id="notes" name="notes" class="layui-textarea"></textarea>
              </div>
          </div>
          <div class="layui-form-item">
              <button  class="layui-btn" lay-filter="add" lay-submit="">
                  增加
              </button>
          </div>
      </form>
    </div>

   
</body>
</html>