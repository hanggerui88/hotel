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
    <link rel="stylesheet" href="lib/layui/css/layui.css" media="all">
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
    		laydate.render({elem: '#fromDate'
		});
			laydate.render({elem:'#toDate'			
			})
			
    	});
    </script>
    <script >
    function checkDate() {
			var fromDate=document.getElementById("fromDate");
			var toDate=document.getElementById("toDate");
			
			if(fromDate.value.length==0) {fromDate.value="2000-01-30";}
			if(toDate.value.length==0){toDate.value="2000-01-30";}

			return true;
	}
   	
    </script>
</head>
<body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">房价管理</a>
        <a>
          <cite>房价规则</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="rateruleList" title="刷新">
        <i class="layui-icon" style="line-height:30px">⥁</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" method="post" action="rate_rule_search" onsubmit="return checkDate();">

           <input type="text" class="layui-input" name="fromDate" id="fromDate" placeholder="开始日期">
        <input type="text" class="layui-input" name="toDate" id="toDate" placeholder="结束日期" >
            <input type="number" class="layui-input" name="id" id="id" placeholder="房间号码" >
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

          <button class="layui-btn"  lay-submit="" lay-filter="sreach" ><i class="layui-icon layui-icon-search"></i></button>
        </form>
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"  ><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加房价规则','show_rate_rule_add')"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：${total}条</span>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th rowspan="2">
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th rowspan="2">开始日期</th>
            <th rowspan="2">结束日期</th>
            <th rowspan="2">房间类型或者房间号码</th>
            <th rowspan="2">规则类型</th>
              <th rowspan="2">规则内容</th>
              <th rowspan="2">可取消预定时间</th>
            <th colspan="7">生效(周)</th>
            <th rowspan="2">操作</th>
            </tr>
            <tr>
            <th>周一</th>
            <th>周二</th>
            <th>周三</th>
            <th>周四</th>
            <th>周五</th>
            <th>周六</th>
            <th>周日</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${cs}" var="c">
          <tr id="biuuu_city_list">
            <td>
              <div name='cb' class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id=${c.rateruleId}><i class="layui-icon">&#xe605;</i></div>
            </td>
              <td><fmt:formatDate value="${c.dateStart}" pattern="yyyy-MM-dd"/></td>
              <td><fmt:formatDate value="${c.dateEnd}" pattern="yyyy-MM-dd"/></td>
              <td>
                  <c:choose>
                      <c:when test="${not empty c.rmtype}">
                          ${c.rmtype}
                      </c:when>
                      <c:when test="${not empty c.rmNumber}">
                          ${c.rmNumber}
                      </c:when>
                      <c:otherwise>
                          未指定
                      </c:otherwise>
                  </c:choose>
              </td>
              <td>
                  <c:choose>
                      <c:when test="${c.formula eq '1'}">折扣</c:when>
                      <c:when test="${c.formula eq '2'}">固定加价</c:when>
                      <c:when test="${c.formula eq '3'}">满减</c:when>
                      <c:when test="${c.formula eq '4'}">减价</c:when>
                      <c:otherwise>${c.formula}</c:otherwise>
                  </c:choose>
              </td>
              <td>
                  <c:choose>
                      <c:when test="${c.formula eq '3'}">
                          满${c.conditionValue}减${c.discountValue}
                      </c:when>

                      <c:when test="${c.formula eq '2'}">
                          原价+${c.discountValue}
                      </c:when>
                      <c:when test="${c.formula eq '4'}">
                          原价-${c.discountValue}
                      </c:when>
                      <c:when test="${c.formula eq '1'}">
                          <fmt:formatNumber value="${c.discountValue * 100}" pattern="0"/>折
                      </c:when>

                      <c:otherwise>
                          标准价格
                      </c:otherwise>
                  </c:choose>
              </td>

              <td>
          <span class="layui-badge layui-bg-gray">
            入住前${c.canceltime}天取消
          </span>
              </td>
            <td>
                <c:if test="${c.mon != null && c.mon == true}">
                    <i class="layui-icon layui-icon-circle-dot" style="color:pink"></i>
                </c:if>
			</td>
              <td>
                  <c:if test="${c.tue != null && c.mon == true}">
                      <i class="layui-icon layui-icon-circle-dot" style="color:pink"></i>
                  </c:if>
              </td>
              <td>
                  <c:if test="${c.wed != null && c.mon == true}">
                      <i class="layui-icon layui-icon-circle-dot" style="color:pink"></i>
                  </c:if>
              </td>
              <td>
                  <c:if test="${c.thu != null && c.mon == true}">
                      <i class="layui-icon layui-icon-circle-dot" style="color:pink"></i>
                  </c:if>
              </td>
              <td>
                  <c:if test="${c.fri != null && c.mon == true}">
                      <i class="layui-icon layui-icon-circle-dot" style="color:pink"></i>
                  </c:if>
              </td>
              <td>
                  <c:if test="${c.sat != null && c.mon == true}">
                      <i class="layui-icon layui-icon-circle-dot" style="color:pink"></i>
                  </c:if>
              </td>
              <td>
                  <c:if test="${c.sun != null && c.mon == true}">
                      <i class="layui-icon layui-icon-circle-dot" style="color:pink"></i>
                  </c:if>
              </td>
			 <td class="td-manage">
              <a title="查看"  onclick="x_admin_show('编辑','rate_rule_edit?rateruleId=${c.rateruleId}')" href="javascript:;">
                <i class="layui-icon">&#xe63c;</i>
              </a>
              <a deleteLink="true" title="删除" href="rate_rule_delete?rateruleId=${c.rateruleId}"  >
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
  	 	window.location.href="rate_rule_delete_group?ids="+data;
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