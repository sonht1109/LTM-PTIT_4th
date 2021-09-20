import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Chat from "./containers/Chat";
import Login from "./containers/Login";
import Signup from "./containers/Signup";
import Onboarding from "./containers/Onboarding";
import GlobalLoadingScreen from "./common/components/GlobalLoadingScreen";
import MyProvider from "./common/context";

import "./app.css";
import "antd/dist/antd.css";
import Profile from "./containers/Chat/Profile";
import ProtectedRoute from "./common/components/ProtectedRoute";

export default function App() {
  return (
    <MyProvider>
      <Router>
        <Switch>
          <Route path="/" exact>
            <Onboarding />
          </Route>

          <Route path="/signup" exact>
            <Signup />
          </Route>

          <Route path="/login" exact>
            <Login />
          </Route>

          <ProtectedRoute path="/profile" exact>
            <Profile />
          </ProtectedRoute>

          <ProtectedRoute path="/c">
            <Chat />
          </ProtectedRoute>

          <ProtectedRoute path="/c/:id" exact>
            <Chat />
          </ProtectedRoute>
        </Switch>
      </Router>
      <GlobalLoadingScreen />
    </MyProvider>
  );
}
