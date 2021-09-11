import { Input } from "antd";
import React, { useContext } from "react";
import { FaPlusCircle, FaUsers } from "react-icons/fa";
import IconButton from "src/common/components/IconButton";
import { ThemeContext } from "styled-components";
import ChatList from "./List";
import { SInnerSidebar } from "./styles";

export default function Chats() {
  const { theme } = useContext(ThemeContext);
  const onSearch = (value: any) => {
    console.log(value);
  };

  return (
    <SInnerSidebar>
      <div className="top">
        <h3 className="header">Chats</h3>

        <div className="icons">
          <IconButton tooltipTitle="Start a new chat" tooltipPosition="bottom">
            <FaPlusCircle color={theme.icon.inactive} size={16} />
          </IconButton>

          <IconButton
            tooltipPosition="bottom"
            tooltipTitle="Create a new group chat"
          >
            <FaUsers color={theme.icon.inactive} size={16} />
          </IconButton>
        </div>
      </div>

      <div className="search">
        <Input.Search placeholder="Search" onSearch={onSearch} enterButton />
      </div>

      <ChatList />
    </SInnerSidebar>
  );
}
