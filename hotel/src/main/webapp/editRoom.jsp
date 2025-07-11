<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
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
	 if(${j}==1) {window.alert("修改成功",);var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);   }
	 else if(${j}==0) {window.alert("修改失败",);var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);   }
	});
    </script>
</head>
<body>
<div class="x-body">
        <form id="addRoomType" class="layui-form" method="post" action="room_update" >
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>房间名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="name" name="name" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${c.name}">
              </div>
          </div>
            <div class="layui-form-item">
                <label  class="layui-form-label">
                    <span class="x-red">*</span>房间图片
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="image" name="image" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" value="${c.image}">
                </div>
            </div>
            <div class="layui-form-item">
                <label  class="layui-form-label">
                    <span class="x-red">*</span>房间号码
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="rmNumber" name="rmNumber" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" value="${c.rmNumber}">
                </div>
            </div>
            <div class="layui-form-item">
                <label  class="layui-form-label">
                    <span class="x-red">*</span>房间类型
                </label>
                <div class="layui-input-inline">
                    <select name="roomtypeId" id="roomtypeId"  lay-search  >
                        <option value="">房间类型</option>
                        <c:forEach items="${map}" var="b">
                            <c:if test="${not empty b.key}">
                                <option value='${b.key}'
                                        <c:if test="${param.rmtypeId eq b.key}">selected="selected"</c:if>>
                                        ${b.value}
                                </option></c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="tagId" id="tagId" lay-search>
                        <option value="">房间标签</option>
                        <c:forEach items="${map2}" var='b'>
                            <c:if test="${not empty b.key}">
                                <option value='${b.key}'
                                        <c:if test="${param.tagId eq b.key}">selected="selected"</c:if>>
                                        ${b.value}
                                </option></c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>评分
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="pf" name="pf" required="" lay-verify="number"
                  autocomplete="off" class="layui-input" value="${c.pf }">
              </div>
          </div>
          <div class="layui-form-item layui-form-text">
              <label  class="layui-form-label">
                  备注
              </label>
              <div class="layui-input-block">
                  <textarea  id="notes" name="notes" class="layui-textarea"  >${c.notes}</textarea>
              </div>
          </div>
          <div class="layui-form-item">

              <button  class="layui-btn" lay-filter="add">
                  修改
              </button>
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

        
          
          
        });
    </script>
   
</body>
</html>