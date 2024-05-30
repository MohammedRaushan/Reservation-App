import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import LandingPage from "./Components/LandingPage"
import AdminLogin from "./Components/AdminLogin"
import UserLogin from "./Components/UserLogin"
import AdminSignup from './Components/AdminSignup';
import AdminResetPassword from './Components/AdminResetPassword';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path='/' element={<LandingPage/>}/>
        <Route path='/adminlogin' element={<AdminLogin/>} />
        <Route path='/adminsignup' element={<AdminSignup/>}/>
        <Route path='/admin/reset-password' element={<AdminResetPassword/>}/>
        <Route path='/userlogin' element={<UserLogin/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
