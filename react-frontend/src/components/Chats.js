import { Link } from "react-router-dom"
import profile_picture from "../assets/profile_picture.png"
import sendButton from "../assets/send.png"

const Chats = () => {
    return(
        <div className="chats-container">
            
            <div className="left-bar">
                <div className="profile">
                    <div className="profile-picture">
                        <img src={profile_picture} alt=""/>
                    </div>
                    Jane Doe 
                </div>
                <div className="chats-list">
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 1<br/>
                            Message 1   
                        </div>
                    </div>
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 2<br/>
                            Message 2   
                        </div>
                    </div>
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 3<br/>
                            Message 3   
                        </div>
                    </div>
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 1<br/>
                            Message 1   
                        </div>
                    </div>
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 2<br/>
                            Message 2   
                        </div>
                    </div>
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 3<br/>
                            Message 3   
                        </div>
                    </div>
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 1<br/>
                            Message 1   
                        </div>
                    </div>
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 2<br/>
                            Message 2   
                        </div>
                    </div>
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 3<br/>
                            Message 3   
                        </div>
                    </div>
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 1<br/>
                            Message 1   
                        </div>
                    </div>
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 2<br/>
                            Message 2   
                        </div>
                    </div>
                    <div className="single-chat-container">
                        <div className="single-chat">
                            Chat 3<br/>
                            Message 3   
                        </div>
                    </div>
                </div>

                <div className="logout-div">
                    <Link to="/">
                        <button className="logout">
                            Logout
                        </button>
                    </Link>
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
                    <div className="single-chat-message sent-message">
                        Wiadomosc1
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2jejnviusenfvsndfvnadinfuvnseugnusegnueaniru
                    </div>
                    <div className="single-chat-message sent-message">
                        W
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2jejnviusenfvsndfvnadinfuvnseugnusegnueanirukfdjnsfjnsjdfnsfnsjfn
                        gdfjgdifgdi jsngnrgsnrgd
                    </div>
                    <div className="single-chat-message sent-message">
                        Wiadomosc1gttgtgyhyyhbhyhbyhyhbyhbyhbyhmkymyhkmyhkmyoymymyhb
                        njknnfgdngoinergner ergjergne rnger
                        gergerjgneirngenrg
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2jejnviusenfvsndfvnadinfuvnseugnusegnueaniru
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2jejnviusenfvsndfvnadinfuvnseugnusegnueaniru
                    </div>
                    <div className="single-chat-message sent-message">
                        Wiadomosc1
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2jejnviusenfvsndfvnadinfuvnseugnusegnueaniru
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2jejnviusenfvsndfvnadinfuvnseugnusegnueaniru
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2jejnviusenfvsndfvnadinfuvnseugnusegnueaniru
                    </div>
                    <div className="single-chat-message sent-message">
                        Wiadomosc1
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2
                    </div>
                    <div className="single-chat-message recieved-message">
                        Wiadomosc2jejnviusenfvsndfvnadinfuvnseugnusegnueaniru
                    </div>
                </div>
                <div className="chat-form">
                    <input type="text"/>
                    <button type="button">
                        <img src={sendButton} alt="send"/>
                    </button>
                </div>
            </div>
        </div>
    )
}

export default Chats