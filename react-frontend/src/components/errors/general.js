import Logo from "../../assets/errors/logo-black.png"
import Image from "../../assets/errors/sad-man.png"
import "../../styles/errors/Error.scss"
import {useNavigate} from "react-router-dom"
import React from "react";

function General(){

    const navigate = useNavigate();

     return(
         <div className="general-error-site">
             <img className="general-error-site__logo" src={Logo}/>
             <div className="general-error-site__text-block">
                 <span className="general-error-site__text-block__title">Oh no!</span>
                 <span className="general-error-site__text-block__cause">SOMETHING WENT WRONG</span>
                 <span className="general-error-site__text-block__description">An unexpected error has occured</span>
                 <span onClick={() => localStorage.getItem("user") ? navigate("/chats") : navigate("/")} className="general-error-site__text-block__recommendation">return to the home page</span>
             </div>
             <img className="general-error-site__image" src={Image}/>
         </div>
     )
}
export default General;