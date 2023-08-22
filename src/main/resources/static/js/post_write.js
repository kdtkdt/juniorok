$(document).ready(() => {
    // 공고 저장 버튼 선택 시 줄바꿈문자는 <br> 태그로 변경
    $('form').on('submit', function(e) {
        let textareas = [$('#requirements'), $('#preferredRequirements')];

        for (let textarea of textareas) {
            textarea.val(textarea.val().replace(/\n/g, "<br>"));
        }

        $(this).off('submit').submit();
    });

    $('#skillsInput').change(function() {
        const selectedSkill = $(this).val();
        const skillId = $(`option[value='${selectedSkill}']`).attr('id').replaceAll('skills', '');

        const thymeleaf = $('<input>')
            .attr('name', '_skills')
            .attr('type', 'hidden')
            .val('on');
        $('#skills').append(thymeleaf);

        const skillDiv = $('<input>')
            .addClass('btn-check')
            .attr('id', 'skill' + skillId)
            .attr('type', '')
            .attr('value', skillId)
            .attr('name', 'skills')
            .click(function() {
                    $(this).prev().remove(); // hidden 태그 제거
                    $(this).next().remove(); // label 태그 제거
                    $(this).remove(); // Skill 태그 제거
            });

        $('#skills').append(skillDiv);

        const label = $('<label>')
            .addClass('btn btn-outline-primary')
            .attr('for', `skill` + skillId)
            .text(selectedSkill);
        $('#skills').append(label);

        $(this).val('');
    });
})