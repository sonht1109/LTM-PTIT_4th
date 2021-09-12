import React, { useContext, useEffect, useState } from "react";
import Overlay from "src/common/components/Overlay";
import { ChatboxInfoContext } from "src/common/context/ChatboxInfoContext";
import { SChatboxInfo } from "./styles";

export default function ChatboxInfo() {
  const { chatboxInfo, openChatboxInfo, toggleChatboxInfo } =
    useContext(ChatboxInfoContext);

  useEffect(() => {
    console.log(chatboxInfo);
  }, [chatboxInfo]);

  return (
    <>
      {openChatboxInfo && <Overlay onClick={() => toggleChatboxInfo(false)} />}
      <SChatboxInfo isOpen={openChatboxInfo}></SChatboxInfo>
    </>
  );
}
