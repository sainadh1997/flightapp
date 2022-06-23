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
<body >
	<c:import url="/WEB-INF/jsps/header.jsp" />
	<c:import url="/WEB-INF/jsps/sidebar.jsp" />
	<c:if test="${timetrackingList.size() > 0}">
	
	 <div class="main-container" id="container">

        <div class="overlay"></div>
        <div class="cs-overlay"></div>
        <div class="search-overlay"></div>
        
        <div id="content" class="main-content">
                    <div class="row" style="margin: 0;">
                        <div id="flActionButtons" class="col-lg-12 layout-spacing layout-top-spacing">
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
										class="display nowrap table table_padding_custom table-hover  table-bordered"
										style="width: 100%">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Captain Name</th>
												<th>Date</th>
												<th>OnDuty</th>
												<th>Schedule Departure</th>
												<th>Schedule Arrival</th>
												<th>Off Duty</th>

											</tr>
										</thead>
										<tbody>
											<c:forEach items="${timetrackingList}" var="list">
												<tr>
													<td><c:set var="count" value="${count + 1}"
															scope="page" /> <c:out value="${count}" /></td>
													<td>${list.captainName}</td>
													<td>${list.dutyDate}</td>
													<td>${list.onduty}</td>
													<td>${list.scheduledeparture}</td>
													<td>${list.schedulearraival}</td>
													<td>${list.offduty}</td>
												</tr>

											</c:forEach>

										</tbody>

									</table>
								</div>
						</div>
					</div>
				</div>
				</div>
				
			<br>
		</div>
	</div>
</c:if>
	
			ghvghhghgjghgjhkjhk
		<c:import url="/WEB-INF/jsps/loadJs.jsp" />
</body>
</html>