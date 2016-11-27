<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="accordion" role="tablist" aria-multiselectable="true">
	<div class="card">
		<div class="card-header" role="tab" id="headingOne">
			<h5 class="mb-0">
				<a data-toggle="collapse" data-parent="#accordion" href="#eventAdmin" aria-expanded="true" aria-controls="eventAdmin">
					Event Administration
				</a>
			</h5>
		</div>

		<div id="eventAdmin" class="collapse in" role="tabpanel" aria-labelledby="eventAdmin">
			<div class="card-block">
				<p class="card-text">Since you are the one responsible for this event, we give you
					a few more options to ensure everything goes as smoothly as possible.</p>
				<a href="#" class="btn btn-primary">Link</a>
			</div>
		</div>
	</div>
</div>