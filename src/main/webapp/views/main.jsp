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
<header>
    <nav>
        <div>Lofty</div><br/>
        <div>
            <form>
                <input type="text" name="search" placeholder="search works"/>
            </form>
        </div>
        <div>
            <a href="<%=request.getContextPath()%>/post-img">Post your work</a><br/><br/>
            <a href="<%=request.getContextPath()%>/user-page"><img src="<%=request.getContextPath()%>/show-img" style="border-radius: 50%; width: 100px; height: 100px; object-fit: cover"></a>
        </div>
    </nav>
</header>

</body>
</html>
