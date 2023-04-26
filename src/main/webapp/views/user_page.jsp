<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        </div>
    </nav>
</header><hr>

<table>
    <tr>
        <td>Nickname: </td>
        <td>${user.name}</td>
    </tr>
    <tr>
        <c:choose>
            <c:when test="${empty user.birth_day}">

            </c:when>
            <c:otherwise>
                <td>Birthday: </td>
                <td>${user.birth_day}</td>
            </c:otherwise>
        </c:choose>
    </tr>
    <tr>
        <c:choose>
            <c:when test="${empty user.gender}">

            </c:when>
            <c:otherwise>
                <td>Gender: </td>
                <td>${user.gender}</td>
            </c:otherwise>
        </c:choose>
    </tr>
    <tr>
        <td>Email: </td>
        <td>${user.email}</td>
    </tr>
</table><br/>
<img src="<%=request.getContextPath()%>/show-img" style="max-width: 500px"><br/><br/>

<a href="<%=request.getContextPath()%>/edit-profile">Profile</a><br/><br/>
<a href="<%=request.getContextPath()%>/edit-photo">Photo</a><br/><br/>
<a href="<%=request.getContextPath()%>/edit-account">Account</a><br/><br/>

<hr>

<c:forEach var="image" items="${images}">
    <img src="<%=request.getContextPath()%>/img-by-id?id=${image.id}" style="max-width: 500px"><br/><br/>
    <p>${image.title}</p>
    <p>${image.caption}</p>
    <table>
        <tr>
            <td>Status: </td>
            <td>${image.status}</td>
        </tr>
        <tr>
            <td>Date: </td>
            <td>${image.date}</td>
        </tr>
        <tr>
            <td>Rate: </td>
            <td>${image.rate}</td>
        </tr>
    </table><br/><br/>
    <form action method="get">
        <input type="submit" value="Edit">
    </form><br/>
    <form action method="get">
        <input type="submit" value="Delete">
    </form><br/><br/>
</c:forEach>

</body>
</html>
