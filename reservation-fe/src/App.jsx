import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import LandingPage from "./Components/LandingPage"
import AdminLogin from "./Components/AdminLogin"
import UserLogin from "./Components/UserLogin"
function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path='/' element={<LandingPage/>}/>
        <Route path='/adminlogin' element={<AdminLogin/>} />
        <Route path='/userlogin' element={<UserLogin/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
