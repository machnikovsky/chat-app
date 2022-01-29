import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { Link } from "react-router-dom"
import logo from '../../assets/placeholder.png'
import signup_button from '../../assets/signup_button.png'
import Auth from "../../auth/Auth";



const Register = () => {

    const navigate = useNavigate();

    const [username,setUsername] = useState('');
    const [email,setEmail] = useState('');
    const [firstname,setFirstName] = useState('');
    const [lastname, setLastName] = useState('');
    const [password,setPassword] = useState('');
    const [repeated_password,setRepeatedPassword] = useState('');
    const [phone_number,setPhoneNumber] = useState('');
    const [gender,setGender] = useState('OTHER');
    const userRole = 'USER';
    const [invalidInput, setInvalidInput] = useState(false);
    const [passwordsError, setPasswordError] = useState(false);
    
    let registerUser = () => {
        Auth.register(username,firstname,lastname,email,password,phone_number,gender,userRole,null);
        //navigate('/');
    }

    const handleRegister = (e) => {
        e.preventDefault();
        
        if(password !== repeated_password) {
            setPasswordError(true);
            setInvalidInput(false);
            return;
        }

        if(username === '' || email === '' || firstname === '' || lastname === '' ||
            password === '' || repeated_password === '' || phone_number === '' || 
            gender === '') {
                setPasswordError(false);
                setInvalidInput(true);
                return;
        }

        registerUser();
    }

    return(
        <div className="register-container">
             <div className="register-window">
                <div className = "logo-container" onClick={(e) => navigate('/')}>
                    <img src={logo} alt="x" />
                </div>
                <div className = "form-container">
                    <h1>Welcome</h1>
                    <form className="register-form">
                        <div className="username register_div">
                            <label for="username">Username</label>
                            <div className="input-div">
                                <input 
                                    type="text"
                                    id="username"
                                    name="username"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}/>
                            </div>
                        </div>
                        <div className="email register_div">   
                            <label for="email">email</label>
                            <div className="input-div">
                                <input
                                    type="email"
                                    id="email"
                                    name="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}/>
                            </div>
                        </div>
                        <div className="name register_div">   
                            <label for="firstname">firstname</label>
                            <div className="input-div">
                                <input 
                                    type="text"
                                    id="firstname"
                                    name="firstname"
                                    value={firstname}
                                    onChange={(e) => setFirstName(e.target.value)}/>
                            </div>
                        </div>
                        <div className="name register_div">   
                            <label for="lastname">lastname</label>
                            <div className="input-div">
                                <input 
                                    type="text"
                                    id="lastname"
                                    name="lastname"
                                    value={lastname}
                                    onChange={(e) => setLastName(e.target.value)}/>
                            </div>
                        </div>
                        <div className="password register_div">   
                            <label for="password">Password</label>
                            <div className="input-div">
                                <input
                                    type="password"
                                    id="password"
                                    name="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}/>
                            </div>
                        </div>
                        <div className="password register_div">   
                            <label for="password">Repeat password</label>
                            <div className="input-div">
                                <input 
                                    type="password"
                                    id="repeated_password"
                                    name="repeated_password"
                                    value={repeated_password}
                                    onChange={(e) => setRepeatedPassword(e.target.value)}/>
                            </div>
                        </div>
                        <div className="number register_div">   
                            <label for="phone_number">phone number</label>
                            <div className="input-div">
                                <input 
                                    type="tel"
                                    id="phone_number"
                                    name="phone_number"
                                    value={phone_number}
                                    onChange = {(e) => setPhoneNumber(e.target.value)}
                                    />
                            </div>
                        </div>
                        <div className="gender register_div">
                            <input
                                type="radio"
                                id="gender-male"
                                name="gender"
                                value="MALE"
                                checked={gender === 'MALE'} 
                                onChange={(e) => setGender(e.target.value)} />
                            <label for="gender"> Male</label>
                            <input 
                                type="radio"
                                id="gender-famale"
                                name="gender"
                                value="FAMALE"
                                checked={gender === 'FAMALE'} 
                                onChange={(e) => setGender(e.target.value)} />
                            <label for="gender"> Famale</label>
                            <input 
                                type="radio"
                                id="gender-other"
                                name="gender"
                                value="OTHER"
                                checked={gender === 'OTHER'} 
                                onChange={(e) => setGender(e.target.value)} />
                            <label for="gender"> Other</label>
                        </div>

                        <button className="register-button" onClick={handleRegister}>
                            <img src={signup_button}/>
                        </button>

                        { invalidInput && <div className={"bad-credentials"}>Wprowadź wszystkie dane.</div>}
                        { passwordsError && <div className={"bad-credentials"}>Hasła się różnia.</div>}
                    </form>
                </div> 
            </div> 
        </div>
    )
}

export default Register