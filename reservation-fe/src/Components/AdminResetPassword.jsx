import axios from 'axios'
import React, { useState } from 'react'

export default function AdminResetPassword() {
    let [data, setData]=useState([])
    let [email, setEmail]=useState("")
    let [password,setPassword]=useState("")
    function findAccount(e){
        e.preventDefault()
        axios.get(`http://localhost:8080/api/admins/find-by-email?email=${email}`)
        .then((res)=>{
            setData(res.data.data)
            let verify = document.getElementById("verified")
            verify.innerText = "(Verified)"
            let passForm = document.getElementById("rp")
            passForm.className = "space-x-2 mt-4"
        })
        .catch((err)=>{
            alert("Account not found")
            let verify = document.getElementById("verified")
            verify.innerText = ""
            let passForm = document.getElementById("rp")
            passForm.className = "hidden"
        })
    }

    function resetPassword(e){
        e.preventDefault()
        if(password.length()<8){
            let passCondn = document.getElementById("passCondn")
            passCondn.innerHTML="Password must be 8 characters"
            passCondn.className="p-2 border-"
        }
        axios.put(`http://localhost:8080/api/admins/reset-password/${data.id}?password=${password}`)
        .then((res)=>{
            alert("Password has been reset")
        })
        .catch((err)=>{
            alert("Error while reset password")
            console.log(err)
        })
    }
  return (
    <div className="resetpassword flex justify-center items-center h-screen">
        <div className="content flex flex-col items-start w-96">
            <h2 className='self-center'>Enter your email to find your account</h2>
            <form onSubmit={findAccount} className='space-x-4'>
                <input type="email" className='border-[1px] border-slate-400' onChange={(e)=>{setEmail(e.target.value)}} placeholder='Email' required/>
                <button className='bg-sky-600 text-white text-xl px-2 rounded'>Find</button>
                <span id='verified'></span>
            </form>

            <form onSubmit={resetPassword} id='rp' className='hidden'>
                <input type="text" pattern='{8-20}' className='border-[1px] border-slate-400' onChange={(e)=>{setPassword(e.target.value)}} placeholder='New Password' required />
                <button className='bg-sky-600 text-white text-xl px-2 rounded'>Reset</button>
                <span id='passCondn'></span>
            </form>
        </div>
    </div>
  )
}
