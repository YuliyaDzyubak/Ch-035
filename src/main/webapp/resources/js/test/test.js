$(document).ready(function (e){
	
	//Delete test modal window.
	$("#btn-modal-delete-test").click(function(e){
		var deleteUrl = $(this).attr("data-deleteurl");
		var removeElementId = deleteUrl.split("/")[0];
		$.ajax({
			url : document.URL + deleteUrl,
			dataType: 'application/json',
			type : "GET",
			
			complete: function(e) {
				$("#hover-" + removeElementId).remove();
			}, error: function(e) {
				alert('Error: ' + thrownError + "\n" + xhr);
			}
		});
		
		//Hide modal window.
		$("#delete-confirmation-modal").modal('hide');

	});
	
	$(".btn-delete-test").click(function(e) {
		var deleteUrl = $(this).attr("data-deleteurl");
		
		//Hide tooltip.
		$(e.delegateTarget).tooltip('hide');
		
		//Add delete rest.
		$("#btn-modal-delete-test").attr("data-deleteurl", deleteUrl);
		
		//Append delete msg to modal.	
		var testName = $(this).closest("tr").find(".test-name").text();
		$("#delete-confirmation-modal").find(".test-delete-msg").text("\"" + testName.trim() + "\"" + "?");
		
		$("#delete-confirmation-modal").modal('show');
	});
	//End delete test modal window.
	
	//Add questions with answers modal window.
	function doAjaxPost() {
		var form = $('#modal-form');
		var testId = $('#modal-form-submit').val();

		$.ajax({
			url : document.URL + testId +'/questions/add/question-form',
			dataType: 'json',
			data : form.serialize(),
			type : "POST",
			
			complete: function() {
				//Hide add question modal after form submitting.
				$("#my-modal").modal('hide');
				
				//Clear text area after add question form closing.
				$(".clear-textarea").val("");
					
			} ,
			success : function(question) {
				var questionHtml =
				'<div id="info">' +
					'<ul class="list-group" >' +
						'<li class="list-group-item list-group-item-warning">' +
						'<input type="hidden" value="' + question.id + '">' +
							'<a href="" class="list-group-item-warning">' +
								question.text +
								'&nbsp' +
								'<a href="#" class="nonUnderlineDelete pull-right"><i class="glyphicon glyphicon-trash"></i></a>' +
								'<a href="#" class="nonUnderlineEdit pull-right"><i class="glyphicon glyphicon-pencil"></i>&nbsp</a>' +								
							'</a>' +
						'</li>' +
					'</ul>' +
				'</div>';
				
				$("#questions-" + testId).append(questionHtml);
		
			},

			error : function(xhr, ajaxOptions, thrownError) {
				alert('Error: ' + thrownError + "\n" + xhr);
			}
		});
	}
	
	//End add questions with answers modal window.
	
	//Only one Test div can be shown in the moment.
	$('div.full-div').click(function(e){
		$('.collapse-off').collapse('hide');
	});
	
	//"Close" button. Hide collapse area.
	$('.close-div-button').click(function(e){
		$('.collapse-off').collapse('hide');
	});
	
	//Get Test id from inner JSP for-each loop.
	$(".question-add").click(function (e){
		 $('#modal-form-submit').val($(this).attr("value"));
	});
	
	//Send ajax request when form submitted.
	$("#modal-form-submit").click(function (e){
		doAjaxPost();
	});
	
})