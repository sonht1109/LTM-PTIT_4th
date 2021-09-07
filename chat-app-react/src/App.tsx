import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Chat from "./containers/Chat";
import Login from "./containers/Login";
import Signup from "./containers/Signup";
import "./app.css";
import "antd/dist/antd.css";
import NavigatorProvider from "./common/context/NavigatorContext";
import CustomThemeProvider from "./common/context/ThemeContext";
import GLProvider from "./common/context/GlobalLoadingContext";
import GlobalLoadingScreen from "./common/components/GlobalLoadingScreen";

export default function App() {
  return (
    <CustomThemeProvider>
      <GLProvider>
        <NavigatorProvider>
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
        </NavigatorProvider>
      </GLProvider>
    </CustomThemeProvider>
  );
}
