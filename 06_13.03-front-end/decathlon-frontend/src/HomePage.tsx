import { useEffect, useState } from "react";
import type { Athlete } from "./assets/Athlete";

function HomePage() {
    const [athletes, setAthletes] = useState<Athlete[]>([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/athletes")
        .then(res => res.json())
        .then(json => setAthletes(json))
    }, []);

    return (
        <div>
            {athletes.map(athletes =>
                <div key={athletes.id}>{athletes.name}</div>
            )}
        </div>
    )



}

export default HomePage;