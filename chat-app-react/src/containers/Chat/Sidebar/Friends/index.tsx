import { Input } from "antd";
import React, { useContext } from "react";
import { FaUserPlus } from "react-icons/fa";
import IconButton from "src/common/components/IconButton";
import { ThemeContext } from "styled-components";
import { SInnerSidebar } from "../Chats/styles";
import FriendList from "./List";

export default function Friends() {
  const { theme } = useContext(ThemeContext);
  const onSearch = (value: any) => {
    console.log(value);
  };

  return (
    <SInnerSidebar>
      <div className="top">
        <h3 className="header">Friends</h3>

        <div className="icons">
          <IconButton tooltipPosition="bottom" tooltipTitle="Add a new friend">
            <FaUserPlus color={theme.icon.inactive} size={16} />
          </IconButton>
        </div>
      </div>

      <div className="search">
        <Input.Search placeholder="Search" onSearch={onSearch} enterButton />
      </div>

      <FriendList />
    </SInnerSidebar>
  );
}