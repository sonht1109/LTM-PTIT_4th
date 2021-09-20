// import { Divider } from "antd";
import { useEffect, useRef } from "react";
import { FaArrowDown } from "react-icons/fa";
import { SChatBody } from "../styles";
import ChatFooter from "./ChatFooter";
// import Message from "./Message";

export default function ChatBody() {
  const bodyRef = useRef(null);
  const scrollButtonRef = useRef(null);

  // scroll events
  useEffect(() => {
    const bodyRefCurrent = bodyRef.current as any;
    if (bodyRefCurrent) {
      bodyRefCurrent.addEventListener("scroll", (e: any) => {
        const scrollTop = e.target.scrollTop;
        const scrollButtonCurrent = scrollButtonRef.current as any;
        if (scrollTop <= -160) {
          scrollButtonCurrent.classList.add("show");
        } else {
          scrollButtonCurrent.classList.remove("show");
        }
      });
    }
  }, []);

  const scrollToBottom = () => {
    if (bodyRef.current) {
      const bodyRefCurrent = bodyRef.current as any;
      // bodyRefCurrent.scrollTop = bodyRefCurrent.scrollHeight
      if (bodyRefCurrent.scrollTop) {
        bodyRefCurrent.scrollTo({
          behavior: "smooth",
          top: 0,
        });
      }
    }
  };

  useEffect(() => {
    scrollToBottom();
  });

  // end scroll events

  return (
    <>
      <SChatBody ref={bodyRef}>
        <div className="top"></div>
{/* 
        <Message fromMe={true} />

        <Message isLast />
        <Message />
        <Message />
        <Message />
        <Message />
        <Message />
        <Message />
        <Message />
        <Message />
        <Message />
        <Message />
        <Message />
        <Message />
        <Message />
        <Message />
        <Message isFirst />
        <Divider>18/09/2021</Divider> */}

        <div className="bottom"></div>
        <div
          className="scroll-button"
          ref={scrollButtonRef}
          onClick={scrollToBottom}
        >
          <FaArrowDown color="white" size={16} />
        </div>
      </SChatBody>
      <ChatFooter />
    </>
  );
}
