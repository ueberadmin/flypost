function init() {
	updateSize();
}

function updateSize() {
	var all = updateMainSize();
	
	var newHeight = parseFloat(all.style.height);
	var newWidth = parseFloat(all.style.width);
	
	
	var newInputHeight = (newHeight * 0.05) + 'px';
	var inputs = document.getElementsByTagName('input');
	for (index = 0; index < inputs.length; ++index) {
		inputs[index].style.height = newInputHeight;
	}
	
	
	var emailAddress = document.getElementById('emailAddress');
	emailAddress.style.width = (newWidth * 0.7) + 'px';
	
	var street = document.getElementById('street');
	street.style.width = (newWidth * 0.7) + 'px';
	
	var postcode = document.getElementById('postCode');
	postcode.style.width = (newWidth * 0.25) + 'px';
	postcode.style.marginRight = (newWidth * 0.05) + 'px';
	
	var city = document.getElementById('city');
	city.style.width = (newWidth * 0.4) + 'px';
	
	
	var radiusLabel = document.getElementById('radiusLabel');
	radiusLabel.style.width = (newWidth * 0.25) + 'px';
	radiusLabel.style.height = (newHeight * 0.05) + 'px';
	radiusLabel.style.marginRight = (newWidth * 0.05) + 'px';
	
	var radius = document.getElementById('radius');
	radius.style.width = (newWidth * 0.4) + 'px';
	
	var buttons = document.getElementById('buttons');
	buttons.style.width = (newWidth * 0.7) + 'px';
	
}