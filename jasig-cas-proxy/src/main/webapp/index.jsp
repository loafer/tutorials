<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    </head>
    <body>
        <h2>Hello CAS Proxy!</h2>
        <a href="http://localhost:8082/cas-client">cas-client-app</a>
        <hr>
        <div>
            <div>以代理方式获取cas-client-app的数据</div>
            <div>
                <input id="btn" type="button" value="load">
            </div>
            <div id="tt">

            </div>
        </div>
        <script src="js/jquery-1.11.1.min.js"></script>
        <script>
            $(function(){
                $('#btn').on('click',function(){
                    $.ajax({
                        url: 'rest',
                        success: function(data){
                            $('#tt').text(data);
                        },
                        error: function(){
                            alert('error!')
                        }
                    })
                })
            });
        </script>
    </body>
</html>
