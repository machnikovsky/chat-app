import { Link, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import profile_picture from "../assets/profile_picture.png";
import sendButton from "../assets/send.png";
import SockJsClient from "react-stomp";
import UserContext from "../auth/UserContext";
import Auth from "../auth/Auth";
import { ApiCall } from "../api/ApiCall";
import spinner from "../assets/circle-notch-solid.svg";

const Chats = (props) => {
  const SOCKET_URL = "http://localhost:8080/chat/";
  const [messages, setMessages] = useState([]);
  const [chat, setChat] = useState("default");
  const [chats, setChats] = useState([]);
  const [currentMess, setCurrentMess] = useState("");
  const [clientRef, setClientRef] = useState(null);
  const { user, setUser } = useContext(UserContext);
  const [users, setUsers] = useState([]);
  const [userData, setUserData] = useState([]);
  const [receivers, setReceivers] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    ApiCall.getAllUsersBesideSelf()
      .then((res) => {
        console.log(res.data.users);
        setUsers(res.data.users);
      })
      .catch((err) => {
        console.log("Error: ", err);
      });

    ApiCall.getUserChats().then((res) => {
      console.log("Res: ", res);
      setChats(res.data);
    });
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      setUserData(await ApiCall.me());
    };

    fetchData();
  }, []);

  useEffect(() => {
    //Fetching messages from DB at the first render
    //will be done here
    //Also there should be some logic to set default
    //chat, like the most recent one, it should be
    //a call to backend
  }, []);

  useEffect(() => {
    //We will handle chat change here,
    //we need to update messages with the ones from DB,
    //kinda like at the default useEffect at the first render
    if (chat !== "default") {
      ApiCall.getChatMessages(chat).then((res) => {
        console.log("messages:", res);
        setMessages(res.data);
      });
    }
  }, [chat]);

  const changeChat = (e) => {
    console.log(`Selected chat: ${e}`);
    setChat(e.chatId);
    setReceivers(e.users.filter((x) => x.username !== user));
  };

  let onConnected = () => {
    console.log("Connected!!");
  };

  let onMessageReceived = (msg) => {
    console.log("New Message Received!!", msg);
    setMessages(messages.concat(msg));
    console.log("Mesages: ", messages);
  };

  let sendMessage = () => {
    console.log(`Sent ${currentMess} to ${chat}`);
    console.log(`Current messages: ${messages}`);
    clientRef.sendMessage(
      `/app/chat/${chat}`,
      JSON.stringify({
        chatId: chat,
        messageAuthorUsername: user,
        messageReceiversUsernames: receivers.map((x) => x.username),
        messageContent: currentMess,
      })
    );
  };

  const handleLogout = () => {
    Auth.logout();
    navigate("/");
  };

  return !userData ? (
    <img className="loading-spinner" src={spinner} />
  ) : (
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
            <img src={profile_picture} alt="" />
          </div>
          {userData.firstName} {userData.lastName}
        </div>
        <div className="chats-list">
          {chats &&
            chats.map((val, idx) => (
              <button onClick={() => changeChat(val)}>
                <div className="single-chat-container">
                  <div className="single-chat">
                    <p>{val.name}</p>
                  </div>
                </div>
              </button>
            ))}
        </div>

        <div className="logout-div">
          <button className="logout-button" onClick={handleLogout}>
            Logout
          </button>
        </div>
      </div>
      <div className="right-bar">
        <div className="chat-info">
          <div className="chat-picture">
            <img src={profile_picture} alt="" />
          </div>
          <div className="chat-name">John Doe</div>
        </div>
        <div className="chat-content">
          {messages &&
            messages.map((message) => {
              return message.messageAuthorUsername === user ? (
                <div className="single-chat-message sent-message">
                  {message.messageAuthorUsername}: {message.messageContent}
                </div>
              ) : (
                <div className="single-chat-message recieved-message">
                  {message.messageAuthorUsername}: {message.messageContent}
                </div>
              );
            })}
        </div>
        <div className="chat-form">
          <input
            type="text"
            value={currentMess}
            onChange={(e) => setCurrentMess(e.target.value)}
          />
          <button type="button" onClick={sendMessage}>
            <img src={sendButton} alt="send" />
          </button>
        </div>
      </div>
    </div>
  );
};

export default Chats;
