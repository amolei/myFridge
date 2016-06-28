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
                食品管理
                <small>食品管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">食品管理</a></li>
                <li class="active">食品管理</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header with-border">
                            <div class="box-title">
                                <a href="pages/createFoodInfo.jsp" id="create" class="btn btn-block btn-primary" type="button">新建食品</a>
                            </div>
                            <div>
                                <select id="foodKindSelect" class="form-control">
                                </select>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="example2" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>名称</th>
                                    <th>描述</th>
                                    <th>简称</th>
                                    <th>分类</th>
                                    <th>图片</th>
                                    <th>是否热点</th>
                                    <th width="10%">操作</th>
                                </tr>
                                </thead>

                                <tbody id="foodInfoTBody">
                                <c:forEach var="item" items="${foodInfoList}" varStatus="status">
                                    <tr>
                                        <td>${item.food_id}</td>
                                        <td>${item.food_name}
                                        </td>
                                        <td>${item.food_des}</td>
                                        <td>${item.simple_name}</td>
                                        <td>${item.foodKindDto.food_kind_name}</td>
                                        <td><img width="60" height="60" src="http://139.196.171.209/${item.food_img}"></td>
                                        <td><c:choose><c:when test="${item.hot==1}">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>
                                        <td><a class="btn btn-block btn-danger" type="button" href="delFoodInfoById.do?food_id=${item.food_id}">删除</a></td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>名称</th>
                                    <th>描述</th>
                                    <th>简称</th>
                                    <th>分类</th>
                                    <th>图片</th>
                                    <th>是否热点</th>
                                    <th width="10%">操作</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
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
<script src="<%=contextPath%>/dist/js/pages/foodInfoManager.js"></script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>