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
<%-- <link
	href="<c:url value="/resources/css/dataTables/buttons.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" /> --%>

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
									<h3>Time Tracking List</h3>
								</div>
							</div>
						</div>
						<div class="widget-content widget-content-area">
                                   <div class="table-responsive">
								<table id="datatableId"
									class="display nowrap table table_padding_custom table-hover table-striped table-bordered"
									style="width: 100%">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Captain Name</th>
											<th>Duty Date</th>
											<th>OnDuty</th>
											<th>Schedule Departure</th>
											<th>Schedule Arrival</th>
											<th>Off Duty</th>
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
												<td>${list.captainName}</td>
												<td>${list.dutyDate}</td>
												<td>${list.onduty}</td>
												<td>${list.scheduledeparture}</td>
												<td>${list.schedulearraival}</td>
												<td>${list.offduty}</td> 
												<td><fmt:formatDate pattern="dd/MM/yyyy hh:mm a"
														value="${list.createdAt}" /></td>
												<td><fmt:formatDate pattern="dd/MM/yyyy hh:mm a"
														value="${list.updatedAt}" /></td>
												<td><a 
													href="<c:url value="/timetracking/edit?id=${list.id}"/>"
													data-toggle="tooltip" data-placement="right" title="Edit"><i
														class="icon-edit left"></i>Edit</a> <a 
													href="#"
													onclick="deleteById('<c:out value="${list.id}"/>','/timetracking/delete')"
													data-toggle="tooltip" data-placement="right" title="Delete"><i
														class="icon-bin left"></i>Delete</a></td> 
											</tr>

										</c:forEach>

									</tbody>

								</table>
							</div>
						</div>
					</div>
				</div>
				</div>
				 <div class="col-md-3 col-sm-12">

				<a class="btn btn-primary" href="<c:url value="/timetracking/time_sheet_entry_form"/>">Add New Time Sheet Entry</a> 
			</div>
			<br>
		</div>
	</div>

	<c:import url="/WEB-INF/jsps/loadJs.jsp" />
	
</body>
</html>