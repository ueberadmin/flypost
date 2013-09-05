function init() {
	updateSize();
	document.getElementById("description").focus();
}


function updateSize() {
	var all = updateMainSize();

	var newHeight = parseFloat(all.style.height);
	var newWidth = parseFloat(all.style.width);
	
	var descriptionField = document.getElementById('description');
	descriptionField.style.height = (newHeight * 0.7) + 'px';
	descriptionField.style.width = (newWidth * 0.7) + 'px';
	
	var descriptionContainer = document.getElementById('descriptionContainer');
	descriptionContainer.style.height = newHeight * 0.8;
	descriptionContainer.style.width = newWidth * 0.8;
	
	var leftBottom = document.getElementById('left_bottom');
	leftBottom.style.width = descriptionField.style.width;
	
	
}

function increaseFont() {
	var descriptionField = document.getElementById('description'); 
	descriptionField.style.fontSize = (getFontSize(descriptionField)+1)+"px";
}

function decreaseFont() {
	var descriptionField = document.getElementById('description');
	var oldFontSize = getFontSize(descriptionField);
	if (oldFontSize>4) {
		descriptionField.style.fontSize = (oldFontSize-1)+"px";
	}
}

function getFontSize(el) {
	var style = window.getComputedStyle(el, null).getPropertyValue('font-size');
	var fontSize = parseFloat(style);
	return fontSize;
}
