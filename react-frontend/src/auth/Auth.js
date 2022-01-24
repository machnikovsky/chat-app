import axios from "axios";
import authHeader from "../auth/AuthHeader";

const API_URL = "http://localhost:8080/auth/";



const register = (username,
                  email,
                  password,
                  firstName,
                  lastName) => {
  return axios.post(API_URL + "register", {
    username,
    email,
    password,
    firstName,
    lastName,
  });
};

const login = (username, password) => {
  return axios
    .post(API_URL + "authenticate", {
      username,
      password,
    })
    .then((response) => {
      if (response.data.jwt) {
        localStorage.setItem("JwtToken", JSON.stringify(response.data));
        localStorage.setItem("user", username);
      }
      return username;
    });
};

const logout = () => {
  localStorage.removeItem("JwtToken");
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return axios.get(API_URL + 'username', { headers: authHeader() })
  .then(res => {
    return res.data;
  })
  .catch(err => {
    console.log("Error getting username: ", err.message)
  })
  ;
};

const Auth = {
  register,
  login,
  logout,
  getCurrentUser,
};

export default Auth;