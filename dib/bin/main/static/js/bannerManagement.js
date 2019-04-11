

/**
 *  javascipt for banner management page
 */

$(document).ready(function(){
	$("#save-top-banner-link").click(function(e){
		e.preventDefault();
		let adLink = $("#ad-link-top").val().trim();
		if(adLink !== ""){
			let data = {
					"bannerLink" : adLink,
					"position" : "top"
			}
			$.ajax({
				type : "POST",
				contentType: "application/json",
				url : serverContextPath + "saveBannerAds",
				data : JSON.stringify(data),
				success : function (data){
					alert("Save success");
				},
				error : function(e) {
					console.log('There is error while save banner ads ', e);
				}
			});
			$("#ad-link-top").val('');
		}
	});
	
	
	$("#save-bottom-banner-link").click(function(e){
		e.preventDefault();
		let adLink = $("#ad-link-bottom").val().trim();
		if(adLink !== ""){
			let data = {
					"bannerLink" : adLink,
					"position" : "bottom"
			}
			$.ajax({
				type : "POST",
				contentType: "application/json",
				url : serverContextPath + "saveBannerAds",
				data : JSON.stringify(data),
				success : function (data){
					alert("Save success");
				},
				error : function(e) {
					console.log('There is error while save banner ads ', e);
				}
			});
			$("#ad-link-bottom").val('');
		}
	});
	
});