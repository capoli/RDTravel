window.addEventListener("load", init, false);
var locations;
function init() {
    locations = #{searchTripController.getAllLocationsAsGson()};
    console.log(locations);
    addListeners();
}

function addListeners() {
    locations.forEach(function (value) {
        document.getElementById(value).addEventListener("click", redirectForCountry, false);
    });
}

function redirectForCountry(event) {
    console.log(this.val);
    console.log(window.location.href);
    console.log(event.target);
    //window.location.href = ;
}