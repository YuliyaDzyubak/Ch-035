$(document).ready(function() {
	$('#saveArea').validate({
        rules: {
        	"name": {
                required: true,
                minlength: 2,
                maxlength: 100
            },
        },
    });
});
