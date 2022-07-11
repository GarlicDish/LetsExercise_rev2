<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<%response.setStatus(HttpServletResponse.SC_OK);%>
<!DOCTYPE html>
<html>
    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 부트스트랩 반응형 웹 작용을 위함-->
<link rel="stylesheet" href="css/bootstrap.css"> <!-- 부트스트랩 적용 -->
<link rel="stylesheet" href="css/custom.css">
<script src="js/bootstrap.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	$("#back").click(function () {
		history.go(-1);
	})
})

</script>

<meta charset="UTF-8">
<title>Insert title here</title>


<head>
<meta charset="UTF-8">
<style type="text/css">
.clearfix:before,
.clearfix:after {
    display: table;

    content: ' ';
}
.clearfix:after {
    clear: both;
}
body {
    background: #f0f0f0 !important;
}
.page-404 .outer {
    position: absolute;
    top: 0;

    display: table;

    width: 100%;
    height: 100%;
}
.page-404 .outer .middle {
    display: table-cell;

    vertical-align: middle;
}
.page-404 .outer .middle .inner {
    width: 300px;
    margin-right: auto;
    margin-left: auto;
}
.page-404 .outer .middle .inner .inner-circle {
    height: 300px;

    border-radius: 50%;
    background-color: #ffffff;
}
.page-404 .outer .middle .inner .inner-circle:hover i {
    color: #39bbdb!important;
    background-color: #f5f5f5;
    box-shadow: 0 0 0 15px #39bbdb;
}
.page-404 .outer .middle .inner .inner-circle:hover span {
    color: #39bbdb;
}
.page-404 .outer .middle .inner .inner-circle i {
    font-size: 5em;
    line-height: 1em;

    float: right;

    width: 1.6em;
    height: 1.6em;
    margin-top: -.7em;
    margin-right: -.5em;
    padding: 20px;

    -webkit-transition: all .4s;
            transition: all .4s;
    text-align: center;

    color: #f5f5f5!important;
    border-radius: 50%;
    background-color: #39bbdb;
    box-shadow: 0 0 0 15px #f0f0f0;
}
.page-404 .outer .middle .inner .inner-circle span {
    font-size: 11em;
    font-weight: 700;
    line-height: 1.2em;

    display: block;

    -webkit-transition: all .4s;
            transition: all .4s;
    text-align: center;

    color: #e0e0e0;
}
.page-404 .outer .middle .inner .inner-status {
    font-size: 20px;

    display: block;

    margin-top: 20px;
    margin-bottom: 5px;

    text-align: center;

    color: #39bbdb;
}
.page-404 .outer .middle .inner .inner-detail {
    line-height: 1.4em;

    display: block;

    margin-bottom: 10px;

    text-align: center;

    color: #999999;
}

.w-100 {
    width: 50%;
}

[type=button]:not(:disabled), [type=reset]:not(:disabled), [type=submit]:not(:disabled), button:not(:disabled) {
    cursor: pointer;
}
.btn {
    display: inline-block;
    font-weight: 400;
    line-height: 1.5;
    text-align: center;
    text-decoration: none;
    vertical-align: middle;
    user-select: none;
    border: 1px solid transparent;
    margin: auto 0;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
.btn-group-lg>.btn, .btn-lg {
    padding: .3em!important;
    font-size: 1.25rem;
    border-radius: .3rem;
}
.w-100 {
    width: 50%;
}
.btn-primary {
    color: #fff;
    background-color: #0d6efd;
    border-color: #0d6efd;
}

</style>
<%@ include file="../main/layout/header.jsp"%>
<title>Insert title here</title>
</head>
<body>

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div class="page-404">
    <div class="outer">
        <div class="middle">
            <div class="inner">
                <!--BEGIN CONTENT-->
                <div class="inner-circle"><i class="fa fa-cogs"></i><span>500</span></div>
                <span class="inner-status">이런, 에러가 발생했어요!</span>
                <span class="inner-detail">페이지를 불러오려다 실패했어요. <br>
              							혹시 빈 칸이 있진 않았는지 확인해 주시거나, <br>
              							저희 팀에 연락해주세요. <br> <br><br>
                <button type="button" class="w-100 btn btn-lg btn-primary "id="back">뒤로 돌아갈래요.</button><br><br>
              							</span>
                <!--END CONTENT-->
                
            </div>
        </div>
    </div>
</div>

</body>
</html>