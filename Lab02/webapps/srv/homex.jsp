<!DOCTYPE html>
<html>
    <head>
        <meta charset=utf8>
    </head><body>
<div>
    
<% if( request.getSession().getAttribute("username") == null ) { %>
    You are not logged in.<br/>
	http://localhost:2020/srv/signup<br/>
	http://localhost:2020/srv/login<br/>
	Example usage:<br/>
	http://localhost:2020/srv/signup?user=Cat&pass=1234&name=Andy<br/>
	http://localhost:2020/srv/signup?user=Cat&pass=1234&name=Andy<br/>
<% } else { %>
    <!-- in HTML, we can expand a session variable with the {} notation -->
    You are logged in as: ${username}<br/>
	http://localhost:2020/srv/logout<br/>
<% } %>
</div>
</body>
</html>
