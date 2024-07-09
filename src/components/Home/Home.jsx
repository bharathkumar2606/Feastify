import React from 'react'
import {MultiItemCarousel} from './MultiItemCarousel'
import {RestaurantCard} from '../Restaurant/RestaurantCard'
import './Home.css';

const restaurant=[1,1,1,1,1,1]
export const Home = () => {
  return (
    <div className='pb-10'>
        <section className="banner -z-50 relative flex flex-col-justify-center 
        items-center mb-5">
          <div className="img-container flex flex-col-justify-center items-center">

          </div>
          
        {/* <div className='w-[50vw] z-10 text-center'>
            <p className="text-2xl lg:text-6xl font-bold z-10 py-5">
                Delightful Delivery
            </p>
        </div> */}
<div className="cover absolute top-0 left-0 right-0">

</div>
        </section>
        <section>
            <MultiItemCarousel/>
        </section>
        <section className="px-5 lg:px-20 pt-5 ">
          <h1 className="text-2xl font-semibold text-grey-400 py-8">
            Order from Our Handpicked Top Restaurants
          </h1>
         
        </section>

        <section>
           <div className='flex flex-wrap items-center justify-around gap-2'>
            {
              restaurant.map((item)=><RestaurantCard/>)
            }
          </div>
        </section>
    </div>
  )
}

