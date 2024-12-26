/*
	let warningCount = 0;
	const maxWarnings = 3;

	function showWarning() {
		warningCount++;
		if (warningCount === 1) {
			alert("Warning 1: Please do not switch tabs or minimize the window.");
		} else if (warningCount === 2) {
			alert("Warning 2: Please stay focused. The quiz will end if you switch again.");
		} else if (warningCount === 3) {
			alert("Final Warning: If you switch tabs or minimize the window again, the quiz will end.");
		} else if (warningCount > maxWarnings) {
			endQuiz();
		}
	}

	function endQuiz() {
		alert("The quiz has ended due to repeated tab switches or window minimizations.");
		localStorage.removeItem("warningCount");
		document.getElementById("submitQuizForm").submit(); // Automatically submit the quiz form
	}

	// Detect tab switching
	document.addEventListener("visibilitychange", () => {
		if (document.visibilityState === "hidden") {
			showWarning();
		}
	});

	// Detect window minimization or resizing below a certain threshold
	window.addEventListener("resize", () => {
		if (window.outerWidth < 600 || window.outerHeight < 400) {
			showWarning();
		}
	});

	// Store the original window size
		const originalWidth = window.outerWidth;
		const originalHeight = window.outerHeight;

//        // Add an event listener to detect resizing
//        window.addEventListener("resize", () => {
//            if (window.outerWidth !== originalWidth || window.outerHeight !== originalHeight) {
//                alert("Resizing the window is not allowed during the quiz.");
//                //window.resize(originalWidth, originalHeight); // Revert to original size
//                startTimer();
//
//            }
//        });

		function handleResize() {
				if (window.outerWidth !== originalWidth || window.outerHeight !== originalHeight) {
					// Only give a warning if the window is resized
						showWarning();
				}
			}


			window.addEventListener("resize", handleResize);

		window.onload = function() {
		warningCount = localStorage.getItem("warningCount")
			? parseInt(localStorage.getItem("warningCount"))
			: 0;
			if (warningCount > maxWarnings) {
				endQuiz();  // End the quiz if warnings exceeded after page load
			}
		};

		window.onbeforeunload = function() {
			// Prevent the warning count from resetting when the page is refreshed
			localStorage.setItem("warningCount", warningCount)
		};




				// Timer settings
				const timerDuration = 5; // Timer in seconds (e.g., 300 seconds = 5 minutes)
				let remainingTime = timerDuration;  // Set the initial remaining time
				let timerId; // Variable to store the timer ID (used to clear the timer later)

				// Start Timer
				function startTimer() {
					timerId = setInterval(function() {
						// Decrease the remaining time
						remainingTime--;

						// Display the remaining time
						document.getElementById('time').innerText = formatTime(remainingTime);

						// If time runs out
						if (remainingTime <= 0) {
							clearInterval(timerId);  // Stop the timer
							alert("Time's up! The quiz will be submitted.");
							submitQuiz();  // Call function to end/submit the quiz
						}
					}, 1000); // Update every second
				}

				// Format seconds into minutes and seconds (MM:SS)
				function formatTime(seconds) {
					const minutes = Math.floor(seconds / 60);
					const sec = seconds % 60;
					return `${minutes}:${sec < 10 ? '0' + sec : sec}`;
				}

				// Submit quiz
				function submitQuiz() {
					// You can add logic to submit the form automatically here
					document.getElementById('quizForm').submit();
				}

				// Call the startTimer function when the page loads
//                window.onload = function() {
//                    startTimer();
//                };


*/

document.addEventListener("keyup", function(event) {
	if (event.key === "Control") {
		console.log("Ctrl key released!");
		document.addEventListener("keyup", function(event) {
			// Check if the 'Ctrl' key is released
			if (event.key === "Control") {
				onCtrlRelease(); // Call your function
			}
		});
	}
});



document.getElementById('username').addEventListener('input', function() {
	const usernameInput = this.value;
	const feedbackElement = document.getElementById('username-feedback');
	if (/\s/.test(usernameInput)) {
		feedbackElement.textContent = "White spaces Not alowed";
		this.value = usernameInput.replace(/\s/g, ""); // Optionally remove whitespaces
	} else if (usernameInput.length > 0) {
		// Make an AJAX request to check the username
		fetch(`/online_exam_system/check_username?username=${encodeURIComponent(usernameInput)}`)
			.then(response => response.json())
			.then(isTaken => {
				if (isTaken) {
					feedbackElement.textContent = "Username is already taken. Please choose another.";
					feedbackElement.classList.add('text-danger');
					feedbackElement.classList.remove('text-success');
				} else {
					feedbackElement.textContent = "Username is available!";
					feedbackElement.classList.add('text-success');
					feedbackElement.classList.remove('text-danger');
				}
			})
			.catch(error => {
				console.error('Error checking username:', error);
				feedbackElement.textContent = "Error checking username availability.";
				feedbackElement.classList.add('text-danger');
				feedbackElement.classList.remove('text-success');
			});
	} else {
		feedbackElement.textContent = "";
		feedbackElement.classList.remove('text-danger', 'text-success');
	}

});

document.getElementById("username").addEventListener("input", function() {
	const input = this.value;
	const feedbackElement = document.getElementById('username-feedback');

});

const submitbtn = document.getElementById('submit')

document.getElementById('emailid').addEventListener('input', function() {
	const emailInput = this.value;
	const feedbackElement = document.getElementById('email-feedback');
	if (emailInput.length > 0) {
		// Make an AJAX request to check the username
		fetch(`/online_exam_system/check_email?emailid=${encodeURIComponent(emailInput)}`)
			.then(response => response.json())
			.then(isTaken => {
				if (isTaken) {
					feedbackElement.textContent = "This email is already registered with differen accoutn. Please choose another.";
					feedbackElement.classList.add('text-danger');
					feedbackElement.classList.remove('text-success');
					submitbtn.style.display='none';
				} else {
					feedbackElement.textContent = "";
					feedbackElement.classList.remove('text-danger', 'text-success');
					submitbtn.style.display='block';
				}
			})
			.catch(error => {
				console.error('Error checking username:', error);
				feedbackElement.textContent = "Error checking username availability.";
				feedbackElement.classList.add('text-danger');
				feedbackElement.classList.remove('text-success');
			});
	} else {
		feedbackElement.textContent = "";
		feedbackElement.classList.remove('text-danger', 'text-success');
	}

});

