import { Link } from "react-router-dom"
import logo from '../../assets/placeholder.png'
import signup_button from '../../assets/signup_button.png'


const Register = () => {

    const handleRegister = (e) => {
        return;
    }
    
    return(
        <div className="register-container">
             <div className="register-window">
                <div className = "logo-container">
                    <img src={logo} alt="x" />
                </div>
                <div className = "form-container">
                    <h1>Welcome</h1>
                    <form className="register-form">
                        <div className="username register_div">
                            <label for="username">Username</label>
                            <div className="input-div">
                                <input type="text" id="username" name="username"/>
                            </div>
                        </div>
                        <div className="email register_div">   
                            <label for="email">email</label>
                            <div className="input-div">
                                <input type="email" id="email" name="email"/>
                            </div>
                        </div>
                        <div className="name register_div">   
                            <label for="firstname">firstname</label>
                            <div className="input-div">
                                <input type="text" id="firstname" name="firstname"/>
                            </div>
                        </div>
                        <div className="name register_div">   
                            <label for="lastname">lastname</label>
                            <div className="input-div">
                                <input type="text" id="lastname" name="lastname"/>
                            </div>
                        </div>
                        <div className="password register_div">   
                            <label for="password">Password</label>
                            <div className="input-div">
                                <input type="password" id="password" name="password"/>
                            </div>
                        </div>
                        <div className="password register_div">   
                            <label for="password">Repeat password</label>
                            <div className="input-div">
                                <input type="password" id="repeated_password" name="repeated_password"/>
                            </div>
                        </div>
                        <div className="number register_div">   
                            <label for="phone_number">phone number</label>
                            <div className="input-div">
                                <input type="text" id="phone_number" name="phone_number"/>
                            </div>
                        </div>
                        <div className="gender register_div">
                            <input type="radio" id="gender" name="gender" value="Male"/>
                            <label for="gender"> Male</label>
                            <input type="radio" id="gender" name="gender" value="Famale"/>
                            <label for="gender"> Famale</label>
                            <input type="radio" id="gender" name="gender" value="Other"/>
                            <label for="gender"> Other</label>
                        </div>

                        <button className="register-button" onClick={handleRegister}>
                            <img src={signup_button}/>
                        </button>
                    </form>
                </div> 
            </div> 
        </div>
    )
}

export default Register