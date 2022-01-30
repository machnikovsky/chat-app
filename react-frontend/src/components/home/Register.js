import { Link } from "react-router-dom";
import logo from "../../assets/placeholder.png";
import username from "../../assets/username_logo.png";
import password from "../../assets/password_logo.png";
import button from "../../assets/login_button.png";

const Register = () => {
  return (
    <div className="register-container">
      <div className="register-window">
        <div className="logo-container">
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
                <img src={username} alt="" />
                <input
                  className="input-field"
                  type="text"
                  id="username"
                  name="username"
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
                />
              </div>
            </div>
            <div className="input-field-wrapper">
              <label className="input-label" for="password">
                Hasło
              </label>
              <div className="input-div">
                <img src={password} alt="" />
                <input
                  className="input-field"
                  type="password"
                  id="password"
                  name="password"
                />
              </div>
            </div>
            <div className="input-field-wrapper">
              <label className="input-label" for="password">
                Powtórz hasło
              </label>
              <div className="input-div">
                <img src={password} alt="" />
                <input
                  className="input-field"
                  type="password"
                  id="repeated_password"
                  name="repeated_password"
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
                  type="text"
                  id="phone_number"
                  name="phone_number"
                />
              </div>
            </div>
            <div className="radio-field-wrapper">
              <input type="radio" id="male" name="gender" value="Male" />
              <label className="radio-btn" for="male">
                Mężczyzna
              </label>
              <input type="radio" id="female" name="gender" value="Famale" />
              <label for="female">Kobieta </label>
              <input type="radio" id="other" name="gender" value="Other" />
              <label for="other">Inne</label>
            </div>
            <div className="center-wrapper form-button-span-2 ">
              <button className="form-button">ZAREJESTRUJ SIĘ</button>
            </div>
          </form>
          <div className="register-text-wrapper">
            <Link to="/">
              <div className="register-link">Wróć</div>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
