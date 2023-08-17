$(document).ready(function() {
    //회사이름으로 검색
    var inputData = [inputDataname];

    var mapContainer = document.getElementById('map4');
    var mapOption = {
        center: new kakao.maps.LatLng(37.54699, 127.09598),
        level: 3
    };

    var map = new kakao.maps.Map(mapContainer, mapOption);

    var count = 0;
    var ps = new kakao.maps.services.Places();
    var bounds = new kakao.maps.LatLngBounds();

    if (inputData != null) {
        keywordSearch(inputData[count]);
    }

    function keywordSearch(keyword) {
        ps.keywordSearch(keyword, placesSearchCB);
        count = count + 1;
    }

    function placesSearchCB(data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {
            displayMarker(data[0]);
            bounds.extend(new kakao.maps.LatLng(data[0].y, data[0].x));
            if (count < inputData.length) {
                keywordSearch(inputData[count]);
            } else if (count == inputData.length) {
                setBounds();
            }
        }
    }

    function displayMarker(place) {
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(place.y, place.x),
        });
        kakao.maps.event.addListener(marker, 'click', function () {
            var position = this.getPosition();
            var url = 'https://map.kakao.com/link/map/' + place.id;
            window.open(url, '_blank');
        });
    }

    function setBounds() {
        map.setBounds(bounds, 90, 30, 10, 30);
    }
});
