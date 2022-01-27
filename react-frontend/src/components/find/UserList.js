import React from 'react';
import { Link } from 'react-router-dom';
import message from '../../assets/start_conversation.png';
import not_found from '../../assets/not_found.jpeg';


const UserList = ({userList}) => {

    const handleAction = (userId) => {
        //Creating new chat and redirecting to it
        //should be implemented here, for now 
        //just console log
        console.log("Redirecting to: ", userId);
    }

    return (
        <div className="user-list">
        { userList && (
                    userList.map((user, index) => (
                        <div className="single-user" key={ index }>
                                <div className="user-picture-container">
                                    <img 
                                    src={ 'profile_picture' in user ? user.profile_picture : not_found } 
                                    onError={(event) => event.target.setAttribute("src", not_found)} 
                                    className="user-picture" 
                                    alt="profile-picture"/>
                                </div>
                                <h2>{ user.username }</h2>
                                <h4>{ user.firstName + ' ' + user.lastName }</h4>
                                <button className="action" onClick={() => { handleAction(user.userId) }}>
                                    <div className="start-chat-container">
                                        <img src={ message } alt="message"/>
                                    </div>
                                    <h3>Napisz</h3>
                                </button>
                        </div>
                    ))
                
            )}
        </div>
    )
};

export default UserList;

