
$(function() {
	load();

});



function sub(){

    		$.ajax({
    		   url : "/ireport/getlist/"+$("#sel option:selected").val(),

    			type : "get",
    			data : {
    				 style:$("#sel option:selected").val(),
    			},
    			success : function(data) {


                		}

    		});
}