// Call the function when the page loads
document.addEventListener("DOMContentLoaded", () => {
  console.log("DOM fully loaded");

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
    console.log("GameState:", gameState); // Log de GameState

    const startButton = document.getElementById("startGameBtn");
    if (gameState === "LOBBY") {
      startButton.style.display = "block";
    } else {
      startButton.style.display = "none";
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
  const text = await response.text();

  // Display the server's response in the result paragraph
  document.getElementById("result").textContent = text;

  // Update the visibility of the start button based on the new game state
  updateStartButtonVisibility();
});

// Event listener for the "Register" button
document.getElementById("registerBtn").addEventListener("click", async () => {
  // Get the value entered in the name input field
  const nameInput = document.getElementById("nameInput").value;

  // If no name is entered, show an alert and stop further execution
  if (!nameInput) {
    alert("Please enter a name.");
    return;
  }

  // Create a player object with the entered name
  const player = { name: nameInput };

  // Send a POST request to the server to register the player
  const response = await fetch("/game/addPlayer", {
    method: "POST", // Specify the HTTP method as POST
    headers: {
      "Content-Type": "application/json", // Indicate that the request body is JSON
    },
    body: JSON.stringify(player), // Convert the player object to a JSON string
  });

  // Retrieve the response text from the server
  const text = await response.text();

  // Display the server's response in the result paragraph
  document.getElementById("result").textContent = text;

  updatePlayerList();
});

async function updatePlayerList() {
  try {
    const response = await fetch("/game/players");
    const players = await response.json();

    const playerList = document.getElementById("playerList");
    playerList.innerHTML = ""; // Clear the list

    players.forEach(player => {
      const listItem = document.createElement("li");
      listItem.textContent = player.name;
      playerList.appendChild(listItem);
    });
  } catch (error) {
    console.error("Error fetching player list:", error);
  }
}
