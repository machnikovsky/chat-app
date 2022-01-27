import React, { useEffect, useState } from 'react';
import { ApiCall } from '../../api/ApiCall';
import Navbar from '../nav/Navbar';
import SearchBar from './SearchBar';
import UserList from './UserList';

const FindUsers = () => {

  const [query, setQuery] = useState('');
  const [userList, setUserList] = useState([]);

  useEffect(() => {
    ApiCall.getAllUsersBesideSelf()
    .then(res => {
      console.log("Received user list: ", res.data);
      setUserList(res.data.users);
    })
    .catch(err => {
      console.log("Error getting user list: ", err.message);
      setUserList([]);
    })
  }, [])

  useEffect(() => {
    ApiCall.getAndSetQueriedListWithNewQuery(query, setUserList)
    .then(res => {
      console.log("Queried list: ", res.data);
      setUserList(res.data)
    })
    .catch(err => {
      console.log("Error getting queried list: ", err.response.data);
    })
}, [query])

  return (
    <div className="find-users-container">
        <Navbar />
        <div className="search-container">
            <SearchBar setQuery={setQuery}/>
        </div>
        <UserList userList={userList} />
    </div>
  );
};

export default FindUsers;

