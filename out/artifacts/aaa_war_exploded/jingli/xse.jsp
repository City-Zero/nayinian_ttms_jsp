<%--
  Created by IntelliJ IDEA.
  User: sqlness
  Date: 18-6-4
  Time: 下午12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>销售额统计</title>
    <link rel="stylesheet" type="text/css" href="/static/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/user.css">
    <script src="/static/javascript/employee.js"></script>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/BootStrap_DateTime_Picker/css/bootstrap-datetimepicker.min.css">
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="/static/BootStrap_DateTime_Picker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/static/BootStrap_DateTime_Picker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="/static/javascript/readmessge.js"></script>
    <script src="/static/javascript/message.js"></script>
    <script src="/static/javascript/userMessage.js"></script>
    <link rel="stylesheet" href="/static/css/me.css">

    <script src="/static/javascript/empList.js"></script>
    <!- #include virtual="./me.html" ->
</head>
<body>
<jsp:include page="/me.jsp"></jsp:include>
<body>

<div class="container">

    <h1>统计销售额</h1>

    <div class="row">

        <div class="col-md-3" style="background-color: #dedef8;box-shadow:
         inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
            <h4>过滤器</h4>
            <p>在这里选择要统计的信息</p>

            <form role="form">
                <div class="form-group">
                    <label>影片名</label>
                    <select id="movie" style="width:180px;" class="form-control">
                        <option value="" selected>*</option>
                        <option value="1">狼与野兽</option>
                        <option value="2">神风突击</option>
                        <option value="3">海豹特工</option>
                    </select>
                    <br>
                    <label>售票员</label>
                    <select id="people" style="width:180px;" class="form-control">
                        <option value="0" selected>*</option>
                        <option value="1">张三</option>
                        <option value="2">李四</option>
                        <option value="3">王五</option>
                    </select>
                    <br>
                    <label>时间段</label>
                    <br>
                    <div class="input-group date form_date col-md-8" data-date="" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                        <input onblur="check_time(this)" id="start" class="form-control" type="text" value="" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    </div>至

                    <div class="input-group date form_date col-md-8" data-date="" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                        <input onblur="check_time(this)" id="end" class="form-control" size="40" type="text" value="" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    </div>
                    <br>
                    <div style="align-content: right">

                        <button type="button" class="btn btn-default btn-sm" onclick="search()">
                            <span class="glyphicon glyphicon-search"></span> Search
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-9" style="background-color: #dedef8;box-shadow:
         inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
            <h4>第二列 - 分为四个盒子</h4>

        </div>

    </div>

</div>
</body>
<script>
    getpeople();
    getmovie();
    $('.form_date').datetimepicker({
        format: 'yyyy-mm-dd',
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    function check_time(val) {
        let start = document.getElementById("start");
        let end = document.getElementById("end");
        if(start.value == "" || end.value == ""){
            return;
        }else{
            let s_time = Date.parse(start.value);
            let e_time = Date.parse(end.value);
            if(s_time > e_time){
                alert("时间段不合法！");
                val.value = "";
            }
        }
    }

    function getmovie() {
        let people = document.getElementById('movie');
        let xhr = new XMLHttpRequest();
        people.innerHTML = "<option value=\"0\" selected>*</option>";
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4 && xhr.status == 200){
                let json = JSON.parse(xhr.responseText);
                if(json.status){
                    for(let x of json.object){
                        let item = document.createElement("option");
                        item.setAttribute("value",x[0]);
                        item.innerText = x[1];
                        people.appendChild(item);
                    }
                }
            }
        }

        xhr.open('GET',"/api/xiaoshou/movie");
        xhr.send()
    }

    function getpeople() {
        let people = document.getElementById('people');
        let xhr = new XMLHttpRequest();
        people.innerHTML = "<option value=\"0\" selected>*</option>";
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4 && xhr.status == 200){
                let json = JSON.parse(xhr.responseText);
                if(json.status){
                    for(let x of json.object){
                        let item = document.createElement("option");
                        item.setAttribute("value",x[0]);
                        item.innerText = x[1];
                        people.appendChild(item);
                    }
                }
            }
        }

        xhr.open('GET',"/api/xiaoshou/shoupiaoyuan");
        xhr.send()
    }

    function search() {
        let movie = document.getElementById("movie");
        let people = document.getElementById("people");
        let start = document.getElementById("start");
        let end = document.getElementById("end");
        alert(movie.value+people.value+start.value+end.value);
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if(xhr.readyState == 4 && xhr.status == 200){
                alert(xhr.responseText);
            }
        };
        xhr.open("GET","/api/xiaoshou?movie="+movie.value+"&people="+people.value+"&start="+start.value+"&end="+end.value);
        xhr.send();

    }
</script>
</html>
