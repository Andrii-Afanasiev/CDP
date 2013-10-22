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
			<th>USER NAME</th>
		</tr>
		<c:forEach items="${tickets}" var="ticket">
			<tr>
				<td>${ticket.ticket.title}</td>
				<td>${ticket.ticket.date}</td>
				<td>${ticket.ticket.category}</td>
				<td>${ticket.ticket.place}</td>
				<td>${ticket.userName}</td>
			</tr>
		</c:forEach>
	</table>
</div>
