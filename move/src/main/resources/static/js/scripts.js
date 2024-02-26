function toggleBoxOfficeSections() {
    var boxOfficeSections = document.getElementById("boxOfficeSections");
    boxOfficeSections.style.display = (boxOfficeSections.style.display === "none") ? "block" : "none";
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
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true
    });

    $('.datepicker').on('changeDate', function (e) {
        var selectedDateWithHyphen = e.format();
        var selectedDateWithoutHyphen = selectedDateWithHyphen.replace(/-/g, '');

        var url = "/daily-box-office?date=" + selectedDateWithoutHyphen;
        window.location.href = url;
    });
});
$(document).ready(function () {
    $('.scrollable-row').scrollLeft(0);
    var scrollSpeed = 2;

    function startScrolling() {
        var $scrollableRow = $('.scrollable-row');
        $scrollableRow.animate({
            scrollLeft: $scrollableRow.scrollLeft() + 200
        }, scrollSpeed, 'linear', function () {
            startScrolling();
        });
    }

    startScrolling();
});

$(document).ready(function () {
    var scrollSpeed = 2;
    var cardWidth = 0.25;

    function startScrolling() {
        var $cardContainer = $('.card-container');
        $cardContainer.animate({
            marginLeft: '-=' + cardWidth + 'px'
        }, scrollSpeed, 'linear', function () {
            if ($cardContainer.offset().left <= -($cardContainer.width())) {
                $cardContainer.css('margin-left', 0);
            }
            startScrolling();
        });
    }

    startScrolling();
});

$(document).ready(function () {
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true
    });
});