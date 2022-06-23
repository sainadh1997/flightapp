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
						<c:if test="${role.roleId!=null}">
							<div class="widget-header">
								<div class="row">
									<div class="col-xl-12 col-md-12 col-sm-12 col-12">
										<h3>Update Role</h3>
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${role.roleId==null}">
							<div class="widget-header">
								<div class="row">
									<div class="col-xl-12 col-md-12 col-sm-12 col-12">
										<h3>Create New Role</h3>
									</div>
								</div>
							</div>
						</c:if>
						<div class="widget-content widget-content-area">
							<c:url value="/role/saveRole" var="createUrl" />
							<form action="${createUrl}" method="POST" modelAttribute="role"
								enctype="multipart/form-data" autocomplete="off" id="form">



								<div class="row">

									<input type="hidden" cssClass="form-control" path="roleId"
										value="${role.roleId}" id="roleId" name="roleId" />
									<div class="col-xl-4 col-md-4 col-sm-4 col-4 form-group mb-4">
										<label>Role Name : </label> <input type="text"
											class="form-control" required="true" placeholder='Role Name'
											path="roleName" id="roleName" name="roleName"
											value="${role.roleName}" />
									</div>


								</div>

								<c:if test="${role.roleId!=null}">
									<button type="submit" class="btn btn-primary mySubButton">
										<i class="icon-check2"></i>Update
									</button>
								</c:if>

								<c:if test="${role.roleId==null}">
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
<script>
	$(document)
			.ready(
					function() {
						$(".mySubButton")
								.on(
										'click',
										function() {
											//alert();
											//$("#form").validator("update");
											if ($(".mySubButton").hasClass(
													"disabled")) {

												$(
														'input[required="true"],select[required="true"]')
														.each(
																function(i, e) {

																	const target = $(this)
																	let isValid = true
																	if (target
																			.is("input")
																			&& (typeof target
																					.val() === "undefined"
																					|| target
																							.val() === null || target
																					.val() === ""))
																		isValid = false
																	if (target
																			.is("select")) {
																		const val = target
																				.val()
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
																		target
																				.css(
																						"border-color",
																						"red");
																})

												//alertify.error('Please fill mandatory fields');
												alertify
														.alert("Please fill mandatory fields");
												//$("#form").submit();
												return false;
											} else {

												alertify
														.confirm(
																'Are you Sure Want to Save',
																function(event) {
																	if (event) {
																		$(
																				"#form")
																				.submit();
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