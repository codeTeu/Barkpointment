$(document).ready(
	function() {

		let remMeID = $('#remMeID');
		let remMeIDstat = remMeID.is(':checked');

		let prevEmail = "";
		let email = $('#emailID').val();
		let pass = $('#passID').val();


		rememberMe();



		/**************************************************
	  		changes the photo preview on upload
   		**************************************************/

		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#preview-container').css('background-image', 'url(' + e.target.result + ')');
					$('#preview-container').hide();
					$('#preview-container').fadeIn(650);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		$("#photo-upload-btn").change(function() {
			readURL(this);
		});



		// on load 
		$("#form-profile #col-photo label").hide();















		//on click, toggle stat
		//live change if checked
		//otherwise, remove from storage
		function rememberMe() {

			remMeID.click(
				function() {
					//toggle remember me stat
					remMeIDstat = remMeIDstat === true ? false : true;
					console.log(remMeIDstat);

					//live change
					$("#emailID. #passID").on("input", function() {
						if (remMeIDstat === true) {
							addToLocStorage();
						}
						else {
							localStorage.removeItem(prevEmail);
						}
					});


					//add default input
					if (email != null) {
						addToLocStorage();
					}


					//remove if uncheck
					if (remMeIDstat === false) {
						localStorage.removeItem(prevEmail);
					}
				}
			);

		}


		function addToLocStorage() {
			email = $('#emailID').val();
			pass = $('#passID').val();
			localStorage.removeItem(prevEmail);
			localStorage.setItem(email, pass);
			prevEmail = email;
		}
	}



);


