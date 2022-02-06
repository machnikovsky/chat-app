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
  return axios.get(API_URL + `chat/${chatId}/messages`, {
    headers: authHeader(),
  });
};

const me = async () => {
  return await axios
    .get(API_URL + "auth/me", { headers: authHeader() })
    .then((res) => {
      return res.data;
    })
    .catch((err) => {
      console.log("Authentication error", err.response.data);
    });
};

const getAndSetQueriedListWithNewQuery = (query, set) => {
  return axios.post(
    API_URL + `user/query/${query}`,
    {},
    { headers: authHeader() }
  );
};

const getChatDtoOrCreateNewAndRetrieveIfNotPresent = (userId) => {
  return axios.get(API_URL + `chat/send/${userId}`, { headers: authHeader() });
};

const createGroupChat = (createChatRequestDTO) => {
  return axios.post(API_URL + `chat/group/new`, createChatRequestDTO, { headers: authHeader() });
}

const changeProfileImage = async (image) =>  {
    return await axios.post(API_URL + `user/profilepicture`, image);
}

export const ApiCall = {
  getAllUsersBesideSelf,
  getUserChats,
  getChatMessages,
  getAndSetQueriedListWithNewQuery,
  getChatDtoOrCreateNewAndRetrieveIfNotPresent,
  me,
  createGroupChat,
  changeProfileImage
};
