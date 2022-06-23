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
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsps/header.jsp" />
	<c:import url="/WEB-INF/jsps/sidebar.jsp" />
	
	<div class="app-content content container-fluid">
		<div class="content-wrapper">
			<div class="content-body">
			<c:url value="/captain/saveCaptainInfo" var="createUrl" />
			<form action="${createUrl}" method="POST" modelAttribute="captain" enctype="multipart/form-data" id="form">
			
			<div class="card">
						<div class="card-header">
							<c:if test="${captain.captainId!=null}">
								<h2 class="card-title" id="basic-layout-icons">Update
									Captain Information</h2>
							</c:if>
							<c:if test="${captain.captainId==null}">
								<h2 class="card-title" id="basic-layout-icons">Create New
									Captain</h2>
							</c:if>
							
						</div>
<input type="hidden" cssClass="form-control"
														path="captainId" value="${captain.captainId}" id="captainId" name="captainId" />

						<div class="card-body collapse in">
							<div class="card-block collapse in create-block">
								<div class="form-body">
									<div class="row">
			 
				<div class="col-sm-4 form-group-user">
					<label>First Name</label>
						<input type="text" class="form-control" required="true" placeholder='First Name' path="captainFirstName" id="captainFirstName"  value="${captain.captainFirstName}" name="captainFirstName" />
				</div>
				
				<div class="col-sm-4 form-group-user">
					<label>Last Name</label>
						<input type="text" class="form-control" required="true" placeholder='Last Name' path="captainLastName" id="captainLastName" name="captainLastName" value="${captain.captainLastName}" />
				</div>
				
				<div class="col-sm-4 form-group-user">
					<label>Gender</label>
					<select class="form-control" id="gender" name="gender" value="${captain.gender}">
					
					<option value="na">--Select</option>
      <option value="male" <c:if test="${captain.gender == 'male'}"> selected </c:if>>Male</option>
	 <option value="female" <c:if test="${captain.gender == 'female'}"> selected </c:if>>Female</option>
     
      
										
					</select>
				</div>	
				
				</div>
				
								
				<div class="row">
				
				
				<div class="col-sm-4 form-group-user">
					<label>Date Of Birth</label>
						<input type="text" class="form-control" id="DOB"  readonly="true" path="date"  autocomplete="off"  name="DOB" value="${captain.DOB}" />
				</div>
				
				
				</div>
				<!-- <div class="col-sm-12">
				<button type="submit" class="btn btn-primary "><i class="icon-check2"></i>save</button>
				</div> -->
				
				</div>
				</div>
				</div>
				</div>
				
				</form>
				
				<c:if test="${captain.captainId!=null}">
					<button type="submit" class="btn btn-primary mySubButton">
						<i class="icon-check2"></i>Update
					</button>
				</c:if>

				<c:if test="${captain.captainId==null}">
					<button type="submit" class="btn btn-primary mySubButton">
						<i class="icon-check2"></i>Save
					</button>
				</c:if>
			</div>
			</div>
			</div>
	
		
 <c:import url="/WEB-INF/jsps/loadJs.jsp" /> 
</body>

<script type="text/javascript">

$("#username").change(function() {
	var employeeNumber = $("#username").val();
	//document.getElementById("username").value = employeeNumber;
	 var dataString  = "username="+username;
		 //alert(employeeNumber);
		 if(username!=''){
		  //$(".mySubButton").prop('disabled', true);					 
		  $.ajax({
	             type:"GET",
	             url: "<c:url value="/user/isUniqueusername"/>",
	             data : dataString,
	             success: function(result){
	               		//$(".mySubButton").prop('disabled',false);             
	                 if(result==true){	 
	                	 alertify.alert(username+" User Name already exists");
	                	 $("#username").val('');
	                	 //$("#form").validator("update");
	                	 }
	            },
				error:function(){
					//$(".mySubButton").prop('disabled', false);
				}
	            });
		 } 
	 }); 
	 
</script>

<script>

$(document).ready(
		function() {	
			$('#DOB').datepicker({
					dateFormat : 'dd/mm/yy',
					changeMonth: true,
                    changeYear: true,
                    yearRange: "-50:+0",
                    maxDate: '0',
					//minDate : '-20y',
					onSelect: function(selected) {
						$(this).select(); 	
					
					}
				});

				
				$('#DOB').prop("readonly", true);

				
			
			});
				  

	           
		</script>
		
		<script>
$(document).ready(
		function() {	
$(".mySubButton")
.on(
		'click',
		function() {	
		 //$("#form").validator("update");
		 if ($(".mySubButton").hasClass("disabled")) {
					
			
				$('input[required="true"],select[required="true"]').each(function(i, e){
				
				const target = $(this)
				let isValid = true
				if(target.is("input") && (typeof target.val() === "undefined" || target.val() === null || target.val() === ""))
					isValid = false
				if(target.is("select")){
					const val =  target.val() || target.find("option:selected").val()
					if(val === "" || val === "select"){
						isValid = false
					}
				}
				if(!isValid)
					target.css("border-color", "red");
					})
					
				//alertify.error('Please fill mandatory fields');
				alertify.alert("Please fill mandatory fields");
				//$("#form").submit();
				return false;
			} else {

				alertify
						.confirm(
								'Are you Sure Want to Save',
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
});
</script>


</html>