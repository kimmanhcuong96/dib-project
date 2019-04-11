/**
 * Result after searching information
 */
const lineOfPage = 20;

$(document).ready(function(){
	var currentPage = 1;
	length = results.length;
	pages = Math.floor(length/lineOfPage) + 1;
	$("#currentPage").val(currentPage + "/" + pages);
//	$("#totalPage").val(pages);
	
	/*
	 * render first page
	 */
	renderResult(0, results, currentPage);
	
	/*
	 * action when click next page
	 */
	$("#next-page").click(function(){
		if(currentPage == pages){
			// do nothing
		}
		
		if(currentPage < pages){
			currentPage++;
			$("#currentPage").val(currentPage + "/" + pages);
			$("#result-content").html("");
			renderResult(0, results, currentPage);
		}
	});
	
	$("#pre-page").click(function(){
		if(currentPage == 1){
			// do nothing
		}
		
		if(currentPage > 1){
			currentPage--;
			$("#currentPage").val(currentPage + "/" + pages);
			$("#result-content").html("");
			renderResult(0, results, currentPage);
		}
	});
	
	$("#currentPage").click(function(){
		$("#currentPage").val('');
	});
	
	$("#currentPage").on('keyup', function (e) {
	    if (e.keyCode == 13) {
	    	var pageTarget = parseInt($("#currentPage").val());
	        if(!	Number.isNaN(pageTarget) && pageTarget <= pages && pageTarget >= 1){
	        	currentPage = pageTarget;
	        	$("#currentPage").val(currentPage + "/" + pages);
	        	$("#result-content").html("");
				renderResult(0, results, currentPage);
	        } else {
	        	$("#currentPage").val(currentPage + "/" + pages);
	        	alert("Số trang nhập vào không hợp lệ");
	        }
	    }
	});
	
	
});


function viewDocument(code){
	   console.log(serverContextPath);
	   let url = serverContextPath + "downloadFile?document=" + code;
	   window.open(url);
}

function renderResult(i, results, currentPage){
	var startRender = (currentPage-1)*lineOfPage;
	var endRender = currentPage*lineOfPage;
	for(let index = startRender; index < endRender; index++ ){
		var item = results[index];
		if(item !== undefined){
			i ++;
			let stt = i + (currentPage-1)*lineOfPage;
			let name = item.hoTenDayDu;
			let alias = item.biDanh;
			let birthDay = item.ngayThangNamSinh;
			let hometown = item.queQuanDD;
			let office = item.coQuan;
			let yearGoB = item.namDiB;
			let idFile = item.hoSoSo;
			if(name == null || name == undefined){
				name = "";
			}
			if(alias == null || alias == undefined){
				alias = "";
			}
			if(birthDay == null || birthDay == undefined){
				birthDay = "";
			}
			if(hometown == null || hometown == undefined){
				hometown = "";
			}
			if(office == null || office == undefined){
				office = "";
			}
			if(yearGoB == null || yearGoB == undefined){
				yearGoB = "";
			}
			
			$("#result-content").append(
					'<tr id=result-'+ idFile + ' onClick="viewDocument('+ idFile + ')"> \
					<td>' + stt + '</td> \
					<td>' + name + '</td> \
					<td>' + alias + ' </td> \
					<td>' + birthDay + '</td> \
					<td>' + hometown + '</td> \
					<td>' + office + '</td> \
					<td>' + yearGoB + '</td> \
					</tr>'
			);
			if(i === lineOfPage) break;
		}
	}
}