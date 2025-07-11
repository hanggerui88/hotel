<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="lib/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="./css/xadmin.css">
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <link href="css/back/style.css" rel="stylesheet">
    <script src="js/jquery-3.3.1/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script>
        layui.use('laydate',function(){
            var laydate = layui.laydate;
            laydate.render({elem: '#ddate'
            });
            laydate.render({elem:'#dateStart'
            })
        });
    </script>
</head>
<body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">房价管理</a>
        <a>
          <cite>每日房价</cite></a>
      </span>
      
    </div>
    <div  class="x-body">
        <form class="layui-form layui-col-md12 x-so" method="post" action="vw_rate_search">
              <div class="layui-row">
        <input type="text" class="layui-input" name="ddate" id="ddate" placeholder="日期" >
        <input type="text" class="layui-input" name="rmNumber" id="rmNumber" placeholder="房间号码" >

		<div class="layui-inline">
            <select name="roomType" id="roomType" lay-search>
                <option value="">房间类型</option>
                <c:forEach items="${map}" var="b">
                    <c:if test="${not empty b.key}">
                        <option value="${b.value}"
                                <c:if test="${param.rmType eq b.key}">selected="selected"</c:if>>
                                ${b.value}
                        </option>
                    </c:if>
                </c:forEach>
            </select>
		</div>
          <button class="layui-btn"  lay-submit="" lay-filter="sreach" ><i class="layui-icon layui-icon-search"></i></button>
          </div>
        </form>

        <table class="layui-table">
        <thead>
          <tr>
            <th>日期</th>
            <th>房间号码</th>
            <th>房间名称</th>
              <th>房间类型</th>
            <th>折扣类型</th>
            <th>价格</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${cs}" var="c">
          <tr>
              <td><fmt:formatDate value="${c.ddate}" pattern="yyyy-MM-dd"/></td>

            <td>${c.rmNumber}</td>
            <td>${c.roomName}</td>
            <td>${c.roomType}</td>
              <td>${c.rateRuleName}</td>
            <td>¥ ${c.finalPrice}</td>
          </tr>
        </c:forEach>

        </tbody>
      </table>


        <c:if test="${sear eq 1}">
            <script>
                function generatePageLink(pageNum) {
                    const params = new URLSearchParams(window.location.search);
                    params.set("page", pageNum);
                    let uri = '${pageContext.request.requestURI}';
                    if(uri.endsWith('.jsp')) {
                        uri = uri.substring(0, uri.length - 4);
                    }
                    return uri+'?' + params.toString();
                }
                function goToPage(pageNum) {
                    window.location.href = generatePageLink(pageNum);
                }
            </script>

            <div class="pageDiv">
                <c:if test="${totalPages > 0}">
                    <ul class="pagination">
                        <!-- 上一页 -->
                        <c:choose>
                            <c:when test="${page > 0}">
                                <li><a href="javascript:;" onclick="goToPage(${page - 1})">«</a></li>
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
                                        <li><a href="javascript:;" onclick="goToPage(${i})">${i + 1}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </c:forEach>

                        <!-- 下一页 -->
                        <c:choose>
                            <c:when test="${page < totalPages - 1}">
                                <li><a href="javascript:;" onclick="goToPage(${page + 1})">»</a></li>
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
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
      var form = layui.form
      ,layer = layui.layer;
    
      //自定义验证规则
      form.verify({
        
        fdk:function(value){
        	if($('#ciDate').val()>=$('#coDate').val()){
        	return '开始日期不能大于结束日期';
        	}
        }});	
      });
    </script>

   
  </body>
</html>