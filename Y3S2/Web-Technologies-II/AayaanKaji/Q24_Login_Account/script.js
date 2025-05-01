const submitForm = (event) => {
  event.preventDefault();

  // Get the form data
  const loginName = document.getElementById("loginName").value;
  const password = document.getElementById("password").value;
  const msg = document.getElementById("loginStatus");

  // Prepare the data object
  const formData = {
    loginName: loginName.trim(),
    password: password.trim(),
  };

  // Send the data to the servlet
  fetch("/AayaanKaji/Q29_Login_Auth/LoginAuthenticateServlet", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    body: new URLSearchParams(formData).toString(),
  })
    .then((response) => response.text())
    .then((data) => {
      if (data.includes("Successful")) {
        // Redirect to dashboard
        window.location.href = "/AayaanKaji/Q32_Dashboard";
      } else if (data.includes("Error")) {
        msg.innerHTML = data;
        msg.style.color = "red";
      }
    })
    .catch((error) => {
      console.error("Error:", error);
      msg.innerHTML = "There was an error processing your request.";
      msg.style.color = "red";
    });
};

// Add event listeners to clear the message as the user types
document.getElementById("loginName").addEventListener("input", () => {
  document.getElementById("loginStatus").innerHTML = "";
});

document.getElementById("password").addEventListener("input", () => {
  document.getElementById("loginStatus").innerHTML = "";
});
