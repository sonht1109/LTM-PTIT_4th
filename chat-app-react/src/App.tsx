import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Chat from "./containers/Chat";
import Login from "./containers/Login";
import Signup from "./containers/Signup";
import './app.css'
import 'antd/dist/antd.css';

export default function App() {
  return (
    <Router>
      <Switch>
        <Route path="/" exact>
          <Chat />
        </Route>
        <Route path="/signup" exact>
          <Signup />
        </Route>
        <Route path="/login" exact>
          <Login />
        </Route>
      </Switch>
    </Router>
  );
}
