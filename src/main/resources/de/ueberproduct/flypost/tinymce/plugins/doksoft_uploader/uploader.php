<?php
require_once('config.php');




function sendResponse($uploadResult, $baseUrl, $doResize, $doThumb, $relAttr, $downloadImgUrl) {
	if ($uploadResult[0] === false)
		$data = array('error' => $uploadResult[1]);
	else {
		
		$action = 'file';
		if ($_GET['type'] == 'Images')
		$action = $doThumb ? 'preview' : 'image';
		
		$html = '';
		foreach ($uploadResult[1] as $fileName) {
			$url = $baseUrl .  $fileName;
			if ($_GET['type'] == 'Images') {
				if ($doThumb) {
					// Thumb
					$urlThumb = $baseUrl . getThumbFileName($fileName);
					$html .= '<a class="doksoft_preview_href" href="'.$url.'" rel="'.$relAttr.'">'.
					         	'<img border="0" class="doksoft_preview_img" src="'.$urlThumb.'">'.
					         '</a>';
				} else if ($doResize) {
					// Resized image
					$html .= '<img class="doksoft_resize_img" src="'.$url.'"/>';
				} else {
					// Image
					$html .= '<img class="doksoft_image_img" src="'.$url.'"/>';
				}
			} else {
				// File
				$html .= 
					'<div class="doksoft_file">'.
						'<a class="doksoft_file_href doksoft_file_href_img" href="'.$url.'">'.
						   	'<img border="0" class="doksoft_file_img" src="'.$downloadImgUrl.'">'.
					    '</a><br>'. // Do not use <br/>, TinyMCE cuts it
						'<a class="doksoft_file_href doksoft_file_href_text" href="'.$url.'">'.
						   	'Download file'.
						'</a>'.
					 '</div>';
			}
		}
		$data = array('html' => $html);
	}
	
	header('Content-type: text/plain'); // Stupid IE does not process any of JSON formats correctly and tries to save file
	echo json_encode($data);
}


function getThumbFileName($fileName) {
	$a = explode('.', $fileName);
	$a[count($a) - 2] .= '_small';
	$fileNameThumb = implode('.', $a);
	return $fileNameThumb;
}

function uploadFile(
	$name, 
	$tmp_name, 
	$error, 
	$size, 
	$toDir, 
	$allowedExtensions,
	$maxSize,
	$doResize,
	$resizeMaxWidth,
	$resizeMaxHeight,
	$resizeQuality, 
	$resizeOnLess,
	$doThumb,
	$thumbMaxWidth,
	$thumbMaxHeight,
	$thumbQuality
) {
	
	if ($error != 0) {
		$message = "There was an upload error for file  `'.$name.'`, code #".$error.". Check your server's configuration";
		switch ($error) {
			case UPLOAD_ERR_INI_SIZE:   $message = "The uploaded file `'.$name.'` exceeds the upload_max_filesize directive in php.ini"; break;
			case UPLOAD_ERR_FORM_SIZE:  $message = "The uploaded file `'.$name.'` exceeds the MAX_FILE_SIZE directive that was specified in the HTML form"; break;
			case UPLOAD_ERR_PARTIAL:    $message = "The uploaded file  `'.$name.'` was only partially uploaded"; break;
			case UPLOAD_ERR_NO_FILE:    $message = "No file was uploaded"; break;
			case UPLOAD_ERR_NO_TMP_DIR: $message = "Missing a temporary folder on your server"; break;
			case UPLOAD_ERR_CANT_WRITE: $message = "Failed to write file to disk on your server"; break;
			case UPLOAD_ERR_EXTENSION:  $message = "File upload stopped by extension"; break;
		}
		return array(false, $message);
	}
	
	if ($size == 0)
		return array(false, 'File `'.$name.'` size = 0');
	
	if (!($maxSize <= 0 || $size <= $maxSize))
		return array(false, 'Size of file `'.$name.'` exceeds the limit of '.$maxSize.' bytes');
	
	$a = explode('.', $name);
	$type = $a[count($a)-1];
	error_log($type);
	if (!in_array(strtolower($type), array_map('strtolower', $allowedExtensions)))
		return array(false, 'Wrong extension for file `'.$name.'`. Allowed extensions are: ' . implode(', ', $allowedExtensions));
	
	$fileName;
	$fileNameThumb;
	
	// Search for file name
	$ok = false;
	$i = -1;
	do {
		$i ++;
		if ($i == 0)
			$fileName = $name;
		else
			$fileName = $i . '_' . $name;
		$ok = !file_exists($toDir . $fileName);
		if ($doThumb) {
			$fileNameThumb = getThumbFileName($fileName);
			$ok = $ok && !file_exists($toDir . $fileNameThumb);
		}		
	} while (!$ok);
	
	$filePath = $toDir . $fileName;
	$moveResult = move_uploaded_file($tmp_name, $filePath);
	if ($moveResult === false)
		return array(false, 'Error while moving uploaded file to destination folder: check folder permissions on server side');
	
	if ($doResize) {
		$err = resizeImg(
			$filePath, 
			$resizeMaxWidth,
			$resizeMaxHeight, 
			$resizeQuality,
			true, 
			$resizeOnLess
		);
		if ($err != null)
			return array(false, 'Error while resizing image `'.$name.'`: '.$err);
	}
	
	if ($doThumb) {
		$err = resizeImg(
			$filePath, 
			$thumbMaxWidth,
			$thumbMaxHeight, 
			$thumbQuality,
			false, 
			true
		);
		if ($err != null)
			return array(false, 'Error while making thumbnail of image `'.$name.'`: '.$err);
	}
	
	return array(true, $fileName);
}


