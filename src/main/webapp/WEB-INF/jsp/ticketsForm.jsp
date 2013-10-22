<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="filterFormDiv">
	<form:form method="POST" action="" modelAttribute="ticketFilter"
		id="ticketsSearchForm" onsubmit="return false;">
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
	<button id="searchTicketsHtml">Search(HTML)</button>
	<button id="searchTicketsPdf">Search(PDF)</button>
</div>
<script>
	getTicketsHtml();
	$('#searchTicketsHtml').click(function() {
		getTicketsHtml();
	});
	function getTicketsHtml() {
		$.ajax({
			url : '/action/getTickets.html',
			type : 'POST',
			data : $('#ticketsSearchForm').serialize(),
			success : function(data) {
				$('#resultDiv').html(data);
			}
		});
	}
	$('#searchTicketsPdf').click(function() {
		getTicketsPdf();
	});
	function getTicketsPdf() {
		window.location.assign('/action/getTickets.pdf?'+$('#ticketsSearchForm').serialize());
	}
</script>
