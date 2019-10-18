<%--
  Created by IntelliJ IDEA.
  User: ars
  Date: 06.10.2019
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Count sum</h2>

<h2>
${num1}+${num2}=?
</h2>
<form   method="post">
    Your answer<br /> <br />
    <input type="number"  name="answer">

    <input type="submit" name="submit" value="Submit"/>

    <input type="hidden" name="hashcode" value=${hashcode} />
</form>
</body>
</html>
