let db;

/* Device details*/
const COMPONENTS = [
  "Monitor",
  "Keyboard",
  "Mouse",
  "CPU",
  "RAM",
  "Motherboard",
  "Storage",
  "Graphics Card",
  "Speakers",
  "Webcam",
].sort();

const MANUFACTURERS = [
  "Samsung",
  "Dell",
  "Logitech",
  "Intel",
  "Corsair",
  "HP",
].sort();

/* Popup Notification */
function showNotification(message) {
  // Create a notification element
  const notification = document.createElement('div');
  notification.classList.add('notification');  
  notification.textContent = message;
  
  // Add to the notification container
  const container = document.getElementById('notification-container');
  container.appendChild(notification);

  // Remove after 3 seconds
  setTimeout(() => {
    notification.style.display = 'none';
  }, 5000);
}

/* Toggle (hide/show) form element */
function togglePopup() {
  const popup = document.getElementById("form-add-device");
  popup.classList.toggle("show");
}

function toggleUpdateForm() {
  const popup = document.getElementById("form-update-device");
  popup.classList.toggle("show");
}

window.addEventListener("click", function (event) {
  const popup1 = document.getElementById("form-add-device");
  const popup2 = document.getElementById("form-update-device");
  if (event.target === popup1) {
    togglePopup();
  }

  if (event.target === popup2) {
    toggleUpdateForm();
  }
});

/* Fill the drop down lists in Form */
function populateComponents() {
  const componentSelect = document.getElementById("component");

  componentSelect.innerHTML =
    '<option value="" selected disabled>--Component--</option>';

  for (let component of COMPONENTS) {
    let option = document.createElement("option");
    option.value = component;
    option.textContent = component;
    componentSelect.appendChild(option);
  }
}

function populateManufacturers() {
  const manufacturerSelect = document.getElementById("manufacturer");

  manufacturerSelect.innerHTML =
    '<option value="" selected disabled>--Manufacturer--</option>';

  for (let manufacturer of MANUFACTURERS) {
    let option = document.createElement("option");
    option.value = manufacturer;
    option.textContent = manufacturer;
    manufacturerSelect.appendChild(option);
  }
}

/* Load Indexed DB */
document.addEventListener("DOMContentLoaded", () => {
  const req = indexedDB.open("Warehouse", 1);

  req.onupgradeneeded = (event) => {
    db = event.target.result;
    const objectStore = db.createObjectStore("electronics", {
      keyPath: ["name", "manufacturer"],
    });
    objectStore.createIndex("componentIndex", "component", {
      unique: false,
    });
    objectStore.createIndex("priceIndex", "price", {
      unique: false,
    });
    objectStore.createIndex("quantityIndex", "quantity", {
      unique: false,
    });
  };

  req.onsuccess = () => {
    db = req.result;
    console.log("Database initialized");
    populateManufacturers();
    populateComponents();
    showDevices();
  };

  req.onerror = (event) => {
    console.error("Database error: " + event.target.errorCode);
  };
});

/* Popolate the electronics table with DB entries */
function showDevices() {
  let transaction = db.transaction(["electronics"], "readonly");
  let store = transaction.objectStore("electronics");
  let request = store.getAll();

  request.onsuccess = (event) => {
    let devices = event.target.result;
    let tableBody = document.querySelector("#electronics-table tbody");
    tableBody.innerHTML = "";

    devices.forEach((device) => {
      let row = tableBody.insertRow();
      row.innerHTML = `
                <td>${device.name}</td>
                <td>${device.manufacturer}</td>
                <td>${device.component}</td>
                <td>${device.price}</td>
                <td>${device.quantity}</td>
                <td>
                    <button onclick='populateUpdateForm("${device.name}", "${device.manufacturer}")'>Update</button>
                    <button onclick='deleteDevice("${device.name}", "${device.manufacturer}")'>Delete</button>
                </td>
            `;
    });
  };

  request.onerror = (event) => {
    console.error("Error retrieving devices: ", event.target.error);
  };
}

