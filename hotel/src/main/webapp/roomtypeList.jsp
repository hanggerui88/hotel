<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>住宿登记辅助系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <link href="css/back/style.css" rel="stylesheet">
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
	<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
</head>
<body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">客房管理</a>
        <a>
          <cite>房型列表</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="roomtypeList" title="刷新">
        <i class="layui-icon" style="line-height:30px">⥁</i></a>
    </div>
    <div class="x-body">

        <xblock>
            <button class="layui-btn layui-btn-danger" onclick="delAll()"  ><i class="layui-icon"></i>批量删除</button>
            <button class="layui-btn" onclick="x_admin_show('添加房型','./addRoomType.jsp')"><i class="layui-icon"></i>添加</button>
            <span class="x-right" style="line-height:40px">共有数据：${total}条</span>
        </xblock>

      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
              <th>房间类型</th>
              <th>价格</th>
            <th>备注</th>
            <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${cs}" var="c">
          <tr>
            <td>
              <div name='cb' class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id=${c.rmtypeId}><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>${c.name}</td>
            <td>${c.rate}</td>
              <td>${c.notes}</td>
            <td class="td-manage">
              <a title="查看"  onclick="x_admin_show('编辑','room_type_edit?rmtypeId=${c.rmtypeId}')" href="javascript:;">
                <i class="layui-icon">&#xe63c;</i>
              </a>
              <a deleteLink="true" title="删除" href="room_type_delete?rmtypeId=${c.rmtypeId}"  >
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
        <c:if test="${sear eq 1}">
            <div class="pageDiv">
                <c:if test="${totalPages > 0}">
                    <ul class="pagination">
                        <!-- 上一页 -->
                        <c:choose>
                            <c:when test="${page > 0}">
                                <li><a href="javascript:;" onclick="location.href=generatePageLink(${page - 1})">«</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="disabled"><span>«</span></li>
                            </c:otherwise>
                        </c:choose>

                        <!-- 页码 -->
                        <c:forEach var="i" begin="0" end="${totalPages - 1}" varStatus="status">
                            <c:if test="${(i >= page - 3 && i <= page + 3) || (i <= 5) || (i >= totalPages - 5)}">
                                <c:choose>
                                    <c:when test="${i == page}">
                                        <li class="active"><span>${i + 1}</span></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="javascript:;" onclick="location.href=generatePageLink(${i})">${i + 1}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </c:forEach>

                        <!-- 下一页 -->
                        <c:choose>
                            <c:when test="${page < totalPages - 1}">
                                <li><a href="javascript:;" onclick="location.href=generatePageLink(${page + 1})">»</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="disabled"><span>»</span></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </c:if>
            </div>
        </c:if>

    </div>
    <script>
      layui.use('laydate', function(){
        var laydate = layui.laydate;
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });

      

      /*用户-删除*/
      $(function(){
      		$("a").click(function(){
      			var deleteLink=$(this).attr("deleteLink");
      			if("true"==deleteLink){
      				var i=window.confirm("确认要删除吗?");
      				if(i)
      					return true;
      				return false;
      			}
      		});
      })
      



      function delAll (argument) {
      var data = tableCheck.getData();
  	if(data.length!=0){
  	var flag=window.confirm("确认要删除吗？");
     if(flag){
     console.info(data);
     $(".layui-form-checked").not('.header').parents('tr').remove();
  	 	window.location.href="room_type_delete_group?rmtypeIds="+data;
  	}
  	}else{
	  alert("请选择一条或多条数据进行删除");
	}


      }
    </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
  </body>
</html>