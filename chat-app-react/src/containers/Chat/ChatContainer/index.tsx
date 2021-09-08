import React from "react";
import { Route, Switch } from "react-router";
import MainChatContainer from "./MainChatContainer";
import NoChatSelected from "./NoChatSelected";

export default function ChatContainer() {
  return (
    <Switch>
      <Route path='/c' exact>
        <NoChatSelected />
      </Route>
      <Route path='/c/:id' exact>
        <MainChatContainer />
      </Route>
    </Switch>
  )
}
