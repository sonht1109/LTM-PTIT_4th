import React from "react";
import { useRouteMatch } from "react-router";
import NoChatSelected from "./NoChatSelected";
import { SChatContainer } from "./styles";

export default function ChatContainer() {
  const router = useRouteMatch() as any;
  if (!router.params?.id) {
    return <NoChatSelected />
  }

  return <MainChatContainer id={router.params.id} />;
}

const MainChatContainer = ({ id } : {id: number}) => {
  return <SChatContainer>
    {id}
  </SChatContainer>;
};
