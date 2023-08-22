$(document).ready(() => {
    // 공고 저장 버튼 선택 시 줄바꿈문자는 <br> 태그로 변경
    $('form').on('submit', function(e) {
        let textareas = [$('#requirements'), $('#preferredRequirements')];

        for (let textarea of textareas) {
            textarea.val(textarea.val().replace(/\n/g, "<br>"));
        }

        $(this).off('submit').submit();
    });
})