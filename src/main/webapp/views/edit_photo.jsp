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
        <div><a href="<%=request.getContextPath()%>/home">Lofty</a><br/></div><br/>
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
</header><hr>

<div>
    <form action="<%=request.getContextPath()%>/edit-photo" method="post" enctype="multipart/form-data">
        <label for="image">
            <input type="file" accept="image/png, image/jpg, image/gif, image/jpeg" id="image" name="image" />
        </label>
        <input type="submit" value="Save"/>
    </form>
</div><br/>
<div>
    <form action="<%=request.getContextPath()%>/delete-photo" method="get">
        <input type="submit" value="Delete">
    </form>
</div>

</body>
</html>
