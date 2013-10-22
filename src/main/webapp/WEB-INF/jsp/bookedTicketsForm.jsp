<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="filterFormDiv">
	<form:form method="POST" modelAttribute="bookedTicketFilter"
		id="bookedTicketsSearchForm" onsubmit="return false;">
		<form:input path="userName" />
		<form:select path="date">
			<form:option value="">NOT SELECTED</form:option>
			<form:options items="${dates}" />
		</form:select>
		<form:select path="title">
			<form:option value="">NOT SELECTED</form:option>
			<form:options items="${movies}" />
		</form:select>
		<form:select path="ticketCategory">
			<form:option value="">NOT SELECTED</form:option>
			<form:options items="${categories}" />
		</form:select>
	</form:form>
	<button id="searchBookedTicketsHtml">Search(HTML)</button>
	<button id="searchBookedTicketsPdf">Search(PDF)</button>
</div>
<script>
	getBookedTicketsHtml();
	$('#searchBookedTicketsHtml').click(function() {
		getBookedTicketsHtml();
	});
	function getBookedTicketsHtml() {
		$.ajax({
			url : '/action/getBookedTickets.html',
			type : 'POST',
			data : $('#bookedTicketsSearchForm').serialize(),
			success : function(data) {
				$('#resultDiv').html(data);
			}
		});
	}
	$('#searchBookedTicketsPdf').click(function() {
		getBookedTicketsPdf();
	});
	function getBookedTicketsPdf() {
		window.location.assign('/action/getBookedTickets.pdf?'+$('#ticketsSearchForm').serialize());
	}
</script>