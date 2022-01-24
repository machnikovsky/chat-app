import { Link } from "react-router-dom"
import logo from '../assets/placeholder.png'
import username from '../assets/username_logo.png'
import password_logo from '../assets/password_logo.png'
import button from '../assets/login_button.png'
import {useContext, useState} from "react"
import UserContext from "../auth/UserContext";
import Auth from "../auth/Auth";
import { useNavigate } from "react-router-dom";



const Login = () => {

    const [login, setLogin] = useState('');
    const [password, setPasword] = useState('');
    const [badCredentials, setBadCredentials] = useState(false);
    const {user, setUser} = useContext(UserContext);
    const navigate = useNavigate();

    const handleLogin = (e) => {
        e.preventDefault();

        if (login === '' || password === '') {
            setBadCredentials(true);
            return;
        }

        //once Spring Security and JWT are implemented,
        //this line will set JWT in LocalStorage

        //something like this
        // Auth.login(login, password)
        //     .then(res => {
        //         setUser(res.username);
        //     });

        setUser(login);
        navigate('/chats');
    }

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
                                <input
                                    type="text"
                                    id="username"
                                    name="username"
                                    value={login}
                                    onChange={(e) => setLogin(e.target.value)}
                                />
                            </div>
                        </div>
                        <div className="password login_div">   
                            <label for="password">Password</label>
                            <div className="input-div">
                                <img src={password_logo} alt="" />
                                <input
                                    type="password"
                                    id="password"
                                    name="password"
                                    value={password}
                                    onChange={(e) => setPasword(e.target.value)}
                                />
                            </div>
                        </div>

                        <button className="login-button" onClick={handleLogin}>
                            <img src={button}/>
                        </button>
                        { badCredentials && <div className={"bad-credentials"}>Wprowadź poprawne dane.</div>}
                    </form>

                </div>
            </div>
        </div>
    )
}

export default Login