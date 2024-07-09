/** @type {import('tailwindcss').Config} */
export const content = [
  "./src/**/*.{js,jsx,ts,tsx}",
];
export const theme = {
  extend: {
    fontFamily: {
      
      sans: ["Roboto", "sans-serif"],
      
    },
    textAlign:{
      justify: 'justify',
    },
    
  },
};
export const plugins = [];