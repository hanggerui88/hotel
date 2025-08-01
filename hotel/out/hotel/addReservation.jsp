<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script type="text/javascript" src="./js/jquery-3.3.1/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script type="text/javascript">
	 $(document).ready(function(){
	 if(${j}==1) {window.alert("Success",);var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);   }
	 else if(${j}==0) {window.alert("Fail",);var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);   }
	});
    </script>
</head>
<body>
<div class="x-body">
	<form id="rate_rule_add" class="layui-form" method="post" action="rsv_update" >
		<div class="layui-form-item">
			<div class="layui-col-xs6">
				<label  class="layui-form-label">
					<span class="x-red">*</span>入住日期
				</label>
				<div class="layui-input-inline">
					<input type="date" class="layui-input" name="cinDate" id="cinDate"  value="<fmt:formatDate value='${c.cinDate}' pattern='yyyy-MM-dd' />"
						   placeholder="yyyy-MM-dd" required="" lay-verify="fd">
				</div>

			</div>
			<div class="layui-col-xs6">
				<label  class="layui-form-label">
					<span class="x-red">*</span>离店日期
				</label>
				<div class="layui-input-inline">
					<input type="date" class="layui-input" name="coutDate"  id="coutDate" value="<fmt:formatDate value='${c.coutDate}' pattern='yyyy-MM-dd' />"
						   placeholder="yyyy-MM-dd" required="" lay-verify="fd">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-col-xs6">
				<label  class="layui-form-label">
					<span class="x-red">*</span>客房类型
				</label>
				<div class="layui-input-inline">
					<select name="rmtypeId" id="rmtypeId"  lay-search  >
						<option value="${c.rtname}">房间类型</option>
						<c:forEach items="${map}" var="b">
							<c:if test="${not empty b.key}">
								<option value='${b.key}'
										<c:if test="${param.rmtypeId eq b.key}">selected="selected"</c:if>>
										${b.value}
								</option></c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-col-xs6">
				<label  class="layui-form-label">
					<span class="x-red">*</span>房间数
				</label>
				<div class="layui-input-inline">
					<input type="number" id="rmNum" value="${c.rmNum}" name="quantity" required="" lay-verify="number"
						   autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-col-xs6">
				<label  class="layui-form-label">
					<span class="x-red">*</span>成人数
				</label>
				<div class="layui-input-inline">
					<input type="number" id="adultNum" name="adultNum" required="" lay-verify="number" value="${c.adultNum}"
						   autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-col-xs6">
				<label  class="layui-form-label">
					<span class="x-red">*</span>儿童数
				</label>
				<div class="layui-input-inline">
					<input type="number" id="childNum" name="childNum" required="" lay-verify="number" value="${c.childNum}"
						   autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-xs6">
				<label  class="layui-form-label">
					<span class="x-red">*</span>姓名
				</label>
				<div class="layui-input-inline">
					<input type="text" id="cinName" name="cinName" value="${c.cinName}" required="" lay-verify="required"
						   autocomplete="off" class="layui-input" placeholder="">
				</div>
			</div>
			<div class="layui-col-xs6">
				<label  class="layui-form-label">
					<span class="x-red">*</span>联系电话
				</label>
				<div class="layui-input-inline">
					<input type="text" id="cinPhone" name="cinPhone" value="${c.cinPhone}" required="" lay-verify="phone"
						   autocomplete="off" class="layui-input" >
				</div>
			</div>

		</div>

		<div class="layui-form-item">
			<div class="layui-col-xs6">
				<label  class="layui-form-label">
					<span class="x-red">*</span>免费取消时限
				</label>
				<div class="layui-input-inline" >
					<input type="text" id="canceltime" name="canceltime" required="" lay-verify=""
						   autocomplete="off" class="layui-input"
						   value="<fmt:formatDate value='${c.canceltime}' pattern='yyyy-MM-dd' />" readonly="readonly">
				</div>
			</div>
			<div class="layui-col-xs6">
				<label  class="layui-form-label" >
					<span class="x-red">*</span>创建时间
				</label>
				<div class="layui-input-inline" >
					<input type="text" id="createDate" name="createDate" required="" lay-verify="" placeholder="yyyy-MM-dd"
						   value="<fmt:formatDate value='${c.createDate}' pattern='yyyy-MM-dd' />"
						   autocomplete="off" class="layui-input" readonly="readonly">
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label  class="layui-form-label">
				用户做的订单备注
			</label>
			<div class="layui-input-block">
				<textarea  id="note" name="note" class="layui-textarea">${c.note}</textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<%--订单取消时不显示按钮--%>
			<c:if test="${c.reStatus != 0}">
				<button  class="layui-btn"  lay-filter="add" lay-submit="">
					增加
				</button>
			</c:if>
		</div>
	</form>
    </div>
    
    <script>
    	layui.use('laydate',function(){
    	var laydate = layui.laydate;
    		laydate.render({elem: '#ciDate'
		});
			laydate.render({elem:'#coDate'			
			});
    	});
    </script>
    <script>
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
          var form = layui.form;
        
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
   <script>
    $('#refresh').click(function(){
    		$.ajax({
    			data:{"ciDate":"123",
    			coDate:document.getElementById("coDate").value,
    			rmType:document.getElementById("rmType").value,
    			rateType:document.getElementById("rateType").value},
    			contentType : "application/x-www-form-urlencoded",
    			type:"POST",
    			url:"query_rate_rsv",
    			success:function(msg){
    				alert("Success!");
    			}
    		});
    	});
    </script>
   
</body>
</html>