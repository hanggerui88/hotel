<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>住宿登记辅助系统</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/iconfont.css">
    <link rel="stylesheet" href="./css/demo.css">
	<link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
     <script type="text/javascript">
	 $(document).ready(function(){

			if("${power}" == ""){
	 		 window.alert("请先登录",);
	 	window.location.href="index";
	 		 }


	});
    </script>

</head>
<body>
	 <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a >住宿登记辅助系统</a></div>
        <div class="left_open">

            <i title="展开左侧栏" class="layui-icon layui-icon-screen-full"></i>
        </div>
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">Hi, ${staff.username}</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a onclick="x_admin_show('个人信息','staff_person?id=${staff.staffId}')">个人信息</a></dd>
                <dd><a _href="/">退出登陆</a></dd>
            </dl>
          </li>
        </ul>
    </div>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
     <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="layui-icon layui-icon-ok-circle"></i>
                    <cite>预订管理</cite>
					<em class="layui-nav-more"></em>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="reservationList">
                            <i class="layui-icon layui-icon-right"></i>
                            <cite>订单列表</cite>
                        </a>
                    </li >
                </ul>
            </li>

             <li>
                <a href="javascript:;">
                    <i class="layui-icon layui-icon-home"></i>
                    <cite>客房管理</cite>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="roomtypeList">
                            <i class="layui-icon layui-icon-right"></i>
                            <cite>房间类型列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="roomList">
                            <i class="layui-icon layui-icon-right"></i>
                            <cite>房间列表</cite>
                        </a>
                    </li>
                    <c:if test="${staff.department=='admin'}">
                    <li>
                        <a _href="rmstatusruleList">
                            <i class="layui-icon layui-icon-right"></i>
                            <cite>房间操作</cite>
                        </a>
                    </li>
                    </c:if>
                    <li>
                        <a _href="vwroomstatusList">
                            <i class="layui-icon layui-icon-right"></i>
                            <cite>每日房间状态</cite>
                        </a>
                    </li>
                </ul>
            </li>
             <li>
                <a href="javascript:;">
                    <i class="layui-icon layui-icon-rmb"></i>
                    <cite>房价管理</cite>

                </a>
                <ul class="sub-menu">
                    <c:if test="${staff.department=='admin'}">
                    <li>
                        <a _href="rateruleList">
                            <i class="layui-icon layui-icon-right"></i>
                            <cite>房价操作</cite>
                        </a>
                    </li >

                    </c:if>
                    <li>
                        <a _href="vwrateList">
                            <i class="layui-icon layui-icon-right"></i>
                            <cite>每日房价</cite>
                        </a>
                    </li>
                </ul>
            </li>
             <li>
                <a href="javascript:;">
                    <i class="layui-icon layui-icon-rate-solid"></i>
                    <cite>标签管理</cite>

                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="tagList">
                            <i class="layui-icon layui-icon-right"></i>
                            <cite>标签列表</cite>
                        </a>
                    </li >
                </ul>
            </li>
           <c:if test="${staff.department=='admin'}">
            <li>
                <a href="javascript:;">
                    <i class="layui-icon layui-icon-user"></i>
                    <cite>员工管理</cite>

                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="staffList">
                            <i class="layui-icon layui-icon-right"></i>
                            <cite>员工列表</cite>
                            
                        </a>
                    </li>
                </ul>
            </li>
           </c:if>
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li>我的桌面</li>
          </ul>
          <div class="layui-tab-content">

          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer">
        <div class="copyright"> Copyright  @2025  by  Sinead </div>
    </div>
    <!-- 底部结束 -->
    
</body>
</html>