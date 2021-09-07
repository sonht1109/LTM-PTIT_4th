import { Tooltip, Input } from "antd";
import React, { useContext } from "react";
import { FaPlusCircle, FaUsers } from "react-icons/fa";
import IconButton from "src/common/components/IconButton";
import { ThemeContext } from "styled-components";
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
          <Tooltip placement="bottom" title="Start a new chat">
            <IconButton>
              <FaPlusCircle color={theme.icon.inactive} size={16} />
            </IconButton>
          </Tooltip>

          <Tooltip placement="bottom" title="Create a new group chat">
            <IconButton>
              <FaUsers color={theme.icon.inactive} size={16} />
            </IconButton>
          </Tooltip>
        </div>
      </div>

      <div className="search">
        <Input.Search
          placeholder="Chat's name .."
          onSearch={onSearch}
          enterButton
        />
      </div>
    </SInnerSidebar>
  );
}
