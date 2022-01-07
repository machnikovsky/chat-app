import {Link, useNavigate} from "react-router-dom"
import {useContext, useEffect, useState} from "react"
import profile_picture from "../assets/profile_picture.png"
import sendButton from "../assets/send.png"
import SockJsClient from 'react-stomp';
import UserContext from "../auth/UserContext";
import Auth from "../auth/Auth";

const Chats = (props) => {

    const SOCKET_URL = 'http://localhost:8080/chat/';
    const [messages, setMessages] = useState([]);
    const [chat, setChat] = useState('default');
    const [currentMess, setCurrentMess] = useState('');
    const [clientRef, setClientRef] = useState(null);
    const {user, setUser} = useContext(UserContext);
    const navigate = useNavigate();


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
    }, [chat]);


    const changeChat = (e) => {
        console.log(`Selected chat: ${e}`);
        setChat(e);
    }

    let onConnected = () => {
        console.log("Connected!!")
      }
    
    let onMessageReceived = (msg) => {
        console.log('New Message Received!!', msg);
        setMessages(messages.concat(msg));
    }

    let sendMessage = () => {
        console.log(`Sent ${currentMess} to ${chat}`)
        console.log(`Current messages: ${messages}`)
        clientRef.sendMessage(`/app/chat/${chat}`, JSON.stringify({
            messageContent: currentMess,
            fromUser: user
        }));
    };


    const handleLogout = () => {
        Auth.logout();
        navigate('/');
    }

    return(
        <div className="chats-container">
            <SockJsClient
              url={SOCKET_URL}
              topics={[`/topic/message/${chat}`]}
              onConnect={onConnected}
              onDisconnect={console.log("Disconnected!")}
              onMessage={msg => onMessageReceived(msg)}
              debug={false}
              ref={(client) => setClientRef(client)}
            />
            
            <div className="left-bar">
                <div className="profile">
                    <div className="profile-picture">
                        <img src={profile_picture} alt=""/>
                    </div>
                    Jane Doe 
                </div>
                <div className="chats-list">
                <button onClick={() => changeChat('userone')}>
                        <div className="single-chat-container">
                            <div className="single-chat">
                                Chat 1<br/>
                                Message 1   
                            </div>
                        </div>
                    </button>
                    <button onClick={() => changeChat('usertwo')}>
                        <div className="single-chat-container">
                            <div className="single-chat">
                                Chat 2<br/>
                                Message 2   
                            </div>
                        </div>
                    </button>
                    <button onClick={() => changeChat('userthree')}>
                        <div className="single-chat-container">
                            <div className="single-chat">
                                Chat 2<br/>
                                Message 2   
                            </div>
                        </div>
                    </button>
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
                        <img src={profile_picture} alt=""/>
                    </div>
                    <div className="chat-name">
                        John Doe
                    </div>
                </div>
                <div className="chat-content">
                    {messages && messages.map(message => {
                        return message.fromUser === user ?
                            <div className="single-chat-message sent-message">{message.fromUser}: {message.messageContent}</div>
                            :
                            <div className="single-chat-message recieved-message">{message.fromUser}: {message.messageContent}</div>
                    })}
                </div>
                <div className="chat-form">
                    <input type="text" value={currentMess} onChange={(e) => setCurrentMess(e.target.value)}/>
                    <button type="button" onClick={sendMessage}>
                        <img src={sendButton} alt="send"/>
                    </button>
                </div>
            </div>
        </div>
    )
}

export default Chats