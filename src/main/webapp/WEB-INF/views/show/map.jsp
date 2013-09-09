<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de-de">
<head>
<title>Zettl</title>
<!-- meta http-equiv="content-type" content="text/html; charset=UTF-8" / -->
<meta http-equiv="content-script-type" content="text/javascript" />
<meta http-equiv="content-style-type" content="text/css" />
<meta http-equiv="content-language" content="de" />
<meta name="author" content="Thomas Heiles" />
<link rel="stylesheet" type="text/css" href="${context}/css/zettl.css">
<link rel="stylesheet" type="text/css" href="${context}/css/map.css"></link>

<script type="text/javascript" src="http://www.openlayers.org/api/OpenLayers.js"></script>
<script type="text/javascript" src="http://www.openstreetmap.org/openlayers/OpenStreetMap.js"></script>
<script type="text/javascript" src="${context}/js/tom.js"></script>
 
<script type="text/javascript">
//<![CDATA[

var map;
var layer_mapnik;
var layer_tah;
var layer_markers;

function drawmap() {
    // Popup und Popuptext mit evtl. Grafik
    var popuptext="<font color=\"black\"><b>${vm.headline}<br>${vm.street}<br>${vm.postcode} ${vm.city}</b><p><img src=\"${imageUrl}\" width=\"180\" height=\"113\"></p></font>";

    OpenLayers.Lang.setCode('de');
    
    // Position und Zoomstufe der Karte
    var lon = ${vm.lon}; //6.641389;
    var lat = ${vm.lat}; //49.756667;
    var zoom = 15;

    map = new OpenLayers.Map('map', {
        projection: new OpenLayers.Projection("EPSG:900913"),
        displayProjection: new OpenLayers.Projection("EPSG:4326"),
        controls: [
            new OpenLayers.Control.Navigation(),
            new OpenLayers.Control.LayerSwitcher(),
            new OpenLayers.Control.PanZoomBar()],
        maxExtent:
            new OpenLayers.Bounds(-20037508.34,-20037508.34,
                                    20037508.34, 20037508.34),
        numZoomLevels: 18,
        maxResolution: 156543,
        units: 'meters'
    });

    layer_mapnik = new OpenLayers.Layer.OSM.Mapnik("Mapnik");
    layer_markers = new OpenLayers.Layer.Markers("Address", { projection: new OpenLayers.Projection("EPSG:4326"), 
    	                                          visibility: true, displayInLayerSwitcher: false });

    map.addLayers([layer_mapnik, layer_markers]);
    jumpTo(lon, lat, zoom);
 
    // Position des Markers
    addMarker(layer_markers, ${vm.lon}, ${vm.lat}, popuptext);

}

//]]>
    </script>

  </head>
  <body onload="drawmap();">
	<%@include file="/WEB-INF/views/header.jsp" %>
	  
	  <%--<div id="header">
	   <div id="content">Karte (Testversion)</div>
	   <div id="osm">� <a href="http://www.openstreetmap.org">OpenStreetMap</a>
	     und <a href="http://www.openstreetmap.org/copyright">Mitwirkende</a>,
	     <a href="http://creativecommons.org/licenses/by-sa/2.0/deed.de">CC-BY-SA</a>
	   </div>
	  </div> --%>
	  <div id="map">
	  </div>
  	<%@include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>