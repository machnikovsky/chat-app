import React, { useState, useEffect } from "react";
import "../../styles/_profile.scss";
import Navbar from "../nav/Navbar.js";
import Rectangle1 from "../../assets/svg/rectangle1.svg";
import Rectangle2 from "../../assets/svg/rectangle2.svg";
import Rectangle3 from "../../assets/svg/rectangle3.svg";
import Rectangle4 from "../../assets/svg/rectangle4.svg";
import ProfilePNG from "../../assets/not_found.jpeg";
import { ApiCall } from "../../api/ApiCall.js";

const Profile = () => {
  const [user, setUser] = useState({});
  const [profileImage, setProfileImage] = useState(null);

  const handleProfileImage = (e) => {
    setProfileImage(e.target.files[0]);

    //sentProfileImage();
  };

  const sentProfileImage = () => {
    console.log(profileImage);
    const imageData = new FormData();
    imageData.append("imageFile", profileImage);

    for (var value of imageData.values()) {
      console.log(value);
    }

    ApiCall.changeProfileImage(imageData)
      .then((res) => {
        console.log("Sent image ", res);
      })
      .catch((err) => {
        console.log("Error: ", err.message);
      });
  };

  useEffect(() => {
    ApiCall.me()
      .then((res) => {
        console.log("Received user info: ", res);
        setUser(res);
      })
      .catch((err) => {
        console.log("Error getting user info: ", err.message);
        setUser({});
      });
  }, []);

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
            <form>
              <input
                accept="image/*"
                type="file"
                id="profile-image-input"
                onChange={handleProfileImage}
              />
            </form>
            <img
              src={ProfilePNG}
              className="profile-block__layer__profile-window__profile-image"
            />
            <span className="profile-block__layer__profile-window__username">
              {user.username}
            </span>
            <span className="profile-block__layer__profile-window__my-profile-text">
              My profile
            </span>
            <hr className="profile-block__layer__profile-window__line"></hr>
            <div className="profile-block__layer__profile-window__inline-block">
              <div className="profile-block__layer__profile-window__inline-block__fullname-block">
                <span className="profile-block__layer__profile-window__inline-block__fullname-block__full-name">
                  {user.firstName + " " + user.lastName}
                </span>
                <hr className="profile-block__layer__profile-window__line"></hr>
              </div>
              <div className="profile-block__layer__profile-window__inline-block__phone-block">
                <span className="profile-block__layer__profile-window__inline-block__phone-block__phone">
                  +48 {user.phoneNumber}
                </span>
                <hr className="profile-block__layer__profile-window__line"></hr>
              </div>
            </div>
            <span className="profile-block__layer__profile-window__email">
              {user.email}
            </span>
            <hr className="profile-block__layer__profile-window__line"></hr>
          </div>
        </div>
        <button onClick={sentProfileImage}> send </button>
      </div>
    </>
  );
};
export default Profile;
