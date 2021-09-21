import { Avatar } from "antd";
import React, { useContext } from "react";
import { FaBan, FaRegCommentAlt } from "react-icons/fa";
import { Link } from "react-router-dom";
import { NavigatorContext } from "src/common/context/NavigatorContext";
import { avatarSrc } from "src/common/ultis";
import { IFriend } from "src/common/ultis/types";
import { ThemeContext } from "styled-components";
import { SFriendItem } from "./styles";

export default function FriendItem({friend}: {friend: IFriend}) {
  const { theme } = useContext(ThemeContext);

  const {setIndex} = useContext(NavigatorContext)

  return (
    <SFriendItem>
      <Avatar src={avatarSrc(friend?.friend?.avatar || "")} className="avt" />
      <div className="detail">
        <p className="name">@{friend?.friend?.username}</p>
        <div className="group-icon">
          <Link to={`/c/${friend?.id}`}>
            <div className="icon" onClick={() => setIndex(0)}>
              <FaRegCommentAlt size={12} color={theme.icon.inactive} />
            </div>
          </Link>
          <div className="icon">
            <FaBan size={12} color={theme.badge} />
          </div>
        </div>
      </div>
    </SFriendItem>
  );
}
