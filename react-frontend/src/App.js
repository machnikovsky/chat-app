import './App.css';
import Login from './components/Login';
import Chats from './components/Chats';
import Register from './components/Register';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

const App = () => {
  return (
    <Router>
        <Switch>
          <Route exact path="/">
            <Login />
          </Route>
          <Route exact path="/register">
            <Register />
          </Route>
          <Route path="/chats">
            <Chats />
          </Route>
        </Switch>
    </Router>
  );
}

export default App;
