import { createContext, ReactNode, useState } from "react";

interface IChatboxInfoContext {
  chatboxInfo: number | null;
  toggleChatboxInfo: any;
  openChatboxInfo: boolean;
  handleToggleChatboxInfo: any;
}

export const ChatboxInfoContext = createContext<IChatboxInfoContext>(
  {} as IChatboxInfoContext
);

const ChatboxInfoProvider = ({ children }: { children: ReactNode }) => {
  // set id of chatbox
  const [chatboxInfo, setChatboxInfo] = useState<number | null>(null);
  // toggle chatbox info
  const [openChatboxInfo, toggleChatboxInfo] = useState<boolean>(false);

  // use args to toggle chatbox info
  const handleToggleChatboxInfo = (id: number) => {
    if (chatboxInfo && id !== chatboxInfo) {
      setChatboxInfo(id);
    }
    toggleChatboxInfo(true);
  };

  return (
    <ChatboxInfoContext.Provider
      value={{
        chatboxInfo,
        handleToggleChatboxInfo,
        openChatboxInfo,
        toggleChatboxInfo,
      }}
    >
      {children}
    </ChatboxInfoContext.Provider>
  );
};

export default ChatboxInfoProvider;
