

/**
 *  javascipt for video management page
 */

var allLinks = [];
var selectedIndex = -1;
$(document).ready(function(){
	
	initialSetup();
	/*
	 * select video to delete
	 */
	$("#delete-by-link").on("click", function(e){
		selectedIndex = $("#delete-by-link").val();
		console.log('---', selectedIndex);
		console.log('---src: ', $("#preview-video-delete").attr('src'));
		$("#preview-video-delete").html(
        		`
        			<iframe src='${allLinks[selectedIndex].videoLink}'></iframe>
        		`		
        );
		
		$("#body-modal").html(
				`
						<div>Link </div>
						<input id="update-link" type="text" }>
						<div>Description </div>
						<textarea rows="4" id="update-description" type="text"></textarea>
				`
					
		);
		$("#update-link").val(allLinks[selectedIndex].videoLink);
		$("#update-description").val(allLinks[selectedIndex].description);
	});
	
	
	/*
	 * edit video link by selection: save
	 */
	$("#save-modal").click(function(e){
		let updatedLink = $("#update-link").val();
		let updatedDescription = $("#update-description").val();
		let edittedId = allLinks[selectedIndex].id;
		let data = {
				"videoLink" : updatedLink,
				"description" : updatedDescription,
				"id": edittedId
		}
		$.ajax({
			type : "POST",
			contentType: "application/json",
			url : serverContextPath + "updateVideo",
			data : JSON.stringify(data),
			success : function (data){
				console.log(data);
				$('#myModal').modal('hide');
				initialSetup();
				
			},
			error : function(e) {
				console.log('There is error while save video ', e);
			}
		});
	});
	
	//save video
	
	$("#save-video").click(function(e){
		e.preventDefault();
		let videoLink = $("#video-link").val().trim();
		let description = $("#description").val().trim();
		if(videoLink !== ""){
			let data = {
					"videoLink" : videoLink,
					"description" : description
			}
			console.log("data send", data);
			$.ajax({
				type : "POST",
				contentType: "application/json",
				url : serverContextPath + "saveVideo",
				data : JSON.stringify(data),
				success : function (data){
					console.log(data);
					initialSetup();
					alert("Save success");
				},
				error : function(e) {
					console.log('There is error while save video ', e);
				}
			});
			$("#video-link").val('');
			$("#description").val('');
		}
	});
	

	/*
	 * edit video link by table: save
	 */
	$("#save-modal-table").click(function(e){
		let updatedLink = $("#update-link-table").val();
		let updatedDescription = $("#update-description-table").val();
		let edittedId = allLinks[selectedIndex].id;
		let data = {
				"videoLink" : updatedLink,
				"description" : updatedDescription,
				"id": edittedId
		}
		$.ajax({
			type : "POST",
			contentType: "application/json",
			url : serverContextPath + "updateVideo",
			data : JSON.stringify(data),
			success : function (data){
				console.log(data);
				$('#table-modal').modal('hide');
				initialSetup();
				
			},
			error : function(e) {
				console.log('There is error while save video ', e);
			}
		});
	});
	
	$("#delete-by-link-button").click(function(e){
		e.preventDefault();
		let videoLinkIndex = $("#delete-by-link").val().trim();
		let videoLink = allLinks[videoLinkIndex];
		console.log('----????', videoLink);
		if(videoLink !== null && videoLink !== undefined){
			$.ajax({
				type : "POST",
				contentType: "application/json",
				url : serverContextPath + "deleteByVideoId",
				data : JSON.stringify(videoLink),
				success : function (data){
					console.log(data);
					initialSetup();
					alert("Delete success");
				},
				error : function(e) {
					console.log('There is error while delete video by link video ', e);
				}
			});
			$("#delete-by-link").val('');
		}
	});
	
	
	$("#delete-all-video-link").click(function(e){
		e.preventDefault();
		$.ajax({
			type : "GET",
			contentType: "application/json",
			url : serverContextPath + "deleteAllVideoLink",
			success : function (data){
				initialSetup();
				alert("Delete success");
			},
			error : function(e) {
				console.log('There is error while delete all videos ', e);
			}
		});
		$("#delete-by-link").val('');
	});
	
});

