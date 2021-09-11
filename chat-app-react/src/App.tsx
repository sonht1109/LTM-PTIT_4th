import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Chat from "./containers/Chat";
import Login from "./containers/Login";
import Signup from "./containers/Signup";
import GlobalLoadingScreen from "./common/components/GlobalLoadingScreen";
import MyProvider from "./common/context";

import "./app.css";
import "antd/dist/antd.css";

export default function App() {
  return (
    <MyProvider>
      <Router>
        <Switch>
          <Route path="/c" exact>
            <Chat />
          </Route>
          <Route path="/c/:id" exact>
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
      <GlobalLoadingScreen />
    </MyProvider>
  );
}
