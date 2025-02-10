console.log("this is script");


const toggleSidebar=()=>{
	
	if($(".sidebar").is(":visible")){
		//true then close
		$(".sidebar").css("display","none");
		$(".content").css("margin-left","0%");
	}else{
		//else show
		$(".sidebar").css("display","block");
		$(".content").css("margin-left","20%");
	}
};

var messageBox = document.getElementById("message-box");
fetch('/user/delete-session', {
        method: 'DELETE'
    })
			.then(response => response.json())
    if (messageBox) {
        // Hide the message box after 10 seconds
        setTimeout(function() {
            messageBox.style.display = 'none';
        }, 10000); // 10,000 milliseconds = 10 seconds
    }
    
  
  
  function deletecontact(cid){
		console.log("inside deletecontact static folder");  

      
      Swal.fire({

			  title: "Do You Wnat To Delte This Contact?",
			  showDenyButton: true,
			  confirmButtonText: "Yes Delete!"
			}).then((result) => {
			  /* Read more about isConfirmed, isDenied below */
			  if (result.isConfirmed) {
			    window.location="/user/delete/"+cid;
			  } else if (result.isDenied) {
			    Swal.fire("Your Contact Is Not Deleted", "", "info");
			  }
			});
      
      }
      
      
      function deleteuser(){
		console.log("inside deleteuser static folder");  

      
      Swal.fire({

			  title: "Do You Wnat To Delte Your Accound!Theres No Turning Back \n You will losse all your contacts and data",
			  showDenyButton: true,
			  confirmButtonText: "Yes Delete!"
			}).then((result) => {
			  if (result.isConfirmed) {
        fetch("/user/delete-profile/", { method: "POST" })
            .then((response) => {
                if (response.ok) {
                    window.location.href = "/signin"; // Redirect after deletion
                } else {
                    Swal.fire("Failed to Delete Profile", "Something went wrong.", "error");
                }
            })
            .catch(() => Swal.fire("Error", "Could not delete profile. Please try again.", "error"));
    } else if (result.isDenied) {
        Swal.fire("Your Profile Is Not Deleted", "", "info");
    }
			});
      
      }
      
      
      
      
const search=()=>{
//	console.log("Searching");
let query=$("#search-input").val();

if(query=="")
{
	$(".search-result").hide();
}else{
	//search
	console.log(query);
	
	//sending request to server
	let url=`http://localhost:8484/search/${query}`;
	
	fetch(url).then(response=>{
		return response.json();
	}).then(data=>{
		//data....
		let text =`<div class='list-group'>`
		
		 data.forEach((contact)=>{
			 text+=`<a href='/user/${contact.cid}/contact' class='list-group-item list-group-item-action'>${contact.name}</a>`
		 });
		text+=`</div>`
		$(".search-result").html(text);
		$(".search-result").show()
	});
	
	
	
}
}