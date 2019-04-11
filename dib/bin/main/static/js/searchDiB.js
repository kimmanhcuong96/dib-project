/**
 * cuongkm dib project
 */


$(document).ready(function(){

	/*
	 * Get list city from server
	 */
	$.ajax({
        type : "GET",
        contentType: "application/json",
        url : serverContextPath + "getCity", 
        success: function(data){
        	let length = data.length;
        	for(let i = 0; i < length; i ++ ){
        		if(data[i] != "")
        		$("#inputCity").append('<option value="' + data[i] + '">' + data[i] + '</option>')
        	}
        },
        error: function(e){
            console.log('there is error while getting list city');
        }
    });
	
	
	
	$("#clear-button").hover(function(){
        $(this).css("cursor", "pointer");
    });
	
	$("#search-button").hover(function(){
        $(this).css("cursor", "pointer");
    });
	
	/*
	 * re-render list districts when selecting city
	 */
	
	$("#inputCity").click(function(){
		$("#inputDistrict").html('<option value="none">-Quận/Huyện-</option>');
		
		$.ajax({
	        type : "GET",
	        contentType: "application/json",
	        url : serverContextPath + "getSpecifiedDistricts?city=" + $(this).val(), 
	        success: function(data){
	        	listDistricts = data;
	        	let length = data.length;
	        	for(let i = 0; i < length; i ++ ){
	        		if(data[i] != "")
	        			$("#inputDistrict").append('<option value="' + data[i].districtName + '">' + data[i].districtName + '</option>')
	        	}
	        },
	        error: function(e){
	            console.log('there is error while synchronizing database with document resource');
	        }
	    });
	});
	
	/*
	 * catch submit event
	 */
	$( "#submitSearch" ).submit(function( event ) {
			var name = $("#inputName").val().trim();
			var birthDayStart = $("#inputStartBirthDay").val().trim();
			if(birthDayStart !== ""){
				birthDayStart = parseInt(birthDayStart);
			}
			var birthDayEnd = $("#inputEndBirthDay").val().trim();
			if(birthDayEnd !== ""){
				birthDayEnd = parseInt(birthDayEnd);
			}
			var aliasName = $("#inputAlias").val().trim();
			var district = $("#inputDistrict").val().trim();
			var city = $("#inputCity").val();
			var ward = $("#inputWard").val();
			var goBStart = $("#StartGOB").val().trim();
			if(goBStart !== ""){
				goBStart = parseInt(goBStart);
			}
			var goBEnd = $("#endGOB").val().trim();
			if(goBEnd !== ""){
				goBEnd = parseInt(goBEnd);
			}
			var village = 	$("#inputVillage").val().trim();
			var form = $( "#submitSearch" );
			var  checkIsNotNumber = false;
			if((birthDayStart != "" && isNaN(birthDayStart)) || (!isNaN(birthDayStart) && birthDayStart < 0)){
				checkIsNotNumber = true;
			}
			if((birthDayEnd != "" && isNaN(birthDayEnd)) || (!isNaN(birthDayEnd) && birthDayEnd < 0)){
				checkIsNotNumber = true;
			}
			if((goBStart != "" && isNaN(goBStart)) || (!isNaN(goBStart) && goBStart < 0)){
				checkIsNotNumber = true;
			}
			if((goBEnd != "" && isNaN(goBEnd)) || (!isNaN(goBEnd) && goBEnd < 0)){
				checkIsNotNumber = true;
			}
			if((name == "" && birthDayStart == "" && birthDayEnd == "" && aliasName == "" && district == "none" && city == "none" && ward == "" && goBStart == "" && goBEnd == "" && village == "")
					|| checkIsNotNumber ){
				if(checkIsNotNumber){
					alert("Ngày tháng năm không đúng định dạng");
				} 
				event.preventDefault();
			} else{
				$('<input>').attr({ type: 'hidden',  name: 'name', value: name }).appendTo(form);
				$('<input>').attr({ type: 'hidden',  name: 'birthDayStart', value: birthDayStart }).appendTo(form);
				$('<input>').attr({ type: 'hidden',  name: 'birthDayEnd', value: birthDayEnd }).appendTo(form);
				$('<input>').attr({ type: 'hidden',  name: 'aliasName', value: aliasName }).appendTo(form);
				$('<input>').attr({ type: 'hidden',  name: 'district', value: district }).appendTo(form);
				$('<input>').attr({ type: 'hidden',  name: 'city', value: city }).appendTo(form);
				$('<input>').attr({ type: 'hidden',  name: 'ward', value: ward }).appendTo(form);
				$('<input>').attr({ type: 'hidden',  name: 'goBStart', value: goBStart }).appendTo(form);
				$('<input>').attr({ type: 'hidden',  name: 'goBEnd', value: goBEnd }).appendTo(form);
				$('<input>').attr({ type: 'hidden',  name: 'village', value: village }).appendTo(form);
			}
		});
	
	
		/*
		 * get video link 
		 */
		$.ajax({
	        type : "GET",
	        contentType: "application/json",
	        url : serverContextPath + "getVideoList", 
	        success: function(listVideo){
	        	if(listVideo.length > 0){
	        		$("#video-container").append(
	        				`<div class="video-set" id="video-set-first"></div>`
	        		);
	        	}
	        	if(listVideo.length > 5){
	        		$("#video-container").append(
	        				`<div class="video-set" id="video-set-second"></div>`
	        		);
	        	}
	        	listVideo.forEach((item, index) => {
	        		if(index < 5){
	        			$("#video-set-first").append(`
	        					<div class="video-cell" id="video-${index}">
	        					<div class="video-show">
	        					<iframe width="240" height="135"src=${item.videoLink}></iframe>
	        					</div>
	        					<div class="video-description">${item.description}</div>		
	        					</div>
	        			`);
	        		} else if(index < 10){
	        			$("#video-set-second").append(`
	        					<div class="video-cell" id="video-${index}">
	        					<div class="video-show">
	        					<iframe width="240" height="135"src=${item.videoLink}></iframe>
	        					</div>
	        					<div class="video-description">${item.description}</div>		
	        					</div>
	        			`);
	        		}
	        	});
	        },
	        error: function(e){
	            console.log('there is error while get videos');
	        }
	    });
});

function clearData(){
	$("#inputName").val("");
	$("#inputStartBirthDay").val("");
	$("#inputEndBirthDay").val("");
	$("#inputAlias").val("");
	$("#inputDistrict").val("none");
	$("#inputVillage").val("");
	$("#inputCity").val("none");
	$("#inputWard").val("");
	$("#StartGOB").val("");
	$("#endGOB").val("");
}

function searchInformation(){
	var name = $("#inputName").val();
	var birthDayStart = $("#inputStartBirthDay").val();
	var birthDayEnd = 	$("#inputEndBirthDay").val();
	var aliasName = $("#inputAlias").val();
	var district = $("#inputDistrict").val();
	var city = $("#inputCity").val();
	var ward = $("#inputWard").val();
	var goBStart = $("#StartGOB").val();
	var goBEnd = $("#endGOB").val();
	var village = 	$("#inputVillage").val();
	var data = {
			"name": name,
			"birthDayStart" : birthDayStart,
			"birthDayEnd" : birthDayEnd,
			"aliasName": aliasName,
			"district" : district,
			"city" : city,
			"ward" : ward,
			"goBStart" : goBStart,
			"goBEnd" : goBEnd
		};
}

