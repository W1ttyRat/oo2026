import { useState } from "react";
import { useNavigate } from "react-router-dom";

function AddAthletePage() {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [country, setCountry] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (event: React.SubmitEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError("");

    try {
      const response = await fetch("/api/athletes", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ name, country }),
      });

      if (!response.ok) {
        throw new Error("Sportlase lisamine ebaõnnestus");
      }

      setName("");
      setCountry("");
      navigate("/athletes");
    } catch {
      setError("Sportlase lisamine ebaõnnestus");
    }
  };

  return (
    <div>
      <h2>Lisa sportlane</h2>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Nimi</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>

        <div>
          <label>Riik</label>
          <input
            type="text"
            value={country}
            onChange={(e) => setCountry(e.target.value)}
            required
          />
        </div>

        <button type="submit">Lisa</button>
      </form>

      {error && <p>{error}</p>}
    </div>
  );
}

export default AddAthletePage;
