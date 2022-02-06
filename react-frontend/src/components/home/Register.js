import logo from "../../assets/placeholder.png";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Auth from "../../auth/Auth";

const Register = () => {
  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [firstname, setFirstName] = useState("");
  const [lastname, setLastName] = useState("");
  const [password, setPassword] = useState("");
  const [repeated_password, setRepeatedPassword] = useState("");
  const [phone_number, setPhoneNumber] = useState("");
  const [gender, setGender] = useState("OTHER");
  const userRole = "USER";
  const [invalidInput, setInvalidInput] = useState(false);
  const [passwordsError, setPasswordError] = useState(false);

  let registerUser = () => {
    Auth.register(
      username,
      firstname,
      lastname,
      email,
      password,
      phone_number,
      gender,
      userRole,
      null
    );
    navigate("/");
  };

  const handleRegister = (e) => {
    e.preventDefault();

    if (password !== repeated_password) {
      setPasswordError(true);
      setInvalidInput(false);
      return;
    }

    if (
      username === "" ||
      email === "" ||
      firstname === "" ||
      lastname === "" ||
      password === "" ||
      repeated_password === "" ||
      phone_number === "" ||
      gender === ""
    ) {
      setPasswordError(false);
      setInvalidInput(true);
      return;
    }

    registerUser();
  };

  return (
    <div className="register-container">
      <div className="register-window">
        <div className="logo-container" onClick={(e) => navigate("/")}>
          <img src={logo} alt="x" />
        </div>
        <div className="form-container">
          <h1>Rejestracja</h1>
          <form className="register-form-grid">
            <div className="input-field-wrapper">
              <label className="input-label" for="username">
                Nazwa użytkownika
              </label>
              <div className="input-div">
                <input
                  className="input-field"
                  type="text"
                  id="username"
                  name="username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
              </div>
            </div>
            <div className="input-field-wrapper">
              <label className="input-label" for="email">
                Email
              </label>
              <div className="input-div">
                <input
                  className="input-field"
                  type="email"
                  id="email"
                  name="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>
            </div>
            <div className="input-field-wrapper">
              <label className="input-label" for="firstname">
                Imię
              </label>
              <div className="input-div">
                <input
                  className="input-field"
                  type="text"
                  id="firstname"
                  name="firstname"
                  value={firstname}
                  onChange={(e) => setFirstName(e.target.value)}
                />
              </div>
            </div>
            <div className="input-field-wrapper">
              <label className="input-label" for="lastname">
                Nazwisko
              </label>
              <div className="input-div">
                <input
                  className="input-field"
                  type="text"
                  id="lastname"
                  name="lastname"
                  value={lastname}
                  onChange={(e) => setLastName(e.target.value)}
                />
              </div>
            </div>
            <div className="input-field-wrapper">
              <label className="input-label" for="password">
                Hasło
              </label>
              <div className="input-div">
                <input
                  className="input-field"
                  type="password"
                  id="password"
                  name="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </div>
            </div>
            <div className="input-field-wrapper">
              <label className="input-label" for="password">
                Powtórz hasło
              </label>
              <div className="input-div">
                <input
                  className="input-field"
                  type="password"
                  id="repeated_password"
                  name="repeated_password"
                  value={repeated_password}
                  onChange={(e) => setRepeatedPassword(e.target.value)}
                />
              </div>
            </div>
            <div className="input-field-wrapper">
              <label className="input-label" for="phone_number">
                Numer telefonu
              </label>
              <div className="input-div">
                <input
                  className="input-field"
                  type="tel"
                  id="phone_number"
                  name="phone_number"
                  value={phone_number}
                  onChange={(e) => setPhoneNumber(e.target.value)}
                />
              </div>
            </div>
            <div className="radio-field-wrapper">
              <input
                type="radio"
                id="male"
                name="gender"
                value="MALE"
                onChange={(e) => setGender(e.target.value)}
              />
              <label className="radio-btn" for="male">
                Mężczyzna
              </label>
              <input
                type="radio"
                id="female"
                name="gender"
                value="FEMALE"
                onChange={(e) => setGender(e.target.value)}
              />
              <label for="female">Kobieta </label>
              <input
                type="radio"
                id="other"
                name="gender"
                value="OTHER"
                onChange={(e) => setGender(e.target.value)}
              />
              <label for="other">Inne</label>
            </div>

            <div className="center-wrapper form-button-span-2 ">
              <button className="form-button" onClick={handleRegister}>
                ZAREJESTRUJ SIĘ
              </button>
            </div>

            {invalidInput && (
              <div className={"bad-credentials"}>Wprowadź wszystkie dane.</div>
            )}
            {passwordsError && (
              <div className={"bad-credentials"}>Hasła się różnia.</div>
            )}
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;
