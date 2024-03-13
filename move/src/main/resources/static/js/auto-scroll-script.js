/**
 * 자동으로 스크롤을 옆으로 넘기는 함수
 * 스크롤 속도와 카드의 너비를 설정하고, 각 스크롤 가능한 행에 대해 스크롤링을 시작함.
 */
$(document).ready(function () {
    var scrollSpeed = 1;
    var cardWidth = 0.25;

    function startScrolling($cardContainer) {
        $cardContainer.animate({
            marginLeft: '-=' + cardWidth + 'px'
        }, scrollSpeed, 'linear', function () {
            if ($cardContainer.offset().left <= -700) {
                $cardContainer.css('margin-left', 0);
            }
            startScrolling($cardContainer);
        });
    }

    $('.scrollable-row').each(function () {
        startScrolling($(this).find('.card-container'));
    });
});