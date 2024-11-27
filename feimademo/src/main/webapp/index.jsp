<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>飞马银行登录</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="login-container">
  <h2>飞马银行登录</h2>
  <form action="LoginServlet" method="POST">
    <label for="username">用户名</label>
    <input type="text" id="username" name="username" required>

    <label for="password">密码</label>
    <input type="password" id="password" name="password" required>

    <button type="submit">登录</button>
  </form>
</div>
</body>
</html>
