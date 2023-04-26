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
    <form action="<%=request.getContextPath()%>/edit-account" method="post" >
        <div>Email</div>
        <input type="text" id="email" name="email" value="${user.email}" required />

        <input type="submit" value="Save"/>
    </form>
</div>

<div>
    <form action="<%=request.getContextPath()%>/update-password" method="post" >
        <div>Password</div>
        <input type="password" id="password" name="password" placeholder="Enter current password" required/><br/>
        <input type="password" id="newPassword" name="newPassword" placeholder="Enter new password" required/>

        <input type="submit" value="Save"/>
    </form>
</div>

<div>
    <a href="<%=request.getContextPath()%>/logout">Log out</a>
</div><br/>
<div>
    <span id="label" onclick="display()" style="cursor: pointer">Delete Account</span>
    <form action="<%=request.getContextPath()%>/delete-account" method="get" id="delete" style="display: none">
        <input type="password" name="password" placeholder="Enter password" required/><br/>
        <input type="submit" value="Delete Account"/>
    </form>
</div><br/>

<script>
    function display() {
        document.getElementById("delete").style.display = "block";
        document.getElementById("label").style.display = "none";
    }
</script>

</body>
</html>

