import { Avatar } from "antd";
import React, { useContext } from "react";
import { FaBan, FaRegCommentAlt } from "react-icons/fa";
import { ThemeContext } from "styled-components";
import { SFriendItem } from "./styles";

export default function FriendItem() {
  const { theme } = useContext(ThemeContext);

  return (
    <SFriendItem>
      <Avatar src="/images/avt-placeholder.png" className="avt" />
      <div className="detail">
        <p className="name">Hoang Thai Son</p>
        <div className="group-icon">
          <div className="icon">
            <FaRegCommentAlt size={12} color={theme.icon.inactive} />
          </div>
          <div className="icon">
            <FaBan size={12} color={theme.badge} />
          </div>
        </div>
      </div>
    </SFriendItem>
  );
}
