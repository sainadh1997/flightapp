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
						<c:if test="${userinfo.userid!=null}">
							<div class="widget-header">
								<div class="row">
									<div class="col-xl-12 col-md-12 col-sm-12 col-12">
										<h3>Update User Information</h3>
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${userinfo.userid==null}">
							<div class="widget-header">
								<div class="row">
									<div class="col-xl-12 col-md-12 col-sm-12 col-12">
										<h3>Create New User</h3>
									</div>
								</div>
							</div>
						</c:if>
						<div class="widget-content widget-content-area">
						<c:url value="/user/saveUserInfo" var="createUrl" />
							<form action="${createUrl}" method="POST"
								modelAttribute="userinfo" enctype="multipart/form-data"
								id="form">
								
                                        <div class="row">
                                        <input type="hidden" cssClass="form-control"
														path="userid" value="${userinfo.userid}" id="userid" name="userid" />	 
                                        <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                            <label>First Name :</label> <input type="text"
												class="form-control" required="true"
												placeholder='First Name' path="firstName" id="firstName"
												name="firstName" value="${userinfo.firstName}" />
                                        </div>
                                        
                                         <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                           <label>Last Name</label> <input type="text"
												class="form-control" required="true" placeholder='Last Name'
												path="lastName" id="lastName" name="lastName" value="${userinfo.lastName}"/>
                                        </div>
                                        
                                          <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                          <label>Gender</label> <select class="form-control"
												id="gender" name="gender">
												<option value="na">--Select--</option>
												 <option value="male" <c:if test="${userinfo.gender == 'male'}"> selected </c:if>>Male</option>
	 											<option value="female" <c:if test="${userinfo.gender == 'female'}"> selected </c:if>>Female</option>
											</select>
                                          </div>
                                          
                                          <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                          <label>Login User Name</label> <input type="text"
												class="form-control" required="true" placeholder='User Name'
												path="username" id="username" name="username" value="${userinfo.username}" />
                                          </div>
                                          <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                          <label>Password</label> <input type="password"
												class="form-control" required="true" placeholder='Password'
												path="password" id="password" name="password" value="${userinfo.password}"/>
                                          </div>
                                          <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                          <label>Date Of Birth</label> <input type="text"
												class="form-control" id="DOB" readonly="false" path="date"
												autocomplete="off" name="DOB" value="${userinfo.DOB}"/>
                                          </div>
                                          
                                           <div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
                                           <label>Role</label> <select class="form-control" id="role"
												name="role">
												<option value="na">--Select--</option>
												<c:forEach items="${rolesList}" var="role">
												<c:choose>
												<c:when test="${userinfo.role eq role.roleName}">
												<option value="${role.roleName}" selected >${role.roleName}</option>
												</c:when>
												<c:otherwise>
												<option value="${role.roleName}">${role.roleName}</option>
												</c:otherwise>
												</c:choose>
													
												</c:forEach>
											</select>
                                           </div>
                                        </div>
                                        
                                        <c:if test="${userinfo.userid !=null}">
							<button type="submit" class="btn btn-primary mySubButton">
								<i class="icon-check2"></i>Update
							</button>
						</c:if>

						<c:if test="${userinfo.userid==null}">
							<button type="submit" class="btn btn-primary mySubButton">
								<i class="icon-check2"></i>Save
							</button>
						</c:if>
                                        
                                        
								</form>
						</div>


											</div>
				</div>

			</div>
		</div>
	</div>


	<c:import url="/WEB-INF/jsps/loadJs.jsp" />
</body>

<script type="text/javascript">
	$("#username").change(
			function() {
				var username = $("#username").val();
				//document.getElementById("username").value = employeeNumber;
				var dataString = "username=" + username;
				//alert(employeeNumber);
				if (username != '') {
					//$(".mySubButton").prop('disabled', true);					 
					$.ajax({
						type : "GET",
						url : "<c:url value="/user/isUniqueusername"/>",
						data : dataString,
						success : function(result) {
							//$(".mySubButton").prop('disabled',false);             
							if (result == true) {
								alertify.alert(username
										+ " User Name already exists");
								$("#username").val('');
								//$("#form").validator("update");
							}
						},
						error : function() {
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
					changeMonth : true,
					changeYear : true,
					yearRange : "-50:+0",
					maxDate : '0',
					//minDate : '-20y',
					onSelect : function(selected) {
						$(this).select();

					}
				});

				//$('#DOB').prop("readonly", true);

				/* $('input[required="true"],select[required="true"]', '#form')
						.on("blur", function(e) {
							$("#form").validator("update");
						}); */

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
});
</script>


</html>