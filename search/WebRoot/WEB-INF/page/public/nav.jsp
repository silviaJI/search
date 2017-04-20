<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="nav-wrapper" class="float-l clear">
<ul id="nav" class="bor-gey1 bor-radius">
<li class="nav-item">
<span class="nav-button"><a>业绩管理</a></span>
<ul class="subnav hide">
<li><a href="${path}/perform/perform.do?pid=0">业绩统计</a></li>
<li><a href="${path}/perform/finance.do?pid=1">财务统计</a></li>
<li><a href="${path}/perform/collection.do?pid=2">催收统计</a></li>
<li><a href="${path}/perform/overdue.do?pid=3">逾期统计</a></li>
<li><a href="${path}/perform/visit.do?pid=4">回访统计</a></li>
<li><a href="${path}/perform/client.do?pid=5">客户统计</a></li>
<li><a href="${path}/perform/analy.do?pid=6">客户分析</a></li>
</ul>
</li>
<li class="nav-item">
<span class="nav-button"><a>客户管理</a></span>
<ul class="subnav hide">
<li><a href="${path}/client/stat.do?pid=0">客户统计</a></li>
<li><a href="${path}/client/files.do?pid=1">客户档案</a></li>
<li><a href="${path}/client/overdue.do?pid=2">逾期客户</a></li>
<li><a href="${path}/client/app.do?pid=3">APP订单</a></li>
<li><a href="${path}/client/prepay.do?pid=4">提前还款客户</a></li>
<li><a href="${path}/client/overs.do?pid=5">结束合约客户</a></li>
</ul>
</li>
${message}
<li class="nav-item">
<span class="nav-button"><a>人员管理</a></span>
<ul class="subnav hide">
<li><a href="${path}/staff/stat.do?pid=0">人员统计</a></li>
<li><a href="${path}/staff/checkors.do?pid=1">审核人员</a></li>
<li><a href="${path}/staff/servers.do?pid=2">客服人员</a></li>
<li><a href="${path}/staff/shop.do?pid=3">商户管理</a></li>
<li><a href="${path}/staff/system.do?pid=4">利率修改</a></li>
</ul>
</li>
<li class="nav-item">
<span class="nav-button"><a>确认收款</a></span>
<ul class="subnav hide">
<li><a href="${path}/confirm/confirm.do?pid=0">正常还款</a></li>
<li><a href="${path}/confirm/prepay.do?pid=1">提前还款</a></li>
</ul>
</li>
</ul>
</div>