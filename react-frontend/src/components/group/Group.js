import React, { useEffect, useState } from 'react';
import { ApiCall } from '../../api/ApiCall';
import SearchBar from '../find/SearchBar';
import UserList from '../find/UserList';
import Navbar from '../nav/Navbar';

const Group = () => {

    const [query, setQuery] = useState('');
    const [userList, setUserList] = useState([]);
    const [groupName, setGroupName] = useState('');
  
  
  
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


  return (
    <div className="find-users-container">
    <Navbar />
    <form className="group-name">
        <div className="search-bar">
            <input
                required
                type="text"
                placeholder="Podaj nazwę grupy"
                value={groupName}
                onChange={(e) => setGroupName(e.target.value)}
            />
        </div>
        <div className="button">
            <button>Utwórz grupę</button>
        </div>
    </form>

    <h2>Wybierz członków grupy</h2>
    <div className="search-container">
        <SearchBar setQuery={setQuery}/>
    </div>
    <UserList userList={userList} />
</div>
  );
};

export default Group;
