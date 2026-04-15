import { useEffect, useState } from "react";
import type { Athlete } from "../assets/Athlete.ts";

function HomePage() {
    const [athletes, setAthletes] = useState<Athlete[]>([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/athletes")
        .then(res => res.json())
        .then(json => setAthletes(json))
    }, []);

    return (
        <div>
            <div>
                {athletes.map(athlete => (
                    <div key={athlete.id}>{athlete.name}</div>
                ))}
            </div>
        </div>
        
    )


}

export default HomePage;