/* Random device entry for faster showcase */
function addRandomDevice() {
  const component = COMPONENTS[Math.floor(Math.random() * COMPONENTS.length)];

  const randomNumbers = Math.floor(Math.random() * 1000);
  const name = String(component + " " + randomNumbers);

  const manufacturer =
    MANUFACTURERS[Math.floor(Math.random() * MANUFACTURERS.length)];
  const price = (Math.random() * (1000 - 50) + 50).toFixed(2); // between 50 and 1000
  const quantity = Math.floor(Math.random() * 100) + 1; // between 1 and 100

  document.getElementById("name").value = name;
  document.getElementById("manufacturer").value = manufacturer;
  document.getElementById("component").value = component;
  document.getElementById("price").value = price;
  document.getElementById("quantity").value = quantity;

  addDevice();
}

function addDevice() {
  let name = document.getElementById("name").value.trim();
  let manufacturer = document.getElementById("manufacturer").value.trim();
  let component = document.getElementById("component").value.trim();
  let price = parseFloat(document.getElementById("price").value);
  let quantity = parseInt(document.getElementById("quantity").value);

  if (
    !name ||
    !MANUFACTURERS.includes(manufacturer) ||
    !COMPONENTS.includes(component) ||
    isNaN(price) ||
    isNaN(quantity)
  ) {
    showNotification("Please fill in all fields correctly.");
    return;
  }

  let transaction = db.transaction(["electronics"], "readwrite");
  let store = transaction.objectStore("electronics");
  let device = { name, manufacturer, component, price, quantity };

  let request = store.add(device);

  request.onsuccess = () => {
    showNotification("New device added!");
    showDevices();
  };

  request.onerror = () => {
    showNotification("Error: Could not add device.");
  };
}

/* Populate Update Form with previos data */
function populateUpdateForm(name, manufacturer) {
  let transaction = db.transaction(["electronics"], "readwrite");
  let store = transaction.objectStore("electronics");
  let request = store.get([name, manufacturer]);

  request.onsuccess = (event) => {
    let device = event.target.result;

    if (device) {
      document.getElementById("hidden-name").value = device.name;
      document.getElementById("hidden-manufacturer").value =
        device.manufacturer;
      document.getElementById("hidden-type").value = device.component;
      document.getElementById("new-price").value = device.price;
      document.getElementById("new-quantity").value = device.quantity;

      toggleUpdateForm();
    } else {
      showNotification("Device not found.");
    }
  };
}

function updateDevice() {
  // Retrieve the hidden field values
  let name = document.getElementById("hidden-name").value.trim();
  let manufacturer = document
    .getElementById("hidden-manufacturer")
    .value.trim();
  // let type = document.getElementById("hidden-type").value.trim();

  // Retrieve the updated values for price and quantity
  let price = parseFloat(document.getElementById("new-price").value);
  let quantity = parseInt(document.getElementById("new-quantity").value);

  if (isNaN(price) || isNaN(quantity)) {
    showNotification("Please fill in all fields correctly.");
    return;
  }

  let transaction = db.transaction(["electronics"], "readwrite");
  let store = transaction.objectStore("electronics");
  let request = store.get([name, manufacturer]);

  request.onsuccess = (event) => {
    let device = event.target.result;
    if (device) {
      device.price = price;
      device.quantity = quantity;

      let updateRequest = store.put(device);
      updateRequest.onsuccess = () => {
        showNotification("Device updated!");
        showDevices();
      };
    } else {
      showNotification("Device not found.");
    }
  };
}

function deleteDevice(name, manufacturer) {
  if (confirm("Are you sure you want to delete this device?")) {
    let transaction = db.transaction(["electronics"], "readwrite");
    let store = transaction.objectStore("electronics");
    let request = store.delete([name, manufacturer]);

    request.onsuccess = () => {
      showNotification("Device deleted!");
      showDevices();
    };
  }
}
