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
                                <input type="password" id="repeated_password" name="password"/>
                            </div>
                        </div>
                        <Link to="/chats"><input type="image" src={button}></input></Link>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default Login