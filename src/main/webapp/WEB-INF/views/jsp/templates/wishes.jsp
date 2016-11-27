<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form class="mt-2 pb-2" action="${pageContext.request.contextPath}/wishes" method="post">
	<div class="input-group">
		<input type="text" id="description" name="description" class="form-control form-control-lg"
				placeholder="Enter a wish description..." aria-label="Wish description text input">
		<span class="input-group-btn">
			<input type="submit" class="btn btn-primary btn-lg" value="Add Wish">
		</span>
	</div>
</form:form>

<table id="wish-table" class="table table-hover">
	<thead>
		<tr>
			<th>Description</th>
			<th><span class="sr-only">Tags</span></th>
			<th><span class="sr-only">Actions</span></th>
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
			<td>
				<c:forEach var="tag" items="${wish.tags}">
					<a tabindex="0" title="Manage Tag" class="tag-manage"
							data-toggle="popover" data-wish-id="${wish.id}" data-tag="${tag}">
						<i class="fa fa-tag fa-fw"></i>
					</a>
				</c:forEach>
			</td>
			<td>
				<a tabindex="0" title="Helpful Link" class="pull-left tag tag-pill tag-default wish-tag" data-toggle="popover" data-wish-id="${wish.id}">
					<i class="fa fa-ellipsis-h"></i>
				</a>
				<span class="pull-right">
					<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal" data-wish-id="${wish.id}">Delete</button>
				</span>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<div id="tag-add-content" class="hidden-xs-up">
	<form:form role="form" action="${pageContext.request.contextPath}/wishes/tag/add" method="post">
		<input type="hidden" name="wishId" />
		<div class="form-group">
			<input type="text" name="url" class="form-control" placeholder="Enter a URL...">
		</div>
		<button type="submit" class="btn btn-primary">Add Tag</button>
	</form:form>
</div>

<div id="tag-manage-content" class="hidden-xs-up">
	<form:form role="form" action="${pageContext.request.contextPath}/wishes/tag/remove" method="post">
		<input type="hidden" name="wishId" />
		<input type="hidden" name="url" />
		<a href="#" class="btn btn-primary" target="_blank">Open Link</a>
		<button type="submit" class="btn btn-danger">Remove Tag</button>
	</form:form>
</div>

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
