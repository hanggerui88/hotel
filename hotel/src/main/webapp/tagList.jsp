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
        <a href="">标签列表</a>
        <a>
          <cite>标签列表</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="tagList" title="刷新">
        <i class="layui-icon" style="line-height:30px">⥁</i></a>
    </div>
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" method="post" action="tag_search">
          <div class="layui-input-inline">

              <input class="layui-input" placeholder="标签编号" name="tagId" id="tagId">
              <input class="layui-input" placeholder="标签" name="content" id="content">

    			</div>
          <button class="layui-btn"  lay-submit="" lay-filter="sreach" ><i class="layui-icon layui-icon-search"></i></button>
        </form>
      </div>

      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"  ><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加标签','./addTag.jsp')"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：${total}条</span>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>标签编号</th>
            <th>标签</th>
              <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${cs}" var="c">
          <tr>
            <td>
              <div name='cb' class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id=${c.tagId}><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td name='id'>${c.tagId}</td>
            <td>${c.content}</td>
            <td class="td-manage">
              <a title="查看"  onclick="x_admin_show('编辑','tag_edit?id=${c.tagId}')" href="javascript:;">
                <i class="layui-icon">&#xe63c;</i>
              </a>
              <a deleteLink="true" title="删除" href="tag_delete?id=${c.tagId}"  >
                <i class="layui-icon">&#xe640;</i>
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
  	 	window.location.href="tag_delete_group?id="+data;
  	}
  	}else{
	  alert("请选择一条或多条数据进行删除");
	}

    </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();
     </script>
   
  </body>
</html>