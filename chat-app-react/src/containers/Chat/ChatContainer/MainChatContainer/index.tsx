import {
  SMainChatContainer,
} from "../styles";
import ChatBody from "./ChatBody";
import ChatFooter from "./ChatFooter";
import ChatHeader from "./ChatHeader";

export default function MainChatContainer() {
  return (
    <SMainChatContainer>
      <ChatHeader />
      <ChatBody />
      <ChatFooter />
    </SMainChatContainer>
  );
}
