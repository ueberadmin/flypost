tinymce.PluginManager.add("doksoft_image",function(c,d){var b=c.settings;function a(g,f,e,h){var f="Insert "+f;e.addCommand("doksoft_"+g,function(){e.windowManager.open({title:f,width:parseInt(e.getParam("plugin_preview_width","450"),10),height:parseInt(e.getParam("plugin_preview_height","150"),10),html:'<iframe src="javascript:\'\'" frameborder="0"></iframe>',buttons:[{text:"Insert",subtype:"primary",onclick:function(){if(doksoft_imageuploadedHtml!=""){e.execCommand("mceInsertContent",false,doksoft_imageuploadedHtml);this.parent().parent().close();}else{alert("Upload files first!");}}},{text:"Close",onclick:function(){this.parent().parent().close();}}],onPostRender:function(){var m=this.getEl("body").firstChild.contentWindow.document,i,k="";tinymce.each(e.contentCSS,function(n){k+='<link type="text/css" rel="stylesheet" href="'+e.documentBaseURI.toAbsolute(n)+'">';});var l=b.body_id||"tinymce";if(l.indexOf("=")!=-1){l=e.getParam("body_id","","hash");l=l[e.id]||l;}var j=b.body_class||"";if(j.indexOf("=")!=-1){j=e.getParam("body_class","","hash");j=j[e.id]||"";}i=doksoft_imagegetHtmlContent(k,l,j,h,g);m.open();m.write(i);m.close();},onsubmit:function(){}});});e.addButton("doksoft_"+g,{title:f,image:h+"/doksoft_"+g+".png",cmd:"doksoft_"+g});e.addMenuItem("doksoft_"+g,{text:f,cmd:"doksoft_"+g,context:"insert"});}a("image","image",c,d);a("preview","image with preview",c,d);a("resize","resized image",c,d);});doksoft_imageuploadedHtml="";function doksoft_imagegetHtmlContent(c,d,a,b,e){return"<!DOCTYPE html>"+"<html>"+"<head>"+c+"</head>"+'<body id="'+d+'" class="mce-content-body '+a+'">'+'<table border="0" cellspacing="0" cellpadding="0" width="100%" style="min-height:100%">'+"<tr>"+'<td style="text-align:center; height:20px; min-height:20px">'+'<form enctype="multipart/form-data" method="post" style="padding:10px;" id="form-upload">'+'	<b style="font-size:14px;">Choose file or files</b><br/><br/>'+'    <input type="file" class="input" name="upload[]" id="fileuploader" multiple style="font-size:14px;" />'+"    &nbsp;"+'    <input type="button" value="Upload" onclick="doksoft_imageuploadFile(); return false" style="font-size:14px;" />'+"</form>"+'<div id="fileuploaderInfo">&nbsp;</div>'+"</td>"+"</tr>"+"</table>"+'<script language="javascript" src="'+b+'/fileuploader.js"><\/script>'+'<script language="javascript">'+"function doksoft_imageuploadFile() {"+'if (document.getElementById("fileuploader").value == "") {'+'    alert("Choose file for upload!");'+"	return false;"+"}"+'var mode = "'+e+'";'+'var params = "type=Images";'+'if (mode == "preview")'+' 	params += "&makeThumb=true";'+'if (mode == "resize")'+'	params += "&resize=true";'+"FileUploader.upload({"+'url: "'+b+'/../doksoft_uploader/uploader.php?" + params,'+'type: "POST",'+'fileElementId: "fileuploader",'+"timeout: 0,"+"secureuri: false,"+"callback: function(data) {"+'if (data.error == "" || data.error == undefined) {'+"parent.doksoft_imageuploadedHtml = data.html;"+'document.getElementById("fileuploaderInfo").innerHTML = "<b>Files uploaded, use `Insert` button to continue</b>";'+'document.getElementById("form-upload").style.visibility="hidden";'+"} else {"+'document.getElementById("fileuploaderInfo").innerHTML = "<b>Files uploading error: "+data.error+"</b>";'+"}"+"},"+"})"+"}"+"<\/script>"+"</body>"+"</html>";}