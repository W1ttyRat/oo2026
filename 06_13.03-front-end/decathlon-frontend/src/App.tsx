import { Link, Route, Routes } from 'react-router-dom'
import './App.css'
import HomePage from './HomePage.tsx'

function App() {

  return (
    <>

      <Link to="/athletes">
      <button>Atletid</button>
      </Link>

      <Routes>
        <Route path="/athletes" element={<HomePage />} />
      </Routes>

    </>
  )
}

export default App