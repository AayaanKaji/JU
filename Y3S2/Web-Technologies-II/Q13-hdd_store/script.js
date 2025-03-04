let db;

document.addEventListener("DOMContentLoaded", () => {
    let request = indexedDB.open("ComponentDB", 1);

    request.onupgradeneeded = (event) => {
        let db = event.target.result;
        let store = db.createObjectStore("components", { keyPath: ["name", "manufacturer"] });
        store.createIndex("priceIndex", "price", { unique: false });
    };

    request.onsuccess = (event) => {
        db = event.target.result;
        console.log("Database initialized");
    };

    request.onerror = (event) => {
        console.error("Database error:", event.target.errorCode);
    };
});

function addComponent() {
    let name = document.getElementById("name").value.trim();
    let manufacturer = document.getElementById("manufacturer").value.trim();
    let price = parseFloat(document.getElementById("price").value);
    let type = document.getElementById("type").value.trim();

    if (!name || !manufacturer || isNaN(price) || !type) {
        alert("Please fill in all fields correctly.");
        return;
    }

    let transaction = db.transaction(["components"], "readwrite");
    let store = transaction.objectStore("components");
    let component = { name, manufacturer, price, type };

    let request = store.add(component);
    request.onsuccess = () => {
        alert("Component added successfully!");
    };
    request.onerror = () => {
        alert("Error: Component with this Name and Manufacturer already exists.");
    };
}

// Show components
function showComponents() {
    let transaction = db.transaction(["components"], "readonly");
    let store = transaction.objectStore("components");
    let request = store.getAll();

    request.onsuccess = (event) => {
        let components = event.target.result;
        let tableBody = document.querySelector("#componentTable tbody");
        tableBody.innerHTML = "";

        components.forEach((component) => {
            let row = tableBody.insertRow();
            row.innerHTML = `
                <td>${component.name}</td>
                <td>${component.manufacturer}</td>
                <td>${component.price}</td>
                <td>${component.type}</td>
                <td>
                    <button onclick='updateComponent("${component.name}", "${component.manufacturer}")'>Update</button>
                    <button onclick='deleteComponent("${component.name}", "${component.manufacturer}")'>Delete</button>
                </td>
            `;
        });
    };
}

// Update component
function updateComponent(name, manufacturer) {
    let newPrice = parseFloat(prompt("Enter new price:"));
    let newType = prompt("Enter new type:");

    if (isNaN(newPrice) || !newType) {
        alert("Invalid input.");
        return;
    }

    let transaction = db.transaction(["components"], "readwrite");
    let store = transaction.objectStore("components");

    let request = store.get([name, manufacturer]);
    request.onsuccess = (event) => {
        let component = event.target.result;
        if (component) {
            component.price = newPrice;
            component.type = newType;

            let updateRequest = store.put(component);
            updateRequest.onsuccess = () => {
                alert("Component updated successfully!");
                showComponents();
            };
        } else {
            alert("Component not found.");
        }
    };
}

// Delete component
function deleteComponent(name, manufacturer) {
    let transaction = db.transaction(["components"], "readwrite");
    let store = transaction.objectStore("components");

    let request = store.delete([name, manufacturer]);
    request.onsuccess = () => {
        alert("Component deleted successfully!");
        showComponents();
    };
}