// If any error returns array(false, string)
// If all ok, returnes array(true, array(file1, file2, ...)) 
function upload($doResize, $doThumb) {
	global $config;
	if (!empty($_GET) && isset($_GET['type']) && array_key_exists($_GET['type'],$config['ResourceType']))
		$rType=$config['ResourceType'][$_GET['type']];
	else 
		return array(false, 'Wrong request. Incorrect or undefined `type` parameter');
	
	if (!isset($_FILES['upload']))
		return array(false, 'No files to process');
		
	$data = $_FILES['upload'];
	$files = array();
	if (is_array($data['name'])) {
		for ($i = 0; $i < count($data['name']); $i++)
			$files[] = array(
				'name' => $data['name'][$i],
				'tmp_name' => $data['tmp_name'][$i],
				'error' => $data['error'][$i],
				'size' => $data['size'][$i]
			);
	} else {
		$files[] = $data;
	}
	
	$resultFiles = array();
	foreach ($files as $file) {
		$fileResult = uploadFile(
			$file['name'], 
			$file['tmp_name'], 
			$file['error'], 
			$file['size'],
			$config['BaseDir'],
			explode(',',$rType['allowedExtensions']),
			$rType['maxSize'],
			$doResize,
			$config['ResizedImages']['maxWidth'],
			$config['ResizedImages']['maxHeight'],
			$config['ResizedImages']['quality'],
			isset($_GET['resizeOnLess']),
			$doThumb,
			$config['Thumbnails']['maxWidth'],
			$config['Thumbnails']['maxHeight'],
			$config['Thumbnails']['quality']
		);
		if ($fileResult[0] !== true)
			return $fileResult; // error
		else
			$resultFiles[] = $fileResult[1];
	}
	
	return array(true, $resultFiles);
}


function resizeImg($sourceFile, $width = 200, $height = 140, $quality = 90, $resizeself=false, $resizeOnLess=true) {
	$sourceImageAttr = @getimagesize($sourceFile);
	if ($sourceImageAttr === false)
		return "unable to get image size";

	switch ($sourceImageAttr['mime']) {
		case 'image/gif': {
				if (@imagetypes() & IMG_GIF)
					$oImage = @imagecreatefromgif($sourceFile);
				else
					$ermsg = 'GIF images are not supported';
			}
			break;
		case 'image/jpeg': {
				if (@imagetypes() & IMG_JPG)
					$oImage = @imagecreatefromjpeg($sourceFile) ;
				else
					$ermsg = 'JPEG images are not supported';
			}
			break;
		case 'image/png': {
				if (@imagetypes() & IMG_PNG)
					$oImage = @imagecreatefrompng($sourceFile) ;
				else
					$ermsg = 'PNG images are not supported';
			}
			break;
		case 'image/wbmp': {
				if (@imagetypes() & IMG_WBMP)
					$oImage = @imagecreatefromwbmp($sourceFile);
				else
					$ermsg = 'WBMP images are not supported';
			}
			break;
		default:
			$ermsg = $sourceImageAttr['mime'].' images are not supported';
		break;
	}

	if (isset($ermsg) || false === $oImage)
		return $ermsg;


	$xscale=imagesx($oImage)/$width;
	$yscale=imagesy($oImage)/$height;

	if (!$resizeOnLess && $xscale<1 &&  $yscale<1) return null;

	if ($yscale>$xscale){
		$new_width = round(imagesx($oImage) * (1/$yscale));
		$new_height = round(imagesy($oImage) * (1/$yscale));
	}
	else {
		$new_width = round(imagesx($oImage) * (1/$xscale));
		$new_height = round(imagesy($oImage) * (1/$xscale));
	}
	$new_image = imagecreatetruecolor($new_width, $new_height);
	imagecopyresampled($new_image, $oImage, 0, 0, 0, 0, $new_width, $new_height, imagesx($oImage), imagesy($oImage));
	$oImage = $new_image;

	if (!$resizeself) {
		$sourceFileArr=explode('.',$sourceFile);
		$sourceFileArr[count($sourceFileArr)-2].='_small';
		$sourceFile=implode('.',$sourceFileArr);
	} else {
		unlink( $sourceFile);
	}

	switch ($sourceImageAttr['mime']) {
		case 'image/gif':
			imagegif($oImage, $sourceFile);
			break;
		case 'image/jpeg':
			imagejpeg($oImage, $sourceFile, $quality);
			break;
		case 'image/png':
			imagepng($oImage, $sourceFile);
			break;
		case 'image/wbmp':
			imagewbmp($oImage, $sourceFile);
			break;
	}

	@imageDestroy($oImage);
	@imageDestroy($new_image);
	return null;
}

function run() {
	global $config;
	if (!isset($config)) {
		$result = array(false, 'Uploader\'s config not found. Check globals are on your server');
	} else {
		$doResize = $_GET['type']=='Images' && isset($_GET['resize']);
		$doThumb = $_GET['type']=='Images' && isset($_GET['makeThumb']);
		$result = upload($doResize, $doThumb);
	}
	
	sendResponse(
		$result,
		$config['BaseUrl']
		,
		$doResize,
		$doThumb,
		$config['RelAttr'],
		$config['DownloadImageUrl']
		
	);	
}

run();

?>