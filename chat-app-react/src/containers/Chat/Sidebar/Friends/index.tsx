import { Input } from "antd";
import React, { useContext, useEffect, useState } from "react";
import { FaChevronLeft } from "react-icons/fa";
import { requestToken } from "src/common/api/axios";
import { ToggleSidebarContext } from "src/common/context/ToggleSidebarContext";
import { handleError } from "src/common/ultis";
import { IFriend } from "src/common/ultis/types";
import { ThemeContext } from "styled-components";
import { SInnerSidebar } from "../Chats/styles";
import AddFriendButton from "./AddFriendButton";
import FriendList from "./List";
import RequestButton from "./RequestButton";

export default function Friends() {
  const { theme } = useContext(ThemeContext);

  const onSearch = (value: any) => {
    console.log(value);
  };

  const [friends, setFriends] = useState<IFriend[]>([])

  const { toggleSidebar } = useContext(ToggleSidebarContext);

  useEffect(() => {
    requestToken({method: "GET", url: "api/list-friend"})
    .then(data => {
      if(data.data?.body) {
        setFriends([...data.data.body]);
      }
    })
    .catch(err => {
      handleError(err)
    })
  }, [])

  return (
    <SInnerSidebar>
      <div className="top">
        <h3
          className="header"
          onClick={() => toggleSidebar && toggleSidebar(false)}
        >
          {" "}
          <FaChevronLeft color={theme.text.main} size={16} />
          Friends
        </h3>

        <div className="icons">
          <RequestButton theme={theme} />
          <AddFriendButton theme={theme} />
        </div>
      </div>

      <div className="search">
        <Input.Search placeholder="Search" onSearch={onSearch} enterButton />
      </div>

      <FriendList friends={friends} />
    </SInnerSidebar>
  );
}
