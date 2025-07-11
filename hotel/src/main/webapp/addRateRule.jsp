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
    <link rel="stylesheet" href="./lib/layui/css/layui.css"  media="all">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script type="text/javascript">
	 $(document).ready(function(){
	 if(${j}==1) {window.alert("增加成功",);var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);   }
	 else if(${j}==0) {window.alert("增加失败",);var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);   }
	});
    </script>
     <script>
        function currencyFormat(fld, milSep, decSep, e) {
            var sep = 0;
            var key = '';
            var i = j = 0;
            var len = len2 = 0;
            var strCheck = '0123456789';
            var aux = aux2 = '';
            var whichCode = (window.Event) ? e.which : e.keyCode;
            if (whichCode == 13) return true; 
            key = String.fromCharCode(whichCode); 
            if (strCheck.indexOf(key) == -1) return false; // Not a valid key
            len = fld.value.length;
            for(i = 0; i < len; i++)
            if ((fld.value.charAt(i) != '0') && (fld.value.charAt(i) != decSep)) break;
            aux = '';
            for(; i < len; i++)
            if (strCheck.indexOf(fld.value.charAt(i))!=-1) aux += fld.value.charAt(i);
            aux += key;
            len = aux.length;
            if (len == 0) fld.value = '';
            if (len == 1) fld.value = '0'+ decSep + '0' + aux;
            if (len == 2) fld.value = '0'+ decSep + aux;
            if (len > 2) {
            aux2 = '';
            for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
            aux2 += milSep;
            j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
            }
            fld.value = '';
            len2 = aux2.length;
            for (i = len2 - 1; i >= 0; i--)
            fld.value += aux2.charAt(i);
            fld.value += decSep + aux.substr(len - 2, len);
            }
            return false;
        }
    </script> 
 	<script >
    function checkDate() {
			var mon=document.getElementById("mon");
			if(mon.checked==true){
			 mon.value="true";
			 }else{
			 mon.value="false";
			 }
			  mon=document.getElementById("tue");
			if(mon.checked==true){
			 mon.value="true";
			 }else{
			 mon.value="false";
			 }
			  mon=document.getElementById("wed");
			if(mon.checked==true){
			 mon.value="true";
			 }else{
			 mon.value="false";
			 } mon=document.getElementById("thu");
			if(mon.checked==true){
			 mon.value="true";
			 }else{
			 mon.value="false";
			 } mon=document.getElementById("fri");
			if(mon.checked==true){
			 mon.value="true";
			 }else{
			 mon.value="false";
			 } mon=document.getElementById("sat");
			if(mon.checked==true){
			 mon.value="true";
			 }else{
			 mon.value="false";
			 } mon=document.getElementById("sun");
			if(mon.checked==true){
			 mon.value="true";
			 }else{
			 mon.value="false";
			 }

			return true;
	}
   	
    </script>
</head>
<body>
<div class="x-body">
        <form id="rate_rule_add" class="layui-form" method="post" action="rate_rule_add" onsubmit="return checkDate();" >
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>开始日期
              </label>
           <div class="layui-input-inline">
        <input type="text" class="layui-input" name="fromDate" id="fromDate" placeholder="yyyy-MM-dd" required="" lay-verify="required">
      </div>
          </div>
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>结束日期
              </label>
           <div class="layui-input-inline">
        <input type="text" class="layui-input" name="toDate" id="toDate" placeholder="yyyy-MM-dd" required="" lay-verify="fd">
      </div>
          </div>
          <div class="layui-form-item">
              <label class="layui-form-label">
                  <span class="x-red">*</span>房价类型
              </label>
            
      <div class="layui-input-inline">
        <select name="rateType" id="rateType" lay-verify="required" lay-search>
          <option value="">请选择</option>
          <c:forEach items="${s}" var='b'>
          	 <option value='${b.code}'>${b.code}</option>
          </c:forEach>
        </select>
    </div>
          </div>
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>客房类型
              </label>
              <div class="layui-input-inline">
                 <select name="rmType" id="rmType" lay-verify="required" lay-search>
          <option value="">请选择</option>
          <c:forEach items="${r}" var='b'>
          	 <option value='${b.code}'>${b.code}</option>
          </c:forEach>
        </select>
              </div>
          </div>
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>价格
              </label>
              <div class="layui-input-inline">
                  <input class="layui-input" type="number" step="0.01" id="rate" name="rate"  onKeyPress="return(currencyFormat(this,'','.',event))" required="" lay-verify="required">
              </div>
          </div>
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>生效日期
              </label>
              <div class="layui-input-block">
                  <input type="checkbox" id="mon" name="mon" title="周一"   >
                  <input type="checkbox" id="tue" name="tue" title="周二"  >
                   <input type="checkbox" id="wed"name="wed" title="周三"  >
                    <input type="checkbox" id="thu" name="thu" title="周四"  >
                     <input type="checkbox" name="fri" id="fri" title="周五"  >
                      <input type="checkbox" name="sat" id="sat" title="周六"  >
                       <input type="checkbox" name="sun" id="sun" title="周日"  >
              </div>
          </div>
          <div class="layui-form-item layui-form-text">
              <label  class="layui-form-label">
                  描述
              </label>
              <div class="layui-input-block">
                  <textarea  id="notes" name="notes" class="layui-textarea"></textarea>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">
                  增加
              </button>
          </div>
      </form>
    </div>
    
    <script>
    	layui.use('laydate',function(){
    	var laydate = layui.laydate;
    		laydate.render({elem: '#fromDate'
		});
			laydate.render({elem:'#toDate'			
			})
    	});
    </script>
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
            ,fd:function(value){
            	if($('#fromDate').val()>$('#toDate').val()){
            	return '开始日期不能大于结束日期';
            	}
            }
          });

        
          
          
        });
    </script>
   
   
</body>
</html>