import axios from "axios";
import authHeader from "../auth/AuthHeader";

const API_URL = "http://localhost:8080/";

const getAllUsersBesideSelf = () => {
    return axios.get(API_URL + "user/all/others", { headers: authHeader() });
};

const getUserChats = () => {
    return axios.get(API_URL + "chat/all", { headers: authHeader() });
};

const getChatMessages = (chatId) => {
    return axios.get(API_URL + `chat/${chatId}/messages`, {});
}


export const ApiCall = {
    getAllUsersBesideSelf,
    getUserChats,
    getChatMessages
};