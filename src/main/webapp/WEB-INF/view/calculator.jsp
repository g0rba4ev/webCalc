<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Calculator</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</head>

<body>

<p>
    <input id ="result" type="text" value="" readonly="readonly"> - Result
    <br />
    <input id="inputField" type="text" value="" readonly="readonly"> - Input field
</p>

<input id="btn_CE" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="CE">
<input id="btn_C" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="C">
<input id="btn_D" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="D">
<input id="btn_/" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="/">
<br>
<input id="btn_7" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="7">
<input id="btn_8" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="8">
<input id="btn_9" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="9">
<input id="btn_*" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="*">
<br>
<input id="btn_5" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="5">
<input id="btn_6" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="6">
<input id="btn_4" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="4">
<input id="btn_-" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="-">
<br>
<input id="btn_1" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="1">
<input id="btn_2" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="2">
<input id="btn_3" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="3">
<input id="btn_+" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="+">
<br>
<input id="btn_+/-" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="+/-">
<input id="btn_0" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="0">
<input id="btn_." type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value=".">
<input id="btn_=" type="button" onclick="sendAjaxToServlet(this.value)" style="height: 40px; width: 40px;" name="key" value="=">
<br>

<a href="logout">Logout</a>


</body>

</html>
