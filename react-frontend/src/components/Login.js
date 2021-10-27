import { Link } from "react-router-dom"
import logo from '../assets/placeholder.png'
import username from '../assets/username_logo.png'
import password from '../assets/password_logo.png'
import button from '../assets/login_button.png'


const Login = () => {
    return(
        <div className="login-container">
            <div className="login-window">
                <div className = "logo-container">
                    <img src={logo} alt="x" />
                </div>
                <div className = "form-container">
                    <h1>Welcome</h1>
                    <form className="login-form">
                        <div className="username login_div">
                            <label for="username">Username</label>
                            <div className="input-div">
                                <img src={username} alt="" />
                                <input type="text" id="username" name="username"/>
                            </div>
                        </div>
                        <div className="password login_div">   
                            <label for="password">Password</label>
                            <div className="input-div">
                                <img src={password} alt="" />
                                <input type="password" id="password" name="password"/>
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