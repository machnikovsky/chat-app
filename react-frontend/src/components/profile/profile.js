import React from "react";
import "../../styles/_profile.scss";
import Navbar from "../nav/Navbar.js";
import Rectangle1 from "../../assets/svg/rectangle1.svg";
import Rectangle2 from "../../assets/svg/rectangle2.svg";
import Rectangle3 from "../../assets/svg/rectangle3.svg";
import Rectangle4 from "../../assets/svg/rectangle4.svg";
import ProfilePNG from "../../assets/profile.png";

const Profile = () => {
  return (
    <>
      <Navbar />
      <div className="profile-block">
        <img src={Rectangle1} className="profile-block__rectangle1" />
        <img src={Rectangle2} className="profile-block__rectangle2" />
        <img src={Rectangle3} className="profile-block__rectangle3" />
        <img src={Rectangle4} className="profile-block__rectangle4" />
        <div className="profile-block__layer">
          <div className="profile-block__layer__profile-window">
            <img
              src={ProfilePNG}
              className="profile-block__layer__profile-window__profile-image"
            />
            <span className="profile-block__layer__profile-window__username">
              xmatixdestroyer
            </span>
            <span className="profile-block__layer__profile-window__my-profile-text">
              My profile
            </span>
            <hr className="profile-block__layer__profile-window__line"></hr>
            <span className="profile-block__layer__profile-window__last-login">
              Last login: 27 aug 2012
            </span>
            <div className="profile-block__layer__profile-window__inline-block">
              <div className="profile-block__layer__profile-window__inline-block__fullname-block">
                <span className="profile-block__layer__profile-window__inline-block__fullname-block__full-name">
                  Jan Kowalski
                </span>
                <hr className="profile-block__layer__profile-window__line"></hr>
              </div>
              <div className="profile-block__layer__profile-window__inline-block__phone-block">
                <span className="profile-block__layer__profile-window__inline-block__phone-block__phone">
                  +49 395 318 932
                </span>
                <hr className="profile-block__layer__profile-window__line"></hr>
              </div>
            </div>
            <span className="profile-block__layer__profile-window__email">
              jankowalski@gmail.com
            </span>
            <hr className="profile-block__layer__profile-window__line"></hr>
            <span className="profile-block__layer__profile-window__friends-counter">
              firends: <span style={{ color: "#44DE82" }}>431 M</span>
            </span>
          </div>
        </div>
      </div>
    </>
  );
};
export default Profile;
