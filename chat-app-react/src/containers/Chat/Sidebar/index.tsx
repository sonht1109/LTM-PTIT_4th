import React, { useContext } from "react";
import { NavigatorContext } from "src/common/context/NavigatorContext";
import Chats from "./Chats";
import Friends from "./Friends";
import { SSidebar } from "./styles";

export default function Sidebar() {

  const { index } = useContext(NavigatorContext);

  return (
    <SSidebar>
      {
        index === 0 && <Chats />
      }
      {
        index === 1 && <Friends />
      }
    </SSidebar>
  );
}
