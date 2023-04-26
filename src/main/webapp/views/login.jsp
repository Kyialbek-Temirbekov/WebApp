<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>lofty</title>
</head>
<body>
<script>
    var perfEntries = performance.getEntriesByType("navigation");

    if (perfEntries[0].type === "back_forward") {
        location.reload();
    }
</script>
<div>
    <form action="<%=request.getContextPath()%>/login" method="post">
        <div>Email</div>
        <input type="email" id="email" name="email" required /><br/>
        <div>Password</div>
        <input type="password" id="password" name="password" required /><br/><br/>
        <input type="submit" value="Log in"/>
    </form>
</div>
<div>Don't have an account <a href="<%=request.getContextPath()%>/signup"> Sign up</a></div>

</body>
</html>
