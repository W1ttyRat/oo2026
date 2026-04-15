import { useEffect, useState } from "react";
import type { Result } from "../assets/Result.ts";

function ResultsPage() {
    const [results, setResults] = useState<Result[]>([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/athletes/scores")
        .then(res => res.json())
        .then(json => setResults(json))
    }, []);

    return (
        <div>
            <div>
                {results.map(results => (
                    <div key={results.id}>
                        {results.name} - {results.totalScore}
                    </div>
                ))}
            </div>
        </div>
        
    )


}

export default ResultsPage;