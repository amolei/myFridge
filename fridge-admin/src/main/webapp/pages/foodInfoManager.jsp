<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String contextPath = request.getContextPath();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>我的冰箱</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=contextPath%>/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%=contextPath%>/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="<%=contextPath%>/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
          rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="<%=contextPath%>/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=contextPath%>/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=contextPath%>/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div id="wrapper">

    <jsp:include page="header.jsp"></jsp:include>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">食物管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">

                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                选择分类
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li><a href="javascript:choseKind(-1,'选择分类 ');">选择分类</a></li>
                                <c:forEach items="${foodKindList}" var="item" varStatus="status">
                                    <li>
                                        <a href="javascript:choseKind(${item.food_kind_id},'${item.food_kind_name}')">${item.food_kind_name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div class="btn-group">
                                <button data-target="#myModal" data-toggle="modal" class="btn btn-primary"
                                        type="button">新增食物
                                </button>
                            </div>
                        </div>


                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>描述</th>
                                    <th>简称</th>
                                    <th>分类</th>
                                    <th>图标</th>
                                    <th>是否热点</th>
                                    <th width="15%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${foodInfoList}" var="item" varStatus="status">
                                    <c:choose>
                                        <c:when test="status.count%2 == 1">
                                            <tr class="odd">
                                        </c:when>
                                        <c:otherwise>
                                            <tr class="event">
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${item.food_name}</td>
                                    <td title="${item.food_des }">${item.food_des}</td>
                                    <td>${item.simple_name}</td>
                                    <td>${item.kind_name}</td>
                                    <td><img width="80px" height="80px" src="http://139.196.171.209${item.food_img}">
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.hot == 1}">是</c:when>
                                            <c:otherwise>否</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="center">
                                        <button type="button" class="btn btn-primary"
                                                onclick="openModifyModal(${item.food_id});">修改
                                        </button>
                                        <button onclick="delFoodInfo(${item.food_id});"
                                                class="btn btn-warning delZoneButton" type="button">删除
                                        </button>
                                    </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->
<!--add-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增食物</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="createFoodInfoForm" action="createFoodInfo.do" method="post"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">食物名称</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="food_name" name="food_name" placeholder="食物名称"
                                   onblur="validateFoodInfoName();">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">食物描述</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="food_info" name="food_info"
                                   placeholder="食物描述">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">简称</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="simple_name" name="simple_name"
                                   placeholder="简称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">类别</label>

                        <div class="col-sm-8">
                            <select id="food_kind" name="food_kind" class="form-control">
                                <c:forEach items="${foodKindList}" var="item" varStatus="status">
                                    <option value="${item.food_kind_id}">${item.food_kind_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否热点</label>

                        <div class="col-sm-8">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" id="hot" name="hot" value="1"/>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">上传图片</label>

                        <div class="col-sm-8">
                            <input id="file_upload" type="file" name="file" class="">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="submitForm();">保存</button>
            </div>

        </div>
    </div>
</div>
<!--modify-->
<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="modifyModal1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModidyModalLabel">编辑食物</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="modifyFoodInfoForm" action="updateFoodInfo.do" method="post"
                      enctype="multipart/form-data">
                    <div style="display: none" class="form-group">
                        <label class="col-sm-3 control-label">食物名称</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="modify_food_id" name="modify_food_id">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">食物名称</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="modify_food_name" name="modify_food_name"
                                   placeholder="食物名称" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">食物描述</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="modify_food_info" name="modify_food_info"
                                   placeholder="食物描述">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">简称</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="modify_simple_name" name="modify_simple_name"
                                   placeholder="简称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">类别</label>

                        <div class="col-sm-8">
                            <select id="modify_food_kind" name="modify_food_kind" class="form-control">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否热点</label>

                        <div class="col-sm-8">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" id="modify_hot" name="modify_hot" value="1"/>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">上传图片</label>

                        <div class="col-sm-8">
                            <input type="file" name="file" class="">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="submitModifyModalForm();">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="<%=contextPath%>/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=contextPath%>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=contextPath%>/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="<%=contextPath%>/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="<%=contextPath%>/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=contextPath%>/dist/js/sb-admin-2.js"></script>
<script src="<%=contextPath%>/js/foodInfo.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>

</script>

</body>

</html>
