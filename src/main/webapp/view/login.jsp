<%--
  Created by IntelliJ IDEA.
  User: Asher
  Date: 31.12.2023
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style rel="stylesheet" type="text/css">
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        a{
            text-decoration: none;
            color:rgb(27, 117, 208);
        }
        a:hover{
            color:rgb(4, 56, 109);
        }
        body{
            min-height: 100vh;
            background: rgb(241, 242, 243);
            display: flex;
            font-family: sans-serif;
        }
        .container{
            margin: auto;
            width: 400px;
            max-width: 90%;
        }
        .container form{
            width: 100%;
            padding: 20px;
            background: white;
            border-radius: 4px;
            box-shadow: 0 8px 16px rgba(0,0,0,.3);
        }
        .container form h1{
            text-align: center;
            margin-bottom: 24px;
            color: #222;
        }
        .container form .form-control{
            width: 100%;
            height: 40px;
            background: white;
            border-radius: 4px;
            border: 1px solid silver;
            margin: 10px 0 18px 0;
            padding: 0 10px;
        }
        .container form .btn{
            margin-left: 50%;
            transform: translateX(-50%);
            width: 120px;
            height: 34px;
            border: none;
            outline: none;
            cursor: pointer;
            font-size: 16px;
            text-transform: uppercase;
            background: #222222;
            color: white;
            border-radius: 4px;
            transition: .3s;
        }
        .container form .btn:hover{
            opacity: .7;
        }
        .signup-text{
            text-align: center;
            margin: 24px;
            color: #222222;
            font-size: 14px;
        }
        .form-group a.forgot-pwd {
            text-align: right;
        }
        .container span{
            margin-left: 46.5%;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="" method="post">
            <h1>Login Form</h1>
            <div class="form-group">
                <b>Email</b>
                <input type="email" class="form-control" name="email" required>
            </div>
            <div class="form-group">
                <b>Password</b>
                <input type="password" class="form-control" name="password" required>
            </div>
            <input type="submit" class="btn" value="Login">
        </form>
        <div class="signup-text">
            Don't have an account? <a href="/signup">Sign up</a>
        </div>
    </div>
</body>
</html>
