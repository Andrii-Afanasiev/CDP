<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="ticketsDiv">
	<table>
		<tr>
			<th>Title</th>
			<th>Date</th>
			<th>Category</th>
			<th>Place</th>
			<th>BOOK</th>
		</tr>
		<c:forEach items="${tickets}" var="ticket">
			<tr>
				<td>${ticket.title}</td>
				<td>${ticket.date}</td>
				<td>${ticket.category}</td>
				<td>${ticket.place}</td>
				<td><input type="button" id="book${ticket.id}" class="book"
					value="BOOK" /></td>
			</tr>
		</c:forEach>
	</table>
</div>
<script>
	$('.book').click(function() {
		$.ajax({
			url : '/action/bookTicket',
			type : 'POST',
			data : {
				id : $(this).attr('id').substring(4),
				name : prompt('Enter yout name')
			},
			success: function(data){
				$('#resultDiv').html(data);
			}
		});
	});
</script>
