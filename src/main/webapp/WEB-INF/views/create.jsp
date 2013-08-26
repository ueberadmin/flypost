<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <body>
        <h1>Flypost</h1>
        <h2>Neu</h2>
        <form:form action="${context}/aushaenge" method="post">
        	<div>
        		<form:label path="headline">&Uuml;berschrift</form:label>
        		<form:input rows="3" path="headline"></form:input>
        	</div>
        	
        	<div>
	        	<label for="image">Bild</label>
	        	<input type="file" />
        	</div>
        	
        	<div>
        		<form:label path="description">Beschreibung</form:label>
        		<form:textarea rows="10" path="description"></form:textarea>
        	</div>
        	
        	<div>
        		<form:label path="contactData">Kontaktdaten</form:label>
        		<form:textarea rows="3" path="contactData"></form:textarea>
        	</div>
        	
        	<button type="submit">Ver&ouml;ffentlichen</button>
        </form:form>
    </body>
</html>