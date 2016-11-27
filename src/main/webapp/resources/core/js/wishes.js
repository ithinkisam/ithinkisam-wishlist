$(function() {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
	
	$('.wish-col').click(function(e) {
		if (!$(this).find('.desc-form input').is(':focus')) {
			var description = $(this).find('.desc-plain').html();
			$(this).find('.desc-plain').hide();
			$(this).find('.desc-form input').val(description);
			$(this).find('.desc-form').show();
			$(this).find('.desc-form input:not(:focus)').focus();
		}
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
	
	$('a.wish-tag').popover({
		trigger: 'click',
		html: true,
		placement: 'top',
		content: function() {
			$('#tag-add-content input[name="wishId"]').val($(this).data('wish-id'));
			return $('#tag-add-content').html();
		}
	}).on('shown.bs.popover', function() {
		var popoverTrigger = $(this);
		var popoverInput = $('#' + $(this).attr('aria-describedby')).find('input');
		popoverInput.focus();
		popoverInput.blur(function() {
			popoverTrigger.popover('hide');
		})
	});
	
	$('a.tag-manage').popover({
		trigger: 'focus',
		html: true,
		placement: 'top',
		content: function() {
			console.log("THIS");
			$('#tag-manage-content input[name="wishId"]').val($(this).data('wish-id'));
			$('#tag-manage-content input[name="url"]').val($(this).data('tag'));
			$('#tag-manage-content a').attr("href", $(this).data('tag'));
			return $('#tag-manage-content').html();
		}
	});
	
});
