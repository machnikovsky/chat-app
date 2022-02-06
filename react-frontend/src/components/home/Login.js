import logo from "../../assets/errors/logo-black.png";
import username from "../../assets/username_logo.png";
import password_logo from "../../assets/password_logo.png";
import { useContext, useState } from "react";
import UserContext from "../../auth/UserContext";
import Auth from "../../auth/Auth";
import { Link, useNavigate } from "react-router-dom";

const Login = () => {
  const [login, setLogin] = useState("");
  const [password, setPasword] = useState("");
  const [invalidInput, setInvalidInput] = useState(false);
  const [badCredentials, setBadCredentials] = useState(false);
  const { user, setUser } = useContext(UserContext);
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();

    if (login === "" || password === "") {
      setBadCredentials(false);
      setInvalidInput(true);
      return;
    }

    Auth.login(login, password)
      .then((username) => {
        console.log(username);
        setUser(username);
        navigate("/chats");
      })
      .catch((err) => {
        console.log(err.message);
        setUser(null);
        setInvalidInput(false);
        setBadCredentials(true);
      });
  };

  return (
    <div className="login-container">
      <div className="login-window">
        <div className="logo-container">
          <img className="logo" src={logo} alt="x" />
        </div>
        <div className="form-container">
          <h1>Zaloguj się</h1>
          <form className="login-form form-wrapper">
            <div className="input-field-wrapper">
              <label className="input-label" for="username">
                Nazwa użytkownika
              </label>
              <div className="">
                <img src={username} alt="" />
                <input
                  className="input-field"
                  type="text"
                  id="username"
                  name="username"
                  value={login}
                  onChange={(e) => setLogin(e.target.value)}
                />
              </div>
            </div>
            <div className="input-field-wrapper">
              <label className="input-label" for="password">
                Hasło
              </label>
              <div className="">
                <img src={password_logo} alt="" />
                <input
                  className="input-field"
                  type="password"
                  id="password"
                  name="password"
                  value={password}
                  onChange={(e) => setPasword(e.target.value)}
                />
              </div>
            </div>

            <div className="center-wrapper">
              <button className="form-button" onClick={handleLogin}>
                ZALOGUJ SIĘ
              </button>
            </div>

            {invalidInput && (
              <div className={"bad-credentials"}>Wprowadź wszystkie dane.</div>
            )}
            {badCredentials && (
              <div className={"bad-credentials"}>
                Login lub hasło niepoprawne.
              </div>
            )}
          </form>

          <div className="register-text-wrapper">
            Nie posiadasz konta?
            <Link to="/register">
              <div className="register-link">Zarejestruj się</div>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
