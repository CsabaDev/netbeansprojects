
var current = "0";

function checkEquality() {
    var password = document.getElementById("password").value;
    var passwordagain = document.getElementById("passwordagain").value;
    var pwdEquals = document.getElementById("passwordsEquals");
    if (password === passwordagain) {
        pwdEquals.innerHTML = '&#10004';
        document.getElementById("btnRegister").disabled = false;
    } else {
        pwdEquals.innerHTML = "!!!";
        document.getElementById("btnRegister").disabled = true;
    }
}

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
            var gameState = response.split(" ")[0];
            finishIfEnded(gameState);
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
    var evaluationColors = response.split(" ").slice(1);
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
        if (newEvaluationPeg.readyState === 'complete') {
            finishIfEnded(response.split(" ")[0]);
        }
        columnIndex ++;
    }
}

function finishIfEnded(gameState) {
    if (gameState === "gameWon") {
        document.getElementById("popup").style.zIndex = 1;
        document.getElementById("msg").innerHTML = "Congratulations! You won!";
        document.getElementById("popupOk").onclick = function () {
            location.href = "index.jsp";
        };
    }
    if (gameState === "gameOver") {
        document.getElementById("popup").style.zIndex = 1;
        document.getElementById("msg").innerHTML = "Game over! Better luck next time!";
        document.getElementById("popupOk").onclick = function () {
            location.href = "index.jsp";
        };
    }
}

function showHallOfFame() {
    var numberOfColors = document.getElementById("numberOfColorsSlider").value;
    var codeLength = document.getElementById("codeLengthSlider").value;
    var colorsRepeatable = (document.getElementById("colorsRepeatable").checked);
    var params = {
        numberOfColors: numberOfColors,
        codeLength: codeLength,
        colorsRepeatable: colorsRepeatable
    };
    $.get("QueryServlet", $.param(params), function(responseJson) {
        document.getElementById("log").innerHTML = responseJson.toString();
        var table = document.getElementById("hallOfFameTable");
        $("#hallOfFameTable tbody").remove();
        var results = JSON.parse(responseJson);
        for (var i = 0, max = 10; i < max; i++) {
            var newRow = table.insertRow(i);
            newRow.insertCell(0).innerHTML = results[i].userName;
            newRow.insertCell(1).innerHTML = results[i].number;
            newRow.insertCell(2).innerHTML = results[i].time /10 + " sec";
            newRow.insertCell(3).innerHTML = results[i].date;
        }
    });
}
