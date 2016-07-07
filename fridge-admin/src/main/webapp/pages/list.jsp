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

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

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
    <script>
        function choseKind(id){
            var h = '<span class="caret"></span>';
            $('#dropdownMenu1').html(id + h);
        }
        function openModifyModal(id){
            alert(id);
        }
        </script>
</head>

<body>

<div id="wrapper">

    <jsp:include page="header.jsp"></jsp:include>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${key}</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">

                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                选择分类
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li><a href="javascript:choseKind('选择分类 ');">选择分类</a></li>
                                <li><a href="javascript:choseKind(0);">0</a></li>
                                <li><a href="javascript:choseKind(1);">1</a></li>
                                <li><a href="javascript:choseKind(2);">2</a></li>
                            </ul>
                            <div class="btn-group">
                                <button data-target="#myModal" data-toggle="modal" class="btn btn-primary" type="button">新增分类</button>
                            </div>
                        </div>



                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>名称</th>
                                    <th>描述</th>
                                    <th>时间</th>
                                    <th>类别</th>
                                    <th width="15%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="item" varStatus="status">
                                    <c:choose>
                                        <c:when test="status.count%2 == 1">
                                            <tr class="odd">
                                        </c:when>
                                        <c:otherwise>
                                            <tr class="event">
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td title="${item.des }">${item.des}</td>
                                    <td><fmt:formatDate value="${item.update_time}" pattern="yyyy-MM-dd"/></td>
                                    <td>${item.kind_id}</td>
                                    <td class="center">
                                        <button type="button" class="btn btn-primary" onclick="openModifyModal(${item.id});">修改</button>
                                        <button onclick="" class="btn btn-warning delZoneButton" type="button">删除</button>
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
                <h4 class="modal-title" id="myModalLabel">新增分类</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="insertForm" action="addFoodInfo.do" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分类名称</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="food_name" placeholder="例如:suningcom...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分类描述</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="food_des"
                                   placeholder="例如:serversuningcom.pem...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">类别</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="kind_id" placeholder="例如:suningcn(官网)证书...">
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
                <button type="button" class="btn btn-primary" onclick="">保存</button>
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
                <h4 class="modal-title" id="modifyModal1">修改分类</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="modifyForm" action="updateCertificate.do" method="post">
                    <input type="hidden" name="modifyModal_certificate_id">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分类名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="modifyModal_certificate_name" placeholder="例如:suningcom...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分类描述</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="modifyModal_private_key" placeholder="例如:suningcom.key...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">类别</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="modifyModal_remark" placeholder="例如:suningcn(官网)证书...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">上传图片</label>
                        <div class="col-sm-8">
                            <input id="modifyModal_file_upload" type="file" name="modifyModal_file" class="">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="">保存</button>
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

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function () {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
</script>

</body>

</html>
