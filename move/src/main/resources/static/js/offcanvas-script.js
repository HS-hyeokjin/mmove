/**
 * 각 섹션을 토글하는 함수
 * offcanvas에서 토글을 숨기고 노출함
 **/
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

function toggleAccountDetailSections() {
    var accountDetailSections = document.getElementById("accountDetailSections");
    accountDetailSections.style.display = (accountDetailSections.style.display === "none") ? "block" : "none";
}