function init() {
	document.getElementById("description").focus();
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
    if (files.length>0) {
      var f = files[0];
      // Only process image files.
      if (f.type.match('image.*')) {

	      var reader = new FileReader();
	      
	      // Closure to capture the file information.
	      reader.onload = (function(theFile) {
	        return function(e) {
	          // Render thumbnail.
	        	var span = document.getElementById('imageArea');
	        	span.innerHTML = ['<img class="thumb" src="', e.target.result, '" title="', escape(theFile.name), '" />'].join('');
	        };
	      })(f);
	
	      // Read in the image file as a data URL.
	      reader.readAsDataURL(f);
	      
	   }
    }
      
  }

 
