<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
    <title>Airline - Time Sheet form</title>
<c:import url="/WEB-INF/jsps/loadcss.jsp" />

</head>


<body>
	<c:import url="/WEB-INF/jsps/header.jsp" />
	<c:import url="/WEB-INF/jsps/sidebar.jsp" />
        <!--  BEGIN CONTENT AREA  -->
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
                                            <h3>Create New Time Sheet Entry</h3>
                                            <h6>It is Very Easy to Track Every Pilot’s On Duty/Off Duty</h6>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget-content widget-content-area">
                                <c:url value="/timetracking/saveTimeTracking" var="createUrl" />
                                    <form class="form-vertical" action="${createUrl}" method="POST"
					modelAttribute="timetracking" autocomplete="off" id="form"
					enctype="multipart/form-data">
                                        <div class="row">
                                        <input type="hidden" cssClass="form-control" path="roleId"
											value="${timetracking.id}" id="id" name="id" />
                                        <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                            <label>Captain Name</label> <select class="form-control"
												id=captainid name="captainid">
												<option value="na">--Select--</option>
												<c:forEach items="${captainList}" var="cap">

													<c:choose>
														<c:when test="${timetracking.captainid eq cap.userid}">
															<option value="${cap.userid}" selected>${cap.firstName}
																${cap.lastName}</option>
														</c:when>
														<c:otherwise>
															<option value="${cap.userid}">${cap.firstName}
																${cap.lastName}</option>
														</c:otherwise>
													</c:choose>

												</c:forEach>

											</select>

                                        </div>
                                        <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                            <label>Date</label> <input type="text" class="form-control"
												id="dutyDate" placeholder='Date' readonly="true" path="date"
												autocomplete="off" id="dutyDate" name="dutyDate"
												value="${timetracking.dutyDate}" />
                                        </div>
                                        
                                         <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                            <label>Flight Type</label>
                                            <select class="form-control"
												id="flighttype" name="flighttype">
												<option value="Domestic">Domestic</option>
												<option value="International">International</option>
											</select>
                                        </div>
                                        
                                        <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                           <label>On Duty</label> <input type="time"
												class="form-control" required="true" path="onduty"
												id="onduty" name="onduty" value="${timetracking.onduty}" />
                                        </div>
                                        <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                            <label>Schedule Departure</label> <input type="text"
												class="form-control" required="true" readonly
												path="scheduledeparture" id="scheduledeparture"
												name="scheduledeparture"
												value="${timetracking.scheduledeparture}" />
                                        </div>
                                        <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                           <label>Schedule Arrival : </label> <input type="time"
												class="form-control" required="true" path="schedulearraival"
												id="schedulearraival" name="schedulearraival"
												value="${timetracking.schedulearraival}" />
                                        </div>
                                        <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                           <label>Off Duty:</label> <input type="text"
												class="form-control" readonly required="true" path="offduty"
												id="offduty" name="offduty" value="${timetracking.offduty}" />
                                        </div>
                                        <div class="col-xl-12 col-md-12 col-sm-12" >
                                        <input type="submit" value="Save" class="btn btn-primary ml-3 mySubButton"> 
                                        </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    
        <!--<div class="footer-wrapper">
                <div class="footer-section f-section-1">
                    <p class="">Copyright © 2022, All rights reserved.</p>
                </div>
            </div>
        </div>-->
        <!--  END CONTENT AREA  -->

    </div>
    <!-- END MAIN CONTAINER -->
    
   </div>
   
    
      
    
    <!-- END GLOBAL MANDATORY SCRIPTS -->
    <c:import url="/WEB-INF/jsps/loadJs.jsp" />
