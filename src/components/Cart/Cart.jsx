import { Box, Button, Card, Divider, Grid, Modal, TextField, Typography } from '@mui/material'
import React from 'react'
import CartItem from './CartItem'
import AddressCart from './AddressCart'
import LocationOn from '@mui/icons-material/LocationOn'
import { AddLocationOutlined } from '@mui/icons-material'
import { ErrorMessage, Field, Form, Formik } from 'formik'
// import * as Yup from 'yupp'
const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        outline:'none',
        bgcolor: 'background.paper',
        boxShadow: 24,
        p: 4,
      };

export const Cart = () => {
        const creatOrderUsingSelectedAddress=()=>{
                console.log("Order Created")
        }
        const handleOpenAddressModal=()=>setOpen(true)
        const [open, setOpen] = React.useState(false);
  
  const handleClose = () => setOpen(false);
  const handleSubmit=(value)=>{console.log(value)}
  const initialValues={
        streedAddress:"",
        city:"",
        state:"",
        pincode:""
  }
//   const validationSchema= Yup.object.shape({
//         streedAddress:Yup.string().required('Street address is required'),
//         city:Yup.string().required('city is required'),
//         state:Yup.required('State is required'),
//         pincode:Yup.string().required('Pincode is required')
//   })
  return (
    <>
        <main className='lg:flex justify-between'>
<section className='lg:w-[30] space-y-6 lg:min-h-screen pt-10'>
    {[1,1].map((item)=><CartItem/>)}
    <Divider/>
    <div className='billDetails px-5 text-sm'>
        <p className='font-extralight py-5 text-black'>Bill Details</p>
        <div className='space-y-3'>
            <div className='flex justify-between text-gray-600'>
                    <p>Item Total</p>
                    <p>₹399</p>
            </div>
            <div className='flex justify-between text-gray-600'>
                    <p>Delivery charges</p>
                    <p>₹40</p>
            </div>
            <div className='flex justify-between text-gray-600'>
                    <p>Platform charges</p>
                    <p>₹21</p>
            </div>
            <div className='flex justify-between text-gray-600'>
                    <p>GST and Restauramt charges</p>
                    <p>₹42</p>
            </div>
            <Divider/>
            <div className='flex justify-between text-black'>
                    <p>Total Payable</p>
                    <p>₹502</p>
            </div>
        </div>
    </div>
</section>
<Divider orientation='vertical' flexItem/>
<section className='lg:w-[70%] flex justify-center px-5 pb-10 lg:pb-0'>
        <div>
                <h1 className='text-center font-semibold text-2xl py-10 '>
                        Choose Delivery Address
                </h1>
                <div className='flex gap-5 flex-wrap justify-center'>
                        {[1,1,1].map((item)=><AddressCart handleSelectAddress={creatOrderUsingSelectedAddress} item={item}  showButton={true}/>
                        )}
                        <Card className='flex gap-5 w-64 p-5'>
                        <AddLocationOutlined/>
        <div className='space-y-3 text-gray-500 justify-center align-middle'>
        
            <h1 className='font-semibold text-lg text-black'>Add New Address</h1>
            
             <Button variant='outlined' fullWidth onClick={handleOpenAddressModal}>Add</Button>
            
        </div>
        
    </Card>
                </div>
        </div>


</section>
        </main>
        <Modal
  open={open}
  onClose={handleClose}
  aria-labelledby="modal-modal-title"
  aria-describedby="modal-modal-description"
>
  <Box sx={style}>
    <Formik
    initialValues={initialValues}
    //validationSchema={validationSchema}
    onSubmit={handleSubmit}>
        <Form>
        <Grid container spacing={2}>
                <Grid item xs={12}>
                        <Field 
                        as={TextField}
                        name='streetAddress'
                        label='Street Address'
                        fullWidth
                        variant='outlined'
                        // error={!ErrorMessage('StreetAddress')}
                        // helperText={
                        //         <ErrorMessage>
                        //                 {(msg)=><span className='text-red-600'>{msg}</span>}
                        //         </ErrorMessage>
                        // }
                        />
                        

                </Grid>
                
                <Grid item xs={12}>
                        <Field 
                        as={TextField}
                        name='city'
                        label='City'
                        fullWidth
                        variant='outlined'
                        // error={!ErrorMessage('StreetAddress')}
                        // helperText={
                        //         <ErrorMessage>
                        //                 {(msg)=><span className='text-red-600'>{msg}</span>}
                        //         </ErrorMessage>
                        // }
                        />
                        

                </Grid>
                <Grid item xs={12}>
                        <Field 
                        as={TextField}
                        name='state'
                        label='State'
                        fullWidth
                        variant='outlined'
                        // error={!ErrorMessage('StreetAddress')}
                        // helperText={
                        //         <ErrorMessage>
                        //                 {(msg)=><span className='text-red-600'>{msg}</span>}
                        //         </ErrorMessage>
                        // }
                        />
                        

                </Grid>
                <Grid item xs={12}>
                        <Field 
                        as={TextField}
                        name='pincode'
                        label='Pincode'
                        fullWidth
                        variant='outlined'
                        // error={!ErrorMessage('StreetAddress')}
                        // helperText={
                        //         <ErrorMessage>
                        //                 {(msg)=><span className='text-red-600'>{msg}</span>}
                        //         </ErrorMessage>
                        // }
                        />
                        

                </Grid>
                <Grid item xs={12}>
                        <Button fullWidth variant='contained' type='submit' color='primary'>Deliver here</Button>

                </Grid>

        </Grid>
        </Form>
    </Formik>
  </Box>
</Modal>
    </>
  )
}
