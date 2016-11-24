$(function() {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
	
	$('.wish-col').click(function() {
		var description = $(this).find('.desc-plain').html();
		$(this).find('.desc-plain').hide();
		$(this).find('.desc-form input').val(description);
		$(this).find('.desc-form').show();
		$(this).find('.desc-form input').focus();
	});
	
	$('.desc-form input').blur(function() {
		var wishId = $(this).data('wish-id');
		var description = $(this).val();
		var targetUrl = $(this).data('target-url');
		
		$.post(targetUrl, {
			'id': wishId,
			'description': description
		});
		
		var e = $(this).parents('.wish-col');
		$(e).find('.desc-plain').html(description);
		$(e).find('.desc-form').hide();
		$(e).find('.desc-plain').show();
	});
	
	$('#confirmDeleteModal').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget); // Button that triggered the modal
		var wishId = button.data('wish-id'); // Extract info from data-* attributes
		var modal = $(this);
		modal.find('#confirmDeleteID').val(wishId);
	});
	
});
