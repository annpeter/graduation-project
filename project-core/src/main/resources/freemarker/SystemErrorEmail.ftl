<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 注意注意--系统异常 </title>
</head>
<body>
<table style="font-family:'\005fae\008f6f\0096c5\009ed1','Helvetica Neue',sans-serif,SimHei;padding:35px 50px;
        margin:25px auto;background:#eeeeee;border-radius:5px" border="0" cellspacing="0" cellpadding="0"
       width="80%" align="center">
    <tbody>
    <tr>
        <td style="padding:0 20px;color:#2dd7c6;font-size: 30px;">系统异常报告--${program}</td>
    </tr>
    <tr>
        <td style="padding:0 20px">
            <hr style="border:none;border-top:1px solid #ccc">
        </td>
    </tr>
    <tr>
        <td style="padding:20px 20px 20px 20px">
            <p style="margin:8px 0">Hi 亲爱的用户:</p>
        </td>
    </tr>
    <tr>
        <td valign="middle" style="line-height:24px;padding:15px 20px">
            <div style="margin:8px 0">
                参数列表:<br>
            <#list parameterMap ? keys as key>
                ${key}--${parameterMap["${key}"]}<br>
            </#list>
                <br>
                异常堆栈:
                <pre>${throwable}</pre>
            </div>
        </td>
    </tr>
    <tr>
        <td style="padding:20px 20px 20px 20px">
            <p style="margin:8px 0">友情提示：<br>
                若点击激活按钮无反应，请复制下面链接在浏览器中激活
            </p>
        </td>
    </tr>

    <tr>
        <td style="padding:0 20px">
            <hr style="border:none;border-top:1px solid #ccc">
        </td>
    </tr>

    <tr>
        <td style="padding:0 20px;font-size:9pt;color:#b5b0b0">
            <span style="float:right">如有什么问题？ 你可以：
                <a style="color:#2dd7c6; text-decoration: none;" href="http://www.boe.com" target="_blank">去查看官网</a>
            </span>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>