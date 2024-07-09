import { Card, Chip, IconButton } from '@mui/material'
import React from 'react'
import { restaurantPics } from './restaurantPics'
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import FavoriteIcon from '@mui/icons-material/Favorite';

export const RestaurantCard = ({image,name,description}) => {
  return (
    <Card className='m-5 w-[18rem]'>
        <div className={`${true?'cursor-pointer':'cursor-not-allowed'} 
        relative`}>

            <img className='w-full h-[10rem] rounded-t-md object-cover' src='./restaurant-pics/restaurant-1.jpg' alt="" />
        <Chip
        size='small'
        className='absolute top-2 left-2'
        color={true?'success':'error'}
        label={true?'Open':'Closed'}
        />
        </div>
        <div className="p-4 textPart lg:flex w-full justify-between">
          <div className="space y-1">
            <p className="font-semibold text-lg">The Hungry Hippo</p>
            <p className="text-grey-100 text-sm">
              Lorem ipsum dolor sit, amet consectetur adipisicing elit. Velit, neque?
            </p>
          </div>
          <div>
<IconButton>
  {true? <FavoriteIcon />:<FavoriteBorderIcon />}

</IconButton>
          </div>
        </div>

    </Card>
  )
}

// import * as React from 'react';
// import Card from '@mui/material/Card';
// import CardContent from '@mui/material/CardContent';
// import CardMedia from '@mui/material/CardMedia';
// import Typography from '@mui/material/Typography';
// import { CardActionArea,IconButton} from '@mui/material';
// import FavoriteIcon from '@mui/icons-material/Favorite'
// import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';


// export default function RestaurantCard() {
//   return (
//     <Card sx={{ maxWidth: 345 }}>
//       <CardActionArea>
//         <CardMedia
//           component="img"
//           height="140"
//           image="https://images.unsplash.com/photo-1536305030588-45dc07a2a372?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
//           alt="The Hungry Hippo"
//         />
//         <CardContent>
//           <Typography gutterBottom variant="h5" component="div">
//             The Hungry Hippo
//           </Typography>
//           <Typography variant="body2" color="text.secondary">
//           Experience the vibrant flavors of India at The Hungry Hippo. Enjoy a diverse menu of traditional dishes, 
//           from aromatic curries to sizzling tandoori grills, crafted with fresh ingredients and authentic spices. 
//           Come hungry, leave happy!
//           </Typography>
//           <IconButton>
//         {true? <FavoriteIcon />:<FavoriteBorderIcon />}

//         </IconButton>
//         </CardContent>
//       </CardActionArea>
//     </Card>
//   );
// }



