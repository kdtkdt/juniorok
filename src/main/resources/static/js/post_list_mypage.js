$(document).ready(() => {
    let isLoading = false;
    let nextPage = 2;

    $(window).scroll(function() {
        if ($(window).scrollTop() + $(window).height() > $("#postListContainer").height() - 100) {
            requestPostList(nextPage);
        }
    });

    addListenerToCard();

    let keyword = '';
    let checkedSkills = [];

    $('#skillsContainer').on('change', 'input[type="checkbox"]', function () {
        checkedSkills.length = 0;
        $("#skillsContainer input:checkbox:checked").each(function() {
            checkedSkills.push($(this).val());
        });
        requestPostList();
    });

    $('#search-button').click(function () {
        // 검색 키워드 불러오기
        keyword = $("#keyword").val();
        requestPostList();

    });

    function requestPostList(requestedPage) {
        if (isLoading) return; // 중복 로드 방지
        isLoading = true;

        // 선택된 스킬 목록 불러오기
        let checkedSkillsString = checkedSkills.join(',');
        // 조건에 맞는 공고 검색
        requestPostListAjax(keyword.toLowerCase(), checkedSkillsString, requestedPage);
    }

    function requestPostListAjax(keyword, skills, requestedPage) {
        $.ajax({
            url: `/post/list?keyword=${keyword}&skills=${skills}&page=${requestedPage == null ? 1 : requestedPage}`,
            type: 'GET',
            contentType: 'application/html',
            headers: {
                'X-CSRF-TOKEN': $('input[name="_csrf"]').val(),
            },
            success: function (xhr, status, response) {
                if (requestedPage != null) {
                    ++nextPage;
                    $('#postListContainer').append(response.responseText);
                } else {
                    $('#postListContainer').empty().append(response.responseText);
                }
                isLoading = false;

                addListenerToCard();
            },
            error: function (xhr, status, error) {
                alert('오류가 발생하였습니다. 나중에 다시 시도해주세요.');
            }
        });
    }

    function addListenerToCard() {
        $(".card").on("click", function() {
            if (isLoading) return; // 중복 로드 방지
            isLoading = true;

            let postId = $(this).find("input[type='hidden']").val();
            requestPostDetail(postId, "GET")
        });
    }

    function requestPostDetail(resource, method) {
        $.ajax({
            url: `/post/${resource}`,
            type: method,
            contentType: 'application/html',
            headers: {
                'X-CSRF-TOKEN': $('input[name="_csrf"]').val(),
            },
            success: function (xhr, status, response) {
                $('.modal-content').empty().append(response.responseText);
                setTimeout(() => {
                    loadMap();
                    // https://devtalk.kakao.com/t/kakao-map-loading-console-parser-blocking-message/77540/4
                    // #map 엘리먼트의 레이아웃 사이즈가 원하는 크기대로 잡혀 있어야만 제대로 지도가 나옵니다.
                    // 그래서 의도적인 지연시간을 넣었습니다.
                    // 지도는 공고 제일 하단에 위치하므로, 1초 정도의 지연은 사용자 경험에 영향이 없습니다.
                }, 1000)
                isLoading = false;
            },
            error: function (xhr, status, error) {
                alert('오류가 발생하였습니다. 나중에 다시 시도해주세요.');
            }
        });
    }

    function loadMap() {
        let mapContainer = document.getElementById('map');
        let mapOption = {
            center: new kakao.maps.LatLng(37.54699, 127.09598),
            level: 3
        };

        let map = new kakao.maps.Map(mapContainer, mapOption);

        // 주소-좌표 변환 객체를 생성합니다
        let geocoder = new kakao.maps.services.Geocoder();

        // 주소로 좌표를 검색합니다
        geocoder.addressSearch($('#companyLocation').html(), function(result, status) {

            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {

                let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 결과값으로 받은 위치를 마커로 표시합니다
                let marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });

                // 인포윈도우로 장소에 대한 설명을 표시합니다
                let infowindow = new kakao.maps.InfoWindow({
                    content: `<div style="width:150px;text-align:center;padding:6px 0;">${$('#companyName').html()}</div>`
                });
                infowindow.open(map, marker);

                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                map.setCenter(coords);

            }
        });
    }
})