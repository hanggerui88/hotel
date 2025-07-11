<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
</head>
<body>
<div class="x-body">
        <form id="addRateType" class="layui-form" method="post" action="staff_update" >
        <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>编号
              </label>
              <div class="layui-input-inline">
                  <input type="number" id="id" name="id" required="" lay-verify="required" value="${c.staffId}"
                  autocomplete="off" class="layui-input" readonly="readonly">
              </div>
          </div>
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="username" required="" lay-verify="required" value="${c.username}"
                  autocomplete="off" class="layui-input" readonly="readonly">
              </div>
          </div>

          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>部门
              </label>
              <div class="layui-input-inline">
					<input type="text" id="dept" name="dept" required="" lay-verify="required" value="${c.department}"
                  autocomplete="off" class="layui-input" readonly="readonly">
    			</div>
          </div>
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>权限
              </label>
              <div class="layui-input-inline">
				<input type="text" id="position" name="position" required="" lay-verify="required" value="${c.qx}"
                  autocomplete="off" class="layui-input" readonly="readonly">
    			</div>
          </div>
      </form>
    </div>
    <script>
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
          var form = layui.form
          ,layer = layui.layer;
        
          //自定义验证规则
          form.verify({
            nikename: function(value){
              if(value.length < 5){
                return '昵称至少得5个字符啊';
              }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,repass: function(value){
                if($('#L_pass').val()!=$('#L_repass').val()){
                    return '两次密码不一致';
                }
            }
            ,code:[/^[\S]{3,3}$/,'代码必须3个字母或数字，且不能出现空格']
          });

          //监听提交
          form.on('submit(add)', function(data){
            /* console.log(data);  */
            //发异步，把数据提交给php
            window.alert("修改成功", {icon: 6});
            var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);    
            return true;
          });
          
          
        });
    </script>
   
</body>
</html>