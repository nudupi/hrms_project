/**
 * script for tabs
 */
window.onload = function() {



	startTab();
};

function startTab() {
//	var activeTab = localStorage.getItem('activeTab');
//	var page = "<%=page%>";
	var activeTab = sessionStorage.getItem('activeTab1');
    
 	
	if (activeTab === null) {
		
		document.getElementById("defaultOpen").click();

	}
	else {
		displayContent(event, activeTab);

	}

}

function displayContent(event, contentNameID) {
	var page = "<%=page%>";
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("contentClass");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablink");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	document.getElementById(contentNameID).style.display = "flex";
	event.currentTarget.className += " active";
//	localStorage.setItem('activeTab', contentNameID);
//	if (page.equals("employee_self"))
    sessionStorage.setItem('activeTab1',contentNameID);

}




