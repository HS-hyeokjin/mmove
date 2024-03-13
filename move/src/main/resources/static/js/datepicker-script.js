/**
 * 일일 박스 오피스 날짜 선택을 위한 캘린더 함수
 */
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
/**
 * 주간 박스 오피스 날짜 선택을 위한 캘린더 함수
 */
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