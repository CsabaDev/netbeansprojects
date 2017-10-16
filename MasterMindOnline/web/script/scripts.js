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
    var guessPegs = document.getElementsByClassName("guessPegDiv");
    for (var i = 0; i < guessPegs.length; i++) {
        guessPegs[i].style.borderStyle = "solid";
    }
    document.getElementById(id).style.borderStyle = "groove"; 
}

function refreshColor(pickId) {
    var color = document.getElementById(pickId).style.backgroundColor;
    var guessPeg = "guessPeg";
    guessId = guessPeg.concat(current);
    document.getElementById(guessId).style.backgroundColor = color;
}

function startEvaluate() {
    var guessesTable = document.getElementById("guesses");
    var numberOfGuesses = guessesTable.rows.length;
    var guessPegs = document.getElementsByClassName("guessPegDiv");
    var colorsJSon = getNewGuess(guessPegs);
    $.get("evaluator", $.param(colorsJSon), function(response) {
        if (response.substring(0,5) === ("ERROR")) {
            alert(response);
        } else {
            var newGuessRow = guessesTable.insertRow(numberOfGuesses);
            drawNewGuess(newGuessRow, guessPegs);
            drawNewEvaluation(newGuessRow, guessPegs.length, response);
        }
    });
}

function getNewGuess(guessPegs) {
    var colorsJSon = {};
    for (var i = 0; i < guessPegs.length; i++) {
        var propertyName = guessPegs[i].id;
        var propertyValue = guessPegs[i].style.backgroundColor;
        colorsJSon[propertyName] = propertyValue;
    }
    return colorsJSon;
}

function drawNewGuess(newGuessRow, guessPegs) {
    var colors = new Array(guessPegs.length);
    for (var i = 0; i < guessPegs.length; i++) {
        colors[i] = guessPegs[i].style.backgroundColor;
        var newPegCell = newGuessRow.insertCell(i);
        var newPeg = document.createElement("div");
        newPegCell.appendChild(newPeg);
        newPeg.className = "codePeg";
        newPeg.style.backgroundColor = colors[i];
    }
}

function drawNewEvaluation(newGuessRow, codeLength, response) {
    var evaluationColors = response.split(" ");
    var evaluationCell = newGuessRow.insertCell(codeLength);
    var evaluationTable = document.createElement("table");
    evaluationCell.appendChild(evaluationTable);
    var newEvaluationRow = evaluationTable.insertRow(0);
    var columnIndex = 0;
    var columnCount = Math.floor((codeLength + 1) / 2);
    for (var i = 0; i < codeLength; i++) {
        if (i === columnCount) {
            newEvaluationRow = evaluationTable.insertRow(1);
            columnIndex = i - columnCount;
        }
        var newEvaluationCell = newEvaluationRow.insertCell(columnIndex);
        var newEvaluationPeg = document.createElement("div");
        newEvaluationCell.appendChild(newEvaluationPeg);
        newEvaluationPeg.className = "resultPegDiv";
        newEvaluationPeg.style.backgroundColor = evaluationColors[i];
        columnIndex ++;
    }
}


