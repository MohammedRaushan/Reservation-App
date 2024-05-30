import axios from 'axios'
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

export default function AdminSignup() {
    let [name, setName] = useState("")
    let [phone, setPhone] = useState()
    let [email, setEmail] = useState("")
    let [password, setPassword] = useState("")
    let [gst_number, setGstNo] = useState("")
    let [travels_name, setTravelsName] = useState("")

    function createAccount(e) {
        e.preventDefault()
        let details = { name, phone, email, password, gst_number, travels_name }
        axios.post("http://localhost:8080/api/admins", details)
            .then((res) => {
                alert("Account created successfully")
                setEmail("")
                setGstNo("")
                setName("")
                setPassword("")
                setPhone("")
                setTravelsName("")
                console.log(res)
            })
            .catch((err) => {
                alert("Failed to create account")
                console.log(err)
            })
    }
    return (
        <div className="adminsignup flex h-screen w-full justify-center items-center bg-green-500">
            <form onSubmit={createAccount} action="" className='text-center space-y-2 bg-white shadow-[0px_0px_10px_white] px-8 py-6 rounded-lg'>
                <h2 className='text-2xl mb-4'>Register Your Account</h2>
                <div className='flex justify-between'>
                    <span>Name  </span>
                    <input type="text" value={name} onChange={(e) => { setName(e.target.value) }} className='border-[1px] border-slate-500 outline-none rounded pl-2' />
                </div>
                <div className='flex justify-between'>
                    <span>Email  </span>
                    <input type="email" value={email} onChange={(e) => { setEmail(e.target.value) }} className='border-[1px] border-slate-500 outline-none rounded pl-2' />
                </div>
                <div className='flex justify-between'>
                    <span>Password  </span>
                    <input type="password" value={password} onChange={(e) => { setPassword(e.target.value) }} className='border-[1px] border-slate-500 outline-none rounded pl-2' />
                </div>
                <div className='flex justify-between'>
                    <span>Phone  </span>
                    <input type="tel" value={phone} onChange={(e) => { setPhone(e.target.value) }} className='border-[1px] border-slate-500 outline-none rounded pl-2' />
                </div>
                <div className='flex justify-between'>
                    <span>Gst Number  </span>
                    <input type="text" value={gst_number} onChange={(e) => { setGstNo(e.target.value) }} className='border-[1px] border-slate-500 outline-none rounded pl-2' />
                </div>
                <div className='flex justify-between'>
                    <span>Travels Name  </span>
                    <input type="text" value={travels_name} onChange={(e) => { setTravelsName(e.target.value) }} className='border-[1px] border-slate-500 outline-none rounded pl-2' />
                </div>
                <button className='bg-sky-600 text-white px-2 text-xl pb-[2px] rounded'>Create account</button>
            </form>
        </div>
    )
}