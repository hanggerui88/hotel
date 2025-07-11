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
    <script>
        layui.use('laydate',function(){
            var laydate = layui.laydate;
            laydate.render({elem: '#dateStart'
            });
            laydate.render({elem:'#dateEnd'
            })
        });
    </script>
</head>
<body>
<div class="x-body">
        <form id="addRoomType" class="layui-form" method="post" action="room_rule_update" >
            <input type="hidden" name="rmstatusruleId" value="${c.rmstatusruleId}">
            <input type="hidden" name="name" value="${c.name}">

            <div class="layui-form-item">
                <label  class="layui-form-label">
                    <span class="x-red">*</span>开始日期
                </label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="dateStart" id="dateStart" placeholder="yyyy-MM-dd" required="" lay-verify="required"
                           value="<fmt:formatDate value='${c.dateStart}' pattern='yyyy-MM-dd' />">
                </div>
            </div>
            <div class="layui-form-item">
                <label  class="layui-form-label">
                    <span class="x-red">*</span>结束日期
                </label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="dateEnd" id="dateEnd" placeholder="yyyy-MM-dd" required="" lay-verify="required"
                           value="<fmt:formatDate value='${c.dateEnd}' pattern='yyyy-MM-dd' />">
                </div>
            </div>

           <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>房间类型
              </label>
              <div class="layui-inline">
                  <select name="rmtypeId" id="rmtypeId"  lay-search  >
                      <option value="">房间类型</option>
                      <c:forEach items="${map}" var="b">
                          <c:if test="${not empty b.key}">
                              <option value='${b.key}'>
                                  <c:if test="${param.rmtypeId eq b.key}">selected="selected"</c:if>
                                      ${b.value}</option></c:if>
                      </c:forEach>
                  </select>
              </div>
          </div>


          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="x-red">*</span>房间号码
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="rmNumber" name="rmNumber" required="" lay-verify="number"
                  autocomplete="off" class="layui-input" value="${c.rmNumber }">
              </div>
          </div>
            <div class="layui-form-item">
                <label  class="layui-form-label">
                    <span class="x-red">*</span>操作
                </label>
                <div class="layui-input-inline">
                    <select name="statusNumber" id="statusNumber" required lay-verify="required">
                        <option value="0" <c:if test="${c.statusNumber eq 0}">selected</c:if>>默认</option>
                        <option value="1" <c:if test="${c.statusNumber eq 1}">selected</c:if>>出售</option>
                        <option value="2" <c:if test="${c.statusNumber eq 2}">selected</c:if>>维修</option>
                        <option value="3" <c:if test="${c.statusNumber eq 3}">selected</c:if>>纠纷停售</option>
                        <option value="4" <c:if test="${c.statusNumber eq 4}">selected</c:if>>打扫</option>
                        <option value="5" <c:if test="${c.statusNumber eq 5}">selected</c:if>>空房</option>
                        <option value="6" <c:if test="${c.statusNumber eq 6}">selected</c:if>>取消预订</option>
                    </select>

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
</body>
</html>