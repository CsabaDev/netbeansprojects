/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function nevCheck(){
    if(document.forms["ujBaba"]["nev"].value===""){
        window.alert("Adja meg a baba nevét!");
        return false;
    }
}

