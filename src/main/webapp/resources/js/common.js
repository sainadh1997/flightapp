

$(document).ready(function(){
$('.numeric').on('input', function() {
    this.value = this.value.match(/^\d+/);
}); 
 

$('.alpha').on('input', function() {
    this.value = this.value.match(/^[a-zA-Z]+/);	 
});

$('.alphaWithSpace').on('input', function() {
    this.value = this.value.match(/^[a-zA-Z\s]*$/);	 
});

$('.email').on('blur', function() {
    this.value = this.value.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/);
   // alertify.error('please enter valid email');
}); 

$('.lengthTen').on('blur', function() {
	if(this.value.length != 10){
		this.value="";
		alertify.error(' Mobile number should be exact 10 digits');
	}
});

$('#panNumber').on('blur', function() {
   // this.value = this.value.match(/[a-zA-z]{5}\d{4}[a-zA-Z]{1}/);
	this.value = this.value.match(/[A-Z]{5}\d{4}[A-Z]{1}/);
	
   // alertify.error('Enter valid pan number');
});


$('.ifsc').on('blur', function() {
    this.value = this.value.match(/^[A-Z]{4}\d{7}$/);	 
}); 

$('#pfNumber').on('blur', function() {
   // this.value = this.value.match(/^[A-Z]{2}\d{5}\d{7}$/); //ex:
	// XX123451234567
	this.value = this.value.match(/^[A-Z]{5}\d{17}$/);
});

$('#passportNumber').on('blur', function() {
		this.value = this.value.match(/^[A-Z][0-9]{8}$/);
});

$('.calanders').datepicker({
	  dateFormat: 'dd/mm/yy' 
});




});

function isNumericKey(evt)
{

	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if ( charCode > 31 
	&& (charCode < 48 || charCode > 57))
	return false;
	return true;
};


function isAlpha(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	   
	    if (!(charCode > 64 && charCode < 91) && // upper alpha (A-Z)
	        !(charCode > 96 && charCode < 123))  // lower alpha (a-z)
	      return false;
	  return true;
	};  
	
	
	var _validFileExtensions = [".jpg", ".jpeg", ".pdf", ".gif", ".png",".doc",".docx"];    
	function ValidateSingleInput(oInput) {
	    if (oInput.type == "file") {
	        var sFileName = oInput.value;
	        var fileSize = oInput.files[0].size;
	        const size =   ( fileSize/ 1024 / 1024).toFixed(2);
	        
	        if(size > 10){
	        	var blnValid = false;
	        	if (!blnValid) {
	        		alertify.alert("Sorry, file size must not exceed 10 MB ");
	                oInput.value = "";
	                return false;
	            }
	        }
	         if (sFileName.length > 0) {
	            var blnValid = false;
	            for (var j = 0; j < _validFileExtensions.length; j++) {
	                var sCurExtension = _validFileExtensions[j];
	                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
	                    blnValid = true;
	                    break;
	                }
	            }
	             
	            if (!blnValid) {
	            	alertify.alert("Sorry, " + sFileName + " is invalid, allowed extensions are: " + _validFileExtensions.join(", "));
	                oInput.value = "";
	                return false;
	            }
	        }
	        	    }
	    return true;
	}
