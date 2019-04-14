<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/pageStyle.css">

</head>
<body style="background:#f3f3f3;">

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">商品管理</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span>新增
                    </button>
                </div>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">

        </div>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field" id="input_search">
                <span class="am-input-group-btn">
                    <button class="am-btn am-btn-default" type="button" id="input_search_btn">搜索</button>
                </span>
            </div>
        </div>
    </div>


</div>

<div class="goods_list">
    <ul class="title_ul">
        <li>序号</li>
        <li>订单号</li>
        <li>下单时间</li>
        <li>订单总金额</li>
        <li>订单用户</li>
        <li>配送地址</li>
    </ul>
    <div id="info_box">
        <c:forEach items="${pageData.pageGoodsList}" var="Goods" varStatus="i">
            <ul class="list_goods_ul">
                <li>${i.index+1}</li>
                <li><img src="${pageContext.request.contextPath}/admin/pimages/${Goods.image}"></li>
                <li>${Goods.name}</li>
                <li>${Goods.price}</li>
                <li>
                    <a href="${pageContext.request.contextPath}/GoodsServlet?id=${Goods.id}&action=getUpdateGoodsType&current=${num}"><img
                            class="img_icon" src="${pageContext.request.contextPath}/admin/images/edit_icon.png" alt="">
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/GoodsServlet?id=${Goods.id}&action=deleteGoods&current=${num}"><img
                            class="img_icon" src="${pageContext.request.contextPath}/admin/images/delete_icon.png"
                            alt="删除">
                    </a>
                </li>
            </ul>
        </c:forEach>
    </div>
    <!--分页-->
    <div id="page" class="page_div"></div>
</div>

<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/paging.js"></script>
<script>
    //分页

    $(function () {
        var currentNo =${pageData.current};
        var pageNoCount =${pageData.pageCount};
        var allCount =${pageData.allCounts};
        $("#page").paging({
            pageNo: currentNo,
            totalPage: pageNoCount,
            totalSize: allCount,
            callback: function (num) {
                $(window).attr('location', '${pageContext.request.contextPath}/GoodsServlet?action=getPageData&current=' + num);
            }
        });

        $("#add").click(function () {
            $(window).attr('location', '${pageContext.request.contextPath}/GoodsServlet?action=getGoodsType&current=${num}');
        });

        $(".list_goods_ul>li img[alt=删除]").click(function () {
            var flag = confirm("是否确认删除？")
            if (!flag) {
                return false;
            }
        });

    });

</script>

</body>
</html>