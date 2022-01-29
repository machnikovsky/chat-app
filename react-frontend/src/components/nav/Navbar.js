import React from 'react';
import { useNavigate } from 'react-router-dom';

const Navbar = () => {

  const navigate = useNavigate();

  return (
        <div className='navbar-container'>
            <div className='navbar-element'>
              <button className="navbar-button" onClick={() => {navigate('/chats')}}>Rozmowy</button>
            </div>
            <div className='navbar-element'>
              <button className="navbar-button" onClick={() => {navigate('/find')}}>Wyszukaj</button>
            </div>
            <div className='navbar-element'>
              <button className="navbar-button" onClick={() => {navigate('/profile')}}>Profil</button>
            </div>
         </div>
  );
};

export default Navbar;
