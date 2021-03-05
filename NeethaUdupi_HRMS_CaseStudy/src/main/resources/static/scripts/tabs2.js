/**
 * script for tabs
 */
window.onload = function() {



	startTab();
};

function startTab() {
//	var activeTab = localStorage.getItem('activeTab');
	 
	var activeTab = sessionStorage.getItem('activeTab2');
    
 
   
	if (activeTab === null) {
		document.getElementById("defaultOpen").click();

	}
	else {
		displayContent(event, activeTab);

	}

}

function displayContent(event, contentNameID) {

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
    sessionStorage.setItem('activeTab2',contentNameID);

}


