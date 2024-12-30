/* logout button  start */
var logoutBtn =document.querySelector("#logout-btn");
logoutBtn.onclick =function(){
    this.innerHTML=" Logging out . . .";
    logoutBtn.disabled = true;
    this.style.background ="#ccc";
  //   changes for parth     
    setTimeout(function(){
        window.location = "/onlineExamSystem/src/main/resources/templates/base.html" 
        sessionStorage.removeItem =("/")
    },500);
}


/* logout button  end */