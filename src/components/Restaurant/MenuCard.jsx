import { Accordion, AccordionDetails, AccordionSummary, Button } from '@mui/material'
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import React from 'react'

const MenuCard = () => {
  return (
    <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1-content"
          id="panel1-header"
        >
          <div className="lg:flex items-center justify between">
            <div className='rounded-lg'>
              <img  className="w-[13.5rem] h-[10rem] object-cover rounded-lg" src="https://images.unsplash.com/photo-1561758033-d89a9ad46330?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
               alt="" />
               <div className="space-y-1 lg:space-y-5 lg:max-w-2xl">
                  <p className='font-semibold text-xl'>Hamburger and Fies</p>
                  <p>₹399</p>
                  <p className='text-grey-400'>Juicy hamburger with crispy fries,
                  perfect comfort food combination.</p>
               </div>
            </div>
          </div>
          
        </AccordionSummary>
        <AccordionDetails>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse
          malesuada lacus ex, sit amet blandit leo lobortis eget.
<br/><br />
          <Button variant='contained' type='submit' className='text-white'>{true?'Add to Cart':'Out of Stock'}</Button>
        </AccordionDetails>
      </Accordion>
  )
}

export default MenuCard