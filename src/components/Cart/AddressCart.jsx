import { Home } from '@mui/icons-material'
import { Button, Card } from '@mui/material'
import React from 'react'

const AddressCart = ({item,showButton,handleSelectAddress}) => {
    
  return (
    <Card className='flex gap-5 w-64 p-5'>
        <Home/>
        <div className='space-y-3 text-gray-500'>
            <h1 className='font-semibold text-lg text-black'>Home</h1>
            <p>
                No.10,Vengateshwara Nagar,Velachery,
                Chennai,
                TamilNadu-600042
            </p>
            {showButton && (<Button variant='outlined' fullWidth onClick={handleSelectAddress(item)}>select</Button>)}
            
        </div>
        
    </Card>
  )
}

export default AddressCart