</body>

 <script>
	$(document).ready(
			function() {
				$('#dutyDate').datepicker({
					dateFormat : 'dd/mm/yy',
					changeMonth : true,
					changeYear : true,
					changeDate : true,
					yearRange : "-50:+0",
					maxDate : '0',
					//minDate : '-20y',
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

	$(".mySubButton")
			.on(
					'click',
					function() {
						$("#form").validator("update");
						if ($(".mySubButton").hasClass("disabled")) {

							$('input[required="true"],select[required="true"]')
									.each(
											function(i, e) {

												const target = $(this)
												let isValid = true
												if (target.is("input")
														&& (typeof target.val() === "undefined"
																|| target.val() === null || target
																.val() === ""))
													isValid = false
												if (target.is("select")) {
													const val = target.val()
															|| target
																	.find(
																			"option:selected")
																	.val()
													if (val === ""
															|| val === "select") {
														isValid = false
													}
												}
												if (!isValid)
													target.css("border-color",
															"red");
											})

							//alertify.error('Please fill mandatory fields');
							alertify.alert("Please fill mandatory fields");
							//$("#form").submit();
							return false;
						} else {

							alertify
									.confirm(
											'Are you sure want to save?',
											function(event) {
												if (event) {
													$("#form").submit();
													$
															.blockUI({
																css : {
																	border : 'none',
																	padding : '15px',
																	backgroundColor : '#000',
																	'-webkit-border-radius' : '10px',
																	'-moz-border-radius' : '10px',
																	opacity : .5,
																	color : '#fff'
																},
																message : "<h3>Saving   <img src=<c:url value='/resources/images/ajax-loader.gif'/> border='0' /></h3>"
															});
													alertify
															.success('Saved Successfully');
													return true;
												} else {
													return false;
												}

											});

							return false;
						}
					});

	function addMinutes(time/*"hh:mm"*/, minsToAdd/*"N"*/) {
		function z(n) {
			return (n < 10 ? '0' : '') + n;
		}
		var bits = time.split(':');
		var mins = bits[0] * 60 + (+bits[1]) + (+minsToAdd);
		return z(mins % (24 * 60) / 60 | 0) + ':' + z(mins % 60);
	}
	
	$("#flighttype").change(function() {
		flighttype=$("#flighttype").val();
		addtime=0;
		if (flighttype=="Domestic")
		{
			addtime=45;
		}
		else{
			addtime=60;
		}
		
		var d = $("#onduty").val();
		if (d!= null && d!= ""){
			var dateVal = d.split(":");
			var hours = dateVal[0];
			var minutes = dateVal[1];
			var Time = hours + ':' + minutes;
			var DisplayTime = addMinutes(Time, addtime);
			$('#scheduledeparture').val(DisplayTime);
		}
		var schedule_arrival = $("#schedulearraival").val();
		if (schedule_arrival != null && schedule_arrival != ""){
			var schedule_arrival_dateVal = schedule_arrival.split(":");
			var schedule_arrival_hours = schedule_arrival_dateVal[0];
			var schedule_arrival_minutes = schedule_arrival_dateVal[1];
			var Time = schedule_arrival_hours + ':' + schedule_arrival_minutes;
			var DisplayTime = addMinutes(Time, 15);
			//var afterspl=DisplayTime.split(":");
			//var ampm = afterspl[0] >= 12 ? 'pm' : 'am';
			// scheduleDp=DisplayTime +' '+ampm;
			$('#offduty').val(DisplayTime);
		}
		
		
		
	});
	

	$("#onduty").change(function() {
		$("#scheduledeparture").val('');
		$("#schedulearraival").val('');
		$('#offduty').val('');
		flighttype=$("#flighttype").val();
		//alert(flighttype);
		addtime=0;
		if (flighttype=="Domestic")
		{
			addtime=45;
		}
		else{
			addtime=60;
		}
		var d = $("#onduty").val();
		var dateVal = d.split(":");
		var hours = dateVal[0];
		var minutes = dateVal[1];
		var Time = hours + ':' + minutes;
		var DisplayTime = addMinutes(Time, addtime);
		//var afterspl=DisplayTime.split(":");
		//var ampm = afterspl[0] >= 12 ? 'pm' : 'am';
		//scheduleDp=DisplayTime +' '+ampm;

		$('#scheduledeparture').val(DisplayTime);
	});

	$("#schedulearraival").change(function() {
		var d = $("#schedulearraival").val();
		var dateVal = d.split(":");
		var hours = dateVal[0];
		var minutes = dateVal[1];
		var Time = hours + ':' + minutes;
		var DisplayTime = addMinutes(Time, 15);
		//var afterspl=DisplayTime.split(":");
		//var ampm = afterspl[0] >= 12 ? 'pm' : 'am';
		// scheduleDp=DisplayTime +' '+ampm;
		$('#offduty').val(DisplayTime);
	});
	
	
</script>
<script
	src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/>
	type="text/javascript"></script>
 
</html>


