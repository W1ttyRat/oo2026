import { Link, Route, Routes } from 'react-router-dom'
import './App.css'
import HomePage from './pages/HomePage.tsx'
import ResultsPage from './pages/ResultsPage.tsx'

function App() {

  return (
    <>

      <Link to="/athletes">
      <button>Kõik sportlased</button>
      </Link>
      <Link to="/results">
        <button>Tulemused</button>
      </Link>

      <Routes>
        <Route path="/athletes" element={<HomePage />} />
        <Route path="/results" element={<ResultsPage />} />
      </Routes>

    </>
  )
}

export default App