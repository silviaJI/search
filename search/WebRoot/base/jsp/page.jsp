<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<c:if test="${totalPage > 10}"><c:set var="totalPage" value="10"></c:set></c:if>

<c:if test="${totalPage > 1}">
<div id="page-wrapper" class="clear">
  <div class="now-page float-r">${page}/${totalPage}</div>
      <div class="page-buttons float-r clear">
      <a href="${path}/${module}?page=1&limit=${limit}">首页</a>
       <c:if test="${page > 1}">
        <a href="${path}/${module}?page=${page-1}&limit=${limit}">前一页</a>
       </c:if>
        <c:if test="${page < totalPage}">
        <a href="${path}/${module}?page=${page + 1}&limit=${limit}">下一页</a>
       </c:if>
      <a href="${path}/${module}?page=${totalPage}&limit=${limit}">末页</a>
      </div>
  </div>
</c:if>