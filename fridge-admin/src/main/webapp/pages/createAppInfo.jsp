<%
    String contextPath = request.getContextPath();
%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="<%=contextPath%>/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<%=contextPath%>/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
    <link rel="stylesheet" href="<%=contextPath%>/dist/css/skins/skin-blue.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->
      <title>我的冰箱后台管理系统</title>
  </head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../header.jsp"></jsp:include>
    <jsp:include page="../aside.jsp"></jsp:include>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                APP管理
                <small>新建版本</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">APP管理</a></li>
                <li class="active">新建版本</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <form id="createAppInfoForm" name="createAppInfoForm" action="<%=contextPath%>/createAppInfo.do" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="app_name">名称</label>
                    <input type="text" name="app_name" class="form-control" id="app_name" placeholder="食品名称" onblur="validateFoodInfoName();">
                </div>
                <div class="form-group">
                    <label for="app_info">描述</label>
                    <input type="text" name="app_info" class="form-control" id="app_info" placeholder="食品描述">
                </div>
                <div class="form-group">
                    <label for="app_version">版本号</label>
                    <input type="text" name="app_version" class="form-control" id="app_version" placeholder="食品简称">
                </div>
                <div class="form-group">
                    <label for="app_path">文件</label>
                    <input type="file" id="app_path" name="app_path">
                    <p class="help-block">Example block-level help text here.</p>
                </div>
                <button type="button" onclick="submitForm();" class="btn btn-default">提交</button>
            </form>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <jsp:include page="../asideControl.jsp"></jsp:include>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.0 -->
<script src="<%=contextPath%>/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<%=contextPath%>/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=contextPath%>/dist/js/app.min.js"></script>
<script src="<%=contextPath%>/dist/js/pages/createFoodInfo.js"></script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>