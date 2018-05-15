//copyrightYear.js - basically makes the copyright tag on the website say stuff
//code used to be nested in the site pages themselves, this makes it easier prolly
window.onload = function() {
    var currentYear = new Date().getFullYear();
    document.getElementById("copyright").innerHTML = ("\u00A9UFO Products " + currentYear);
  }