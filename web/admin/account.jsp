<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/pageStyle.css">
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">管理员列表</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加管理员
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>用户</li>
        <li>修改密码</li>
        <li>移除管理员</li>
    </ul>
    <div id="admin_box">
        <c:forEach items="${allAdmin}" var="Admin" varStatus="i">
            <ul class="list_goods_ul">
                <li>${i.index+1}</li>
                <li>${Admin.username}</li>
                <li><a href="#"><img class="img_icon"
                                     src="${pageContext.request.contextPath}/admin/images/edit_icon.png"></a></li>
                <li><a href="#"><img class="img_icon"
                                     src="${pageContext.request.contextPath}/admin/images/delete_icon.png"></a>
                </li>
            </ul>
        </c:forEach>
    </div>

</div>

<div id="modal_view">


</div>

<div id="modal_content_account">
    <div id="close"><img src="${pageContext.request.contextPath}/admin/images/delete_icon.png" alt=""></div>
    <div class="edit_content">
        <div class="item1">
            <div>
                <span>添加管理员：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>用户名:</span>
                <input type="text" class="am-form-field">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <div>
                <span>密&nbsp;&nbsp;&nbsp;码:</span>
                <input type="text" class="am-form-field">&nbsp;&nbsp;
            </div>

        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="add_admin">添加</button>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script>
    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content_account").fadeIn();
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_account").fadeOut();
        });

        $("#add_admin").click(function () {
            var $userText = $(".item1 .am-form-field:eq(0)").val();
            var $pwdText = $(".item1 .am-form-field:eq(1)").val();
            if ($userText.trim() != "") {
                if ($pwdText.trim() != "") {
                    $.post("AdminServlet",
                        {
                            "action": "addAdmin",
                            "username": $userText,
                            "password": $pwdText
                        }, function (data) {
                        if (typeof data!="object"){
                            data=JSON.parse(data)
                        }
                        var html="";
                            for (var i = 0; i < data.length; i++) {
                                var ul="<ul class=\"list_goods_ul\">\n" +
                                    " <li>"+(i+1)+"</li>\n" +
                                    " <li>"+data[i].username+"</li>\n" +
                                    " <li><a href=\"#\"><img class=\"img_icon\"\n" +
                                    " src=\"${pageContext.request.contextPath}/admin/images/edit_icon.png\"></a></li>\n" +
                                    " <li><a href=\"#\"><img class=\"img_icon\"\n" +
                                    " src=\"${pageContext.request.contextPath}/admin/images/delete_icon.png\"></a>\n" +
                                    " </li>\n" +
                                    " </ul>";
                                    html+=ul;
                            }
                        $("#admin_box").html(html);
                        }, "JSON");
                    $("#modal_view").fadeOut();
                    $("#modal_content_account").fadeOut();
                    $(".item1 .am-form-field:eq(0)").val("");
                    $(".item1 .am-form-field:eq(1)").val("")
                } else {
                    alert("请输入密码");
                }
            } else {
                alert("请输入用户名");
            }
        })

    });
</script>
</body>
</html>