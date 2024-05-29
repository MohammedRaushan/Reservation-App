import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

const AdminNavbar = ({background}) => {
  let [bg, setBg] = useState(background);
  useEffect(()=>{
    if(bg==null){
      setBg("purple")
    }
  },[])
  return (
    <div className='adminnavbar absolute m-2 top-0 left-0 flex justify-between items-center w-11/12'>
      <div className="left">
        <Link>
        <img src="https://cdn.vectorstock.com/i/500p/13/56/modern-tour-bus-symbol-stylized-icon-for-logo-vector-40341356.jpg"
        className='sm:w-20 sm:h-20 px-2 rounded-full'
         alt="" />
        </Link>
      </div>
      <div className="right space-x-2">
        <Link className='hidden sm:inline text-xl  sm:text-white'>Home</Link>
        <Link className='hidden sm:inline text-xl  sm:text-white'>book bus</Link>
        <button className='sm:hidden bg-white border-[1px] border-white px-2 pb-[3px] text-purple-700 rounded-2xl'>menu</button>
      </div>
    </div>
  )
}

export default AdminNavbar