/*
 * get video link and set data
 */

function initialSetup(){
	
	$.ajax({
        type : "GET",
        contentType: "application/json",
        url : serverContextPath + "getVideoList", 
        success: function(listVideo){
        	console.log('listVideo: ', listVideo);
        	if(listVideo instanceof Array && listVideo.length > 0){
        		$("#delete-by-link").html('');
        		$("#video-table-manage-body").html('');
        		allLinks = listVideo;
        		console.log('----+++', allLinks);
        		selectedIndex = 0;
        		$("#preview-video-delete").html(
        		`
        			<iframe src='${listVideo[selectedIndex].videoLink}'></iframe>
        		`		
        		);
        		$("#body-modal").html(
        				`
        						<div>Link </div>
        						<input id="update-link" type="text" }>
        						<div>Description </div>
        						<textarea rows="4" id="update-description" type="text"></textarea>
        				`
        		);
        		$("#update-link").val(allLinks[selectedIndex].videoLink);
        		$("#update-description").val(allLinks[selectedIndex].description);
        		listVideo.forEach((videoLink, index) => {
        			$("#delete-by-link").append(
        					`
        					<option value='${index}'>${videoLink.videoLink}</option>
        					`		
        			);
        			$("#video-table-manage-body").append(
        					`
        					<tr id='row-${index}'>
        						<td>${index + 1}</td>
        						<td>${videoLink.videoLink}</td>
        						<td>${videoLink.description}</td>
        						<td>
        							<button type="button" class="btn btn-primary custom-button" id="edit-${index}" onclick="editButtonhandleClick(event)">Edit</button>
								</td>
								<td>
									<button type="button" class="btn btn-danger custom-button" id="delete-${index}" onclick="deleteButtonhandleClick(event)">Delete</button>
								</td>
        					</tr>
        					`		
        			);
        		});
        	}else{
        		$("#delete-by-link").html('');
        		$("#video-table-manage-body").html('');
        		allLinks = listVideo;
        		console.log('----+++', allLinks);
        		$("#preview-video-delete").html(
        		`
        		`		
        		);
        		$("#body-modal").html(
        				`
        						<div>Link </div>
        						<input id="update-link" type="text" }>
        						<div>Description </div>
        						<textarea rows="4" id="update-description" type="text"></textarea>
        				`
        		);
        		$("#delete-by-link").html(
    					`
    					`		
    			);
        		$("#video-table-manage-body").html(
    					`
    					`		
    			);
        	}
        },
        error: function(e){
            console.log('there is error while get videos');
        }
    });
}


function editButtonhandleClick(e){
	console.log('edit', e.target.id);
	let rowId = e.target.id;
	let rowIndex = extractIdex(rowId);
	console.log('index:', rowIndex);
	$("#body-modal-table").html(
			`
					<div>Link </div>
					<input id="update-link-table" type="text" }>
					<div>Description </div>
					<textarea rows="4" id="update-description-table" type="text"></textarea>
			`
				
	);
	$("#update-link-table").val(allLinks[rowIndex].videoLink);
	$("#update-description-table").val(allLinks[rowIndex].description);
	$("#table-modal").modal({backdrop: 'static', keyboard: false});
}


function deleteButtonhandleClick(e){
	console.log('delete', e.target.id);
	let rowId = e.target.id;
	let rowIndex = extractIdex(rowId);
	console.log('index:', rowIndex);
	let linkToDelete = allLinks[rowIndex];
	if(linkToDelete !== null && linkToDelete !== undefined){
		$.ajax({
			type : "POST",
			contentType: "application/json",
			url : serverContextPath + "deleteByVideoId",
			data : JSON.stringify(linkToDelete),
			success : function (data){
				console.log(data);
				initialSetup();
				alert("Delete success");
			},
			error : function(e) {
				console.log('There is error while delete video by link video ', e);
			}
		});
		$("#delete-by-link").val('');
	}
}

function extractIdex(string){
	let index = string.indexOf('-');
	let result = string.slice(index+1);
	return result.trim();
}


