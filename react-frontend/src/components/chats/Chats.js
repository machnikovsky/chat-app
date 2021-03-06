import { Link, useNavigate, useParams } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import profile_picture from "../../assets/profile_picture.png";
import sendButton from "../../assets/send.png";
import SockJsClient from "react-stomp";
import UserContext from "../../auth/UserContext";
import Auth from "../../auth/Auth";
import { ApiCall } from "../../api/ApiCall";
import Navbar from "../nav/Navbar";
import spinner from "../../assets/circle-notch-solid.svg";

const Chats = () => {
  const SOCKET_URL = "http://localhost:8080/chat/";
  const [messages, setMessages] = useState([]);
  const [noChats, setNoChats] = useState(true);
  const [chat, setChat] = useState("default");
  const [chats, setChats] = useState([]);
  const [currentMess, setCurrentMess] = useState("");
  const [clientRef, setClientRef] = useState(null);
  const [chatName, setChatName] = useState(null);
  const { user, setUser } = useContext(UserContext);
  const [receivers, setReceivers] = useState([
    {
      profileImage: [],
    },
  ]);
  const [userData, setUserData] = useState({
    profileImage: [],
  });
  const [image, setImage] = useState(null);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      setUserData(await ApiCall.me());
      console.log(userData);
    };

    fetchData();
    console.log(userData);
  }, []);

  useEffect(() => {
    ApiCall.getUserChats().then((res) => {
      setChats(res.data);
      console.log(res.data);
      if (res.data.length > 0) {
        changeChat(res.data[0]);
        setNoChats(false);
      }
    });
  }, []);

  useEffect(() => {
    if (chat !== "default") {
      ApiCall.getChatMessages(chat).then((res) => {
        setMessages(res.data);
      });
    }
  }, [chat]);

  const getBase64 = (file) => {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = (error) => reject(error);
    });
  };

  const handleImage = async (e) => {
    let encoded = await getBase64(e.target.files[0]);
    console.log(encoded);
    setImage(encoded);
  };

  const changeChat = (e) => {
    console.log("Selected chat: ", e);
    setChat(e.chatId);
    setChatName(e.name);
    setReceivers(e.users.filter((x) => x.username !== user));
    console.log("current chat: ", chat);
  };

  let onConnected = () => {
    console.log("Connected!!");
  };

  let onMessageReceived = (msg) => {
    console.log("New Message Received!!", msg);
    setMessages(messages.concat(msg));
    console.log("Mesages: ", messages);
  };

  let sendMessage = (e) => {
    e.preventDefault();
    if (currentMess !== "" || image != null) {
      console.log(user);
      console.log(`Sent ${currentMess} to ${chat}`);
      console.log(`Current messages: ${messages}`);
      clientRef.sendMessage(
        `/app/chat/${chat}`,
        JSON.stringify({
          chatId: chat,
          messageAuthorUsername: user,
          messageReceiversUsernames: receivers.map((x) => x.username),
          messageContent: currentMess,
          imageContent: image,
        })
      );
      setCurrentMess("");
      setImage(null);
    }
  };

  const handleLogout = () => {
    Auth.logout();
    navigate("/");
  };

  return !userData ? (
    <img className="loading-spinner" src={spinner} />
  ) : (
    <div className="chats-page-container">
      <Navbar />
      <div className="chats-container">
        <SockJsClient
          url={SOCKET_URL}
          topics={[`/topic/message/${chat}`]}
          onConnect={onConnected}
          onDisconnect={console.log("Disconnected!")}
          onMessage={(msg) => onMessageReceived(msg)}
          debug={false}
          ref={(client) => setClientRef(client)}
        />

        <div className="left-bar">
          <div className="profile">
            <div className="profile-picture">
              {userData.profileImage ? (
                <img
                  src={`data:image/jpeg;base64,${userData.profileImage.data}`}
                  alt=""
                />
              ) : (
                <img src={profile_picture} alt="" />
              )}
            </div>
            {userData.firstName} {userData.lastName}
          </div>
          <div className="chats-list">
            {chats &&
              chats.map((val, idx) => (
                <button onClick={() => changeChat(val)}>
                  <div className="single-chat-container">
                    <div className="single-chat">
                      {val.users.length < 3 ? (
                        userData.username === val.users[0] ? (
                          <p>
                            {val.users[1].firstName} {val.users[1].lastName}
                          </p>
                        ) : (
                          <p>
                            {val.users[0].firstName} {val.users[0].lastName}
                          </p>
                        )
                      ) : (
                        <p>{val.name}</p>
                      )}
                    </div>
                  </div>
                </button>
              ))}
          </div>
          <div className="center-wrapper">
            <button className="form-button" onClick={handleLogout}>
              Wyloguj si??
            </button>
          </div>
        </div>
        <div className="right-bar">
          {noChats ? (
            <div className="no-chats">
              <p>Nie uczestniczysz jeszcze w ??adnej rozmowie.</p>
              <p>
                Przejd?? do zak??adki 'Wyszukaj' lub 'Utw??rz grup??' aby to
                zmieni??!
              </p>
            </div>
          ) : (
            <>
              <div className="chat-info">
                <div className="chat-picture">
                  {receivers[0].profileImage ? (
                    <img
                      src={`data:image/jpeg;base64,${receivers[0].profileImage.data}`}
                      alt=""
                    />
                  ) : (
                    <img src={profile_picture} alt="" />
                  )}
                </div>
                <div className="chat-name">
                  {receivers.length > 1
                    ? chatName
                    : receivers[0].firstName + " " + receivers[0].lastName}
                </div>
              </div>
              <div className="chat-content">
                {messages &&
                  messages.map((message) => {
                    return message.messageAuthorUsername === user ? (
                      <div className="single-chat-message sent-message">
                        {message.messageAuthorUsername}:{" "}
                        {message.messageContent}
                        {message.imageContent ? (
                          <img
                            className="message-image"
                            src={message.imageContent}
                          ></img>
                        ) : (
                          ""
                        )}
                      </div>
                    ) : (
                      <div className="single-chat-message recieved-message">
                        {message.messageAuthorUsername}:{" "}
                        {message.messageContent}
                        {message.imageContent ? (
                          <img
                            className="message-image"
                            src={message.imageContent}
                          ></img>
                        ) : (
                          ""
                        )}
                      </div>
                    );
                  })}
              </div>
              <form className="chat-form" onSubmit={sendMessage}>
                <label className="image-form">
                  {image ? "OK" : "Dodaj zdj??cie"}
                  <input
                    accept="image/*"
                    type="file"
                    id="image-input"
                    onChange={handleImage}
                  />
                </label>
                <input
                  type="text"
                  value={currentMess}
                  onChange={(e) => setCurrentMess(e.target.value)}
                />
                <button className="send-button" type="button" type="submit">
                  <img src={sendButton} alt="send" />
                </button>
              </form>
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default Chats;
