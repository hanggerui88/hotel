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
        <form id="addRateType" class="layui-form" method="post" action="tag_update" >
<%--            c.tagI--%>
            <input type="hidden" name="tagId" value="${c.tagId}">
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class="x-red">*</span>标签内容
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="content" name="content" required="" lay-verify="required" value="${c.content }"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <button  class="layui-btn" lay-filter="add" lay-submit="">
                    更改
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