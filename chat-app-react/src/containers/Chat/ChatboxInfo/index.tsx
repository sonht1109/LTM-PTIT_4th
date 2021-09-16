import { Collapse } from "antd";
import Avatar from "antd/lib/avatar/avatar";
import React, { useContext, useEffect } from "react";
import { FaTimes } from "react-icons/fa";
import IconButton from "src/common/components/IconButton";
import Overlay from "src/common/components/Overlay";
import { ChatboxInfoContext } from "src/common/context/ChatboxInfoContext";
import { ThemeContext } from "styled-components";
import { SChatboxInfo } from "./styles";

export default function ChatboxInfo() {
  const { theme } = useContext(ThemeContext);
  const { chatboxInfo, openChatboxInfo, toggleChatboxInfo } =
    useContext(ChatboxInfoContext);

  useEffect(() => {
    console.log(chatboxInfo);
  }, [chatboxInfo]);

  return (
    <>
      {openChatboxInfo && <Overlay onClick={() => toggleChatboxInfo(false)} />}
      <SChatboxInfo isOpen={openChatboxInfo}>
        <IconButton
          onClick={() => toggleChatboxInfo(false)}
          style={{ position: "absolute", left: "15px", right: "15px" }}
        >
          <FaTimes color={theme.text.main} />
        </IconButton>
        <Avatar size={60} src="/images/avt-placeholder.png" />
        <h3 className="header">Box chat</h3>

        <div className="module-content">
          <Collapse>
            <Collapse.Panel
              key={1}
              className="custom-panel"
              header="Member (30)"
            >
              <div> Custom panel</div>
              <div> Custom panel</div>
            </Collapse.Panel>
          </Collapse>
        </div>
      </SChatboxInfo>
    </>
  );
}
