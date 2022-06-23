<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.security.Principal"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  BEGIN SIDEBAR  -->
<div class="sidebar-wrapper sidebar-theme">

	<nav id="sidebar">
		<div class="profile-info">
			<figure class="user-cover-image"></figure>
			<div class="user-info">
				<img src="<c:url value="/resources/assets/img/profile-17.jpg"/>"
					alt="avatar">
				<sec:authorize access="isAuthenticated()">

					<h6>
						<sec:authentication property="principal.username" />
					</h6>
					<%-- <p>
						<sec:authentication property="principal.username" />
					</p> --%>
				</sec:authorize>
			</div>
		</div>

		<ul class="list-unstyled menu-categories" id="accordionExample">

			<li class="menu"><a href="<c:url value="/home"/>"
				data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
					<div class="">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
							viewBox="0 0 24 24" fill="none" stroke="currentColor"
							stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
							class="feather feather-home">
							<path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
							<polyline points="9 22 9 12 15 12 15 22"></polyline></svg>
						<span>Dashboard</span>
					</div>
			</a></li>

			<c:if test="${roleName =='USER'}">
				<li class="menu active"><a
					href="<c:url value="/timetracking/list"/>" aria-expanded="true"
					class="dropdown-toggle">
						<div class="">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
								viewBox="0 0 24 24" fill="none" stroke="currentColor"
								stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
								class="feather feather-layout">
								<rect x="3" y="3" width="18" height="18" rx="2" ry="2"></rect>
								<line x1="3" y1="9" x2="21" y2="9"></line>
								<line x1="9" y1="21" x2="9" y2="9"></line></svg>
							<span>Time Sheet Entry</span>
						</div>
				</a></li>
			</c:if>
			<c:if test="${roleName =='ADMIN'}">
				<li class="menu active"><a
					href="<c:url value="/timetracking/list"/>" aria-expanded="true"
					class="dropdown-toggle">
						<div class="">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
								viewBox="0 0 24 24" fill="none" stroke="currentColor"
								stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
								class="feather feather-layout">
								<rect x="3" y="3" width="18" height="18" rx="2" ry="2"></rect>
								<line x1="3" y1="9" x2="21" y2="9"></line>
								<line x1="9" y1="21" x2="9" y2="9"></line></svg>
							<span>Time Sheet Entry</span>
						</div>
				</a></li>
			</c:if>

			<li class="menu active"><a
				href="<c:url value ="/timetracking/report"/>" aria-expanded="true"
				class="dropdown-toggle">
					<div class="">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
							viewBox="0 0 24 24" fill="none" stroke="currentColor"
							stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
							class="feather feather-layout">
							<rect x="3" y="3" width="18" height="18" rx="2" ry="2"></rect>
							<line x1="3" y1="9" x2="21" y2="9"></line>
							<line x1="9" y1="21" x2="9" y2="9"></line></svg>
						<span>Time Sheet Report</span>
					</div>
			</a></li>

			<!-- <li class="menu">
                        <a href="#datatables" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                            <div class="">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-airplay"><path d="M5 17H4a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-1"></path><polygon points="12 15 17 21 7 21 12 15"></polygon></svg>
                                <span>Captain</span>
                            </div>
                        </a>
                    </li> -->
			<c:if test="${roleName =='ADMIN'}">
				<li class="menu"><a href="<c:url value ="/user/list"/>"
					aria-expanded="false" class="dropdown-toggle">
						<div class="">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
								viewBox="0 0 24 24" fill="none" stroke="currentColor"
								stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
								class="feather feather-clipboard">
								<path
									d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path>
								<rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect></svg>
							<span>User</span>
						</div>
				</a></li>

				<li class="menu"><a href="<c:url value ="/role/list"/>"
					aria-expanded="false" class="dropdown-toggle">
						<div class="">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
								viewBox="0 0 24 24" fill="none" stroke="currentColor"
								stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
								class="feather feather-layers">
								<polygon points="12 2 2 7 12 12 22 7 12 2"></polygon>
								<polyline points="2 17 12 22 22 17"></polyline>
								<polyline points="2 12 12 17 22 12"></polyline></svg>
							<span>Role</span>
						</div>
				</a></li>
			</c:if>
		</ul>

	</nav>

</div>

<!--  END SIDEBAR  -->