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
                <h1 class="page-header">抓取源管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                            <span style="">
									<button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#myModal">新建抓取源
                                    </button>
                            </span>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>名称</th>
                                    <th>地址</th>
                                    <th>类型</th>
                                    <th width="15%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${crawlerSourceList}" var="item" varStatus="status">
                                    <c:choose>
                                        <c:when test="status.count%2 == 1">
                                            <tr class="odd">
                                        </c:when>
                                        <c:otherwise>
                                            <tr class="event">
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${status.count}</td>
                                    <td>${item.source_name}</td>
                                    <td>${item.source_path}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.source_type == 0}">微博九宫格</c:when>
                                            <c:otherwise>普通非微博</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="center">
                                        <button type="button" class="btn btn-primary" onclick="openModifyModal(${item.source_id});">修改</button>
                                        <button onclick="del(${item.source_id});" class="btn btn-warning delZoneButton" type="button">删除
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增抓取源</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="createCrawlerSourceForm" action="createCrawlerSource.do" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">抓取源名称</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="source_name" name="source_name" placeholder="抓取源名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">抓取源地址</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="source_path" name="source_path"
                                   placeholder="抓取源地址">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">类型</label>

                        <div class="col-sm-8">
                            <select id="source_type" name="source_type" class="form-control">
                                <option value="0">微博九宫格</option>
                                <option value="1">普通非微博</option>
                            </select>
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
                <h4 class="modal-title" id="myModifyModalModalLabel">编辑抓取源</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="modifyModalCrawlerSourceForm" action="updateCrawlerSource.do" method="post">
                    <div class="form-group" style="display:none">
                        <label class="col-sm-3 control-label">抓取源id</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="modifyModal_source_id" name="modifyModal_source_id" placeholder="抓取源id">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">抓取源名称</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="modifyModal_source_name" name="modifyModal_source_name" placeholder="抓取源名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">抓取源地址</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="modifyModal_source_path" name="modifyModal_source_path"
                                   placeholder="抓取源地址">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">类型</label>

                        <div class="col-sm-8">
                            <select id="modifyModal_source_type" name="modifyModal_source_type" class="form-control">
                            </select>
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
<%--<script src="<%=contextPath%>/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>--%>
<%--<script src="<%=contextPath%>/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>--%>

<!-- Custom Theme JavaScript -->
<script src="<%=contextPath%>/dist/js/sb-admin-2.js"></script>
<script src="<%=contextPath%>/js/crawler.js"></script>
<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<%--<script>--%>
    <%--$(document).ready(function () {--%>
        <%--$('#dataTables-example').DataTable({--%>
            <%--responsive: true--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>

</body>

</html>
