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

<body  >

	<div id="content" class="main-content">
                    <div class="row" style="margin: 0;">
                        <div id="flActionButtons" class="col-lg-12 layout-spacing layout-top-spacing">
                            <div class="statbox widget box box-shadow">
                             <div class="widget-content widget-content-area">
				<div class="col-xl-12 col-md-12 col-sm-12 col-12">

					<div class="login-form">
						<div class="login-logo">

						 <img 
							src="<c:url value="/resources/assets/img/logo.png"/>" class="navbar-logo" alt="logo" />
						</div>
						<c:if test="${not empty errorMsg}">
							<div class="alert alert-danger">
								<b>${errorMsg}</b>
							</div>
						</c:if>
						
						

						<form name='f' role="form" method="post"
							action="<c:url value="login"/>">
							
							  <div class="col-xl-12 col-md-12 col-sm-12 col-12">
                                             <input type="text"
									class="form-control form-control-lg input-lg text-left"
									name="username" placeholder="Login User Name" required>
                                        </div>
                                        <br>
                                        
                                <div class="col-xl-12 col-md-12 col-sm-12 col-12">
                                            <input type="password"
									class="form-control form-control-lg input-lg text-left"
									name="password" placeholder="password" required>
                                        </div>
                                        
							<!-- <fieldset
								class="form-group position-relative has-icon-left mb-0 inputWithIcon" >
								<input type="text"
									class="form-control form-control-lg input-lg text-left"
									name="username" placeholder="Login User Name" required>
								<div class="form-control-position">
									<i class="icon-user4"></i>
								</div>
							</fieldset>
							<fieldset class="form-group position-relative has-icon-left">
								<input type="password"
									class="form-control form-control-lg input-lg text-left"
									name="password" placeholder="password" required>
								<div class="form-control-position">
									<i class="icon-key2"></i>
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<div class="col-md-6 col-xs-12 text-xs-center text-md-left">

								</div>

							</fieldset>
							
 -->							
 <br>
 <button type="submit" class="btn btn-primary btn-lg btn-block">
								<i class="icon-unlock-alt"></i> Login
							</button>
						</form>

					</div>


				</div>
			</div>
			</div>
			
			</div>
		</div>
	</div>




</body>
</html>