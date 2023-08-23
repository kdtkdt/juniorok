$(document).ready(() => {
    $(".card").on("click", function() {
        let postId = $(this).find("input[type='hidden']").val();
        requestAjax(postId, "GET", null)
    });

    function requestAjax(resource, method) {
        $.ajax({
            url: `/post/${resource}`,
            type: method,
            contentType: 'application/html',
            headers: {
                'X-CSRF-TOKEN': $('input[name="_csrf"]').val(),
            },
            success: function (xhr, status, response) {
                $('.modal-content').empty().append(response.responseText);
            },
            error: function (xhr, status, error) {
                alert('오류가 발생하였습니다. 나중에 다시 시도해주세요.');
            }
        });
    }
})