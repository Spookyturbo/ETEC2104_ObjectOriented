<!DOCTYPE html>
<html>
    <head>
        <meta charset=utf8>
    </head><body>
<div>
    
<% if( request.getSession().getAttribute("username") == null ) { %>
    You are not logged in.<br/>
        <a href = "http://localhost:2020/srv/signup" >http://localhost:2020/srv/signup</a><br/>
        <a href = "http://localhost:2020/srv/login" >http://localhost:2020/srv/login</a><br/>
        Example usage:<br/>
        <a href = "http://localhost:2020/srv/signup?user=Cat&pass=1234&name=Andy" >http://localhost:2020/srv/signup?user=Cat&pass=1234&name=Andy</a><br/>
        <a href = "http://localhost:2020/srv/signup?user=Skywalker&pass=1234&name=Daniel" >http://localhost:2020/srv/signup?user=Skywalker&pass=1234&name=Daniel</a><br/>
        <a href = "http://localhost:2020/srv/signup?user=I<3CPP&pass=1234&name=Jim" >http://localhost:2020/srv/signup?user=I<3CPP&pass=1234&name=Jim</a><br/>
        <a href = "http://localhost:2020/srv/login?user=Cat&pass=1234" >http://localhost:2020/srv/login?user=Cat&pass=1234</a><br/>
     
<% } else { %>
    <!-- in HTML, we can expand a session variable with the {} notation -->
    You are logged in as: ${username}<br/>
    <a href="http://localhost:2020/srv/logout">http://localhost:2020/srv/logout</a><br/>
<% } %>
</div>
</body>
</html>
