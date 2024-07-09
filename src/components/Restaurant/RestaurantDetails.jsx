import { Divider, FormControl, FormControlLabel, Grid, Radio, RadioGroup, Typography } from '@mui/material'
import React, { useState } from 'react'
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import MenuCard from './MenuCard';




const categories=[
    "pizza",
    "burger",
    "chicken",
    "Chicken Fried rice",
    "Noodles",
    "Dosa",
    "Idli",
]

const foodTypes=[
    {label:"All",value:"all"},
    {label:"Veg-Only",value:"veg"},
    {label:"Non-Veg Only",value:"non-veg"},
    {label:"Seasonal Only",value:"seasonal"},
]

const menu=[1,1,1,1,1,1]

const RestaurantDetails = () => {
    const [foodType,setFoodType]=useState('all')
    const handleFilter=(e)=>{
        console.log(e.target.value,e.target.name)
    }
  return (
    <div className='px-5 lg:px-20 font-poppins'>
        <section> 
            <h3 className='text-grey-200 py-2 mt-10'>Home/India/The Hungry Hippo</h3>
            <div >
                <Grid container spacing={2} >
                    <Grid item xs={12}>
                        <img
                        className='w-full h-60 object-cover rounded-lg' 
                        src='https://images.unsplash.com/photo-1593160688806-4a3604ef4cbd?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
                        />
                    </Grid>
                    <Grid item xs={12} lg={6}>
                        <img
                        className='w-full h-[40h] object-cover rounded-lg' 
                        src='https://images.unsplash.com/photo-1628294896516-344152572ee8?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
                        />
                    </Grid>
                    <Grid item xs={12} lg={6}>
                        <img
                        className='w-full h-90h object-cover rounded-lg' 
                        src='https://images.unsplash.com/photo-1576867757603-05b134ebc379?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
                        />
                    </Grid>
                </Grid>
            </div>
            <div className="pt-3 pb-7 flex flex-col space-between">
                <h1 className="text-4xl font-semibold">
                    The Hungry Hippo
                </h1>
                <p className="text-grey-500 flex items-center gap-3">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. 
                    Dolores at enim explicabo impedit error ratione est. Adipisci quisquam maiores enim ex, 
                    asperiores unde doloribus assumenda nostrum dolorem illo soluta dicta?
                </p>
                <span className='mt-3'>
                    <LocationOnIcon/><span>Chennai,India</span>
                </span><br/>
                <span>
                    <CalendarTodayIcon/> Mon-Sun: 9:00 AM - 9:00 PM (Today)
                </span>
            </div>
        </section>
        <Divider/>
        <section className='pt-[2rem] lg:flex relative'>
            <div className='space-y-10 lg:w-[20%] filter'>
            <div className='box space-y-5 lg:sticky top-28'>
                <div className='p-5 shadow-md rounded-lg'>
                    <Typography variant='h6' sx={{paddingBottom:'1rem'}}>
                        <p>Food Type</p>   
                    </Typography>

                    <FormControl className='py-10 space-y-5' component={'fieldset'}>
                        <RadioGroup onChange={handleFilter} name='food_type' value={foodType}>
                            {foodTypes.map((item)=>(<FormControlLabel 
                            key={item.value}
                            value={item.value} 
                            control={<Radio/>} 
                            label={<span>{item.label}</span>} 
                            sx={{ fontFamily: 'Poppins, sans-serif'}}
                            />))}
                        </RadioGroup>
                    </FormControl>
                </div>
                <Divider/>
                <div className='p-5 shadow-md rounded-lg'>
                    <Typography variant='h6' sx={{paddingBottom:'1rem'}}>
                        <span>Food Category</span>
                    </Typography>

                    <FormControl className='py-10 space-y-5' component={'fieldset'}>
                        <RadioGroup onChange={handleFilter} name='food_type' value={foodType}>
                            {categories.map((item)=><FormControlLabel 
                            key={item}
                            value={item} 
                            control={<Radio/>} 
                            label={<span>{item}</span>}
                            sx={{ fontFamily: 'Poppins, sans-serif'}}
                            />)}

                        </RadioGroup>
                    </FormControl>
                </div>
            </div>
            </div>

            <div className='space-y-5 lg:w-[80%] lg:pl-10'>
                {menu.map((item)=>(
                    <div>
                        <MenuCard/>
                    </div>

                ))}
            </div>
        </section>
    </div>
  )
}

export default RestaurantDetails