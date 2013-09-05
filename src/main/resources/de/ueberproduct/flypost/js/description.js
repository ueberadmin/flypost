function init() {
	updateSize();
	document.getElementById("description").focus();
}


function updateSize() {
	var all = updateMainSize();

	var newHeight = parseFloat(all.style.height);
	var newWidth = parseFloat(all.style.width);
	
	var descriptionField = document.getElementById('description');
	descriptionField.style.height = (newHeight * 0.6) + 'px';
	descriptionField.style.width = (newWidth * 0.6) + 'px';
	descriptionField.style.fontSize = (ratio*100)+'%';
	
	var descriptionContainer = document.getElementById('descriptionContainer');
	descriptionContainer.style.height = newHeight * 0.7+ 'px';
	descriptionContainer.style.width = newWidth * 0.7 + 'px';
	
	var leftBottom = document.getElementById('left_bottom');
	leftBottom.style.width = descriptionField.style.width;
	
	var preferedWidth = 420;
	var ratio = newWidth / 420;
	document.getElementById('headline').style.fontSize = (2*100*ratio)+'%';
	descriptionField.style.fontSize = (100*ratio)+'%';
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

function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object

    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {

      // Only process image files.
      if (!f.type.match('image.*')) {
        continue;
      }

      var reader = new FileReader();

      // Closure to capture the file information.
      reader.onload = (function(theFile) {
        return function(e) {
          // Render thumbnail.
          var span = document.getElementById('imageArea');
          span.innerHTML = ['<img class="thumb" src="', e.target.result, '" title="', escape(theFile.name), '" height="80%" />'].join('');
        };
      })(f);

      // Read in the image file as a data URL.
      reader.readAsDataURL(f);
    }
  }

 
