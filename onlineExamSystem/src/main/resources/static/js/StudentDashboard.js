/* logout button  start */
var logoutBtn =document.querySelector("#logout-btn");
logoutBtn.onclick =function(){
    this.innerHTML=" Logging out . . .";
    logoutBtn.disabled = true;
    this.style.background ="#ccc";
  //   changes for parth     
    setTimeout(function(){
        window.location = "/src/template/teacher/base.html" 
        sessionStorage.removeItem =("/")
    },500); 
}
/* logout button  end */

/* <!-- Side_bar tabs Start --> */
// var loadContent = document.getElementById('#SProfile');
//  loadContent.onclick=  function (page){
//     const xhr = new XMLHttpRequest();
//     xhr.open('GET', `${page}.html` ,true);
//     xhr.onload = function ()
//     {
//       if(this.status === 200){
//         document.querySelector('.content').innerHTML =this.responseText;
//         }
//       };
//       xhr.send();
//     }
// function showContent(tabId){
//   const contents =document.querySelectorAll('.tab-content');
//   contents.forEach(content => content.classList.remove('.active'));

//   document.getElementById(tabId).classList.add('.active');
// }




