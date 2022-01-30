import './styles/styles.css';
import Login from './components/home/Login';
import Chats from './components/chats/Chats';
import Register from './components/home/Register';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import useLocalStorage from "./hooks/useLocalStorage";
import UserContext from "./auth/UserContext";
import FindUsers from './components/find/FindUsers';
import PageNotFound from "./components/errors/404.js"
import GeneralError from "./components/errors/general.js"
import Group from './components/group/Group';

const App = () => {

    const [user, setUser] = useLocalStorage("user", null);

    return (
        <Router>
            <UserContext.Provider value={{ user, setUser }}>
                <Routes>
                    <Route exact path="/error" element={<GeneralError />} />
                    <Route exact path="/" element={<Login />} />
                    <Route exact path="/register" element={<Register />} />
                    <Route exact path="/find" element={<FindUsers />} />
                    <Route exact path="/group" element={<Group />} />
                    <Route path="/chats" element={<Chats />} />
                    <Route path="/*" element={<PageNotFound />} />
                </Routes>
            </UserContext.Provider>
        </Router>
  );
}

export default App;
