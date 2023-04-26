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
    <form action="<%=request.getContextPath()%>/post-img" method="post" enctype="multipart/form-data">
        <div>Title</div>
        <input type="text" id="title" name="title" required /><br/>
        <div>Caption</div>
        <textarea id="caption" name="caption"></textarea><br/>
        <label for="image">
            <input type="file" accept="image/png, image/jpg, image/gif, image/jpeg" id="image" name="image" required/>
        </label><br/><br/>
        <div>Access</div>
        <input type="radio" id="public" name="access" value="Public" checked/>
        <label for="public">Public</label>
        <input type="radio" id="private" name="access" value="Private"/>
        <label for="private">Private</label><br/>

        <input type="submit" value="Post"/>
    </form>
</div>

</body>
</html>
