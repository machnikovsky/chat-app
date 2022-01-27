import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import message from '../../assets/start_conversation.png';
import not_found from '../../assets/not_found.jpeg';
import { ApiCall } from '../../api/ApiCall';


const UserList = ({userList}) => {

    const navigate = useNavigate();

    const handleAction = (userId) => {
        ApiCall.getChatDtoOrCreateNewAndRetrieveIfNotPresent(userId)
        .then(_ => {
            navigate('/chats');
        })
        .catch(err => {
            console.log("Error getting chat dto: ", err.response.data);
        })
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

