<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx }/admin/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx }/admin/css/amazeui.min.css"/>
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">添加商品</strong>
            <small></small>
        </div>
    </div>
    <hr>

    <div class="edit_content">
        <form action="${ctx}/GoodsServlet?action=addGoods&current=${num}" enctype="multipart/form-data" method="post"
              id="add_form" style="background: none; width: 700px;">
            <div class="item1">
                <%--enctype="multipart/form-data"--%>
                <div>
                    <span>商品名称:</span>
                    <input type="text" class="am-form-field" name="name" required id="name">&nbsp;&nbsp;
                </div>
                <div>
                    <span>商品价格:</span>
                    <input type="text" class="am-form-field" name="price" required id="price">
                </div>

            </div>

            <div class="item1">
                <div>
                    <span>所属分类:</span>
                    <select id="category_select" name="cid">
                        <c:forEach items="${allGoodsType }" var="GoodsType">
                            <option value="${GoodsType.cid }">${GoodsType.cname }</option>
                        </c:forEach>
                    </select>
                    &nbsp;
                </div>

                <div>
                    <span>是否热门：</span>
                    <select id="isHotSel" name="is_host">
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                </div>

            </div>

            <div class="item1">
                <span>商品图片：</span>
                <input type="file" name="image" id="image"/>
            </div>
            <div>
                图片预览:&nbsp;&nbsp;&nbsp;<img src="" style="width: 80px;height: 80px;border:  1px" id="img">
            </div>
            <div class="item1 item_desc">
                <span>商品描述：</span>
                <textarea id="desc" name="gdesc" rows="4" cols="50"></textarea>
            </div>
            <button class="am-btn am-btn-default" type="button" id="add">添加</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="am-btn am-btn-default" type="button" id="reset">重置</button>
        </form>
    </div>

</div>

<script src="${ctx }/admin/js/jquery.min.js"></script>

<script>

    $("#add").click(function () {
        if ($("#name").val().trim() == '' || $("#name").val() == null) {
            alert("请输入商品名称");
            return;
        } else if ($("#price").val().trim() == '' || $("#price").val() == null) {
            alert("请输入价格");
            return;
        }
        $("#hidden").val($("#image").val());
        //让表单提交 GoodsAddServlet
        //获取表单  让其提交
        $("#add_form").submit();
    });

    $("#image").change(function () {
        var objUrl = getObjectURL(this.files[0]);//获取文件信息
        if (objUrl) {
            $("#img").attr("src", objUrl);
        }
    });

    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }

</script>
</body>
</html>