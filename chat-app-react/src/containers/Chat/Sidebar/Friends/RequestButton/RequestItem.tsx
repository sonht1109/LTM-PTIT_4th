import { Avatar, message } from "antd";
import React, { useContext } from "react";
import { FaTimes, FaUserCheck } from "react-icons/fa";
import { requestToken } from "src/common/api/axios";
import { avatarSrc, handleError } from "src/common/ultis";
import { IFriend } from "src/common/ultis/types";
import { ThemeContext } from "styled-components";
import { SResultItem } from "../AddFriendButton/styles";

export default function RequestItem({ request }: { request: IFriend }) {
  const { theme } = useContext(ThemeContext);

  const handleRequest = (status: boolean) => {
    requestToken({
      method: "POST",
      url: "api/confirm-friend",
      data: {
        friend_id: request.id,
        status,
      },
    })
      .then((data) => {
        if (data.data?.code === "200") {
          message.success(data.data?.message);
        } else message.error(data.data?.message);
      })
      .catch((err) => {
        handleError(err);
      });
  };

  return (
    <SResultItem>
      <Avatar
        src={avatarSrc(request?.user_id_1.avatar || "")}
        className="avt"
      />
      <div className="detail">
        <p className="name">@{request?.user_id_1.username}</p>
        <div className="group-icon">
          <div className="icon">
            <FaUserCheck
              size={12}
              color="#1890ff"
              onClick={() => handleRequest(true)}
            />
          </div>
          <div className="icon">
            <FaTimes
              size={12}
              color={theme.badge}
              onClick={() => handleRequest(false)}
            />
          </div>
        </div>
      </div>
    </SResultItem>
  );
}
