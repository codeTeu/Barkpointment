$(document).ready(
  function () {
    console.log("script connected!");

    let remMeID = $("#remMeID");
    let remMeIDstat = remMeID.is(":checked");

    let prevUsername = "";
    let username = $("#usernameID").val();
    let pass = $("#passID").val();

    /**************************************************
		generate default accounts
	**************************************************/
	//login
  	$("#genContainer #btnUserAcct").click(function(){
   	 	$("#form__login #usernameID").val("user");
    	$("#form__login #passID").val("user");
  	});
	 
	$("#genContainer #btnAdminAcct").click(function(){
   	 	$("#form__login #usernameID").val("admin");
    	$("#form__login #passID").val("admin");
  	});
  		$("#genContainer #btnReset").click(function(){
   	 	$("#form__login #usernameID").val("");
    	$("#form__login #passID").val("");
    	
    	$("#form__register #fnameID").val("");
        $("#form__register #lnameID").val("");
      	$("#form__register #phoneID").val("");
      	$("#form__register #emailID").val("");
      	$("#form__register #usernameID").val("");
      	$("#form__register #passID").val("");
  	});
  	
    //register page
    $("#genContainer #btnRegInfo").click(function () {
      $("#form__register #fnameID").val("Iron");
      $("#form__register #lnameID").val("Man");
      $("#form__register #phoneID").val("6471010101");
      $("#form__register #emailID").val("iron@gmail.com");
      $("#form__register #usernameID").val("ironMan");
      $("#form__register #passID").val("ironMan");
    });
    
    
    /**************************************************
				login remember me checkbox
				--on click, toggle stat
				--live change if checked
				--otherwise, remove from storage
	**************************************************/
    function rememberMe() {
      console.log("remember me function ran");
      remMeID.click(function () {
        //toggle remember me stat
        remMeIDstat = remMeIDstat === true ? false : true;
        console.log(remMeIDstat);

        //add to storage if there's data
        if (username != null && remMeIDstat === true) {
          addToLocStorage();
          console.log("added to storage 2");
        }

        //live change data in storage
        $("#usernameID, #passID").on("input", function () {
          if (remMeIDstat === true) {
            addToLocStorage();
            console.log("added new input to storage");
          } else {
            localStorage.removeItem(prevUsername);
          }
        });

        //remove data if unchecked
        if (remMeIDstat === false) {
          localStorage.removeItem(prevUsername);
          console.log("removed to storage");
        }
      });
    }

    function addToLocStorage() {
      username = $("#usernameID").val();
      pass = $("#passID").val();
      localStorage.removeItem(prevUsername);
      localStorage.setItem(username, pass);
      prevUsername = username;
    }

    /**************************************************
			changes the photo preview on upload
	**************************************************/

    function readURL(input) {
      // $("#form__addPet label").hide();

      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
          $("#form__addPet #photo").css(
            "background-image",
            "url(" + e.target.result + ")"
          );
          $("#form__addPet #photo").hide();
          $("#form__addPet #photo").fadeIn(650);
        };
        reader.readAsDataURL(input.files[0]);
      }
    }

    $("#photoUploadBtn").change(function () {
      readURL(this);
    });

    // /**************************************************
    // 	menu dropdown
    // **************************************************/
    $(".dropdown").hover(function () {
      var dropdownMenu = $(this).children(".dropdown-menu");
      if (dropdownMenu.is(":visible")) {
        dropdownMenu.parent().toggleClass("open");
      }
    });

    // /**************************************************
    // 	run on load
    // **************************************************/

    rememberMe();
  } //end script
);
