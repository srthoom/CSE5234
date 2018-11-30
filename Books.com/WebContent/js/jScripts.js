$(document).ready(function(){
//	$("#creditCardTxtBox").on("input", function(){
//		var creditCard = $('#creditCardTxtBox').val();
//		
//	})
	
	 $('#creditCardTxtBox').on('focusout', function() {
		 $("#creditCardError").html("");
		 var cardNum = $('#creditCardTxtBox').val();		 
		 var isnum = /^\d+$/.test(cardNum);
		 if(!isnum){
			 alert("Enter a valid credit card number");
//			 $("#creditCardError").html("invalid number");
		 }
	    })
	
	   
});