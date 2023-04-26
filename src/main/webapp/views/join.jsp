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
    <form action="<%=request.getContextPath()%>/signup" method="post" enctype="multipart/form-data">
        <div>Email</div>
        <input type="email" id="email" name="email" required /><br/>
        <div>Password</div>
        <input type="password" id="password" name="password" required /><br/>
        <div>Nickname</div>
        <input type="text" id="name" name="name" required /><br/>
        <div>Birthday</div>
        <input type="date" id="birth_day" name="birth_day"/><br/>
        <div>Gender</div>
        <input type="radio" id="male" name="gender" value="Male"/>
        <label for="male">Male</label>
        <input type="radio" id="female" name="gender" value="Female"/>
        <label for="female">Female</label><br/>
            <label for="image">
                <input type="file" accept="image/png, image/jpg, image/gif, image/jpeg" id="image" name="image"/>
            </label><br/><br/>
        <input type="submit" value="Sign Up"/>
    </form>
</div>
<div>Already have an account <a href="<%=request.getContextPath()%>/login"> Log in</a></div>

</body>
</html>
