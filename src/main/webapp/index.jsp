<%--这个页面可以画出来,画一个页面正在跳转中,当用户网络不好时可以安慰一下--%>
<head>
    <title>宿舍管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            location.href = "${basePath}/login";
        });
    </script>
</head>
<body>

</body>