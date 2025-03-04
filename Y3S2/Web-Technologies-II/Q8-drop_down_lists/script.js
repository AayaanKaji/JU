const yearSelect = document.getElementById("year");
const semSelect = document.getElementById("sem");
const subjectList = document.getElementById("subjects");

let subjects = {};

function getSubjects() {
    fetch("subjects.csv")
    .then(response => response.text())
    .then(csvText => {
        rows = csvText.trim().split("\n").map(row => row.split(","));
        for (let i = 1; i<rows.length; i++) { // ignore header
            let [yr, sem, sub] = rows[i];
            let key = `${yr}-${sem}` // unique key for subject list
            if (!subjects[key]) {
                subjects[key] = [];
            }
            subjects[key].push(sub);
        }
        updateSubjects();
    })
    .catch(error => console.error("Error loading CSV:", error));
}

function updateSubjects() {
    const selectedYear = yearSelect.value;
    const selectedSemester = semSelect.value;
    const key = `${selectedYear}-${selectedSemester}`;

    subjectList.innerHTML = ""; 

    if (subjects[key]) {
        subjects[key].forEach(subject => {
            const li = document.createElement("li");
            li.textContent = subject;
            subjectList.appendChild(li);
        });
    }
}

yearSelect.addEventListener("change", updateSubjects);
semSelect.addEventListener("change", updateSubjects);

getSubjects();
updateSubjects();
