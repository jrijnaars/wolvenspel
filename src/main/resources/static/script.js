// Call the function when the page loads
document.addEventListener("DOMContentLoaded", () => {
    console.log("DOM fully loaded");
    // update the game state display initially
    updateGameStateDisplay();
    setInterval(updateGameStateDisplay, 1000); // Optional: Update every second

    // Define the startButton variable
    const startButton = document.getElementById("startGameBtn");

    // Add an event listener to the start button
    startButton.addEventListener("click", () => {
        updateStartButtonVisibility();
    });

    updateStartButtonVisibility();
});

// Function to check the game state and toggle the visibility of the start button
async function updateStartButtonVisibility() {
    try {
        const response = await fetch("/game/state");
        const gameState = await response.text();
        console.log("GameState:", gameState); // Log the GameState

        const startButton = document.getElementById("startGameBtn");
        const registerButton = document.getElementById("registerBtn");
        const nameInput = document.getElementById("nameInput");

        if (gameState === "LOBBY") {
            // Show the "Start Game" button, "Schrijf in" button, and input field
            startButton.style.display = "block";
            registerButton.style.display = "block";
            nameInput.style.display = "block";
        } else {
            // Hide the "Start Game" button, "Schrijf in" button, and input field
            startButton.style.display = "none";
            registerButton.style.display = "none";
            nameInput.style.display = "none";
        }
    } catch (error) {
        console.error("Error fetching game state:", error);
    }
}

// Event listener for the "Start game" button
document.getElementById("startGameBtn").addEventListener("click", async () => {
    // Send a GET request to the server to start the game
    const response = await fetch("/game/start");

    // Retrieve the response text from the server
    // Display the server's response in the result paragraph
    document.getElementById("result").textContent = await response.text();

    // Update the visibility of the start button based on the new game state
    await updateStartButtonVisibility();
    // Update the player list to reflect the new layout
    await updatePlayerList();
});

// Event listener for the "Register" button
document.getElementById("registerBtn").addEventListener("click", registerPlayer);

// Event listener for the "Enter" key in the input field
document.getElementById("nameInput").addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
        registerPlayer();
    }
});

async function updateGameStateDisplay() {
    try {
        const response = await fetch("/game/state");
        const gameState = await response.text();

        // Update the game state display
        const gameStateDisplay = document.getElementById("gameStateDisplay");
        gameStateDisplay.textContent = `Spel fase: ${gameState}`;
    } catch (error) {
        console.error("Error fetching game state:", error);
    }
}

async function updatePlayerList() {
    try {
        const response = await fetch("/game/players");
        const players = await response.json();

        const playerList = document.getElementById("playerList");
        playerList.innerHTML = ""; // Clear the list

        // Fetch the current game state
        const gameStateResponse = await fetch("/game/state");
        const gameState = await gameStateResponse.text();

        if (gameState === "LOBBY") {
            // Display players as a list
            playerList.style.display = "block";
            players.forEach(player => {
                const listItem = document.createElement("li");
                listItem.textContent = player.name;
                playerList.appendChild(listItem);
            });
        } else {
            // Display players in a grid
            playerList.style.display = "grid";
            playerList.style.gridTemplateColumns = "repeat(2, 1fr)";
            playerList.style.gap = "10px";

            players.forEach(player => {
                const tile = document.createElement("div");
                tile.className = "player-tile";
                tile.innerHTML = `<strong>${player.name}</strong><br>
                <em>${player.role ? player.role.roleName : "No role assigned"}</em></br>
                <span>${player.isAlive ? "actief" : "weggestuurd"}</span>`;
                playerList.appendChild(tile);
            });
        }
    } catch (error) {
        console.error("Error fetching player list:", error);
    }
}

// Function to handle player registration
async function registerPlayer() {
    const nameInputField = document.getElementById("nameInput");
    const nameInput = nameInputField.value;

    if (!nameInput) {
        alert("Please enter a name.");
        return;
    }

    const player = {name: nameInput};

    try {
        const response = await fetch("/game/addPlayer", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(player),
        });

        document.getElementById("result").textContent = await response.text();

        // Update the player list
        updatePlayerList();

        // Clear the input field
        nameInputField.value = "";
    } catch (error) {
        console.error("Error registering player:", error);
    }
}
