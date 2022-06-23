<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Application</title>
<c:import url="/WEB-INF/jsps/loadcss.jsp" />
<link
	href="<c:url value="/resources/css/dataTables/buttons.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/dataTables/jquery.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />
</head>
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsps/header.jsp" />
	<c:import url="/WEB-INF/jsps/sidebar.jsp" />
	<div class="app-content content container-fluid">
		<div class="content-wrapper">
			<div class="content-body">
				<div class="card">
					<div class="card-header">
						<div class="row">
							<div class="col-md-3">
								<h2 class="content-header-title">Captains List</h2>
							</div>
						</div>
					</div>

					<div class="card-body collapse in">
						<div class="card-block card-dashboard">

							<div class="table-responsive">
								<table id="datatableId"
									class="display nowrap table table_padding_custom table-hover table-striped table-bordered"
									style="width: 100%">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Captain Name</th>
											<th>Gender</th>
											<th>Date Of Birth</th>
											<th>Created Date</th>
											<th>Modified Date</th>
											<!-- <th>Actions</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="list">
											<tr>
												<td><c:set var="count" value="${count + 1}"
														scope="page" /> <c:out value="${count}" /></td>
												<td>${list.captainFirstName} ${list.captainLastName} </td>
												<td>${list.gender}</td>
												<td><fmt:formatDate pattern="dd-MM-yyyy"
														value="${list.dateofBirth}" /></td> 
												<td><fmt:formatDate pattern="dd/MM/yyyy hh:mm a"
														value="${list.createdAt}" /></td>
												<td><fmt:formatDate pattern="dd/MM/yyyy hh:mm a"
														value="${list.updatedAt}" /></td>
												<td><a class="btn btn-edit"
													href="<c:url value="/captain/edit?id=${list.captainId}"/>"
													data-toggle="tooltip" data-placement="right" title="Edit"><i
														class="icon-edit left"></i></a> <a class="btn btn-delete"
													href="#"
													onclick="deleteById('<c:out value="${list.captainId}"/>','/captain/delete')"
													data-toggle="tooltip" data-placement="right" title="Delete"><i
														class="icon-bin left"></i></a> <%-- <a class="btn btn-view"
													href="<c:url value="/employee/view?id=${list.captainid}"/>"><i
														class="icon-eye3 left" data-toggle="tooltip"
														data-placement="right" title="View"></i></a> --%></td>
											</tr>

										</c:forEach>

									</tbody>

								</table>
							</div>
						</div>
					</div>
				</div>
				 <div class="col-md-3 col-sm-12"> 

				 <a class="btn btn-primary" href="<c:url value="/captain/captain_creation"/>">Add New Captain</a> 
			</div>
			<br>
		</div>
	</div>
</div>
	<c:import url="/WEB-INF/jsps/loadJs.jsp" />
	<script type="text/javascript">

$(document).ready(function() {
	  $('#datatableId').DataTable( {
		  "scrollX" : true
	    } );
} );
</script>

	<script
		src=<c:url value="/resources/js/scripts/dataTables/jquery.dataTables.min.js"/>
		type="text/javascript"></script>
	<script
		src=<c:url value="/resources/js/scripts/dataTables/dataTables.buttons.min.js"/>
		type="text/javascript"></script>
	<script
		src=<c:url value="/resources/js/scripts/dataTables/buttons.html5.min.js"/>
		type="text/javascript"></script>
</body>
</html>