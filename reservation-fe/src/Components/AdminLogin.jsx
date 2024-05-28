import React from 'react'

const AdminLogin = () => {
  return (
    <div className='adminlogin flex justify-center items-center h-screen bg-sky-600'>
      <form action="" className='grid grid-cols-2 w-96 rounded-xl shadow shadow-[0px_0px_10px_grey] pr-6 py-6 bg-white'>
        <span>Username: </span>
        <input type="text" className='border-2 border-slate-500 mb-2' required />
        <span>Password: </span>
        <input type="password" className='border-2 border-slate-500' required />
      </form>
    </div>
  )
}

export default AdminLogin