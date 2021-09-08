import Avatar from "antd/lib/avatar/avatar";
import { FaEllipsisV } from "react-icons/fa";
import IconButton from "src/common/components/IconButton";
import StatusDot from "src/common/components/StatusDot";
import { SChatHeader } from "../styles";
import { ThemeContext } from "styled-components";
import React, { useContext } from "react";
import { Dropdown, Menu } from "antd";

export default function ChatHeader() {
  const { theme } = useContext(ThemeContext);

  return (
    <SChatHeader>
      <div className="avt">
        <Avatar src="/images/avt-placeholder.png" size={40} />
        <StatusDot />
      </div>
      <div className="detail">
        <p className="name">Hoang Thai Son</p>
      </div>
      <div className="group-button">
        <Dropdown overlay={overlay(theme)} placement="bottomRight" trigger={['click']}>
          <div>
            <IconButton>
              <FaEllipsisV color={theme.icon.inactive} size={16} />
            </IconButton>
          </div>
        </Dropdown>
      </div>
    </SChatHeader>
  );
}

const overlay = (theme: any) => {
  return (
    <Menu>
      <Menu.Item>Info</Menu.Item>
      <Menu.Divider />
      <Menu.Item style={{color: theme.badge}}>Leave</Menu.Item>
    </Menu>
  );
};
