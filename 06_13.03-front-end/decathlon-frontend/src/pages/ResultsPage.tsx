import { useEffect, useState } from "react";
import type { Result } from "../assets/Result.ts";

function ResultsPage() {
    const [results, setResults] = useState<Result[]>([]);
    const [discipline, setDiscipline] = useState("");

    useEffect(() => {
        const params = new URLSearchParams();
        if (discipline) {
            params.set("discipline", discipline);
        }

        const url = params.toString() ? "/api/results?" + params.toString() : "/api/results";
        
        fetch(url)
        .then(res => res.json())
        .then(json => setResults(json))
        .catch(() => setResults([]));
    }, [discipline]);

    return (
        <div>
            <h2>Tulemused</h2>

            <div>
                <label>Ditsipliin: </label>
                <input
                type="text"
                value={discipline}
                onChange={(e) => setDiscipline(e.target.value)}
                placeholder="nt 100m"
                />

                <button type="button" onClick={() => setDiscipline("")}>
                    Tühjenda filter
                </button>
            </div>

            <div>
                {results.map((result) => (
                    <div key={result.id}>
                        {result.athleteName} | {result.discipline} | väärtus: {result.value} | punktid: {result.score}
                    </div>
                ))}
            </div>
        </div>  
    );



}

export default ResultsPage;