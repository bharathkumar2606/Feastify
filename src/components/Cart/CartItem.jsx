import { AddCircleOutlineOutlined, RemoveCircleOutlineOutlined } from '@mui/icons-material'
import { Chip, Divider, IconButton } from '@mui/material'
import React from 'react'

const CartItem = () => {
  return (
    <div className='px-5 w-full'>
        <div className='lg:flex items-center lg:space-x-5'>
            <div>
                <img className='w-[5rem] h-[5rem] object-cover' src="https://images.unsplash.com/photo-1561758033-d89a9ad46330?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                 alt="burger" />
            </div>
            <div className='flex items-center justify-between lg:w-[70%]'>
                <div className='space-y-1 lg:space-y-3 w-full'>
                    <p>Hamburger</p>
                    <div className='flex justify-between items-center'>
                        <div className='flex items-center space-x-1'>
                             <IconButton>
                                <RemoveCircleOutlineOutlined/>
                             </IconButton>
                             <div className='w-5 h-5 text-xs flex items-center justify-center'>
                                {5}
                             </div>
                             <IconButton>
                                <AddCircleOutlineOutlined/>
                             </IconButton>
                        </div>

                    </div>
                </div>
                <p>₹399</p>

            </div>
        </div>
<div className='pt-3 space-x-2'>
    {[1,1].map((item)=><Chip label={"beacon strips"}/>)}
</div>

    </div>
    
  )
}

export default CartItem