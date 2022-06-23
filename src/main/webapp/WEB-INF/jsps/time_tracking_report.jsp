<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight Application</title>

<c:import url="/WEB-INF/jsps/loadcss.jsp" />
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
										<h3>Time Sheet Report</h3>
									</div>
								</div>
							</div>
				<div class="widget-content widget-content-area">
									<div class="row">


										<c:choose>
											<c:when test="${roleName eq 'Captain' }">
											<input type="hidden" class="form-control" readonly="readonly"  id=captainid name="captainid"  value="${userid}">
												
											</c:when>
											<c:otherwise>
												<div class="col-sm-4 form-group-user">
													<label>Captain Name</label> <select class="form-control" required
														id=captainid name="captainid">
														<option value="na">--Select--</option>
														<c:forEach items="${captainList}" var="cap">
															<option value="${cap.userid}">${cap.firstName}
																${cap.lastName}</option>
														</c:forEach>
													</select>
												</div>

											</c:otherwise>
										</c:choose>

										<div class="col-sm-4 form-group-user">
											<label>From Date</label> <b><input type="text" class="form-control" required
												id="fromDate" placeholder='Date' readonly="true" path="date"
												autocomplete="off"  name="fromDate"
												 /></b>
										</div>
										
											<div class="col-sm-4 form-group-user">
											<label>To Date</label> <input type="text" class="form-control" required
												 placeholder='Date' readonly="true" path="date"
												autocomplete="off" id="toDate" name="toDate"
												/>
										</div>




									</div>
									
									

								</div>
								<button type="button" class="btn btn-primary" id="search_button" >
					<i class="icon-search"></i>Search
				</button>

						<div id="msgDiv" style="display: none;">
							<font color="red">No Search Results Found!!!</font>
						</div>



						<div class="widget-content widget-content-area" id="search_List" style="display: none;">

							<div class="table-responsive">
								<table id="datatableId"
									class="display nowrap table table_padding_custom table-hover table-striped table-bordered"
									style="width: 100%">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Date</th>
											<th>Captain Name</th>
											<th>OnDuty</th>
											<th>Scheduled Arrival </th>
											<th>Schedule Departure</th>
											<th>Off Duty</th>
											
											<!-- <th>Actions</th> -->
										</tr>
									</thead>
									<tbody id="search_data">


									</tbody>

								</table>
							</div>
							
							<div>
							<a href="<c:url value='/timetracking/downloadTimesheetExcel'/>" id="exceldownload" 
							class="excel-download exceldownload float-sm-right"
							title="Excel Report Download">Excel Download</a> 
							</div>
					
					</div>
							</div>
							
							
							
						</div>
					</div>

				



			</div>
		</div>

	
		

				


	<c:import url="/WEB-INF/jsps/loadJs.jsp" />
	<script type="text/javascript">

/* $(document).ready(function() {
	  $('#datatableId').DataTable( {
		  "scrollX" : true,
		  "pagingType": "",
		  "paging": false,
		  "searching": false,
		  "info":false
	    } );
} ); */
</script>

	<script
		src=<c:url value="/resources/js/scripts/dataTables/jquery.dataTables.min.js"/>
		type="text/javascript"></script>
	<script
		src=<c:url value="/resources/js/scripts/dataTables/buttons.html5.min.js"/>
		type="text/javascript"></script>
</body>

<script>
	$(document).ready(
			function() {
				$('#fromDate').datepicker({
					dateFormat : 'dd/mm/yy',
					changeMonth : true,
					changeYear : true,
					changeDate : true,
					yearRange : "-50:+0",
					maxDate : new Date().toLocaleDateString(),
					onSelect : function(selected) {
						$("#toDate").datepicker("option", "minDate", $('#fromDate').val());
						//$(this).select();
					}
				});
				
				
				$('#toDate').datepicker({
					dateFormat : 'dd/mm/yy',
					changeMonth : true,
					changeYear : true,
					changeDate : true,
					maxDate : new Date().toLocaleDateString(),
					onSelect : function(selected) {
						$(this).select();
					}
				});

				$('#date').prop("readonly", true);

				$('input[required="true"],select[required="true"]', '#form')
						.on("blur", function(e) {
							$("#form").validator("update");
						});

			});

		
	
	$("#search_button").click(function(){
		
		var fromDate=$('#fromDate').val();
		var captainid=$('#captainid').val();
		var todate=$('#toDate').val();
		if(captainid == '' ||captainid =='na'){
			alert("Please Select Captain Name");
			return false;
		}
		if(fromDate == ''){
			alert("Please Select From Date");
			return false;
		}
		if(todate == '' ){
			alert("Please Select To Date");
			return false;
		}
		
		
		  $.ajax({
				type : "GET",
				url : "<c:url value="/timetracking/search"/>",
				data: {
					captainId: $("#captainid").val(),
					search_from_date: $("#fromDate").val(),
					search_to_date:$("#toDate").val()
				},
				success : function(result) {   
					//alert(result.length);
					if(result.length > 0){
						$('#msgDiv').hide();
					$('#search_List').show();
					$.each(result, function(index,jsonObject){
					    	var html = "<tr><td>"+(index+1)+"</td><td>"+jsonObject.dutyDate+"</td><td>" +jsonObject.captainName+ "</td><td>" +jsonObject.onduty+ "</td><td>" +jsonObject.scheduledeparture+ "</td><td>" +jsonObject.schedulearraival+ "</td><td>" +jsonObject.offduty+ "</td></tr>";
					        $('#search_data').append(html);
					});
					}else{
						alert("No Search Results Found")
						$('#search_List').hide();
						$('#msgDiv').show();
					}
					
				},
				error : function() {
					//$(".mySubButton").prop('disabled', false);
				}
			});
		}); 
	
					
	
</script>

<script
	src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/>
	type="text/javascript"></script>


</html>