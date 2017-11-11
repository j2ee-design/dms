<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>宿舍列表</title>
    <link rel="stylesheet" href="${basePath}/css/reset.css">
    <link rel="stylesheet" href="${basePath}/css/dormList.css">
    <script src="${basePath}/js/comment/jquery-3.2.1.js"></script>
    <script src="${basePath}/js/dormList.js"></script>
</head>
<body>
<div class="content">
    <div class="apart-info">
        <h3>#${apartDto.id} ${apartDto.name}</h3>
        <div class="apart-info-son-wrap">
            <span>宿舍类型：${apartDto.apartmentTypeName}</span>
            <span>高度：${apartDto.floorNum} 层</span>
            <span>单位宿舍床铺数：${apartDto.dormBedNum} / 位</span>
        </div>
        <div class="apart-info-son-wrap">
            <span>总宿舍数：${apartDto.usedDorm} / ${apartDto.dormNum}</span>
            <span>总床铺数：${apartDto.remainBed} / ${apartDto.allBed}</span>
        </div>
    </div>
    <div class="table-content scroller-bar-01">

        <table class="dorm-table" id="dorm-table">
            <c:forEach items="${dormDtos}" var="item">
                <tr class="dlist-tr" onclick="alertStudent('${item.id}')">
                    <td class="dlist-td dorm-id">#${item.apartId}-${item.dormId}</td>
                    <td class="dlist-td type">${apartDto.apartmentTypeName}</td>
                    <td class="dlist-td bed-num">${item.usedBed} / ${item.allBed}</td>
                    <td class="dlist-td">${(item.className==null)?"未分配":item.className}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<input type="hidden" value="${basePath}" id="basePath">
</body>
</html>