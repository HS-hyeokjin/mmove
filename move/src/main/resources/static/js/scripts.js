function toggleBoxOfficeSections() {
    var boxOfficeSections = document.getElementById("boxOfficeSections");
    boxOfficeSections.style.display = (boxOfficeSections.style.display === "none") ? "block" : "none";
}

function toggleMovieDetailSections() {
    var movieDetailSections = document.getElementById("movieDetailSections");
    movieDetailSections.style.display = (movieDetailSections.style.display === "none") ? "block" : "none";
}

function togglePersonDetailSections() {
    var personDetailSections = document.getElementById("personDetailSections");
    personDetailSections.style.display = (personDetailSections.style.display === "none") ? "block" : "none";
}

$(document).ready(function () {
    var navbar = $(".navbar");
    var offset = navbar.offset().top;

    $(window).scroll(function () {
        if ($(window).scrollTop() >= offset) {
            navbar.addClass("fixed-top");
        } else {
            navbar.removeClass("fixed-top");
        }
    });
});

$(document).ready(function () {
    $('#dailyDatePicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true
    });

    $('#dailyDatePicker').on('changeDate', function (e) {
        var selectedDateWithHyphen = e.format();
        var selectedDateWithoutHyphen = selectedDateWithHyphen.replace(/-/g, '');

        var url = "/daily-box-office?date=" + selectedDateWithoutHyphen;
        window.location.href = url;
    });
});

$(document).ready(function () {
    $('#weeklyDatePicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true
    });

    $('#weeklyDatePicker').on('changeDate', function (e) {
        var selectedDateWithHyphen = e.format();
        var selectedDateWithoutHyphen = selectedDateWithHyphen.replace(/-/g, '');

        var url = "/weekly-box-office?date=" + selectedDateWithoutHyphen;
        window.location.href = url;
    });
});

$(document).ready(function () {
    // 스크롤 속도 및 카드의 너비 설정
    var scrollSpeed = 1;
    var cardWidth = 0.25;

    // 스크롤을 시작하는 함수
    function startScrolling() {
        // 카드 컨테이너 선택
        var $cardContainer = $('.card-container');

        // 카드를 왼쪽으로 이동하는 애니메이션 적용
        $cardContainer.animate({
            marginLeft: '-=' + cardWidth + 'px'
        }, scrollSpeed, 'linear', function () {
            // 만약 컨테이너가 왼쪽 끝을 벗어났다면 초기 위치로 되돌아감
            if ($cardContainer.offset().left <= -($cardContainer.width())) {
                $cardContainer.css('margin-left', 0);
            }
            // 애니메이션이 끝난 후에 다시 시작
            startScrolling();
        });
    }

    // 초기에 스크롤 시작
    startScrolling();
});