
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

function myPopUp(message, nextPage) {
    document.getElementById("popup").style.zIndex = 1;
    document.getElementById("popup").style.visibility = "visible";
    document.getElementById("msg").innerHTML = message;
    document.getElementById("popupOk").onclick = function () {
        if(nextPage === "") {
            document.getElementById("popup").style.zIndex = -1;
            document.getElementById("popup").style.visibility = "hidden";
        } else {
            location.href = nextPage;
        }
    };
}

function showHallOfFame(userName) {
    var numberOfColors = document.getElementById("numberOfColorsSlider").value;
    var codeLength = document.getElementById("codeLengthSlider").value;
    var colorsRepeatable = (document.getElementById("colorsRepeatable").checked);
    var onlyMine = (document.getElementById("onlyMine").checked);
    var params = {
        numberOfColors: numberOfColors,
        codeLength: codeLength,
        colorsRepeatable: colorsRepeatable,
        onlyMine: onlyMine
    };
    $.get("QueryServlet", $.param(params), function(responseJson) {
//        document.getElementById("log").innerHTML = responseJson.toString();
        var tbody = document.getElementById("hallOfFameTableRows");
        $("#hallOfFameTableRows tr").remove();
        var results = JSON.parse(responseJson);
        if(userName === "" && onlyMine === true) {
            var messageRow = tbody.insertRow(0).insertCell(0);
            messageRow.colSpan = 3;
            messageRow.innerHTML = "If you wish to store your results, " +
                    "<a href='register.jsp'>create an account</a>" + "!";
            return;
        }
        for (var i = 0, max = Math.min(10,results.length); i < max; i++) {
            var newRow = tbody.insertRow(i);
            newRow.insertCell(0).innerHTML = results[i].number;
            newRow.insertCell(1).innerHTML = results[i].time /10 + " sec";
            newRow.insertCell(2).innerHTML = results[i].date;
            if(onlyMine === false) {
                newRow.insertCell(0).innerHTML = results[i].userName;
            }
        }
    });
    if(onlyMine === true) {
        $("#hallOfFameTable th:first-child").hide();
    } else {
        $("#hallOfFameTable th:first-child").show();
    }
}