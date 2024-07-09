import logo from './logo.svg';
import './App.css';
import './index.css';
import { Navbar } from './components/Navbar/Navbar';
// import { ThemeProvider } from '@emotion/react';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { lightTheme } from './theme/LightTheme';
import { CssBaseline } from '@mui/material';
import { Home } from './components/Home/Home';
import RestaurantDetails from './components/Restaurant/RestaurantDetails';
import {Cart} from './components/Cart/Cart';
import AddressCart from './components/Cart/AddressCart';
import Profile from './components/Profile/Profile';

const theme = createTheme({
  typography: {
    fontFamily: 'Poppins, sans-serif',
  },
});
function App() {
  return (
    <ThemeProvider theme={lightTheme}>


   <Navbar/>
   {/* <Profile/> */}
   {/* <Cart/> */}
   {/* <RestaurantDetails/>*/}
   <Home/> 
   <CssBaseline/>
   </ThemeProvider>
  );
}

export default App;
