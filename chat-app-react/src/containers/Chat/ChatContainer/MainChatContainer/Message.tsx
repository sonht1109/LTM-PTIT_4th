import { Avatar } from "antd";
import React from "react";
import { FaEye } from "react-icons/fa";
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
  return (
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
  );
}
