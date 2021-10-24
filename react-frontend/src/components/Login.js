import { Link } from "react-router-dom"


const Login = () => {
    return(
        <div className="login-container">
            <div className="login-page">
                <h1>Login page</h1>
                <form className="login-form">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username"/>

                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password"/>

                    <Link to="/chats"><button>Login</button></Link>
                </form>
                    
                
            </div>
        </div>
    )
}

export default Login