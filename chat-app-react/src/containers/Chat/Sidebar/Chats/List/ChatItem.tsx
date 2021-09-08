import { Avatar } from "antd";
import React from "react";
import { useRouteMatch } from "react-router";
import { Link } from "react-router-dom";
import { SChatItem } from "./styles";

export default function ChatItem({ id }: { id: number }) {
  const { params } = useRouteMatch() as any;

  return (
    <Link to={`/c/${id}`}>
      <SChatItem seen={id === 1} active={params?.id === id.toString()}>
        <Avatar src="/images/avt-placeholder.png" className="avt" />
        <div className="detail">
          <div className="timestamp">11:08 AM</div>
          <p className="name">Hoang Thai Son</p>
          <div className="message-preview">
            <div className="content">
              How are you? It has been raining heavily all day.
            </div>
            {id === 1 && <span className="badge">2</span>}
          </div>
        </div>
      </SChatItem>
    </Link>
  );
}
