$(document).ready(function() {

    var mapContainer = document.getElementById('map');
    var mapOption = {
        center: new kakao.maps.LatLng(37.54699, 127.09598),
        level: 3
    };

    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(loc, function(result, status) {

        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });

            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: `<div style="width:150px;text-align:center;padding:6px 0;">${$('#companyName').html()}</div>`
            });
            infowindow.open(map, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        }
    });

    /*if (inputData != null) {
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
    }*/
});
