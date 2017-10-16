var ajax;

function sendReq(){
    sendRequest();
    
}

function sendRequest(){
    console.log("send");
    ajax=new AjaxRequest();
    ajax.send("GET", "http://localhost:8080/CKOEEVizsga/AdoszamKuldo", handleRequest);
    
}

function handleRequest(){
    if(ajax.getReadyState()===4 && ajax.getStatus()===200){
        var field=document.getElementById("adoszam");
        field.value=ajax.getResponseText();
        //alert(ajax.getResponseText());
    }
}


