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