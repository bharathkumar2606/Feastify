import React from 'react';
import Slider from 'react-slick';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { topMeal } from './topMeal';
import { CarouselItem } from './CarouselItem';

export const MultiItemCarousel = () => {
  var settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 2,
    autoplay: true,
    autoPlaySpeed:3000
    
  };
  return (
    <div>
      <Slider {...settings}>
        {topMeal.map((item, index) => (
          <div key={index}>
            <CarouselItem image={process.env.PUBLIC_URL + '/' + item.image} title={item.title} />
          </div>
        ))}
      </Slider>
    </div>
  );
};

//export default MultiItemCarousel;
