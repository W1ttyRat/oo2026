import { useEffect, useState } from "react";
import type { Athlete } from "../assets/Athlete.ts";
import { useNavigate } from "react-router-dom";

const disciplines = [
  { label: "100m", value: "100m" },
  { label: "Long Jump", value: "kaugushüpe" },
  { label: "Shot Put", value: "kuulitõuge" },
  { label: "High Jump", value: "kõrgushüpe" },
  { label: "400m", value: "400m" },
  { label: "110m Hurdles", value: "110mtõkked" },
  { label: "Discus Throw", value: "kettaheide" },
  { label: "Pole Vault", value: "teivashüpe" },
  { label: "Javelin Throw", value: "odavise" },
  { label: "1500m", value: "1500m" },
];

function AddResultPage() {
  const navigate = useNavigate();
  const [athletes, setAthletes] = useState<Athlete[]>([]);
  const [athleteId, setAthleteId] = useState("");
  const [discipline, setDiscipline] = useState("");
  const [value, setValue] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("/api/athletes?page=0&size=1000&sort=id,asc")
      .then((response) => response.json())
      .then((json) => setAthletes(json.content ?? []))
      .catch(() => setAthletes([]));
  }, []);

  const handleSubmit = async (event: React.SubmitEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError("");

    if (!athleteId || !discipline || !value) {
      setError("Kõik väljad on kohustuslikud");
      return;
    }

    try {
      const res = await fetch("/api/results", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          athleteId: Number(athleteId),
          discipline,
          value: Number(value),
        }),
      });

      if (!res.ok) throw new Error("Tulemuse lisamine ebaõnnestus");

      setAthleteId("");
      setDiscipline("");
      setValue("");
      navigate("/results");
    } catch {
      setError("Tulemuse lisamine ebaõnnestus");
    }
  };

  return (
    <div>
      <h2>Lisa tulemus</h2>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Sportlane</label>
          <select
            value={athleteId}
            onChange={(e) => setAthleteId(e.target.value)}
            required
          >
            <option value="">Vali sportlane</option>
            {athletes.map((athlete) => (
              <option key={athlete.id} value={athlete.id}>
                {athlete.name} ({athlete.country})
              </option>
            ))}
          </select>
        </div>

        <div>
          <label>Distsipliin</label>
          <select
            value={discipline}
            onChange={(e) => setDiscipline(e.target.value)}
            required
          >
            <option value="">Vali distsipliin</option>
            {disciplines.map((item) => (
              <option key={item.value} value={item.value}>
                {item.label}
              </option>
            ))}
          </select>
        </div>

        <div>
          <label>Väärtus</label>
          <input
            type="number"
            step="0.01"
            value={value}
            onChange={(e) => setValue(e.target.value)}
            required
          />
        </div>

        <button type="submit">Lisa tulemus</button>
      </form>

      {error && <p>{error}</p>}
    </div>
  );
}

export default AddResultPage;
