<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
    	<h1>Flypost</h1>
    	<h2>${flypost.headline}</h2>
    	
   		<c:if test="${imageUrl != null}">
   		<div>
   			<img border="0" src="${imageUrl}" />
   		</div>
   		</c:if>
    	
    	<p>
    		${flypost.description}
    	</p>
    	
    	<p>
    		${flypost.contactData}
    	</p>
    	
    </body>
</html>