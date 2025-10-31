document.getElementById("helloBtn").addEventListener("click", async () => {
  const response = await fetch("/game/start");
  const text = await response.text();
  document.getElementById("result").textContent = text;
});

document.getElementById("greetBtn").addEventListener("click", async () => {
  const name = document.getElementById("nameInput").value || "wereld";
  const response = await fetch(`/api/greet?name=${encodeURIComponent(name)}`);
  const text = await response.text();
  document.getElementById("result").textContent = text;
});
