import { Link } from "react-router-dom"
import username from '../assets/username_logo.png'
import password from '../assets/password_logo.png'
import button from '../assets/login_button.png'


const Register = () => {
    return(
        <div className="register-container">
            <div className="register-window">
                <div className = "register-container">
                    <img src={logo} alt="x" />
                </div>
                <div className = "form-container">
                    <h1>Welcome</h1>
                    <form className="register-form">
                        <div className="username register_div">
                            <label for="username">Username</label>
                            <div className="input-div">
                                <img src={username} alt="" />
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
                                <img src={password} alt="" />
                                <input type="password" id="password" name="password"/>
                            </div>
                        </div>
                        <div className="password register_div">   
                            <label for="password">Repeat password</label>
                            <div className="input-div">
                                <img src={password} alt="" />
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
                            <div className="input-div">
                                <input type="radio" id="gender" name="gender" value="Male"/>
                            </div>
                            <label for="gender"> Male</label>
                            <div className="input-div">
                                <input type="radio" id="gender" name="gender" value="Famale"/>
                            </div>
                            <label for="gender"> Famale</label>
                            <div className="input-div">
                                <input type="radio" id="gender" name="gender" value="Other"/>
                            </div>
                            <label for="gender"> Other</label>
                        </div>
                        <Link to="/chats"><input type="image" src={button}></input></Link>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default Login