import { Link } from "react-router-dom"
import { useState } from "react"
import SockJsClient from 'react-stomp';


const Chats = (props) => {

    const SOCKET_URL = 'http://localhost:8080/chat/';
    const [messages, setMessages] = useState([]);
    const [chat, setChat] = useState('default');
    const [currentMess, setCurrentMess] = useState('');
    const [clientRef, setClientRef] = useState(null);
    const loggedInUser = props.location.state.username;


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
            fromUser: loggedInUser
        }));
    };





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
                <div className="chats-list">
                    <button onClick={() => changeChat('userone')}>
                        <div className="single-chat">
                            Chat 1   
                        </div>
                    </button>
                    <button onClick={() => changeChat('usertwo')}>
                        <div className="single-chat">
                            Chat 2  
                        </div>
                    </button>
                    <button onClick={() => changeChat('userthree')}>
                        <div className="single-chat">
                            Chat 3
                        </div>
                    </button>
                </div>
                <div className="logout">
                    <button>
                        <Link to="/">Logout</Link>
                    </button>
                </div>
            </div>
            <div className="right-bar">
                <nav className="chat-info">
                    Chat name & info
                </nav>
                <div className="chatform-container">
                    {messages && messages.map(message =>(
                        <div className="message">{message.fromUser}: {message.messageContent}</div>
                    ))}
                    <div className="chat-form">
                        <input type="text" value={currentMess} onChange={(e) => setCurrentMess(e.target.value)}/>
                        <button type="button" onClick={sendMessage}>Send</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Chats