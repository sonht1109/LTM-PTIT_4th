import ChatboxInfo from "../../ChatboxInfo";
import { SMainChatContainer } from "../styles";
import ChatBody from "./ChatBody";
import ChatHeader from "./ChatHeader";
import Reacts from "./Reacts";

export default function MainChatContainer() {
  return (
    <>
      <SMainChatContainer>
        <ChatHeader />
        <ChatBody />
        <ChatboxInfo />
      </SMainChatContainer>
      <Reacts />
    </>
  );
}
