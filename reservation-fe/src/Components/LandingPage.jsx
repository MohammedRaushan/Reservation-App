import React from 'react'
import { Link } from "react-router-dom"
import "../Styles/LandingPage.css"
const LandingPage = () => {
  return (
    <div className='landingpage'>
      <Link to={"/adminlogin"}>
        <img src="https://static.vecteezy.com/system/resources/previews/012/210/707/non_2x/worker-employee-businessman-avatar-profile-icon-vector.jpg" alt="" />
        <h2 className='text-2xl'>Admin</h2>
      </Link>
      <Link to={"/userlogin"}>
        <img src="https://cdn-icons-png.freepik.com/512/7547/7547994.png" alt="" />
        <h2 className='text-2xl'>User</h2>
      </Link>

    </div>
  )
}

export default LandingPage