<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form class="mt-2 pb-2" action="${pageContext.request.contextPath}/wishes" method="post">
	<div class="input-group">
		<input type="text" id="description" name="description" class="form-control form-control-lg"
				placeholder="Enter a wish description..." aria-label="Wish description text input">
		<span class="input-group-btn">
			<button class="btn btn-primary btn-lg" type="button">Add Wish</button>
		</span>
	</div>
</form:form>

<table id="wish-table" class="table table-hover">
	<thead>
		<tr>
			<th>Description</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="wish" items="${wishes}">
		<tr>
			<td class="wish-col">
				<span class="desc-plain">${wish.description}</span>
				<span class="desc-form" style="display: none;">
					<input type="text" value="" class="form-control" data-wish-id="${wish.id}" data-target-url="${pageContext.request.contextPath}/wishes/update" />
				</span>
			</td>
			<td class="text-xs-right">
				<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal" data-wish-id="${wish.id}">Delete</button>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<div id="confirmDeleteModal" class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header text-xs-center">
          		<h4 class="modal-title">Delete this wish?</h4>
        	</div>
        	<div class="modal-body">
	          	<form:form action="${pageContext.request.contextPath}/wishes/delete" method="post">
	          		<input type="hidden" id="confirmDeleteID" name="id" value="" />
		            <button type="button" class="btn btn-secondary btn-block" data-dismiss="modal">No! Go back!</button>
	          		<button type="submit" class="btn btn-danger btn-block">Delete it!</button>
	          	</form:form>
	        </div>
		</div>
	</div>
</div>
