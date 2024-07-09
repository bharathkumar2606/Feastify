import React from 'react'
import ProfileNavigation from './ProfileNavigation'
import { Navbar } from '../Navbar/Navbar'

const Profile = () => {
  return (
    <div className='lg:flex justify-between'>
        <div className='sticky h-[80vh] lg:w-[20%]'>
            
            <ProfileNavigation/>

        </div>
        <div className='lg:w-[80%]'>

        </div>

    </div>
  )
}

export default Profile