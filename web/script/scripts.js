/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var current = "0";

function refreshValue(id, value) {
    document.getElementById(id).innerHTML = value;
}

function setCurrent(id) {
    current = id.substring(8);
    var codePegs = document.getElementsByClassName("codePeg");
    for (var i = 0; i < codePegs.length; i++) {
        codePegs[i].style.borderStyle = "none";
    }
    document.getElementById(id).style.borderStyle = "groove"; 
}

function refreshColor(pickId) {
    var color = document.getElementById(pickId).style.backgroundColor;
    var guessPeg = "guessPeg";
    guessId = guessPeg.concat(current);
    document.getElementById(guessId).style.backgroundColor = color;
}