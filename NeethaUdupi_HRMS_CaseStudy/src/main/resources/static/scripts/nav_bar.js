/**
 * 
 */
function open_nav() {
	document.getElementById("nav_drop").classList.toggle("show");

}

window.onclick = function(event) {
	if (!event.target.matches('.nav_drop_btn')) {
		var dropdowns = document.getElementsByClassName("nav_dropdown");
		var i;
		for (i = 0; i < dropdowns.length; i++) {
			var openDropdown = dropdowns[i];
			if (openDropdown.classList.contains('show')) {
				openDropdown.classList.remove('show');
			}
		}
	}
}