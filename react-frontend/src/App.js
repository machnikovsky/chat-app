import './App.css';
import Login from './components/Login';
import Chats from './components/Chats';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import useLocalStorage from "./hooks/useLocalStorage";
import UserContext from "./auth/UserContext";
import GeneralError from "./components/errors/general.js"
import PageNotFound from "./components/errors/404.js"
const App = () => {

    const [user, setUser] = useLocalStorage("user", null);

    return (
        <Router>
            <UserContext.Provider value={{ user, setUser }}>
                <Routes>
                    <Route exact path="/error/404" element={<PageNotFound />} />
                    <Route exact path="/" element={<Login />} />
                    <Route exact path="/chats" element={<Chats />} />
                    <Route path="/*" element={<GeneralError />} />
                </Routes>
            </UserContext.Provider>
        </Router>
  );
}

export default App;
