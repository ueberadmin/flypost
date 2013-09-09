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
	
	var preferedWidth = 420;
	var ratio = newWidth / 420;
	
	document.getElementById('header_headline').style.fontSize = (ratio * 220)+'%';
	document.getElementById('header_text').style.fontSize = (ratio*100)+'%';
	

	var footer = document.getElementById('footer');
	footer.style.marginTop = (newHeight * 0.95) + 'px';
	footer.style.width = newWidth + 'px';
	footer.style.height = (newHeight * 0.2) + 'px';
	
	return all;
}

function print(id, context) {
	var url = context+'/anschauen/'+id+'/abreisszettel';
	// 2480 X 3508
	var mywindow = window.open(url, 'Druckansicht', 'height=600,width=424');;

	mywindow.print();
}