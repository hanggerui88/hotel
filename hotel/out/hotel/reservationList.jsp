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
    <link rel="stylesheet" href="./css/xadmin.css">
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <link href="css/back/style.css" rel="stylesheet">
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script>
        layui.use('laydate',function(){
            var laydate = layui.laydate;
            laydate.render({elem: '#cinDate'
            });
            laydate.render({elem:'#coutDate'
            });
            laydate.render({elem:'#madeDate'})
        });
    </script>
    <script >
        function checkDate() {
            var cinDate=document.getElementById("cinDate");
            var cinDatev=cinDate.value;
            var coutDate=document.getElementById("coutDate");
            var coutDatev=coutDate.value;
            var madeDate=document.getElementById("madeDate");
            var madeDatev=madeDate.value;
            var n=1;
            if(cinDate.value.length==0) {cinDate.value="2024-01-30";}
            if(coutDate.value.length==0){coutDate.value="2024-01-30";}
            if(madeDate.value.length==0){ madeDate.value="2024-01-30";}

            return true;
        }

    </script>
</head>
<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">预订管理</a>
        <a><cite>预订列表</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="reservationList" title="刷新">
        <i class="layui-icon" style="line-height:30px">⥁</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form id="searchForm" class="layui-form layui-col-md12 x-so" method="post" action="search_rsv">


            <input type="text" class="layui-input" name="cinDate" id="cinDate" placeholder="入住日期">
            <input type="text" class="layui-input" name="coutDate" id="coutDate" placeholder="离店日期" >

            <div class="layui-inline">
                <select name="rmtypeId" id="rmtypeId"  lay-search  >
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
            <div class="layui-inline">
                <select name="staffId" id="staffId"  lay-search  >
                    <option value="">对此订单负责的员工</option>
                    <c:forEach items="${map3}" var="b">
                        <c:if test="${not empty b.key}">
                            <option value='${b.key}'
                                    <c:if test="${param.staffId eq b.key}">selected="selected"</c:if>>
                                    ${b.value}
                            </option></c:if>
                    </c:forEach>
                </select>
            </div>

            <div class="layui-input-inline">
                <select name="reStatus" id="reStatus" >
                    <option value="">所有状态</option>
                    <option value="1" ${param.reStatus == '1' ? 'selected' : ''}>
                        确认订单
                    </option>
                    <option value="0" ${param.reStatus == '0' ? 'selected' : ''}>
                        取消订单
                    </option>
                </select>
            </div>
<%--1确认，0取消--%>
            <button   class="layui-btn" lay-submit=""><i class="layui-icon layui-icon-search"></i></button>
        </form></div>

    </div>
    <xblock>
        <span style="line-height:40px">共有数据：${total}条</span>
    </xblock>

    <!-- 表格内容保持不变 -->
    <table class="layui-table">
        <thead>
        <tr>
            <th>入住日期</th>
            <th>离店日期</th>
            <th>客人姓名(电话)</th>
            <th>房间类型</th>
            <th>房间号码</th>
            <th>对此订单负责的员工</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cs}" var="c">
            <tr >
                <td>
                    <fmt:formatDate value="${c.cinDate}" />
                </td>
                <td>
                    <fmt:formatDate value="${c.coutDate}"/>
                </td>

                <!-- 客人姓名和电话 -->
                <td>${c.cinName} (${c.cinPhone})</td>
                <td>${c.rtname}</td>

                <td>${c.rmNum}</td>

                <!-- 员工信息 -->
                <td>${c.staffName}</td>


                <!-- 状态显示 -->
                <td class="status-cell" data-status="${c.reStatus}">
                    <c:choose>
                        <c:when test="${c.reStatus == 0}">
                            <span class="status-badge canceled">取消订单</span>
                        </c:when>
                        <c:when test="${c.reStatus == 1}">
                            <span class="status-badge confirmed">确认订单</span>
                        </c:when>
                        <c:otherwise>
                            <span class="status-badge pending">error</span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <!-- 操作按钮 -->
                <td class="td-manage">
                    <a title="查看" onclick="x_admin_show('编辑','rsv_edit?id=${c.reId}')" href="javascript:;">
                        <i class="layui-icon">&#xe63c;</i>
                    </a>
                </td>
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
</body>
</html>