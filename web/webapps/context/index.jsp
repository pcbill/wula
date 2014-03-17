<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%--
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js" ></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular-resource.min.js"></script>
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<!-- 
<script type="text/javascript" src='<c:url value="/resources/ui-bootstrap-0.4.0.min.js" />'></script> 
<script type="text/javascript" src='<c:url value="/resources/application.js" />'></script>  
 -->

<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">

</head>
<body>
<table border="1" cellpadding="2" cellspacing="2">
    <tr><!-- header -->
        <td height="30" colspan="2">
        
	        <h1>TWM SDMP 模擬商店</h1>
	
			<div ng-controller="UrlsController">
				<p>
				Current URL: http://{{currentTarget}}/{{currentWebApp}}
				</p>
				
				<p>
				Change TO: http://
				<select id="connectToUrls" ng-model="currentTargetUrl" ng-options="i.url for i in connectToUrls" ng-change="changeTargetUrl()">
				<option value="">-- chose target url --</option>
				</select>
				/
				<select id="webAppNames" ng-model="currentWebAppName" ng-options="i.name for i in webAppNames" ng-change="changeTargetUrl()">
				<option value="">-- chose target web app name --</option>
				</select>
				</p>
			</div>
			
			<div ng-controller="StoreController">
				<p>
				Current Store: {{currentStore.name}}, productCode code: {{currentStore.productCode}}, prefix:{{currentStore.prefix}}
				</p>
				
				<p>
				Change Store:
				<select id="stores" ng-model="currentStore" ng-options="s.name for s in stores" ng-change="changeCurrentStore()">
				<option value="">-- chose Store --</option>
				</select>
				</p>
			<!-- 
			 -->
			
			</div>
			
			<div ng-controller="UserController">
				<p>
				Current User:{{currentUser.name}}
				</p>
				<p>
				Change User:
				<select id="users" ng-model="currentUser" ng-options="u.name for u in users" ng-change="changeCurrentUser()">
				<option value="">-- chose User --</option>
				</select>
				</p>
			</div>
        
        </td>
    </tr>
    <tr>
        <td><!-- menu -->
          	<ul>
			<li><a href="/simStore/store/list">Store List</a></li>
			<li><a href="#/batchBuy"">批次交易</a></li>
			<li>
			     取消交易
			  <ul>
			  <a href="/simStore/transaction/SCMScancel">客服SCMS(Begin)</a>
			  </ul>
			</li>
			</ul>
        </td>
        <td>
            <div ng-view>
            </div>
        </td>
    </tr>
    <tr>
        <td height="30" colspan="2">
        	<p>Copyright &copy; bill1wu@taiwanmobile.com</p>
        </td>
    </tr>
</table>
</body>
</html>