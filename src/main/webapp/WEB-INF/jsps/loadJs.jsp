<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



 <!-- BEGIN GLOBAL MANDATORY SCRIPTS -->
    <script src=<c:url value="/resources/assets/js/jquery-3.1.1.min.js"/> type="text/javascript"></script>
    <script src=<c:url value="/resources/assets/js/popper.min.js"/> type="text/javascript"></script>
    <script src=<c:url value="/resources/assets/js/bootstrap.min.js"/> type="text/javascript"></script>
    <script src=<c:url value="/resources/assets/js/app.js"/> type="text/javascript"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

<!-- alertifyjs -->

<link
	href="<c:url value="/resources/components/alertifyjs/css/alertify.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/components/alertifyjs/css/themes/default.css"/>"
	rel="stylesheet" type="text/css" />
	
<script
	src=<c:url value="/resources/js/scripts/ui-blocker/jquery.blockUI.js"/>
	type="text/javascript"></script>	



<script
	src=<c:url value="/resources/components/alertifyjs/alertify.min.js"/>
	type="text/javascript"></script>


<!-- alertfy-->

<script>

  
	function deleteById(id, url) {
		alertify
				.confirm(
						'Are you Sure Want to Delete',
						function() {

							var parts = url.split('/');
							var answer = parts[parts.length - 1];

							$.blockUI({
										css : {
											border : 'none',
											padding : '15px',
											backgroundColor : '#000',
											'-webkit-border-radius' : '10px',
											'-moz-border-radius' : '10px',
											opacity : .5,
											color : '#fff'
										},
										message : "<h3>Deleting <img src=<c:url value='/resources/images/ajax-loader.gif'/> border='0' /></h3>"
									});

							var dataString = "id=" + id;
							$.ajax({
								type : "POST",
								/*  url: url, */
								url : answer,
								data : dataString,
								success : function(result) {
									alertify.success('Deleted successfully');
									setTimeout(function() {
										location.reload();
									}, 800);
								}
							});

						}, function() {
							alertify.error('Cancelled');
							setTimeout($.unblockUI, 1000);
						});

	}
</script>


