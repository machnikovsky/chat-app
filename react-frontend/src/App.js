import './styles/styles.css';
import Login from './components/home/Login';
import Chats from './components/chats/Chats';
import Register from './components/home/Register';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import useLocalStorage from "./hooks/useLocalStorage";
import UserContext from "./auth/UserContext";
import FindUsers from './components/find/FindUsers';

const App = () => {

    const [user, setUser] = useLocalStorage("user", null);

    return (
        <Router>
            <UserContext.Provider value={{ user, setUser }}>
                <Routes>
                    <Route exact path="/" element={<Login />} />
                    <Route exact path="/register" element={<Register />} />
                    <Route exact path="/chats" element={<Chats />} />
                    <Route exact path="/find" element={<FindUsers />} />
                </Routes>
            </UserContext.Provider>
        </Router>
  );
}

export default App;
