import { useEffect, useState } from "react";
import type { Athlete } from "../assets/Athlete.ts";

function HomePage() {
    const [athletes, setAthletes] = useState<Athlete[]>([]);
    const [totalElements, setTotalElements] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(3);
    const [sort, setSort] = useState("id,asc");
    const [country, setCountry] = useState(0);

    useEffect(() => {
      const params = new URLSearchParams({
        page: String(page),
        size: String(size),
        sort,
      });

      // Send country only when an actual filter is selected
      if (country !== 0) {
        params.set("country", String(country));
      }

      fetch(`/api/athletes?${params.toString()}`)
        .then((res) => res.json())
        .then((json) => {
          setAthletes(json.content ?? []);
          setTotalElements(json.totalElements ?? 0);
          setTotalPages(json.totalPages ?? 0);
        });
    }, [page, size, sort, country]);

    const sizeHandler = (newSize: number) => {
        setSize(newSize);
        setPage(0);
    }

    const sortHandler = (newSort: string) => {
        setSort(newSort);
        setPage(0);
    }

    const countryHandler = (newCountry: number) => {
        setCountry(newCountry);
        setPage(0);
    }


    return (
        <div>
            <div>
                {page* size + 1}-{(page + 1) * size > totalElements ? totalElements : (page + 1) * size}
                kuvatud {totalElements}-st
            </div>

            <select defaultValue={3} onChange={(e) => sizeHandler(Number(e.target.value))}>
                <option>2</option>
                <option>3</option>
                <option>4</option>
            </select>

            <br /><br />

            <button onClick={() => sortHandler("id,asc")}>Sorteeri id,asc</button>
            <button onClick={() => sortHandler("id,desc")}>Sorteeri id,desc</button>
            <button onClick={() => sortHandler("name,asc")}>Sorteeri name,asc</button>
            <button onClick={() => sortHandler("name,desc")}>Sorteeri name,desc</button>
            <button onClick={() => sortHandler("country,asc")}>Sorteeri country,asc</button>
            <button onClick={() => sortHandler("country,desc")}>Sorteeri country,desc</button>

            <br /><br />

            <button
                style={country === 0 ? {fontWeight: "bold"}: undefined}
                onClick={() => countryHandler(0)}
            >
                Kõik riigid
            </button>

            <div>
                {athletes.map(athlete => (
                    <div key={athlete.id}>{athlete.name} - {athlete.country}</div>
                ))}
            </div>

            <button disabled={page === 0} onClick={() => setPage(page - 1)}>Eelmine</button>
            <span>{page + 1}</span>
            <button disabled={page+1 === totalPages} onClick={() => setPage(page + 1)}>Järgmine</button>
        </div>
        
    )


}

export default HomePage;