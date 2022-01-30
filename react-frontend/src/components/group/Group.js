import React, { useEffect, useState } from 'react';
import { ApiCall } from '../../api/ApiCall';
import SearchBar from '../find/SearchBar';
import UserList from '../find/UserList';
import Navbar from '../nav/Navbar';
import { Link, useNavigate } from 'react-router-dom';
import add from '../../assets/add.png';
import remove from '../../assets/remove.png';
import not_found from '../../assets/not_found.jpeg';

const Group = () => {

    const [query, setQuery] = useState('');
    const [userList, setUserList] = useState([]);
    const [groupName, setGroupName] = useState('');
    const [groupParticipantsIds, setGroupParticipantsIds] = useState([]);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

  
  
    useEffect(() => {
      if (query === '') {
        ApiCall.getAllUsersBesideSelf()
        .then(res => {
        console.log("Received user list: ", res.data);
        setUserList(res.data.users);
        })
        .catch(err => {
        console.log("Error getting user list: ", err.message);
        setUserList([]);
        })
      } else {
        ApiCall.getAndSetQueriedListWithNewQuery(query, setUserList)
        .then(res => {
          console.log("Queried list: ", res.data);
          setUserList(res.data)
        })
        .catch(err => {
          console.log("Error getting queried list: ", err.response.data);
        })
      }
  }, [query])


  const handleAction = (userId) => {
    if (groupParticipantsIds.includes(userId)) {
      setGroupParticipantsIds(groupParticipantsIds.filter(item => item !== userId));
    } else {
      setGroupParticipantsIds([...groupParticipantsIds, userId]);
    }
  }

  const handleCreate = (e) => {
    e.preventDefault();
    if (groupParticipantsIds.length < 1) {
      console.log("Choose at least one person!");
      setError("Wybierz przynajmniej jednego uczestnika czatu!");
      return;
    }
    if (groupName === null || groupName === '') {
      console.log("You have to choose group name!");
      setError("Musisz podać nazwę grupy!");
      return;
    }

    let createChatRequestDTO = {
      groupParticipantsIds: groupParticipantsIds,
      groupName: groupName
    }
    ApiCall.createGroupChat(createChatRequestDTO)
    .then(res => {
      console.log("Success creating new chat: ", res.data);
      navigate('/chats');
    })
    .catch(err => {
      console.log("Error creating new chat: ", err.response.data);
    })
  }

  return (
    <div className="find-users-container">
    <Navbar />
    { error && (<div className='error'>{error}</div>)}
    <form className="group-name">
        <div className="search-bar">
            <input
                type="text"
                placeholder="Podaj nazwę grupy"
                value={groupName}
                onChange={(e) => setGroupName(e.target.value)}
            />
        </div>
        <div className="button">
            <button onClick={handleCreate}>Utwórz grupę</button>
        </div>
    </form>

    <h2>Wybierz członków grupy</h2>
    <div className="search-container">
        <SearchBar setQuery={setQuery}/>
    </div>
    <div className="user-list">
        { userList && (
                    userList.map((user, index) => (
                        <div className={ groupParticipantsIds.includes(user.userId) ? "single-user present" : "single-user absent"} key={ index }>
                                <div className="user-picture-container">
                                    <img 
                                    src={ 'profile_picture' in user ? user.profile_picture : not_found } 
                                    onError={(event) => event.target.setAttribute("src", not_found)} 
                                    className="user-picture" 
                                    alt="profile-picture"/>
                                </div>
                                <h2>{ user.username }</h2>
                                <h4>{ user.firstName + ' ' + user.lastName }</h4>
                                {
                                  groupParticipantsIds.includes(user.userId) ? 
                                  <>
                                    <button className="action" onClick={() => { handleAction(user.userId) }}>
                                      <div className="start-chat-container">
                                          <img src={ remove } alt="remove"/>
                                      </div>
                                    <h3>Usuń</h3>
                                    </button>
                                  </>
                                  :
                                  <>
                                    <button className="action" onClick={() => { handleAction(user.userId) }}>
                                      <div className="start-chat-container">
                                          <img src={ add } alt="add"/>
                                      </div>
                                    <h3>Dodaj</h3>
                                    </button>
                                  </>                                  
                                }
                        </div>
                    ))
                
            )}
        </div>
</div>
  );
};

export default Group;
