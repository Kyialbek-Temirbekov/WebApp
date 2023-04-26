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
      <a href="<%=request.getContextPath()%>/user-page"><img src="<%=request.getContextPath()%>/show-img" style="border-radius: 50%; width: 100px; height: 100px; object-fit: cover"></a>
    </div>
  </nav>
</header><hr>

<div>
  <form action="<%=request.getContextPath()%>/edit-profile" method="post" enctype="multipart/form-data">
    <div>Nickname</div>
    <input type="text" id="name" name="name" value="${user.name}" required /><br/>
    <div>Birthday</div>
    <input type="date" id="birth_day" name="birth_day"  value="${user.birth_day}"/><br/>
    <div>Gender</div>
    <c:choose>
      <c:when test="${user.gender == 'Male'}">
        <input type="radio" id="male" name="gender" value="Male" checked/>
        <label for="male">Male</label>
        <input type="radio" id="female" name="gender" value="Female"/>
        <label for="female">Female</label><br/>
      </c:when>
      <c:when test="${user.gender == 'Female'}">
        <input type="radio" id="male" name="gender" value="Male"/>
        <label for="male">Male</label>
        <input type="radio" id="female" name="gender" value="Female" checked/>
        <label for="female">Female</label><br/>
      </c:when>
      <c:when test="${empty user.gender}">
        <input type="radio" id="male" name="gender" value="Male"/>
        <label for="male">Male</label>
        <input type="radio" id="female" name="gender" value="Female"/>
        <label for="female">Female</label><br/>
      </c:when>
    </c:choose>
    <input type="submit" value="Save"/>
  </form>
</div>

</body>
</html>
