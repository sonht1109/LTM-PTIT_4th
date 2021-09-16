import { SChatBody } from "../styles";
import ChatFooter from "./ChatFooter";
import Message from "./Message";

export default function ChatBody() {
  return (
    <>
      <SChatBody>
        <div className="top"></div>

        <Message fromMe={true} />

        <Message isLast />
        <Message isFirst />

        <div className="bottom"></div>
        
      </SChatBody>
      <ChatFooter />
    </>
  );
}
