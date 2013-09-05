function updateMainSize() {
	var windowWidth = window.innerWidth;
	var windowHeight = window.innerHeight;
	
	var newWidth = windowWidth * 0.99;
	var newHeight = windowHeight * 0.99;
	if (windowWidth>windowHeight) {
		newWidth = windowHeight / 1.414285714;
	} else {
		newHeight = windowWidth * 1.414285714;
	}
	
	
	var all = document.getElementById('zettl');
	all.style.height = newHeight;
	all.style.width = newWidth;
	

	var footer = document.getElementById('footer');
	footer.style.marginTop = (newHeight * 0.95) + 'px';
	footer.style.width = newWidth + 'px';
	footer.style.height = (newHeight * 0.2) + 'px';
	
	return all;
}

function print(id, context) {
	var url = context+'/anschauen/'+id+'/abreisszettel';
	
	var mywindow = window.open(url, 'Druckansicht', 'height=594,width=420');;

	//mywindow.print();
}