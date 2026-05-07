import { Link, Route, Routes } from 'react-router-dom'
import './App.css'
import HomePage from './pages/HomePage.tsx'
import ResultsPage from './pages/ResultsPage.tsx'
import AddAthletePage from './pages/AddAthletePage.tsx'
import AddResultPage from './pages/AddResultPage.tsx'

function App() {

  return (
    <>

      <Link to="/athletes">
      <button>Kõik sportlased</button>
      </Link>
      <Link to="/results">
        <button>Tulemused</button>
      </Link>
      <Link to="/add">
        <button>Lisa sportlane</button>
      </Link>
      <Link to="/addResult">
        <button>Lisa tulemus</button>
      </Link>

      <Routes>
        <Route path="/athletes" element={<HomePage />} />
        <Route path="/results" element={<ResultsPage />} />
        <Route path="/add" element={<AddAthletePage />} />
        <Route path="/addResult" element={<AddResultPage />} />
      </Routes>

    </>
  )
}

export default App