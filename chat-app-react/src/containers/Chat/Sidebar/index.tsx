import React, { useContext } from "react";
import Overlay from "src/common/components/Overlay";
import { NavigatorContext } from "src/common/context/NavigatorContext";
import { ToggleSidebarContext } from "src/common/context/ToggleSidebarContext";
import Chats from "./Chats";
import Friends from "./Friends";
import { SSidebar } from "./styles";

export default function Sidebar() {
  const { index } = useContext(NavigatorContext);
  const { open, toggleSidebar } = useContext(ToggleSidebarContext);

  return (
    <>
      {open && (
        <Overlay
          onClick={() => {
            toggleSidebar && toggleSidebar(false);
          }}
        />
      )}
      <SSidebar open={open}>
        {index === 0 && <Chats />}
        {index === 1 && <Friends />}
      </SSidebar>
    </>
  );
}
