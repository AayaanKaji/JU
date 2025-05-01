const stateSelect = document.getElementById("states");
const districtSelect = document.getElementById("districts");
const infoPara = document.getElementById("district-info");

let places = {}; // {states: { districts: descriptions, }, }

function getPlaces() {
    fetch("data.csv")
    .then(response => response.text())
    .then(csvText => {
        // the info column (3rd column) may contain additional comma (,)
        rows = csvText.trim().split("\n").map(row => [
            row.split(",", 2)[0], // splits first two columns on the basis of ',' and gives column 1
            row.split(",", 2)[1], // splits first two columns on the basis of ',' and gives column 2
            row.substring([...row.matchAll(/,/g)][1].index + 1) // remaining text after the 2nd comma siting i.e. 3rd column
        ]);

        for (let i = 1; i<rows.length; i++) { // ignore header
            let [state, dist, info] = rows[i];
            if (!places[state]) {
                places[state] = {};
            }
            places[state][dist] = info;
        }
        populateStates();
    })
    .catch(error => console.error("Error loading CSV:", error));
}

function populateStates() {
    for (let state in places) {
        let option = document.createElement("option");
        option.value = state;
        option.textContent = state;
        stateSelect.appendChild(option);
    }
}

function populateDistricts() {
    districtSelect.innerHTML = '<option value="" selected disabled>--Select District--</option>';
    infoPara.textContent = "";
    let selectedState = stateSelect.value;
    if (selectedState) {
        let districts = Object.keys(places[selectedState]);
        districts.forEach(district => {
            let option = document.createElement("option");
            option.value = district;
            option.textContent = district;
            districtSelect.appendChild(option);
        });
    }
}

function showDistrictInfo() {
    let selectedState = stateSelect.value;
    let selectedDistrict = districtSelect.value;
    if (selectedState && selectedDistrict) {
        infoPara.textContent = places[selectedState][selectedDistrict];
    } else {
        infoPara.textContent = "";
    }
}

getPlaces();