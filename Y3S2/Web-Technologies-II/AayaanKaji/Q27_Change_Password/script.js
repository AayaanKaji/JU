function changePassword() {
  const oldPassword = document.getElementById("oldPassword").value.trim();
  const newPassword = document.getElementById("newPassword").value.trim();

  if (!validateForm()) {
    showMessage("Properly fill the form.", false);
    return;
  }

  const xhr = new XMLHttpRequest();
  xhr.open("POST", "/AayaanKaji/Q27_Change_Password/ChangePasswordServlet", true);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

  xhr.onload = function () {
    const msgElem = document.getElementById("result");
    msgElem.innerText = this.responseText;
    msgElem.style.color = this.responseText.toLowerCase().includes("success")
      ? "green"
      : "red";

    if (this.responseText.toLowerCase().includes("success")) {
      window.location.href = "/AayaanKaji/Q32_Dashboard";
    }
  };

  const params = `oldPassword=${encodeURIComponent(oldPassword)}
    &newPassword=${encodeURIComponent(newPassword)}
    `;
  xhr.send(params);
}

function validateForm() {
  const newPassword = document.getElementById("newPassword");
  const confirmPassword = document.getElementById("confirmPassword");
  const submitBtn = document.getElementById("submitBtn");

  let isValid = true;

  // Check password length
  if (newPassword.value.trim().length > 16) {
    document.getElementById("passwordStatus").innerText =
      "Password must be less than 16 characters.";
    newPassword.style.borderColor = "red";
    isValid = false;
  } else {
    document.getElementById("passwordStatus").innerText = "";
    newPassword.style.borderColor = "";
  }

  // Check if passwords match
  if (
    newPassword.value.trim().length !== 0 &&
    confirmPassword.value.trim().length !== 0 &&
    newPassword.value !== confirmPassword.value
  ) {
    document.getElementById("passwordConformationCheck").innerText =
      "Passwords don't match.";
    confirmPassword.style.borderColor = "red";
    isValid = false;
  } else {
    document.getElementById("passwordConformationCheck").innerText = "";
    confirmPassword.style.borderColor = "";
  }

  if (
    oldPassword.value.trim().length !== 0 &&
    newPassword.value === oldPassword.value
  ) {
    document.getElementById("passwordStatus").innerText =
      "New password must be different from old password.";
    newPassword.style.borderColor = "red";
    isValid = false;
  }

  submitBtn.disabled = !isValid;
  return isValid;
}

function showMessage(message, isSuccess) {
  const msgElem = document.getElementById("result");
  msgElem.innerText = message;
  msgElem.style.color = isSuccess ? "green" : "red";
}
