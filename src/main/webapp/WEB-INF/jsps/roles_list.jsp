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

</head>
<body>
	<c:import url="/WEB-INF/jsps/header.jsp" />
	<c:import url="/WEB-INF/jsps/sidebar.jsp" />
	<div class="main-container" id="container">

		<div class="overlay"></div>
		<div class="cs-overlay"></div>
		<div class="search-overlay"></div>

		<div id="content" class="main-content">
			<div class="row" style="margin: 0;">
				<div id="flActionButtons"
					class="col-lg-12 layout-spacing layout-top-spacing">
					<div class="statbox widget box box-shadow">
						<div class="widget-header">
							<div class="row">
								<div class="col-xl-12 col-md-12 col-sm-12 col-12">
									<h3>Roles List</h3>
								</div>
							</div>
						</div>
						<div class="widget-content widget-content-area">
                                   <div class="table-responsive">

							<div class="table-responsive">
								<table id="datatableId"
									class="display nowrap table table_padding_custom table-hover table-striped table-bordered"
									style="width: 100%">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Role Name</th>
											<th>Created Date</th>
											<th>Modified Date</th>
											<th>Actions</th> 
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="list">
											<tr>
												<td><c:set var="count" value="${count + 1}"
														scope="page" /> <c:out value="${count}" /></td>
												<td>${list.roleName}</td>
												<td><fmt:formatDate pattern="dd/MM/yyyy hh:mm a"
														value="${list.createdAt}" /></td>
												<td><fmt:formatDate pattern="dd/MM/yyyy hh:mm a"
														value="${list.updatedAt}" /></td>
												 <td><a 
													href="<c:url value="/role/edit?id=${list.roleId}"/>"
													data-toggle="tooltip" data-placement="right" title="Edit"><i
														class="icon-edit left"></i>Edit</a> <a 
													href="#"
													onclick="deleteById('<c:out value="${list.roleId}"/>','/role/delete')"
													data-toggle="tooltip" data-placement="right" title="Delete"><i
														class="icon-bin left"></i>Delete</a>
											</tr>

										</c:forEach>

									</tbody>

								</table>
							</div>
						</div>
					</div>
					<div class="col-xl-12 col-md-12 col-sm-12 col-12">

				<a class="btn btn-primary"  href="<c:url value="role_creation"/>">Create New Role</a>
			</div>
			<br>
				</div>
				
		</div>
		</div>
	</div>
</div>
	<c:import url="/WEB-INF/jsps/loadJs.jsp" />
	<!-- <script type="text/javascript">

$(document).ready(function() {
	  $('#datatableId').DataTable( {
		  "scrollX" : true
	    } );
} );
</script> -->

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