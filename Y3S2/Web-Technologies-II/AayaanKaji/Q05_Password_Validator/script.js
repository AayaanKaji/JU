function validateUsername() {
  let username = document.getElementById("username");
  let usernameError = document.getElementById("usernameError");
  
  /**
   * 1-10 Characters in length
   * Starts with a letter
   * Matches alphanumeric and underscores
   * No consecutive underscores
   * No underscore at the end
   */
  let usernamePattern = /^(?!.*__)(?!.*_$)[A-Za-z]\w{0,9}$/;
  
  usernameError.innerText = "";
  
  let errorMessages = [];
  
  if (username.value.length < 1 || username.value.length > 10) {
    errorMessages.push("Username must be between 1 and 10 characters.");
  }
  
  if (!/^[A-Za-z]/.test(username.value)) {
    errorMessages.push("Username must start with a letter.");
  }
  
  if (!/^[A-Za-z0-9_]+$/.test(username.value)) {
    errorMessages.push("Username can only contain letters, numbers, and underscores.");
  }
  
  if (username.value.includes("__")) {
    errorMessages.push("Username cannot have consecutive underscores.");
  }
  
  if (username.value.endsWith("_")) {
    errorMessages.push("Username cannot end with an underscore.");
  }
  
  if (errorMessages.length > 0) {
    errorMessages[0] = "* " + errorMessages[0];
    usernameError.innerText = errorMessages.join("\n* ");
  }
}

function validatePassword() {
  let password = document.getElementById("password");
  let passwordError = document.getElementById("passwordError");

  /**
   * 6-10 Characters in length
   * At least one digit
   * At least one Uppercase letter
   */
  let passwordPattern = /^(?=.*\d)(?=.*[A-Z]).{6,10}$/;
  
  passwordError.innerText = "";
  
  let errorMessages = [];
  
  if (password.value.length < 6 || password.value.length > 10) {
    errorMessages.push("Password must be between 6 and 10 characters.");
  }
  
  if (!/\d/.test(password.value)) {
    errorMessages.push("Password must contain at least one digit.");
  }
  
  if (!/[A-Z]/.test(password.value)) {
    errorMessages.push("Password must contain at least one uppercase letter.");
  }
  
  if (errorMessages.length > 0) {
    errorMessages[0] = "* " + errorMessages[0];
    passwordError.innerText = errorMessages.join("\n* ");
  }
}

function validateForm() {
  validateUsername();
  validatePassword();

  const usernameError = document.getElementById('usernameError').textContent;
  const passwordError = document.getElementById('passwordError').textContent;
  
  return !(usernameError || passwordError);
}

function handleSubmit(event) {
  event.preventDefault();
  
  if (validateForm()) {
    window.location.href = "/AayaanKaji";
  } else {
    return
  }
}