
// 1. Get the HTML elements we need
const messageInput = document.getElementById("messageInput");
const analyzeButton = document.getElementById("analyzeButton");
const clearButton = document.getElementById("clearButton");
const resultContainer = document.getElementById("resultContainer");
const historyContainer = document.getElementById("historyContainer");
const historySearch = document.getElementById("historySearch");


// 2. Listen for clicks on the Analyze button
analyzeButton.addEventListener("click", analyzeMessage);
clearButton.addEventListener("click", clearForm);
historySearch.addEventListener("input", loadHistory);

// 3. Analyze a message using the Spring Boot API
async function analyzeMessage() {

    const message = messageInput.value.trim();

    // Check whether the user entered a message
    if (!message) {
        resultContainer.textContent = "Please enter a message first.";
        return;
    }

    analyzeButton.disabled = true;
    analyzeButton.textContent = "Analyzing...";

    try {

        // Send the message to the backend
        const response = await fetch("/api/messages/analyze", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                message: message
            })
        });

        if (!response.ok) {
            throw new Error("Message analysis failed.");
        }

        // Convert the JSON response into a JavaScript object
        const result = await response.json();

        // Display the analysis result
        displayResult(result);

        // Refresh the analysis history
        await loadHistory();

    } catch (error) {

        resultContainer.textContent =
            "Something went wrong: " + error.message;

    } finally {

        analyzeButton.disabled = false;
        analyzeButton.textContent = "Analyze Message";
    }
}
// Clear the message input and current result
function clearForm() {
    messageInput.value = "";
    resultContainer.replaceChildren();
}


// 4. Display the analysis result
function displayResult(result) {

    resultContainer.replaceChildren();

    const card = document.createElement("div");
    card.className = "result-card " + result.riskLevel.toLowerCase();

    const heading = document.createElement("h3");
    heading.textContent = "Analysis Result";

    const category = document.createElement("p");
    category.textContent =
        "Category: " + result.detectionCategory;

    const risk = document.createElement("span");
    risk.className = "risk-badge " + result.riskLevel.toLowerCase();
    risk.textContent = result.riskLevel;

    const keyword = document.createElement("p");
    keyword.textContent =
        "Matched Keyword: " + (result.matchedKeyword || "None");

    const reason = document.createElement("p");
    reason.textContent =
        "Reason: " + (result.reason || "No additional explanation.");

    card.append(heading, category, risk, keyword, reason);
    resultContainer.appendChild(card);
}


// 5. Retrieve previously analyzed messages

// 5. Retrieve previously analyzed messages and group them by date
async function loadHistory() {
    try {
        const response = await fetch("/api/messages");

        if (!response.ok) {
            throw new Error("Could not retrieve analysis history.");
        }

        const messages = await response.json();
        historyContainer.replaceChildren();

        if (messages.length === 0) {
            historyContainer.textContent =
                "No messages have been analyzed yet.";
            return;
        }
        // Get the search text and convert it to lowercase
        const searchTerm = historySearch.value.trim().toLowerCase();

// Keep only messages containing the search text
        const filteredMessages = messages.filter(message =>
            message.messageText.toLowerCase().includes(searchTerm)
        );

// Handle searches with no matching results
        if (filteredMessages.length === 0) {
            historyContainer.textContent = "No matching messages found.";
            return;
        }

        // Get today's and yesterday's dates
        const today = new Date();

        const yesterday = new Date();
        yesterday.setDate(yesterday.getDate() - 1);

        // Convert a date into YYYY-MM-DD format
        function getDateKey(date) {
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, "0");
            const day = String(date.getDate()).padStart(2, "0");

            return `${year}-${month}-${day}`;
        }

        const todayKey = getDateKey(today);
        const yesterdayKey = getDateKey(yesterday);

        // Create groups for the three date categories
        const groups = {
            Today: [],
            Yesterday: [],
            Earlier: []
        };

        // Sort messages from newest to oldest
        filteredMessages.sort((a, b) =>
            new Date(b.analyzedAt) - new Date(a.analyzedAt)
        );

        // Place each message into the correct date group
        filteredMessages.forEach(message => {
            const messageDate = message.analyzedAt.substring(0, 10);

            if (messageDate === todayKey) {
                groups.Today.push(message);
            } else if (messageDate === yesterdayKey) {
                groups.Yesterday.push(message);
            } else {
                groups.Earlier.push(message);
            }
        });

        // Display each group
        Object.entries(groups).forEach(([groupName, groupMessages]) => {

            if (groupMessages.length === 0) {
                return;
            }

            const section = document.createElement("div");

            const heading = document.createElement("h2");
            heading.textContent = groupName;
            heading.style.margin = "25px 0 15px";

            section.appendChild(heading);

            groupMessages.forEach(message => {
                const card = document.createElement("div");
                card.className =
                    "history-card " + message.riskLevel.toLowerCase();

                const text = document.createElement("p");
                text.textContent = "Message: " + message.messageText;

                const category = document.createElement("p");
                category.textContent =
                    "Category: " + message.detectionCategory;

                const risk = document.createElement("span");
                risk.className =
                    "risk-badge " + message.riskLevel.toLowerCase();
                risk.textContent = message.riskLevel;

                const date = document.createElement("p");
                date.textContent =
                    "Analyzed At: " + message.analyzedAt.replace("T", " ");

                card.append(text, category, risk, date);
                section.appendChild(card);
            });

            historyContainer.appendChild(section);
        });

    } catch (error) {
        historyContainer.textContent =
            "Error loading history: " + error.message;
    }
}



// 6. Load history when the webpage opens
loadHistory();
// 7. Handle dashboard navigation
const navButtons = document.querySelectorAll(".nav-button");
const pageSections = document.querySelectorAll(".page-section");

navButtons.forEach(button => {
    button.addEventListener("click", () => {

        // Remove active status from all navigation buttons
        navButtons.forEach(nav => nav.classList.remove("active"));

        // Activate the clicked navigation button
        button.classList.add("active");

        // Hide all page sections
        pageSections.forEach(section => section.classList.remove("active"));

        // Show the selected page section
        const selectedView = button.dataset.view + "-view";
        document.getElementById(selectedView).classList.add("active");

    });
});