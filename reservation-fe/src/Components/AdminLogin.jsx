import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import AdminNavbar from './AdminNavbar'
import axios from 'axios'

const AdminLogin = () => {
  let [username, setUsername] = useState("")
  let [password, setPassword]= useState("")

  function verify(e){
    e.preventDefault()
    axios.get(`http://localhost:8080/api/admins/verify-by-email?email=${username}&password=${password}`)
    .then((res)=>{
      alert("Login Success")
    })
    .catch((err)=>{
      alert("Login Failed. Invalid Email or password")
    })
  }
  return (
    <div className='adminlogin flex-1 bg-purple-600'>
      {/* <AdminNavbar/> */}
      <div className="content flex flex-1 justify-center items-center h-screen bg-purple-600">
      <form onSubmit={verify} action="" className='w-64 sm:w-96 flex flex-col rounded-xl shadow shadow-[0px_0px_5px_black] sm:px-6 py-6 bg-white space-y-2'>
      <h1 className='text-3xl '>Admin Login</h1>
        <div>
        <span className='sm:inline hidden'>Username: </span>
        <input type="text" value={username} onChange={(e)=>{setUsername(e.target.value)}} placeholder='Enter Username or email' className='outline-none border-[1px] rounded-lg border-slate-500 mb-2 px-2' required />
        </div>
        <div>
        <span className='sm:inline hidden'>Password: </span>
        <input type="password" value={password} onChange={(e)=>{setPassword(e.target.value)}} placeholder='Enter Password' className='outline-none border-[1px] ml-[4px] rounded-lg border-slate-500 px-2' required />
        </div>
        <Link to={"/admin/reset-password"} className='text-purple-600 self-end pr-6'>forgot password?</Link><br />
        <button className='bg-sky-500 w-28 text-white text-xl px-2 pb-[2px] self-center rounded'>Login</button>
        <br />
        <span>don't have account? <Link to={"/adminsignup"} className='text-purple-700 hover:underline underline-2'>Create One</Link> </span>
      </form>
      </div>
    </div>
  )
}

export default AdminLogin