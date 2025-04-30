document.getElementById("generateBtn").addEventListener("click", () => {
  const numInput = document.getElementById("numInput").value;
  const number = parseInt(numInput);

  if (isNaN(number) || number < 0) {
    alert("Please enter a whole number.");
    return;
  }

  const worker = new Worker("/Q15_Web_Worker/worker.js");
  worker.postMessage(number);

  worker.onmessage = (e) => {
    const factorialTable = e.data;
    const tableBody = document
      .getElementById("factorialTable")
      .getElementsByTagName("tbody")[0];

    tableBody.innerHTML = "";

    // Display the factorial table
    factorialTable.forEach((entry) => {
      const row = tableBody.insertRow();
      const cell1 = row.insertCell(0);
      const cell2 = row.insertCell(1);
      cell1.textContent = entry.number;
      cell2.textContent = entry.factorial;
    });

    worker.terminate();
  };

  worker.onerror = (error) => {
    console.error("Error from worker:", error);
  };
});
