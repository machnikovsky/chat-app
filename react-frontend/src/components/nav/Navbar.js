import React from "react";
import { useNavigate } from "react-router-dom";
import Logo from "../../assets/errors/logo-black.png";

const Navbar = () => {
  const navigate = useNavigate();

  return (
    <div className="navbar-wrapper">
      <div className="navbar-container">
        <button
          className="navbar-logo-button"
          onClick={() => {
            navigate("/chats");
          }}
        >
          <img className="navbar-logo" src={Logo} a="logo" />
        </button>
      </div>
      <div className="navbar-container navbar-grid-links">
        <div className="navbar-element">
          <button
            className={
              window.location.pathname.includes("/chats")
                ? "navbar-button navbar-button-page"
                : "navbar-button"
            }
            onClick={() => {
              navigate("/chats");
            }}
          >
            Rozmowy
          </button>
        </div>
        <div className="navbar-element">
          <button
            className={
              window.location.pathname.includes("/find")
                ? "navbar-button navbar-button-page"
                : "navbar-button"
            }
            onClick={() => {
              navigate("/find");
            }}
          >
            Wyszukaj
          </button>
        </div>
        <div className="navbar-element">
          <button
            className={
              window.location.pathname.includes("/profile")
                ? "navbar-button navbar-button-page"
                : "navbar-button"
            }
            onClick={() => {
              navigate("/profile");
            }}
          >
            Mój profil
          </button>
        </div>
      </div>
    </div>
  );
};

export default Navbar;
