$(document).ready(function () {
		console.log("script connected!");


		let remMeID = $('#remMeID');
		let remMeIDstat = remMeID.is(':checked');

		let prevEmail = "";
		let email = $('#emailID').val();
		let pass = $('#passID').val();


		/**************************************************
			login remember me checkbox
			--on click, toggle stat
			--live change if checked
			--otherwise, remove from storage
				**************************************************/
		function rememberMe() {
			console.log("remember me function ran");
			remMeID.click(
				function () {
					//toggle remember me stat
					remMeIDstat = remMeIDstat === true ? false : true;
					console.log(remMeIDstat);


					//add to storage if there's data
					if (email != null && remMeIDstat === true) {
						addToLocStorage();
						console.log("added to storage 2");
					}

					//live change data in storage
					$("#emailID, #passID").on("input", function () {
						if (remMeIDstat === true) {
							addToLocStorage();
							console.log("added new input to storage");
						} else {
							localStorage.removeItem(prevEmail);
						}
					});


					//remove data if unchecked
					if (remMeIDstat === false) {
						localStorage.removeItem(prevEmail);
						console.log("removed to storage");
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


		/**************************************************
				changes the photo preview on upload
				**************************************************/

		function readURL(input) {
			// $("#form__addPet label").hide();

			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function (e) {
					$('#form__addPet #photo').css('background-image', 'url(' + e.target.result + ')');
					$('#form__addPet #photo').hide();
					$('#form__addPet #photo').fadeIn(650);
				}
				reader.readAsDataURL(input.files[0]);
			}

		}

		$("#photoUploadBtn").change(function () {
			readURL(this);
		});







		// /**************************************************
		// 	run on load
		// **************************************************/

		 rememberMe();



	} //end script
);