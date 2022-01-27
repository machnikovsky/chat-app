import Logo from "../../assets/errors/logo-black.png"
import Image from "../../assets/errors/sad-woman.png"
import "../../styles/errors/Error.scss"

function Error(){
     return(
         <div className="general-error-site">
             <img className="general-error-site__logo" src={Logo}/>
             <div className="general-error-site__text-block">
                 <span className="general-error-site__text-block__title">404</span>
                 <span className="general-error-site__text-block__cause">Page not found</span>
                 <span className="general-error-site__text-block__recommendation">return to the home page</span>
             </div>
             <img className="general-error-site__image" src={Image}/>
         </div>
     )
}
export default Error;