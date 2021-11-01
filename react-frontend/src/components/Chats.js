import { Link } from "react-router-dom"

const Chats = () => {
    return(
        <div className="chats-container">
            
            <div className="left-bar">
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
                <div className="chat-form">
                    <input type="text"/>
                        Wprowadz swoja wiadomosc
                        <div className="chat-form-line">
                        </div>
                        <button type="button"></button>
                </div>
            </div>
        </div>
    )
}

export default Chats