import { Avatar, Dropdown, Menu } from "antd";
import React, { Dispatch, SetStateAction, useContext } from "react";
import { FaEye, FaRegLaughSquint } from "react-icons/fa";
import { ReactsContext } from "src/common/context/ReactContext";
import { SMessage } from "../styles";

interface IMessage {
  fromMe?: boolean;
  isLast?: boolean;
  isFirst?: boolean;
}

export default function Message({
  fromMe = false,
  isLast = false,
  isFirst = false,
}: IMessage) {
  const { setMessageId } = useContext(ReactsContext);

  return (
    <Dropdown overlay={overlay(setMessageId)} trigger={["contextMenu"]}>
      <SMessage
        fromMe={fromMe}
        className={`${isFirst ? "is_first" : ""} ${isLast ? "is_last" : ""}`}
      >
        {!fromMe && <Avatar src="/images/avt-placeholder.png" size={36} />}
        <div className="detail">
          {!fromMe && <p className="name">Vii yeu quai</p>}
          <div className="content">
            Ex amet culpa non cillum ad cillum dolore.
            <div className="timestamp">
              {fromMe && <FaEye size={10} color="white" />}
              <span>11:08 PM</span>
            </div>
          </div>
        </div>
      </SMessage>
    </Dropdown>
  );
}

const overlay = (setMessageId: Dispatch<SetStateAction<number | null>>) => {
  return (
    <Menu>
      <Menu.Item key={1}>Reply</Menu.Item>
      <Menu.Divider />
      <Menu.Item key={2} onClick={() => {
        setMessageId(Math.random())
      }}>
        <FaRegLaughSquint size={14} />
      </Menu.Item>
    </Menu>
  );
};
