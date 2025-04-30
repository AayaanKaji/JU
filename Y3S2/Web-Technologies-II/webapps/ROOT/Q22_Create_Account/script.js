// Delay before requesting servlet
let timeoutID;

// Check if login name is available
const checkLoginNameAvailability = () => {
  const loginName = document.getElementById("loginName");
  const submitBtn = document.getElementById("submitBtn");
  const loginNameAvailability = document.getElementById(
    "loginNameAvailability"
  );

  clearTimeout(setTimeout);

  if ((loginName.value != null) & (loginName.value.trim().length > 0)) {
    // Makes a GET requst to the servlet
    timeoutID = setTimeout(
      () =>
        fetch(
          "/Q22_Create_Account/CheckLoginNameServlet?loginName=" +
            encodeURIComponent(loginName.value)
        )
          .then((response) => response.text())
          .then((response) => {
            if (response === "available") {
              loginName.style.borderColor = "";
              loginNameAvailability.innerHTML = "Login name is available.";
              loginNameAvailability.style.color = "green";
              submitBtn.disabled = false;
              return true;
            } else {
              loginName.style.borderColor = "red";
              loginNameAvailability.innerHTML = "Login name is already taken.";
              loginNameAvailability.style.color = "red";
              submitBtn.disabled = true;
              return false;
            }
          })
          .catch((error) => {
            loginName.style.borderColor = "";
            loginNameAvailability.innerHTML = "Network Error.";
            loginNameAvailability.style.color = "orange";
            submitBtn.disabled = true;
            return false;
          }),
      100
    );
  } else {
    loginName.style.borderColor = "";
    loginNameAvailability.innerHTML = "";
    submitBtn.disabled = true;
    return false;
  }
};

const validateForm = () => {
  const loginName = document.getElementById("loginName");
  const password = document.getElementById("password");
  const confirmPassword = document.getElementById("confirmPassword");
  const email = document.getElementById("email");
  const firstName = document.getElementById("firstName");
  const lastName = document.getElementById("lastName");
  const phone = document.getElementById("phone");
  const submitBtn = document.getElementById("submitBtn");

  let isValid = true;

  checkLoginNameAvailability();

  // Login Name validation (max length 50)
  if (loginName.value.trim().length > 50) {
    document.getElementById("loginNameStatus").innerText =
      "Login Name is too long (Max 50 characters).";
    document.getElementById("loginNameStatus").style.color = "red";
    loginName.style.borderColor = "red";
    isValid = false;
  } else {
    document.getElementById("loginNameStatus").innerText = "";
    loginName.style.borderColor = "";
  }

  // Password validation (max length 16)
  if (password.value.trim().length > 16) {
    document.getElementById("passwordStatus").innerText =
      "Password must be less than 16 characters.";
    document.getElementById("passwordStatus").style.color = "red";
    password.style.borderColor = "red";
    isValid = false;
  } else {
    document.getElementById("passwordStatus").innerText = "";
    password.style.borderColor = "";
  }

  // Email validation (max length 100)
  if (email.value.trim().length > 100) {
    document.getElementById("emailStatus").innerText =
      "Email must be less than 100 characters.";
    document.getElementById("emailStatus").style.color = "red";
    email.style.borderColor = "red";
    isValid = false;
  } else {
    document.getElementById("emailStatus").innerText = "";
    email.style.borderColor = "";
  }

  // First Name validation (max length 50)
  if (firstName.value.trim().length > 50) {
    document.getElementById("firstNameStatus").innerText =
      "First Name must be less than 50 characters.";
    document.getElementById("firstNameStatus").style.color = "red";
    firstName.style.borderColor = "red";
    isValid = false;
  } else {
    document.getElementById("firstNameStatus").innerText = "";
    firstName.style.borderColor = "";
  }

  // Last Name validation (max length 50)
  if (lastName.value.trim().length > 50) {
    document.getElementById("lastNameStatus").innerText =
      "Last Name must be less than 50 characters.";
    document.getElementById("lastNameStatus").style.color = "red";
    lastName.style.borderColor = "red";
    isValid = false;
  } else {
    document.getElementById("lastNameStatus").innerText = "";
    lastName.style.borderColor = "";
  }

  // Phone validation (exactly 10 characters)
  if (phone.value.trim().length !== 0 && phone.value.trim().length !== 10) {
    document.getElementById("phoneStatus").innerText =
      "Phone number must be exactly 10 characters.";
    document.getElementById("phoneStatus").style.color = "red";
    phone.style.borderColor = "red";
    isValid = false;
  } else {
    document.getElementById("phoneStatus").innerText = "";
    phone.style.borderColor = "";
  }

  // Confirm Password validation
  if (
    password.value.trim().length !== 0 &&
    confirmPassword.value.trim().length !== 0 &&
    password.value !== confirmPassword.value
  ) {
    document.getElementById("passwordConformationCheck").innerText =
      "Passwords don't match.";
    document.getElementById("passwordConformationCheck").style.color = "red";
    confirmPassword.style.borderColor = "red";
    isValid = false;
  } else {
    document.getElementById("passwordConformationCheck").innerText = "";
    confirmPassword.style.borderColor = "";
  }

  // Enable/Disable submit button based on validation
  submitBtn.disabled = !isValid;

  return isValid;
};

const submitForm = (event) => {
  event.preventDefault();

  // Get the form data
  const loginName = document.getElementById("loginName").value;
  const password = document.getElementById("password").value;
  const email = document.getElementById("email").value;
  const firstName = document.getElementById("firstName").value;
  const lastName = document.getElementById("lastName").value;
  const phone = document.getElementById("phone").value;

  // Check if all required fields are filled
  if (!validateForm()) {
    alert("Form invalid!");
    return;
  }

  // Prepare the data object
  const formData = {
    loginName: loginName.trim(),
    password: password.trim(),
  };

  // Only include optional fields if they are not empty
  if (email.trim()) {
    formData.email = email.trim();
  }
  if (firstName.trim()) {
    formData.firstName = firstName.trim();
  }
  if (lastName.trim()) {
    formData.lastName = lastName.trim();
  }
  if (phone.trim()) {
    formData.phone = phone.trim();
  }

  // Send the data to the servlet
  fetch("/Q22_Create_Account/CreateAccountServlet", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    body: new URLSearchParams(formData).toString(),
  })
    .then((response) => response.text())
    .then((data) => {
      if (data.includes("success")) {
        document.getElementById("signup").reset();
        // Redirect to Dashboard
        window.location.href = "/Q32_Dashboard/";
      } else {
        alert("Error: " + data);
      }
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("There was an error processing your request.");
    });

  return false;
};
